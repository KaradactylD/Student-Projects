using System;
using System.IO;
using System.Collections.Generic;
using System.Data;
using System.Data.OleDb;
using System.Linq;
using System.Windows.Forms;
using System.Drawing;
using System.Drawing.Text;
using System.Runtime.InteropServices;

/* Kara Crumpton
 * CPT 185 - Final Project - Form1 "Draft Page"
 * Background Image from freepik.com
 */


namespace Horror_Draft
{
    public partial class Form1 : Form
    {

        private Timer turnTimer;
        private int timeLeft;

        private string SelectedTeam;
        private static List<string> pickedTeams = new List<string>();


        // This connects my Database. Got 18,751 Horror Movies in there. :)

        string connectionString = $@"Provider=Microsoft.ACE.OLEDB.12.0;Data Source=..\..\HorrorDraftPractice.accdb;";

        private Queue<string> draftOrder; // Order the teams pick in (need to make this random... a task for later.)
        private Dictionary<string, List<string>> teams; // Team Names
        private List<Movie> moviePool; // Available movies

        // Stack for Undo and Redo 
        private Stack<DraftAction> undoStack;
        private Stack<DraftAction> redoStack;

        // Yo, you done yet?
        private bool isFinished = false;

        public Form1(string team)
        {
            InitializeComponent();

            movieBox.BackColor = Color.Black;
            team1Box.BackColor = Color.Black;
            team2Box.BackColor = Color.Black;
            team3Box.BackColor = Color.Black;
            team4Box.BackColor = Color.Black;
            team5Box.BackColor = Color.Black;
            searchLabel.BackColor = Color.Transparent;
            currentDrafter.BackColor = Color.Transparent;
            this.Load += new System.EventHandler(this.DraftForm_Load);
            SelectedTeam = team;
            currentDrafter.Text = string.Empty;

            // 
            if (!pickedTeams.Contains(SelectedTeam))
            {
                pickedTeams.Add(SelectedTeam); // Mark the team as picked
                currentDrafter.Text = $"Welcome {SelectedTeam}! You can make your pick.";
            }
            else
            {
                currentDrafter.Text = $"It's not your turn, {SelectedTeam}. Please wait for your next turn.";

            }


            moviePool = new List<Movie>(); // Puts the movies in the movieBox
            draftOrder = new Queue<string>(new[] { "Ginger Snaps Balls", "Horror Boobs", "Season of the Bitch", "Tit Follows", "Children of the Candy Corn" }); // Our Team Names
            teams = draftOrder.ToDictionary(name => name, name => new List<string>()); // Puts us in an order for the draft. Need to fix to be random.

            undoStack = new Stack<DraftAction>(); // Initialize Undo
            redoStack = new Stack<DraftAction>(); // Initialize Redo 

            // Timer for player turns. It resets every time they click a button... need to fix.
            turnTimer = new Timer();
            turnTimer.Interval = 1000;
            turnTimer.Tick += TurnTimer_Tick;

            // Initialize UI
            UpdateUI();
        }

        private void TurnTimer_Tick(object sender, EventArgs e)
        {
            // Sets the timer for each person's turn. Goes to the next person when it runs out. *Restarts every time you click something. ? 
            if (timeLeft > 0)
            {
                timeLeft--;
                currentDrafter.Text = $"It's {draftOrder.Peek()}'s turn! \nTime Left: {timeLeft} seconds";
            }
            else
            {
                turnTimer.Stop();
                currentDrafter.Text = $"Time's Up for {draftOrder.Peek()}! \nMoving to the next player!";
                EndTurn();
            }
        }

        public void TeamPicked(string team)
        {
            if (!pickedTeams.Contains(team))
            {
                pickedTeams.Add(team);
            }
        }

        private void DraftForm_Load(object sender, EventArgs e)
        {
            LoadMoviesFromDatabase();  // Loads the movies when the form loads

        }

