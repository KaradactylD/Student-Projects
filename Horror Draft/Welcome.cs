using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Text;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

/* Kara Crumpton
 * CPT 185 - Final Project - Welcome Page
 * Background Image from freepik.com
 */

namespace Horror_Draft
{
    public partial class Welcome : Form
    {
        public string SelectedTeam { get; private set; } //Sets who you are, so you're "logged in"

        // Dictionary of the Weeks/Movies/Scores from this season so far... we're on week 4 now, so there's zeros for the ones we're not finished with.
        Dictionary<string, Dictionary<string, int>> weekData = new Dictionary<string, Dictionary<string, int>>()

{
    {
        "WEEK 1: \"Solo Movies\" - No Sequels/Prequels", new Dictionary<string, int>()
        {
            { "Ginger Snaps Balls: Hospital Massacre", 42 },
            { "Ginger Snaps Balls: Popcorn", 16 },
            { "Ginger Snaps Balls: Belzebuth", 268 },
            { "Ginger Snaps Balls: Stage Fright", 19 },
            { "Tit Follows: Dr. Giggles", 56 },
            { "Tit Follows: From Beyond", 24 },
            { "Tit Follows: Shocker", 44 },
            { "Tit Follows: Firestarter", 204 },
            { "Horror Boobs: Wild Zero", 340 },
            { "Horror Boobs: Unicorn Wars", 115 },
            { "Horror Boobs: Shark Encounters of the Third Kind", 39 },
            { "Horror Boobs: Embrace of the Vampire", 30 },
            { "Season of the Bitch: Green Room", 50 },
            { "Season of the Bitch: In the Mouth of Madness", 254 },
            { "Season of the Bitch: Videodrome", 34 },
            { "Season of the Bitch: The House That Jack Built", 105 },
            { "Children of the Candy Corn: Longlegs", 12 },
            { "Children of the Candy Corn: Train to Busan", 228 },
            { "Children of the Candy Corn: Slotherhouse", 88 },
            { "Children of the Candy Corn: Sweeney Todd", 48 }
        }
    },
    {
        "WEEK 2: \"The Roaring 20s\" - Only Movies from 2020s", new Dictionary<string, int>()
        {
            { "Ginger Snaps Balls: Terrifier 3", 234 },
            { "Ginger Snaps Balls: ZOM 100: Bucket List of the Dead", 114 },
            { "Ginger Snaps Balls: Christmas Bloody Christmas", 58 },
            { "Ginger Snaps Balls: Slaxx", 192 },
            { "Tit Follows: Black Phone", 34 },
            { "Tit Follows: Trap", 8 },
            { "Tit Follows: The Inn", 0 },
            { "Tit Follows: Beyond the Chamber of Terror", 0 },
            { "Horror Boobs: Godzilla Minus One", 670 },
            { "Horror Boobs: Alien Romulus", 36 },
            { "Horror Boobs: Army of the Dead", 396 },
            { "Horror Boobs: Crackcoon", 74 },
            { "Season of the Bitch: In A Violent Nature", 48 },
            { "Season of the Bitch: The Menu", 161 },
            { "Season of the Bitch: Til Death", 46 },
            { "Season of the Bitch: Freaky", 50 },
            { "Children of the Candy Corn: Nope", 168 },
            { "Children of the Candy Corn: Spontaneous", 106 },
            { "Children of the Candy Corn: Resident Evil: Death Island", 231 },
            { "Children of the Candy Corn: Sharks of the Corn", 64 }
        }
    },
    {
        "WEEK 3: \"Fucked Up Mountain People\" - Rednecks and Such", new Dictionary<string, int>()
        {
            { "Ginger Snaps Balls: BYE WEEK 1", 0 },
            { "Ginger Snaps Balls: BYE WEEK 2", 0 },
            { "Ginger Snaps Balls: BYE WEEK 3" , 0 },
            { "Ginger Snaps Balls: BYE WEEK 4" , 0 },
            { "Tit Follows: Texas Chainsaw Massacre 2", 0 },
            { "Tit Follows: Train", 0 },
            { "Tit Follows: House of Wax", 0 },
            { "Tit Follows: Crazy Fat Ethel", 0 },
            { "Horror Boobs: Redneck Zombies", 72 },
            { "Horror Boobs: Wrong Turn 5", 73 },
            { "Horror Boobs: Daddy's Deadly Darling", 10 },
            { "Horror Boobs: The Final Terror", 31 },
            { "Season of the Bitch: The Hills Have Eyes", 26 },
            { "Season of the Bitch: Bubba the Redneck Werewolf", 38 },
            { "Season of the Bitch: Wrong Turn 2", 57 },
            { "Season of the Bitch: Pearl", 35 },
            { "Children of the Candy Corn: Trailer Park of Terror", 110 },
            { "Children of the Candy Corn: Wolf Creek 2", 97 },
            { "Children of the Candy Corn: Dead and Breakfast", 88 },
            { "Children of the Candy Corn: The Hills Run Red", 74 }
        }
    },
    {
        "WEEK 4: \"Holiday Kill-tacular!\" - Holiday Themed movies ONLY.", new Dictionary<string, int>()
        {
            { "Ginger Snaps Balls: Thankskilling", 0 },
            { "Ginger Snaps Balls: Nutcracker Massacre", 34 },
            { "Ginger Snaps Balls: The Mean One", 0 },
            { "Ginger Snaps Balls: The Last Thanksgiving", 0 },
            { "Tit Follows: BYE WEEK 5", 0 },
            { "Tit Follows: BYE WEEK 6", 0 },
            { "Tit Follows: BYE WEEK 7" , 0 },
            { "Tit Follows: BYE WEEK 8" , 0 },
            { "Horror Boobs: Black Friday", 0 },
            { "Horror Boobs: Beaster Day: Here Comes Peter Cotton Hell", 0 },
            { "Horror Boobs: Thanksgiving", 0 },
            { "Horror Boobs: Night of the Comet", 174 },
            { "Season of the Bitch: Violent Night", 210 },
            { "Season of the Bitch: Santa's Slay", 181 },
            { "Season of the Bitch: Jack Frost", 41 },
            { "Season of the Bitch: Silent Night Deadly Night", 0 },
            { "Children of the Candy Corn: Independence Day", 0 },
            { "Children of the Candy Corn: Jack Frost 2", 138 },
            { "Children of the Candy Corn: Anna and the Apocalypse", 132 },
            { "Children of the Candy Corn: Silent Night Deadly Night 2", 0 }
        }
    }
};




