using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Entity;
using System.Data.SQLite;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

/* Kara Crumpton - CPT 206 - Final Project
 * Friday the 13th Slot Machine
 * Pics from OpenArt AI Image Generator 
 * Sound Effects from Soundboard 
 * started: Feb 19th 2025 - finished: Apr 20 2025
 */

namespace KCrumpton_CPT_206_Final_Project_Slot_Machine
{
    public partial class Create : Form
    {
        // Path to store the database in the Debug folder
        private string connectionString = "Data Source=SlotUsers.db;Version=3;"; // stores the user name/password
        public Create()
        {
            InitializeComponent();
            CreateDatabase();

            // button/label stuff
            nameLabel.BackColor = Color.Transparent;
            emailLabel.BackColor = Color.Transparent;
            passLabel.BackColor = Color.Transparent;
            pass2Label.BackColor = Color.Transparent;
            createBtn.BackColor = Color.Transparent;
            exitBtn.BackColor = Color.Transparent;
            infoLabel.BackColor = Color.Transparent;

        }

        // Create the database file and Users table if they don't exist
        private void CreateDatabase()
        {
            using (SQLiteConnection conn = new SQLiteConnection(connectionString))
            {
                conn.Open();
                string query = @"CREATE TABLE IF NOT EXISTS SlotUsers (
                                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                                    UserName TEXT UNIQUE,
                                    Email TEXT,
                                    Password TEXT,
                                    Credit INTEGER DEFAULT 50000 

                                );";

                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    cmd.ExecuteNonQuery();
                }
            }
        }

        // Hash password
        public static string HashPassword(string password)
        {
            using (SHA256 sha256 = SHA256.Create())
            {
                byte[] hashBytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(password));
                return BitConverter.ToString(hashBytes).Replace("-", "").ToLower();
            }
        }
        private void createBtn_Click(object sender, EventArgs e)
        {
            // Don't let em create if all fields aren't filled out
            if (string.IsNullOrWhiteSpace(userBox.Text) ||
                string.IsNullOrWhiteSpace(emailBox.Text) ||
                string.IsNullOrWhiteSpace(passBox.Text) ||
                string.IsNullOrWhiteSpace(passBox2.Text))
            {
                MessageBox.Show("You must fill in all fields to create an account.");
                return;
            }

            string UserName = userBox.Text;
            string Email = emailBox.Text;
            string Password = passBox.Text;
            string confirm = passBox2.Text;


            // make sure passwords match
            if (Password != confirm)
            {
                MessageBox.Show("Passwords do not match!");
                return;
            }

            string hashedPassword = HashPassword(Password);

            using (SQLiteConnection conn = new SQLiteConnection(connectionString))
            {
                conn.Open();
                string query = "INSERT INTO SlotUsers (UserName, Email, Password, Credit, Balance) VALUES (@username, @email, @password, 50000, 50000)";
                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@username", UserName);
                    cmd.Parameters.AddWithValue("@email", Email);
                    cmd.Parameters.AddWithValue("@password", hashedPassword);
                    cmd.Parameters.AddWithValue("Credit", 50000);
                    cmd.Parameters.AddWithValue("Balance", 50000);

                    try
                    {
                        cmd.ExecuteNonQuery();
                        MessageBox.Show("Account created successfully!");

                        this.Close();
                    }
                    catch (SQLiteException ex)
                    {
                        MessageBox.Show("Error: " + ex.Message);
                    }
                }
            }
        }

        private void exitBtn_Click(object sender, EventArgs e)
        {
            this.Close();
        }

    }
    
}