        private void UpdateUI()
        {
            // Update Movie Pool (List Box that shows all the movies from the Database)
            movieBox.DataSource = null;
            movieBox.DataSource = moviePool.Select(movie => movie.Title).ToList(); // Show movie titles (wanted years too, but kept getting errors)

            // Update Teams ListBoxes - Each box shows that teams picks
            team1Box.DataSource = null;
            team1Box.DataSource = teams["Ginger Snaps Balls"];
            team2Box.DataSource = null;
            team2Box.DataSource = teams["Horror Boobs"];
            team3Box.DataSource = null;
            team3Box.DataSource = teams["Season of the Bitch"];
            team4Box.DataSource = null;
            team4Box.DataSource = teams["Tit Follows"];
            team5Box.DataSource = null;
            team5Box.DataSource = teams["Children of the Candy Corn"];

            // Show current drafter
            currentDrafter.Text = $"It's {draftOrder.Peek()}'s turn!";

            finishedButton.Enabled = true;

            if (SelectedTeam == draftOrder.Peek())
            {
                finishedButton.Enabled = true;
                draftButton.Enabled = true;
                undoButton.Enabled = true;
                redoButton.Enabled = true;
                timeLeft = 30;
                turnTimer.Start();

            }
            else
            {
                finishedButton.Enabled = false;
                draftButton.Enabled = false;
                undoButton.Enabled = false;
                redoButton.Enabled = false;
                turnTimer.Stop();
            }

        }

        private void LoadMoviesFromDatabase()
        {
            moviePool = new List<Movie>(); // Make the Movie Box empty at first, so it doesn't try to double up.

            using (var connection = new OleDbConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT Title, Genres FROM Movies"; // Shows Title and Genre *Can't get years to work, and will probably remove Genres.*
                using (var command = new OleDbCommand(query, connection))
                using (var reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        // Movie Titles
                        string title = reader.IsDBNull(0) ? string.Empty : reader.GetString(0);

                        // Movie Genres
                        string genres = reader.IsDBNull(1) ? string.Empty : reader.GetString(1);

                        // Create Movie object and add it to Movie Box
                        var movie = new Movie(title, genres);
                        moviePool.Add(movie);
                    }
                }
            }

            // Update UI after loading movies
            UpdateUI();
        }

        // Draft Button Click handler
        private void buttonDraft_Click(object sender, EventArgs e)
        {

            // Ensure a movie is selected
            if (movieBox.SelectedItem != null)
            {
                if (SelectedTeam == draftOrder.Peek())
                {
                    string selectedMovieTitle = movieBox.SelectedItem.ToString();
                    Movie selectedMovie = moviePool.FirstOrDefault(m => m.Title == selectedMovieTitle);

                    if (selectedMovie != null)
                    {
                        // Add the movie to the current team's list
                        string currentTeam = draftOrder.Peek();
                        teams[currentTeam].Add(selectedMovie.Title);


                        // Remove the selected movie from the list, so no one else can pick it.
                        moviePool.Remove(selectedMovie);

                        // Add the draft action to the undo stack
                        undoStack.Push(new DraftAction(currentTeam, selectedMovie));

                        // Clear the redo stack, as new action invalidates redo history
                        redoStack.Clear();

                        // Update the UI
                        UpdateUI();

                        // Enable "I'm finished" button
                        finishedButton.Enabled = true;
                    }

                }
            }
        }



        // Undo Button Click handler
        private void buttonUndo_Click(object sender, EventArgs e)
        {

            if (undoStack.Count > 0)
            {
                // Using them Pops! :) - Takes off the last pick. 
                DraftAction lastAction = undoStack.Pop();

                // Revert the action (move the movie back to the player pool)
                teams[lastAction.Team].Remove(lastAction.Movie.Title);
                moviePool.Add(lastAction.Movie);

                // Using them Pushes! :) - Puts back what you undid.
                redoStack.Push(lastAction);

                // Update the UI
                UpdateUI();

                // Don't move to the next team, keep the current drafter the same
                currentDrafter.Text = $"Fix your pick, {draftOrder.Peek()}!";

                // Enable the "I'm finished" button again since they need to finish their turn after the undo
                finishedButton.Enabled = true;

                // Mark the player as not finished, so they can pick again
                isFinished = false;
            }
        }

        // Redo Button Click handler
        private void buttonRedo_Click(object sender, EventArgs e)
        {
            if (redoStack.Count > 0)
            {

                DraftAction lastAction = redoStack.Pop();


                teams[lastAction.Team].Add(lastAction.Movie.Title);
                moviePool.Remove(lastAction.Movie);


                undoStack.Push(lastAction);


                UpdateUI();


                finishedButton.Enabled = true;
            }

        }


