using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Media;
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
    public partial class MiniGame : Form
    {

        private string[] prizes;
        private bool[] picked;
        private bool prizePicked;
        
        // Gets the bonus so we can add it to the user's balance
        public int Bonus {  get; set; } 
        public MiniGame()
        {
            InitializeComponent();
            picBox1.BackColor = Color.Transparent;
            picBox2.BackColor = Color.Transparent;
            picBox3.BackColor = Color.Transparent;
            picBox4.BackColor = Color.Transparent;
            picBox5.BackColor = Color.Transparent;
            label1.BackColor = Color.Transparent;


            // What's in the boxes
            prizes = new string[5] { "Pamela", "Jason", "Machete", "Counselor", "Counselor" };
            picked = new bool[5];
            prizePicked = false;
            Bonus = 0;

            // Set what the prizes are with Random
            Random random = new Random();
            for (int i = prizes.Length - 1; i > 0; i--)
            {
                int j = random.Next(0, i + 1);
                string temp = prizes[j];
                prizes[j] = temp;
                prizes[i] = temp;
            }
        }

        private void PickedBox(int index) // originally I was using pictures of boxes "Pick a box to see what you won"... but then I changed it.
        {
            if (prizePicked || picked[index]) return; // Only let 'em pick one 
            prizePicked = true;
            picked[index] = true;

            // Disable all boxes
            picBox1.Enabled = false;
            picBox2.Enabled = false;
            picBox3.Enabled = false;
            picBox4.Enabled = false;
            picBox5.Enabled = false;

            // What they get from whatever they pick
            string prize = prizes[index];
            string message = "";
            string imageFile = "";

            // the prizes - how much they win, what the label will say, and what picture will show
            if (prize == "Pamela")
            {
                Bonus = 255000; 
                message = "You found Pamela’s Shrine! \n+255,000 credits!";
                imageFile = "shrine.jpg";
            }
            else if (prize == "Jason")
            {
                Bonus = 60000; 
                message = "You found Jason’s mask! \n+60,000 credits!";
                imageFile = "mask.jpg";
            }
            else if (prize == "Machete")
            {
                Bonus = 25000;
                message = "You found a bloody machete! \n+25,000 credits!";
                imageFile = "machete.jpg";
            }
            else // Counselor
            {
                Bonus = 15000;
                message = "You found a counselor’s badge! \n+15,000 credits!";
                imageFile = "badge.jpg";
            }

            PrizeForm prizeForm = new PrizeForm(message, imageFile);
            prizeForm.Show();

            // Close after they play. I did 8 seconds, trying to time it with the sound effect but it's not really as good as I wanted
            Timer closeTimer = new Timer { Interval = 8000 };
            closeTimer.Tick += (s, args) => { closeTimer.Stop(); this.Close(); };
            closeTimer.Start();
        }

        private void PlaySound()
        {
            try
            {
                // Specify the path to your sound file
                string soundFilePath = System.IO.Path.Combine(Application.StartupPath, "kill her mommy.wav"); 

                // use the SoundPlayer for the sound
                using (SoundPlayer player = new SoundPlayer(soundFilePath))
                {
                    
                    player.Play();
                }
            }
            catch (Exception ex)
            {
                // In case there's a problem with the sound file
                MessageBox.Show($"Error playing sound: {ex.Message}");
            }
        }

        private void MiniGame_Load(object sender, EventArgs e)
        {
            PlaySound();

            picBox1.Enabled = true;
            picBox2.Enabled = true;
            picBox3.Enabled = true;
            picBox4.Enabled = true;
            picBox5.Enabled = true;
        }

        private void picBox1_Click(object sender, EventArgs e)
        {
            PickedBox(0);
        }

        private void picBox4_Click(object sender, EventArgs e)
        {
            PickedBox(1);
        }

        private void picBox5_Click(object sender, EventArgs e)
        {
            PickedBox(2);
        }

        private void picBox2_Click(object sender, EventArgs e)
        {
            PickedBox(3);
        }

        private void picBox3_Click(object sender, EventArgs e)
        {
            PickedBox(4);
        }
    }
}
