using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.OleDb;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Schema;

/* Kara Crumpton
 * CPT 185 - Final Project - Teams Page
 * Background Image from freepik.com
 */

namespace Horror_Draft
{
    public partial class Teams : Form
    {
        public string SelectedTeam { get; private set; }
        private Dictionary<string, string> teamUserMapping = new Dictionary<string, string>
{
            // What table in the Database to use for each player
    { "Ginger Snaps Balls", "GingerTable" },
    { "Tit Follows", "TitTable" },
    { "Horror Boobs", "HorrorTable" },
    { "Season of the Bitch", "SeasonTable" },
    { "Children of the Candy Corn", "ChildrenTable" }
};

        
        public Teams(string selectedTeam)
        {
            InitializeComponent();
            
            SelectedTeam = selectedTeam; // HOLY SHIT. It took me forever to realize I had this wrong at first. Couldn't get it to work for anything! I was doing SelectedTeam = SelectedTeam.
            teamLabel.Text = $"{selectedTeam}'s Page!";
            teamLabel.BackColor = Color.Transparent;
            moviesLabel.BackColor = Color.Transparent;
            totalLabel.BackColor = Color.Transparent;
            highestLabel.BackColor = Color.Transparent;
            lowestLabel.BackColor = Color.Transparent;
            overallLabel.BackColor = Color.Transparent;

        }

        private void LoadPicksForTeam(string teamTableName)
        {
            // This is where it connects to the Database. Same one as the movies, but I'm using different tables.
            string connectionString = $@"Provider=Microsoft.ACE.OLEDB.12.0;Data Source=..\..\HorrorDraftPractice.accdb;";

            // Tell it what to get.
            string query = $"SELECT [Movie Title], [Score] FROM [{teamTableName}]";

            using (OleDbConnection connection = new OleDbConnection(connectionString))
            {
                OleDbCommand command = new OleDbCommand(query, connection);

                try
                {
                    connection.Open();
                    OleDbDataReader reader = command.ExecuteReader();

                    // Clear the boxes at start.
                    picksBox.Items.Clear();

                    // Read the stuff, add it to the box.
                    while (reader.Read())
                    {
                        string movieTitle = reader["Movie Title"].ToString();
                        string score = reader["Score"].ToString();

                        // Make it look better. - Need to play with this more. 
                        string listItem = $"{movieTitle}:  Score: {score}";
                        picksBox.Items.Add(listItem);
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Error loading data for {teamTableName}: {ex.Message}");
                }
            }
        }


        private string GetTeamTableNameForCurrentUser()
        {
            // All of this is from the errors I was getting... that ended up just being a damn typo. OOF.
            if (SelectedTeam == null || !teamUserMapping.ContainsKey(SelectedTeam))
            {
                throw new Exception("User not mapped to a team or SelectedTeam is null.");
            }
            return teamUserMapping[SelectedTeam];
        }

        private void Teams_Load(object sender, EventArgs e)
        {

            try
            {
                // Ensure SelectedTeam is assigned before calling this method - Typos. Leaving it in, it might come in handy.
                if (SelectedTeam == null)
                {
                    throw new Exception("SelectedTeam has not been set.");
                }

                string teamTableName = GetTeamTableNameForCurrentUser();
                LoadPicksForTeam(teamTableName);
                GetUserStats(); // Show all the stuff for the user (totals and whatnot)
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error: {ex.Message}");
            }
        }

        private void GetUserStats()
        {
            // Get the stuff for each person
            List<int> scores = new List<int>();
            List<string> movieTitles = new List<string>();

            foreach (var item in picksBox.Items)
            {
                string itemText = item.ToString();
                int scoreIndex = itemText.LastIndexOf("Score: ") + 7; 
                if (scoreIndex > 6 && scoreIndex < itemText.Length)
                {
                    string movieTitle = itemText.Substring(0, scoreIndex - 7).Trim();
                    string scoreText = itemText.Substring(scoreIndex);
                    if (int.TryParse(scoreText, out int score))
                    {
                        scores.Add(score);
                        movieTitles.Add(movieTitle);
                    }
                }
            }

            if (scores.Count > 0)
            {
                // Variables
                int totalMovies = scores.Count;
                int highestScore = scores.Max();
                int lowestScore = scores.Min();
                int totalScore = scores.Sum();
                int highestScoreIndex = scores.IndexOf(highestScore);
                int lowestScoreIndex = scores.IndexOf(lowestScore);
                string highestScoreMovie = movieTitles[highestScoreIndex];
                string lowestScoreMovie = movieTitles[lowestScoreIndex];

                // Put the stuff in the labels
                totalLabel.Text = $"Total Movies Scored:  {totalMovies}";
                highestLabel.Text = $"Highest Score: {highestScoreMovie}  {highestScore}";
                lowestLabel.Text = $"Lowest Score: {lowestScoreMovie}  {lowestScore}";
                overallLabel.Text = $"Total Overall Score So Far: {totalScore}";
            }
            else
            {
                MessageBox.Show("No valid scores found!");
            }
        }

        private void UpdateMovieScore(string teamTableName, string movieTitle, int newScore)
        {

            string connectionString = $@"Provider=Microsoft.ACE.OLEDB.12.0;Data Source=..\..\HorrorDraftPractice.accdb;";
            string query = $"UPDATE [{teamTableName}] SET [Score] = ? WHERE [Movie Title] = ?";

            using (OleDbConnection connection = new OleDbConnection(connectionString))
            {
                using (OleDbCommand command = new OleDbCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@Score", newScore);
                    command.Parameters.AddWithValue("@MovieTitle", movieTitle);

                    try
                    {
                        connection.Open();
                        int rowsAffected = command.ExecuteNonQuery();
                        if (rowsAffected > 0)
                        {
                            MessageBox.Show("Score updated successfully.");
                        }
                        else
                        {
                            MessageBox.Show("Error: Movie not found in the database.");
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show($"Error updating score: {ex.Message}");
                    }
                }
            }
        }


            private string GetCurrentUser()
        {

            return SelectedTeam ?? "No team selected";
        }

        

        private string GetCurrentUserTeam()
        {
            // Return with the user. 
            return SelectedTeam;
        }

        private void backButton_Click(object sender, EventArgs e)
        {
            this.Close();
            Welcome welcomeForm = new Welcome(SelectedTeam);
            welcomeForm.ShowDialog();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (picksBox.SelectedItem != null)
            {
                string selectedMovie = picksBox.SelectedItem.ToString().Split(':')[0].Trim();
                string teamTableName = GetTeamTableNameForCurrentUser();

                if (int.TryParse(scoreBox.Text, out int newScore))
                {
                    // Update the database with the new score
                    UpdateMovieScore(teamTableName, selectedMovie, newScore);

                    // Refresh the picksBox to show the updated score
                    LoadPicksForTeam(teamTableName);
                }
                else
                {
                    MessageBox.Show("Please enter a valid score.");
                }
            }
            else
            {
                MessageBox.Show("Please select a movie from the list.");
            }
        }

    }
}

