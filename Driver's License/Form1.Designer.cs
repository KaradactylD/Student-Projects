namespace Kara_Crumpton___CPT_185___Lab_6___Driver_s_License
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.titleLabel = new System.Windows.Forms.Label();
            this.nameDescLabel = new System.Windows.Forms.Label();
            this.nameBox = new System.Windows.Forms.TextBox();
            this.scoreButton = new System.Windows.Forms.Button();
            this.resultDescLabel = new System.Windows.Forms.Label();
            this.resultLabel = new System.Windows.Forms.Label();
            this.correctDescLabel = new System.Windows.Forms.Label();
            this.correctLabel = new System.Windows.Forms.Label();
            this.incorrectDescLabel = new System.Windows.Forms.Label();
            this.incorrectLabel = new System.Windows.Forms.Label();
            this.missedDescLabel = new System.Windows.Forms.Label();
            this.missedLabel = new System.Windows.Forms.Label();
            this.clearButton = new System.Windows.Forms.Button();
            this.exitButton = new System.Windows.Forms.Button();
            this.resultPicBox = new System.Windows.Forms.PictureBox();
            this.resultPicLabel = new System.Windows.Forms.Label();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.resultPicBox)).BeginInit();
            this.SuspendLayout();
            // 
            // titleLabel
            // 
            this.titleLabel.AutoSize = true;
            this.titleLabel.Font = new System.Drawing.Font("Microsoft YaHei", 48F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.titleLabel.Location = new System.Drawing.Point(12, 19);
            this.titleLabel.Name = "titleLabel";
            this.titleLabel.Size = new System.Drawing.Size(733, 86);
            this.titleLabel.TabIndex = 0;
            this.titleLabel.Text = "Driver\'s License Exam";
            this.toolTip1.SetToolTip(this.titleLabel, "Kara Crumpton - Lab 6: Driver\'s License Exam");
            // 
            // nameDescLabel
            // 
            this.nameDescLabel.AutoSize = true;
            this.nameDescLabel.Font = new System.Drawing.Font("Microsoft YaHei", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nameDescLabel.Location = new System.Drawing.Point(28, 152);
            this.nameDescLabel.Name = "nameDescLabel";
            this.nameDescLabel.Size = new System.Drawing.Size(271, 26);
            this.nameDescLabel.TabIndex = 1;
            this.nameDescLabel.Text = "Please enter student name:";
            this.toolTip1.SetToolTip(this.nameDescLabel, "Enter the student\'s name here.");
            // 
            // nameBox
            // 
            this.nameBox.Location = new System.Drawing.Point(322, 152);
            this.nameBox.Name = "nameBox";
            this.nameBox.Size = new System.Drawing.Size(175, 29);
            this.nameBox.TabIndex = 0;
            // 
            // scoreButton
            // 
            this.scoreButton.Location = new System.Drawing.Point(322, 205);
            this.scoreButton.Name = "scoreButton";
            this.scoreButton.Size = new System.Drawing.Size(132, 50);
            this.scoreButton.TabIndex = 1;
            this.scoreButton.Text = "Get &Score!";
            this.toolTip1.SetToolTip(this.scoreButton, "Click to see the student\'s results.");
            this.scoreButton.UseVisualStyleBackColor = true;
            this.scoreButton.Click += new System.EventHandler(this.scoreButton_Click);
            // 
            // resultDescLabel
            // 
            this.resultDescLabel.AutoSize = true;
            this.resultDescLabel.Font = new System.Drawing.Font("Microsoft YaHei", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.resultDescLabel.Location = new System.Drawing.Point(552, 138);
            this.resultDescLabel.Name = "resultDescLabel";
            this.resultDescLabel.Size = new System.Drawing.Size(76, 26);
            this.resultDescLabel.TabIndex = 4;
            this.resultDescLabel.Text = "Result:";
            this.toolTip1.SetToolTip(this.resultDescLabel, "Shows if the student Passed or Failed.");
            // 
            // resultLabel
            // 
            this.resultLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.resultLabel.Font = new System.Drawing.Font("Microsoft YaHei", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.resultLabel.Location = new System.Drawing.Point(557, 164);
            this.resultLabel.Name = "resultLabel";
            this.resultLabel.Size = new System.Drawing.Size(270, 106);
            this.resultLabel.TabIndex = 5;
            this.resultLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // correctDescLabel
            // 
            this.correctDescLabel.AutoSize = true;
            this.correctDescLabel.Font = new System.Drawing.Font("Microsoft YaHei", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.correctDescLabel.Location = new System.Drawing.Point(28, 358);
            this.correctDescLabel.Name = "correctDescLabel";
            this.correctDescLabel.Size = new System.Drawing.Size(87, 26);
            this.correctDescLabel.TabIndex = 6;
            this.correctDescLabel.Text = "Correct:";
            this.toolTip1.SetToolTip(this.correctDescLabel, "Shows number of correct answers.");
            // 
            // correctLabel
            // 
            this.correctLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.correctLabel.Location = new System.Drawing.Point(27, 395);
            this.correctLabel.Name = "correctLabel";
            this.correctLabel.Size = new System.Drawing.Size(109, 115);
            this.correctLabel.TabIndex = 7;
            // 
            // incorrectDescLabel
            // 
            this.incorrectDescLabel.AutoSize = true;
            this.incorrectDescLabel.Font = new System.Drawing.Font("Microsoft YaHei", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.incorrectDescLabel.Location = new System.Drawing.Point(162, 358);
            this.incorrectDescLabel.Name = "incorrectDescLabel";
            this.incorrectDescLabel.Size = new System.Drawing.Size(102, 26);
            this.incorrectDescLabel.TabIndex = 8;
            this.incorrectDescLabel.Text = "Incorrect:";
            this.toolTip1.SetToolTip(this.incorrectDescLabel, "Shows number of incorrect answers.");
            // 
            // incorrectLabel
            // 
            this.incorrectLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.incorrectLabel.Location = new System.Drawing.Point(167, 395);
            this.incorrectLabel.Name = "incorrectLabel";
            this.incorrectLabel.Size = new System.Drawing.Size(120, 115);
            this.incorrectLabel.TabIndex = 9;
            // 
            // missedDescLabel
            // 
            this.missedDescLabel.AutoSize = true;
            this.missedDescLabel.Font = new System.Drawing.Font("Microsoft YaHei", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.missedDescLabel.Location = new System.Drawing.Point(317, 358);
            this.missedDescLabel.Name = "missedDescLabel";
            this.missedDescLabel.Size = new System.Drawing.Size(85, 26);
            this.missedDescLabel.TabIndex = 10;
            this.missedDescLabel.Text = "Missed:";
            this.toolTip1.SetToolTip(this.missedDescLabel, "Shows the question numbers that were wrong.");
            // 
            // missedLabel
            // 
            this.missedLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.missedLabel.Location = new System.Drawing.Point(322, 395);
            this.missedLabel.Name = "missedLabel";
            this.missedLabel.Size = new System.Drawing.Size(120, 115);
            this.missedLabel.TabIndex = 11;
            // 
            // clearButton
            // 
            this.clearButton.Location = new System.Drawing.Point(557, 273);
            this.clearButton.Name = "clearButton";
            this.clearButton.Size = new System.Drawing.Size(132, 50);
            this.clearButton.TabIndex = 2;
            this.clearButton.Text = "&Clear!";
            this.toolTip1.SetToolTip(this.clearButton, "Click to clear.");
            this.clearButton.UseVisualStyleBackColor = true;
            this.clearButton.Click += new System.EventHandler(this.clearButton_Click);
            // 
            // exitButton
            // 
            this.exitButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.exitButton.Location = new System.Drawing.Point(695, 273);
            this.exitButton.Name = "exitButton";
            this.exitButton.Size = new System.Drawing.Size(132, 50);
            this.exitButton.TabIndex = 3;
            this.exitButton.Text = "&Exit!";
            this.toolTip1.SetToolTip(this.exitButton, "Click to exit the program.");
            this.exitButton.UseVisualStyleBackColor = true;
            this.exitButton.Click += new System.EventHandler(this.exitButton_Click);
            // 
            // resultPicBox
            // 
            this.resultPicBox.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.resultPicBox.Location = new System.Drawing.Point(542, 358);
            this.resultPicBox.Name = "resultPicBox";
            this.resultPicBox.Size = new System.Drawing.Size(314, 147);
            this.resultPicBox.TabIndex = 14;
            this.resultPicBox.TabStop = false;
            // 
            // resultPicLabel
            // 
            this.resultPicLabel.Font = new System.Drawing.Font("Microsoft YaHei", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.resultPicLabel.Location = new System.Drawing.Point(516, 508);
            this.resultPicLabel.Name = "resultPicLabel";
            this.resultPicLabel.Size = new System.Drawing.Size(376, 49);
            this.resultPicLabel.TabIndex = 15;
            this.resultPicLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Form1
            // 
            this.AcceptButton = this.scoreButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.OldLace;
            this.CancelButton = this.exitButton;
            this.ClientSize = new System.Drawing.Size(913, 598);
            this.Controls.Add(this.resultPicLabel);
            this.Controls.Add(this.resultPicBox);
            this.Controls.Add(this.exitButton);
            this.Controls.Add(this.clearButton);
            this.Controls.Add(this.missedLabel);
            this.Controls.Add(this.missedDescLabel);
            this.Controls.Add(this.incorrectLabel);
            this.Controls.Add(this.incorrectDescLabel);
            this.Controls.Add(this.correctLabel);
            this.Controls.Add(this.correctDescLabel);
            this.Controls.Add(this.resultLabel);
            this.Controls.Add(this.resultDescLabel);
            this.Controls.Add(this.scoreButton);
            this.Controls.Add(this.nameBox);
            this.Controls.Add(this.nameDescLabel);
            this.Controls.Add(this.titleLabel);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Kara Crumpton - Lab 6: Driver\'s License";
            ((System.ComponentModel.ISupportInitialize)(this.resultPicBox)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label titleLabel;
        private System.Windows.Forms.Label nameDescLabel;
        private System.Windows.Forms.TextBox nameBox;
        private System.Windows.Forms.Button scoreButton;
        private System.Windows.Forms.Label resultDescLabel;
        private System.Windows.Forms.Label resultLabel;
        private System.Windows.Forms.Label correctDescLabel;
        private System.Windows.Forms.Label correctLabel;
        private System.Windows.Forms.Label incorrectDescLabel;
        private System.Windows.Forms.Label incorrectLabel;
        private System.Windows.Forms.Label missedDescLabel;
        private System.Windows.Forms.Label missedLabel;
        private System.Windows.Forms.Button clearButton;
        private System.Windows.Forms.Button exitButton;
        private System.Windows.Forms.PictureBox resultPicBox;
        private System.Windows.Forms.Label resultPicLabel;
        private System.Windows.Forms.ToolTip toolTip1;
    }
}

