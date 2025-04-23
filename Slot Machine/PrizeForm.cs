using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
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
    public partial class PrizeForm : Form
    {
        public PrizeForm(string message, string imageFile)
        {
            InitializeComponent();
            prizeLabel.Text = message; // Pulls the message from the Mini Game
            prizeLabel.BackColor = Color.Transparent;
            prizeBox.Image = Image.FromFile(Path.Combine(Application.StartupPath, imageFile)); // Pulls this from Mini Game too
            prizeBox.SizeMode = PictureBoxSizeMode.Zoom;
            prizeBox.BackColor = Color.Transparent;


            // Used a Timer to close the form automatically, instead of using a button
            Timer closeTimer = new Timer { Interval = 8000 }; 
            closeTimer.Tick += (s, e) =>
            {
                closeTimer.Stop();  
                this.Close();       
            };
            closeTimer.Start();
        }

        private void PrizeForm_Load(object sender, EventArgs e)
        {

        }
    }
}
