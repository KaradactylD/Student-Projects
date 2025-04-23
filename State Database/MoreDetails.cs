using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

/* Kara Crumpton CPT 206
 * Lab 3 - State Database
 * Turtle pic from Freepik.com / State Flag pics from the Wikipedia :)
 * Turtle knowledge from being a huge nerd :)
 */

namespace KCrumpton_CPT_206___Lab_3
{
    public partial class MoreDetails : Form
    {

        public MoreDetails()
        {
            InitializeComponent();

            friendLabel.ForeColor = Color.Green;
            label1.ForeColor = Color.Purple;

            // Changing the way the buttons look
            backBtn.BackColor = Color.Khaki;
            friendBtn.BackColor = Color.Khaki;
            button1.BackColor = Color.Khaki;
            clearBtn.BackColor = Color.Khaki;
            searchBtn.BackColor = Color.Khaki;
            button1.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            button1.FlatAppearance.BorderSize = 2; // Size of the border
            button1.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            backBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            backBtn.FlatAppearance.BorderSize = 1; // Size of the border
            backBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            clearBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            clearBtn.FlatAppearance.BorderSize = 2; // Size of the border
            clearBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            friendBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            friendBtn.FlatAppearance.BorderSize = 2; // Size of the border
            friendBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            searchBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            searchBtn.FlatAppearance.BorderSize = 2; // Size of the border
            searchBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black

        }

        // Saves the stuff
        private void statesBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.statesBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.statesDataSet);

        }

        // Loads the stuff
        private void MoreDetails_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'statesDataSet.States' table. You can move, or remove it, as needed.
            this.statesTableAdapter.Fill(this.statesDataSet.States);


        }

            // Search button - search for any word and it'll show all the records that include that word.
            private void button1_Click(object sender, EventArgs e) // again with the names? Weird.
        {
            string keyword = searchBox.Text.Trim().ToLower();

            if (string.IsNullOrEmpty(keyword))
            {
                // If search box is empty, reset the DataGridView to show all data
                statesBindingSource.RemoveFilter();
                return;
            }

            // holds/shows the results from the search without losing the original table stuff
            DataTable filteredTable = statesDataSet.States.Clone(); 

            foreach (DataRow row in statesDataSet.States.Rows)
            {
                foreach (var item in row.ItemArray) // Check all the rows
                {
                    if (item.ToString().ToLower().Contains(keyword))
                    {
                        filteredTable.ImportRow(row); // Add the whole row for any matches found
                        break; 
                    }
                }
            }

            // Add all the matches to a "filtered table" 
            statesBindingSource.DataSource = filteredTable;
        }

        // This was an effin NIGHTMARE. Google to the rescue! Got it to clear the search box fine, but couldn't get the table to reset after searching. 
        private void clearBtn_Click(object sender, EventArgs e)
        {
            // Clear the search box
            searchBox.Text = string.Empty;

            // Remove any filter applied to the BindingSource - tried this alone and it did not work. Had to do all this other BS too...
            statesBindingSource.RemoveFilter();

            // Reload the data from the original table 
            this.statesTableAdapter.Fill(this.statesDataSet.States);

            // Reset the DataSource to be unfiltered
            statesBindingSource.DataSource = statesDataSet.States;

            // Reset bindings to reflect the changes
            statesBindingSource.ResetBindings(false);

        }

        private void exitBtn_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        // Filter for specific "friendly" turtles
        private void friendBtn_Click(object sender, EventArgs e)
        {
            statesBindingSource.Filter = "Turtles = 'Red-eared Slider' OR Turtles = 'Painted Turtle'";


        }

        // Filter for specific flowers turtles like to eat
        private void button1_Click_1(object sender, EventArgs e)
        {
            statesBindingSource.Filter = "Flower = 'Hawaiian Hibiscus' OR Flower = 'Cherokee Rose' OR Flower = 'Rose' OR Flower = 'Wild Prarie Rose' OR Flower = 'Wild Rose'";
        }
    }
}


    
