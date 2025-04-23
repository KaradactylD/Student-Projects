using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using System.Windows.Forms;


/* Kara Crumpton
 * CPT 185 - Lab 6 - Driver's License Exam
 * I've included 21 Text Files, one for every student in this class. You can run it using their first names, except we've got 2 Brandons... so one is just Brandon, the other is "Brandon R"
 */

namespace Kara_Crumpton___CPT_185___Lab_6___Driver_s_License
{
    public partial class Form1 : Form
    {

        string[] answersArray = new string[] { "B", "D", "A", "A", "C", "A", "B", "A", "C", "D", "B", "C", "D", "A", "D", "C", "C", "B", "D", "A" };
        string name;
        StreamReader inputFile;


        // Keep track of the ones that are wrong
        List<int> incorrectQuestions = new List<int>();

        public Form1()
        {
            InitializeComponent();
            resultPicBox.Visible = false;
            resultPicLabel.Visible = false;
        }

        private void exitButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void scoreButton_Click(object sender, EventArgs e)
        {
            name = nameBox.Text;
            string filePath = $"{name}.txt";

            try
            {
                // Get the answers from the student files
                string[] studentAnswers = File.ReadAllLines(filePath);


                // Reset the list for incorrect answers - without this, it was keeping everyone's wrong answers and adding them all.
                List<int> incorrectQuestions = new List<int>();

                // Compare the answers, get the ones that are wrong
                for (int i = 0; i < answersArray.Length; i++)
                {
                    if (answersArray[i] != studentAnswers[i])
                    {
                        incorrectQuestions.Add(i + 1);
                    }
                }

                // Show the results for Correct, Incorrect, and Missed
                if (incorrectQuestions.Count >= 0)
                {
                    missedLabel.Text = string.Join(", ", incorrectQuestions);
                    incorrectLabel.Text = incorrectQuestions.Count.ToString();
                    correctLabel.Text = (20 - incorrectQuestions.Count).ToString();

                    if (incorrectQuestions.Count <= 5)
                    {
                        resultLabel.Text = "PASS!";
                        resultLabel.ForeColor = Color.Green;
                        resultPicBox.Visible = true;
                        resultPicLabel.Visible = true;
                        resultPicBox.Image = Image.FromFile("McLovin.jpg");
                        resultPicBox.SizeMode = PictureBoxSizeMode.Zoom;
                        resultPicLabel.Text = "\"I got it, it is flawless! Check it!\"";
                        resultPicLabel.ForeColor = Color.Green;
                    }
                    else
                    {
                        resultLabel.Text = "FAIL!";
                        resultLabel.ForeColor = Color.Red;
                        resultPicBox.Visible = true;
                        resultPicLabel.Visible = true;
                        resultPicBox.Image = Image.FromFile("clueless.png");
                        resultPicBox.SizeMode = PictureBoxSizeMode.Zoom;
                        resultPicLabel.Text = "\"You're a virgin who can't drive.\"";
                        resultPicLabel.ForeColor = Color.Red; 
                    }
                }

            }
            catch (FileNotFoundException)
            {
                MessageBox.Show("File does not exist. Check your spelling and try again!");
            }
        }

        private void clearButton_Click(object sender, EventArgs e)
        {
            nameBox.Clear();
            nameBox.Focus();
            resultLabel.Text = string.Empty;
            correctLabel.Text = string.Empty;
            incorrectLabel.Text = string.Empty ;
            missedLabel.Text = string.Empty ;
            resultPicBox.Visible= false;
            resultPicLabel.Visible= false;
        }
    }
}
