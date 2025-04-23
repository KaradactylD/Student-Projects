using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            GetStates();

            // Changing the color, shape, etc of the buttons... none of that boring shit over here, man!
            exitBtn.BackColor = Color.Khaki;
            goBtn.BackColor = Color.Khaki;
            exitBtn.FlatStyle = FlatStyle.Flat;  // Flat style, instead of whatever weird popped up stuff they got going on
            exitBtn.FlatAppearance.BorderSize = 2; // Size of the border
            exitBtn.FlatAppearance.BorderColor = Color.Black; // Border color - Black
            goBtn.FlatStyle = FlatStyle.Flat;  // Flat Stlye for Go Button
            goBtn.FlatAppearance.BorderSize = 1; // Go Button, smaller border because it's set with the tab, and looks funny set at 2 - makes it too thick
            goBtn.FlatAppearance.BorderColor = Color.Black; // Black Border

        }

        private void exitBtn_Click(object sender, EventArgs e)
        {
            this.Close();

        }

        private void GetStates()
        {
            // Probably should have just pulled this from the databse... but this just seemed easier?
            string[] states = {
                "Alabama",
                "Alaska",
                "Arizona",
                "Arkansas",
                "California",
                "Colorado",
                "Connecticut",
                "Delaware",
                "Florida",
                "Georgia",
                "Hawaii",
                "Idaho",
                "Illinois",
                "Indiana",
                "Iowa",
                "Kansas",
                "Kentucky",
                "Louisiana",
                "Maine",
                "Maryland",
                "Massachusetts",
                "Michigan",
                "Minnesota",
                "Mississippi",
                "Missouri",
                "Montana",
                "Nebraska",
                "Nevada",
                "New Hampshire",
                "New Jersey",
                "New Mexico",
                "New York",
                "North Carolina",
                "North Dakota",
                "Ohio",
                "Oklahoma",
                "Oregon",
                "Pennsylvania",
                "Rhode Island",
                "South Carolina",
                "South Dakota",
                "Tennessee",
                "Texas",
                "Utah",
                "Vermont",
                "Virginia",
                "Washington",
                "West Virginia",
                "Wisconsin",
                "Wyoming"
            };

            stateBox.Items.AddRange(states);

        }

        private void goBtn_Click(object sender, EventArgs e)
        {
            // Go to the details page after they pick a state
            if (stateBox.SelectedIndex != -1) // I jacked this up at first, was trying to do != null... but that's dumb. It doesn't work like that... 
            {
                string state = stateBox.SelectedItem.ToString();
                StateDetails detailsForm = new StateDetails(state);
                detailsForm.ShowDialog();
            }
            else
            {
                // Make sure they pick a state from the dropdown box or Teddy won't be happy 
                MessageBox.Show("Teddy's patience is wearing thin... Select a state before he gets upset!", "TIME IS RUNNING OUT!", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