        // Finished Button for when the Player is done with their turn
        private void finishedButton_Click(object sender, EventArgs e)
        {
            searchBox.Text = String.Empty;
            if (!isFinished)
            {
                // Ensure a movie is selected
                if (movieBox.SelectedItem != null)
                {
                    // Mark the player as finished
                    isFinished = true;
                    turnTimer.Stop();
                    finishedButton.Enabled = false; // Disable the button after they click it

                    string selectedTeam = SelectedTeam; // Assumes this property is set in the form based on the current team
                    string movieTitle = String.Empty;

                    // Adding the picks from the draft back to each person's table
                    switch (SelectedTeam)
                    {
                        case "Ginger Snaps Balls":
                            if (team1Box.Items.Count > 0)
                                movieTitle = team1Box.Items[team1Box.Items.Count - 1].ToString();
                            break;
                        case "Horror Boobs":
                            if (team2Box.Items.Count > 0)
                                movieTitle = team2Box.Items[team2Box.Items.Count - 1].ToString();
                            break;
                        case "Season of the Bitch":
                            if (team3Box.Items.Count > 0)
                                movieTitle = team3Box.Items[team2Box.Items.Count - 1].ToString();
                            break;
                        case "Tit Follows":
                            if (team4Box.Items.Count > 0)
                                movieTitle = team4Box.Items[team4Box.Items.Count - 1].ToString();
                            break;
                        case "Children of the Candy Corn":
                            if (team5Box.Items.Count > 0)
                                movieTitle = team5Box.Items[team5Box.Items.Count - 1].ToString();
                            break;
                    }

                    if (!string.IsNullOrEmpty(selectedTeam) && !string.IsNullOrEmpty(movieTitle))
                    {
                        string tableName = selectedTeam.Split(' ')[0] + "Table"; // Generate the table name from the first word of the team name
                        AddMovieToTeamTable(tableName, movieTitle); // Add the movie to the database table
                    }
                    else
                    {
                        MessageBox.Show("Error: Unable to determine the team or movie title.");
                    }


                    // Move to the next player in the draft order
                    draftOrder.Enqueue(draftOrder.Dequeue());

                    // Reset the "I'm finished" button for the next player
                    finishedButton.Enabled = false; // Disable again until the next pick
                    isFinished = false; // Reset the finished flag for the next player

                    // Update to show the next player's turn
                    currentDrafter.Text = $"It's {draftOrder.Peek()}'s turn!";

                    // Update the team lists and available movie pool for the new player
                    UpdateUI();


                }
                else
                {
                    // Make sure they pick something.
                    MessageBox.Show("Please select a movie before finishing your turn!");
                }
            }
        }

        private void AddMovieToTeamTable(string tableName, string movieTitle)
        {
            using (var connection = new OleDbConnection(connectionString))
            {
                connection.Open();
                string query = $"INSERT INTO {tableName} ([Movie Title]) VALUES (@MovieTitle)";

                using (var command = new OleDbCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@MovieTitle", movieTitle);
                    try
                    {
                        command.ExecuteNonQuery();

                    }
                    catch (Exception ex)
                    {

                    }
                }
            }
        }

        private void searchBox_TextChanged(object sender, EventArgs e)
        {
            // Search box to make finding what you want easier.
            string searchText = searchBox.Text.ToLower();

            if (string.IsNullOrEmpty(searchText))
            {
                return; // Do nothing if the search box is empty
            }

            // Find the first movie title that starts with whatever they type in
            var match = moviePool.FirstOrDefault(m => m.Title.ToLower().StartsWith(searchText));
            if (match != null)
            {
                // Find the index of the matching movie
                int index = moviePool.IndexOf(match);

                if (index >= 0)
                {
                    // Scroll the movieBox to the matching item
                    movieBox.SelectedIndex = index;
                }
            }
        }

        private void exitButton_Click(object sender, EventArgs e)
        {
            this.Close();
            Welcome welcomeForm = new Welcome(SelectedTeam);
            welcomeForm.ShowDialog();
        }

        private void EndTurn()
        {
            draftOrder.Enqueue(draftOrder.Dequeue());
            isFinished = false;
            UpdateUI();
        }

    }

    // Class to represent a draft action (movie drafted by which team)
    public class DraftAction
    {
        public string Team { get; set; }
        public Movie Movie { get; set; }

        public DraftAction(string team, Movie movie)
        {
            Team = team;
            Movie = movie;
        }
    }

    // Movie class
    public class Movie
    {
        public string Title { get; set; }
        public string Genres { get; set; }

        public Movie(string title, string genres)
        {
            Title = title;
            Genres = genres;
        }
    }
}