        public Welcome(string selectedTeam)
        {

            InitializeComponent();
            SelectedTeam = selectedTeam;
            horrorBox.BackColor = Color.Black;
            horrorBox.ForeColor = Color.White;
            childrenBox.BackColor = Color.Black;
            childrenBox.ForeColor = Color.White;
            gingerBox.BackColor = Color.Black;
            gingerBox.ForeColor = Color.White;
            titBox.BackColor = Color.Black;
            titBox.ForeColor = Color.White;
            seasonBox.BackColor = Color.Black;
            seasonBox.ForeColor = Color.White; 
            scoreLabel.BackColor = Color.Black;
            scoreLabel.ForeColor = Color.White;
            weekBox.BackColor = Color.Black;
            weekBox.ForeColor = Color.White;

        }

        private void teamButton_Click(object sender, EventArgs e)
        {
            // Takes you to the Teams Page for whoever you're logged in as
            Teams teamForm = new Teams(SelectedTeam);
            teamForm.ShowDialog();
            this.Close();
           

        }

        private void draftButton_Click(object sender, EventArgs e)
        {
            // Takes you to the Draft Page for whoever you're logged in as
            Form1 draftForm = new Form1(SelectedTeam);
            draftForm.ShowDialog();
            this.Close();
            
        }

        private void weekBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Clear all ListBoxes before adding updated items
            gingerBox.Items.Clear();
            horrorBox.Items.Clear();
            childrenBox.Items.Clear();
            seasonBox.Items.Clear();
            titBox.Items.Clear();

            // Check if a selection has been made
            if (weekBox.SelectedItem != null)
            {
                string selectedWeek = weekBox.SelectedItem.ToString();

                // Check if there's stuff for the week
                if (weekData.ContainsKey(selectedWeek))
                {
                    var weekScores = weekData[selectedWeek];

                    // Where to put the stuff 
                    PopulateListBox(gingerBox, weekScores, "Ginger Snaps Balls");
                    PopulateListBox(horrorBox, weekScores, "Horror Boobs");
                    PopulateListBox(titBox, weekScores, "Tit Follows");
                    PopulateListBox(seasonBox, weekScores, "Season of the Bitch");
                    PopulateListBox(childrenBox, weekScores, "Children of the Candy Corn");
                }
                else
                {
                    MessageBox.Show($"No data found for {selectedWeek}");
                }
            }
        }

        // Get the stuff to go in the boxes
        private void PopulateListBox(ListBox listBox, Dictionary<string, int> weekScores, string teamName)
        {
            // Struggled here... had to look this up. 
            var scores = weekScores.Where(entry => entry.Key.StartsWith(teamName)).ToList();
            int totalScore = 0;

            foreach (var score in scores)
            {
                listBox.Items.Add($"{score.Key.Split(':')[1].Trim()}, {score.Value}");
                totalScore += score.Value;
            }

            listBox.Items.Add($"TOTAL: {totalScore}");
        }

        // Put the stuff in the boxes
        private void AddScoresToListBox(ListBox listBox, List<KeyValuePair<string, int>> scores)
        {
            int totalScore = 0;

            foreach (var score in scores)
            {
                listBox.Items.Add($"{score.Key.Split(':')[1]}, {score.Value}");
                totalScore += score.Value;
            }

            listBox.Items.Add($"TOTAL: {totalScore}");
        }

        private void backButton_Click(object sender, EventArgs e)
        {
            this.Close();
            Login login = new Login();
            login.ShowDialog();
        }

        // Include a Word Document with the rules.
        private void rulesButton_Click(object sender, EventArgs e)
        {
            // Get the path to the current directory (Debug folder)
            string filePath = System.IO.Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "HorrorDraftRules.docx");

            try
            {
                // Open the .doc file with the default application (e.g., Microsoft Word)
                Process.Start(new ProcessStartInfo(filePath)
                {
                    UseShellExecute = true // Opens the file with the default program
                });
            }
            catch (Exception ex)
            {
                // Handle any errors that occur when trying to open the file
                MessageBox.Show($"Failed to open the document: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
        
    

