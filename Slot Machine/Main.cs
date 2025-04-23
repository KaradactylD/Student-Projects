using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SQLite;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Media;

/* Kara Crumpton - CPT 206 - Final Project
 * Friday the 13th Slot Machine
 * Pics from OpenArt AI Image Generator 
 * Sound Effects from Soundboard 
 * started: Feb 19th 2025 - finished: Apr 20 2025
 */

namespace KCrumpton_CPT_206_Final_Project_Slot_Machine
{
    public partial class Main : Form
    {
        // For the music/sound effects
        private SoundPlayer _soundPlayer;

        // So it knows who's playing
        public string UserName { get; private set; }

        public Main()
        {
            InitializeComponent();
            nameLabel.BackColor = Color.Transparent;
            passLabel.BackColor = Color.Transparent;
            newLabel.BackColor = Color.Transparent;
            logBtn.BackColor = Color.Transparent;
            createBtn.BackColor = Color.Transparent;
            _soundPlayer = new SoundPlayer(System.IO.Path.Combine(Application.StartupPath, "theme.wav"));
        }

        // Goes to Create Page for making new account
        private void createBtn_Click(object sender, EventArgs e)
        {
            Create createAccount = new Create();
            createAccount.ShowDialog();
        }

        // Login button
        private void logBtn_Click(object sender, EventArgs e)
        {
            // stops this sound effect, there's a different one on the other page
            _soundPlayer.Stop();

            string username = userBox.Text;
            string password = passBox.Text;

            if (ValidateLogin(username, password))
            {
                Game gamePage = new Game(username);
                gamePage.ShowDialog();

            }
            else
            {
                MessageBox.Show("Invalid username or password.", "Failure!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

        }

        // Nerdy password stuff... 
        private static string HashPassword(string password)
        {
            using (System.Security.Cryptography.SHA256 sha256 = System.Security.Cryptography.SHA256.Create())
            {
                byte[] hashBytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(password));
                return BitConverter.ToString(hashBytes).Replace("-", "").ToLower();
            }
        }

        // Checkin' the DB to make sure the stuff is correct
        private bool ValidateLogin(string username, string password)
        {
            string connectionString = "Data Source=SlotUsers.db;Version=3;";
            string hashedPassword = HashPassword(password); // Hash input to compare

            using (SQLiteConnection conn = new SQLiteConnection(connectionString))
            {
                conn.Open();
                string query = "SELECT COUNT(*) FROM SlotUsers WHERE username=@username AND password=@password";
                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@username", username);
                    cmd.Parameters.AddWithValue("@password", hashedPassword);

                    int count = Convert.ToInt32(cmd.ExecuteScalar());
                    return count > 0;
                }
            }
        }

        private void exitBtn_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void Main_Load(object sender, EventArgs e)
        {
            // To play the music, loops while it's open
            _soundPlayer.PlayLooping();  
        }
    }
}

