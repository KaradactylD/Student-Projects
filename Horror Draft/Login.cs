using System;
using System.Drawing;
using System.Windows.Forms;

/* Kara Crumpton
 * CPT 185 - Final Project - Login Page
 * Background Image from freepik.com
 */

namespace Horror_Draft
{
    public partial class Login : Form
    {

        // Figure out who you are 
        public string SelectedTeam { get; private set; }

        public Login()
        {
            InitializeComponent();
            // Make the buttons transparent
            gingerButton.BackColor = Color.Transparent;
            gingerButton.FlatStyle = FlatStyle.Flat;
            gingerButton.FlatAppearance.BorderSize = 0;
            gingerButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            gingerButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            gingerButton.TabStop = false;

            titButton.BackColor = Color.Transparent;
            titButton.FlatStyle = FlatStyle.Flat;
            titButton.FlatAppearance.BorderSize = 0;
            titButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            titButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            titButton.TabStop = false;

            horrorButton.BackColor = Color.Transparent;
            horrorButton.FlatStyle = FlatStyle.Flat;
            horrorButton.FlatAppearance.BorderSize = 0;
            horrorButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            horrorButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            horrorButton.TabStop = false;

            childrenButton.BackColor = Color.Transparent;
            childrenButton.FlatStyle = FlatStyle.Flat;
            childrenButton.FlatAppearance.BorderSize = 0;
            childrenButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            childrenButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            childrenButton.TabStop = false;

            seasonButton.BackColor = Color.Transparent;
            seasonButton.FlatStyle = FlatStyle.Flat;
            seasonButton.FlatAppearance.BorderSize = 0;
            seasonButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            seasonButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            seasonButton.TabStop = false;
        }

        // You'll be "logged in" as whatever user name you click on, and taken to the Main Page.
        private void gingerButton_Click(object sender, EventArgs e)
        {
            SelectedTeam = "Ginger Snaps Balls"
;           OpenWelcomeForm(SelectedTeam);
            
        }

        private void titButton_Click(object sender, EventArgs e)
        {
            SelectedTeam = "Tit Follows";
            OpenWelcomeForm(SelectedTeam);
            
        }

        private void horrorButton_Click(object sender, EventArgs e)
        {
            SelectedTeam = "Horror Boobs";
            OpenWelcomeForm(SelectedTeam);
            
        }

        private void childrenButton_Click(object sender, EventArgs e)
        {
            SelectedTeam = "Children of the Candy Corn";
            OpenWelcomeForm(SelectedTeam);
            
        }

        private void seasonButton_Click(object sender, EventArgs e)
        {
            SelectedTeam = "Season of the Bitch";
            OpenWelcomeForm(SelectedTeam);
            
        }

        // Open the Welcome Form
        private void OpenWelcomeForm(string SelectedTeam)
        {
            this.Close();
            Welcome welcomeForm = new Welcome(SelectedTeam);
            welcomeForm.ShowDialog();
        }
    }
}
