namespace Horror_Draft
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.movieBox = new System.Windows.Forms.ListBox();
            this.team1Box = new System.Windows.Forms.ListBox();
            this.team2Box = new System.Windows.Forms.ListBox();
            this.team3Box = new System.Windows.Forms.ListBox();
            this.team4Box = new System.Windows.Forms.ListBox();
            this.team5Box = new System.Windows.Forms.ListBox();
            this.draftButton = new System.Windows.Forms.Button();
            this.undoButton = new System.Windows.Forms.Button();
            this.redoButton = new System.Windows.Forms.Button();
            this.finishedButton = new System.Windows.Forms.Button();
            this.currentDrafter = new System.Windows.Forms.Label();
            this.searchBox = new System.Windows.Forms.TextBox();
            this.exitButton = new System.Windows.Forms.Button();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.searchLabel = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // movieBox
            // 
            this.movieBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.movieBox.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.movieBox.ForeColor = System.Drawing.Color.White;
            this.movieBox.FormattingEnabled = true;
            this.movieBox.ItemHeight = 38;
            this.movieBox.Location = new System.Drawing.Point(48, 28);
            this.movieBox.Name = "movieBox";
            this.movieBox.Size = new System.Drawing.Size(318, 344);
            this.movieBox.TabIndex = 0;
            // 
            // team1Box
            // 
            this.team1Box.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.team1Box.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.team1Box.ForeColor = System.Drawing.Color.White;
            this.team1Box.FormattingEnabled = true;
            this.team1Box.ItemHeight = 31;
            this.team1Box.Location = new System.Drawing.Point(431, 52);
            this.team1Box.Name = "team1Box";
            this.team1Box.Size = new System.Drawing.Size(205, 186);
            this.team1Box.TabIndex = 1;
            // 
            // team2Box
            // 
            this.team2Box.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.team2Box.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.team2Box.ForeColor = System.Drawing.Color.White;
            this.team2Box.FormattingEnabled = true;
            this.team2Box.ItemHeight = 31;
            this.team2Box.Location = new System.Drawing.Point(656, 52);
            this.team2Box.Name = "team2Box";
            this.team2Box.Size = new System.Drawing.Size(205, 186);
            this.team2Box.TabIndex = 2;
            // 
            // team3Box
            // 
            this.team3Box.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.team3Box.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.team3Box.ForeColor = System.Drawing.Color.White;
            this.team3Box.FormattingEnabled = true;
            this.team3Box.ItemHeight = 31;
            this.team3Box.Location = new System.Drawing.Point(889, 52);
            this.team3Box.Name = "team3Box";
            this.team3Box.Size = new System.Drawing.Size(205, 186);
            this.team3Box.TabIndex = 3;
            // 
            // team4Box
            // 
            this.team4Box.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.team4Box.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.team4Box.ForeColor = System.Drawing.Color.White;
            this.team4Box.FormattingEnabled = true;
            this.team4Box.ItemHeight = 31;
            this.team4Box.Location = new System.Drawing.Point(509, 301);
            this.team4Box.Name = "team4Box";
            this.team4Box.Size = new System.Drawing.Size(205, 186);
            this.team4Box.TabIndex = 4;
            // 
            // team5Box
            // 
            this.team5Box.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.team5Box.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.team5Box.ForeColor = System.Drawing.Color.White;
            this.team5Box.FormattingEnabled = true;
            this.team5Box.ItemHeight = 31;
            this.team5Box.Location = new System.Drawing.Point(801, 301);
            this.team5Box.Name = "team5Box";
            this.team5Box.Size = new System.Drawing.Size(205, 186);
            this.team5Box.TabIndex = 5;
            // 
            // draftButton
            // 
            this.draftButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.draftButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.draftButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.draftButton.ForeColor = System.Drawing.Color.White;
            this.draftButton.Location = new System.Drawing.Point(12, 525);
            this.draftButton.Name = "draftButton";
            this.draftButton.Size = new System.Drawing.Size(136, 52);
            this.draftButton.TabIndex = 6;
            this.draftButton.Text = "Draft!";
            this.draftButton.UseVisualStyleBackColor = false;
            this.draftButton.Click += new System.EventHandler(this.buttonDraft_Click);
            // 
            // undoButton
            // 
            this.undoButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.undoButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.undoButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.undoButton.ForeColor = System.Drawing.Color.White;
            this.undoButton.Location = new System.Drawing.Point(179, 525);
            this.undoButton.Name = "undoButton";
            this.undoButton.Size = new System.Drawing.Size(136, 52);
            this.undoButton.TabIndex = 7;
            this.undoButton.Text = "Undo!";
            this.undoButton.UseVisualStyleBackColor = false;
            this.undoButton.Click += new System.EventHandler(this.buttonUndo_Click);
            // 
            // redoButton
            // 
            this.redoButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.redoButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.redoButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.redoButton.ForeColor = System.Drawing.Color.White;
            this.redoButton.Location = new System.Drawing.Point(342, 525);
            this.redoButton.Name = "redoButton";
            this.redoButton.Size = new System.Drawing.Size(136, 52);
            this.redoButton.TabIndex = 8;
            this.redoButton.Text = "Redo!";
            this.redoButton.UseVisualStyleBackColor = false;
            this.redoButton.Click += new System.EventHandler(this.buttonRedo_Click);
            // 
            // finishedButton
            // 
            this.finishedButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.finishedButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.finishedButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.finishedButton.ForeColor = System.Drawing.Color.White;
            this.finishedButton.Location = new System.Drawing.Point(179, 599);
            this.finishedButton.Name = "finishedButton";
            this.finishedButton.Size = new System.Drawing.Size(136, 52);
            this.finishedButton.TabIndex = 9;
            this.finishedButton.Text = "I\'m Finished!";
            this.finishedButton.UseVisualStyleBackColor = false;
            this.finishedButton.Click += new System.EventHandler(this.finishedButton_Click);
            // 
            // currentDrafter
            // 
            this.currentDrafter.Font = new System.Drawing.Font("Chiller", 26.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.currentDrafter.ForeColor = System.Drawing.Color.White;
            this.currentDrafter.Location = new System.Drawing.Point(532, 523);
            this.currentDrafter.Name = "currentDrafter";
            this.currentDrafter.Size = new System.Drawing.Size(498, 126);
            this.currentDrafter.TabIndex = 10;
            this.currentDrafter.Text = "label1";
            this.currentDrafter.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // searchBox
            // 
            this.searchBox.BackColor = System.Drawing.Color.Black;
            this.searchBox.Font = new System.Drawing.Font("Chiller", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchBox.ForeColor = System.Drawing.Color.White;
            this.searchBox.Location = new System.Drawing.Point(65, 454);
            this.searchBox.Name = "searchBox";
            this.searchBox.Size = new System.Drawing.Size(301, 32);
            this.searchBox.TabIndex = 11;
            this.searchBox.TextChanged += new System.EventHandler(this.searchBox_TextChanged);
            // 
            // exitButton
            // 
            this.exitButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.exitButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.exitButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.exitButton.ForeColor = System.Drawing.Color.White;
            this.exitButton.Location = new System.Drawing.Point(12, 599);
            this.exitButton.Name = "exitButton";
            this.exitButton.Size = new System.Drawing.Size(136, 52);
            this.exitButton.TabIndex = 12;
            this.exitButton.Text = "Exit!";
            this.exitButton.UseVisualStyleBackColor = false;
            this.exitButton.Click += new System.EventHandler(this.exitButton_Click);
            // 
            // timer1
            // 
            this.timer1.Tick += new System.EventHandler(this.TurnTimer_Tick);
            // 
            // searchLabel
            // 
            this.searchLabel.AutoSize = true;
            this.searchLabel.Font = new System.Drawing.Font("Chiller", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchLabel.ForeColor = System.Drawing.Color.White;
            this.searchLabel.Location = new System.Drawing.Point(60, 421);
            this.searchLabel.Name = "searchLabel";
            this.searchLabel.Size = new System.Drawing.Size(60, 24);
            this.searchLabel.TabIndex = 13;
            this.searchLabel.Text = "Search:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1117, 663);
            this.Controls.Add(this.searchLabel);
            this.Controls.Add(this.exitButton);
            this.Controls.Add(this.searchBox);
            this.Controls.Add(this.currentDrafter);
            this.Controls.Add(this.finishedButton);
            this.Controls.Add(this.redoButton);
            this.Controls.Add(this.undoButton);
            this.Controls.Add(this.draftButton);
            this.Controls.Add(this.team5Box);
            this.Controls.Add(this.team4Box);
            this.Controls.Add(this.team3Box);
            this.Controls.Add(this.team2Box);
            this.Controls.Add(this.team1Box);
            this.Controls.Add(this.movieBox);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox movieBox;
        private System.Windows.Forms.ListBox team1Box;
        private System.Windows.Forms.ListBox team2Box;
        private System.Windows.Forms.ListBox team3Box;
        private System.Windows.Forms.ListBox team4Box;
        private System.Windows.Forms.ListBox team5Box;
        private System.Windows.Forms.Button draftButton;
        private System.Windows.Forms.Button undoButton;
        private System.Windows.Forms.Button redoButton;
        private System.Windows.Forms.Button finishedButton;
        private System.Windows.Forms.Label currentDrafter;
        private System.Windows.Forms.TextBox searchBox;
        private System.Windows.Forms.Button exitButton;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Label searchLabel;
    }
}

