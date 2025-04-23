using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.IO;
using System.Drawing.Text;
using static System.Windows.Forms.AxHost;

/* Kara Crumpton CPT 206
 * Lab 3 - State Database
 * Turtle pic from Freepik.com / State Flag pics from the Wikipedia :)
 * Turtle knowledge from being a huge nerd :)
 */

namespace KCrumpton_CPT_206___Lab_3
{
    public partial class StateDetails : Form
    {
        private List<string> stateList = new List<string>(); // This is to hold the states so my "next" button will loop through
        private int currentIndex = 0; // This is to get the index of the selected state

        // This is where my mdf file with all the stuff is
        private string connectionString = @"Server=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\States.mdf;Integrated Security=True;";
        private string selectedState;
        public StateDetails(string state)
        {
            InitializeComponent();
            selectedState = state;
            nameLabel.Text = state;
            LoadStateList();
            DisplayInfo();

            // Changing the colors for the labels
            birdLabel.ForeColor = Color.Teal;
            flowerLabel.ForeColor = Color.Purple;
            turtleLabel.ForeColor = Color.Green;
           

            // Setting the picturebox mode to zoom - doesn't work just setting it in properties
            flagBox.SizeMode = PictureBoxSizeMode.Zoom;

            // Changing the buttons to look cool - just realized I didn't name the Table View button right. OOPS!
            button2.BackColor = Color.Khaki;
            backBtn.BackColor = Color.Khaki;
            prevBtn.BackColor = Color.Khaki;
            nextBtn.BackColor = Color.Khaki;
            button2.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            button2.FlatAppearance.BorderSize = 2; // Size of the border
            button2.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            backBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            backBtn.FlatAppearance.BorderSize = 1; // Size of the border
            backBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            prevBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            prevBtn.FlatAppearance.BorderSize = 2; // Size of the border
            prevBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            nextBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            nextBtn.FlatAppearance.BorderSize = 2; // Size of the border
            nextBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black
        }

        // Load the states
        private void LoadStateList()
        {
            string query = "SELECT State FROM dbo.States ORDER BY State";

            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();
                using (SqlCommand command = new SqlCommand(query, connection))
                {
                    SqlDataReader reader = command.ExecuteReader();
                    stateList.Clear(); 
                    while (reader.Read())
                    {
                        stateList.Add(reader["State"].ToString());
                    }
                }
            }

            currentIndex = stateList.IndexOf(selectedState);
        }

            private void DisplayInfo()
            {
            // SQL Query to pull the info:
            string query = "SELECT Capital, Population, Flower, Bird, Turtles, Colors, LargestCity, MedianIncome, ComputerJobs FROM dbo.States WHERE State = @state";

            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();

                using (SqlCommand command = new SqlCommand(query, connection))
                {
                    command.Parameters.AddWithValue("@state", selectedState);

                    SqlDataReader reader = command.ExecuteReader();

                    // Make sure the thing worked and the stuff is in there
                    if (reader.HasRows)
                    {
                        while (reader.Read())
                        {
                            // Pulling all the info from the table
                            string capital = reader["Capital"].ToString();
                            string population = reader["Population"].ToString();
                            string bird = reader["Bird"].ToString();
                            string flower = reader["Flower"].ToString();
                            string turtle = reader["Turtles"].ToString();
                            string colors = reader["Colors"].ToString();
                            string largest = reader["LargestCity"].ToString();
                            string income = reader["MedianIncome"].ToString();
                            string jobs = reader["ComputerJobs"].ToString();

                            // Put the data in the labels
                            capLabel.Text = "Capital: \n" + capital;
                            popLabel.Text = "Population: \n" + int.Parse(population).ToString("N0"); // "N0" is N for number, the 0 is for zero decimal places.
                            birdLabel.Text = "Bird: \n" + bird;
                            flowerLabel.Text = "Flower: \n" + flower;
                            turtleLabel.Text = "Common Turtles: \n" + turtle;
                            cityLabel.Text = "Largest City: \n" + largest;
                            jobsLabel.Text = "Percentage of Computer Jobs: \n" + jobs;

                            // Show income with the dollar sign, struggled way too hard with this... YIKES.
                            if (decimal.TryParse(reader["MedianIncome"].ToString(), out decimal incomeValue))
                            {
                                incomeLabel.Text = "Median Income: \n" + incomeValue.ToString("C0"); // "C0" is C for currency, the 0 is for zero decimal places. 
                            }
                            else
                            {
                                incomeLabel.Text = "Median Income: \n Data Unavailable"; // I had NULL values for income at first, I was able to find them all eventually tho - leaving this in here anyway
                            }
                        }
                    }
                    else
                    {
                        MessageBox.Show("State not found.");
                    }
                }
            }

            // Show the flag pics for each state. Named them 'statename'.png 
            string imagePath = Path.Combine(Application.StartupPath, selectedState + ".png");

            if (File.Exists(imagePath))
            {
                flagBox.Image = Image.FromFile(imagePath); 
            }
            else
            {
                MessageBox.Show("Flag not found for " + selectedState); // In case there is no picture
            }
        }

        private void button1_Click(object sender, EventArgs e) // I named the buttons, not sure why it didn't change here?
        {
            // Go back
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e) // again, button not named right! ??
        {
           // Go to the Table View page
            MoreDetails detailsForm = new MoreDetails();
            detailsForm.ShowDialog();
        }

        // Click the next button to keep going through the states to see all the details
        private void nextBtn_Click(object sender, EventArgs e)
        {
            if (stateList.Count == 0) return; // if for some reason nothing is loaded, it won't run anything

            currentIndex = (currentIndex + 1) % stateList.Count; // Had to look this up too, didn't want it to stop at the end, wanted it to just keep looping. 
            selectedState = stateList[currentIndex];
            nameLabel.Text = selectedState;
            DisplayInfo();
        }

        // Go back through previous states - almost the exact same code, but -1 index to go backwards
        private void prevBtn_Click(object sender, EventArgs e)
        {
            if (stateList.Count == 0) return; // if for some reason nothing is loaded, it won't run anything

            // Subtract the index to go backwards. Had to look up that modulo part... I'm not good with using that, couldn't figure it out.
            currentIndex = (currentIndex - 1 + stateList.Count) % stateList.Count;
            selectedState = stateList[currentIndex];
            nameLabel.Text = selectedState;
            DisplayInfo();
        }
    }
}









