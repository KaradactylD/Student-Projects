using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
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
    public partial class Details : Form
    {
        public Details()
        {
            InitializeComponent();
            titleLabel.BackColor = Color.Transparent;
            picBox1.BackColor = Color.Transparent;
            nextBtn.BackColor = Color.Transparent;
            backBtn.BackColor = Color.Transparent;
        }

        private void backBtn_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void nextBtn_Click(object sender, EventArgs e)
        {
            Details2 detailsform2 = new Details2();
            detailsform2.ShowDialog();
        }
    }
}
