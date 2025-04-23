namespace Horror_Draft
{
    partial class Welcome
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Welcome));
            this.draftButton = new System.Windows.Forms.Button();
            this.weekBox = new System.Windows.Forms.ComboBox();
            this.scoreLabel = new System.Windows.Forms.Label();
            this.gingerBox = new System.Windows.Forms.ListBox();
            this.titBox = new System.Windows.Forms.ListBox();
            this.childrenBox = new System.Windows.Forms.ListBox();
            this.seasonBox = new System.Windows.Forms.ListBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.horrorBox = new System.Windows.Forms.ListBox();
            this.groupBox5 = new System.Windows.Forms.GroupBox();
            this.teamButton = new System.Windows.Forms.Button();
            this.backButton = new System.Windows.Forms.Button();
            this.rulesButton = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.groupBox5.SuspendLayout();
            this.SuspendLayout();
            // 
            // draftButton
            // 
            this.draftButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.draftButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.draftButton.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.draftButton.Location = new System.Drawing.Point(606, 660);
            this.draftButton.Name = "draftButton";
            this.draftButton.Size = new System.Drawing.Size(174, 43);
            this.draftButton.TabIndex = 1;
            this.draftButton.Text = "Go to Draft!";
            this.draftButton.UseVisualStyleBackColor = false;
            this.draftButton.Click += new System.EventHandler(this.draftButton_Click);
            // 
            // weekBox
            // 
            this.weekBox.Font = new System.Drawing.Font("Chiller", 27.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.weekBox.FormattingEnabled = true;
            this.weekBox.Items.AddRange(new object[] {
            "WEEK 1: \"Solo Movies\" - No Sequels/Prequels",
            "WEEK 2: \"The Roaring 20s\" - Only Movies from 2020s",
            "WEEK 3: \"Fucked Up Mountain People\" - Rednecks and Such",
            "WEEK 4: \"Holiday Kill-tacular!\" - Holiday Themed movies ONLY."});
            this.weekBox.Location = new System.Drawing.Point(133, 78);
            this.weekBox.Name = "weekBox";
            this.weekBox.Size = new System.Drawing.Size(922, 51);
            this.weekBox.TabIndex = 7;
            this.weekBox.SelectedIndexChanged += new System.EventHandler(this.weekBox_SelectedIndexChanged);
            // 
            // scoreLabel
            // 
            this.scoreLabel.Font = new System.Drawing.Font("Chiller", 27.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.scoreLabel.Location = new System.Drawing.Point(370, 31);
            this.scoreLabel.Name = "scoreLabel";
            this.scoreLabel.Size = new System.Drawing.Size(448, 44);
            this.scoreLabel.TabIndex = 8;
            this.scoreLabel.Text = "See Current Season Weekly Scores:";
            // 
            // gingerBox
            // 
            this.gingerBox.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.gingerBox.FormattingEnabled = true;
            this.gingerBox.ItemHeight = 31;
            this.gingerBox.Location = new System.Drawing.Point(6, 38);
            this.gingerBox.Name = "gingerBox";
            this.gingerBox.Size = new System.Drawing.Size(369, 159);
            this.gingerBox.TabIndex = 9;
            // 
            // titBox
            // 
            this.titBox.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.titBox.FormattingEnabled = true;
            this.titBox.ItemHeight = 31;
            this.titBox.Location = new System.Drawing.Point(6, 38);
            this.titBox.Name = "titBox";
            this.titBox.Size = new System.Drawing.Size(369, 159);
            this.titBox.TabIndex = 10;
            // 
            // childrenBox
            // 
            this.childrenBox.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.childrenBox.FormattingEnabled = true;
            this.childrenBox.ItemHeight = 31;
            this.childrenBox.Location = new System.Drawing.Point(6, 38);
            this.childrenBox.Name = "childrenBox";
            this.childrenBox.Size = new System.Drawing.Size(369, 159);
            this.childrenBox.TabIndex = 12;
            // 
            // seasonBox
            // 
            this.seasonBox.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.seasonBox.FormattingEnabled = true;
            this.seasonBox.ItemHeight = 31;
            this.seasonBox.Location = new System.Drawing.Point(6, 38);
            this.seasonBox.Name = "seasonBox";
            this.seasonBox.Size = new System.Drawing.Size(369, 159);
            this.seasonBox.TabIndex = 13;
            // 
            // groupBox1
            // 
            this.groupBox1.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.groupBox1.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("groupBox1.BackgroundImage")));
            this.groupBox1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.groupBox1.Controls.Add(this.gingerBox);
            this.groupBox1.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox1.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.groupBox1.Location = new System.Drawing.Point(91, 182);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(381, 204);
            this.groupBox1.TabIndex = 14;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Ginger Snaps Balls";
            // 
            // groupBox2
            // 
            this.groupBox2.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.groupBox2.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("groupBox2.BackgroundImage")));
            this.groupBox2.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.groupBox2.Controls.Add(this.titBox);
            this.groupBox2.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox2.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.groupBox2.Location = new System.Drawing.Point(695, 182);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(381, 204);
            this.groupBox2.TabIndex = 15;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Tit Follows";
            // 
            // groupBox3
            // 
            this.groupBox3.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.groupBox3.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("groupBox3.BackgroundImage")));
            this.groupBox3.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.groupBox3.Controls.Add(this.seasonBox);
            this.groupBox3.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox3.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.groupBox3.Location = new System.Drawing.Point(797, 432);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(381, 204);
            this.groupBox3.TabIndex = 16;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Season of the Bitch";
            // 
            // groupBox4
            // 
            this.groupBox4.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.groupBox4.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("groupBox4.BackgroundImage")));
            this.groupBox4.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.groupBox4.Controls.Add(this.horrorBox);
            this.groupBox4.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.groupBox4.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox4.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.groupBox4.Location = new System.Drawing.Point(3, 432);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(381, 204);
            this.groupBox4.TabIndex = 17;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "Horror Boobs";
            // 
            // horrorBox
            // 
            this.horrorBox.Font = new System.Drawing.Font("Chiller", 20.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.horrorBox.FormattingEnabled = true;
            this.horrorBox.ItemHeight = 31;
            this.horrorBox.Location = new System.Drawing.Point(6, 38);
            this.horrorBox.Name = "horrorBox";
            this.horrorBox.Size = new System.Drawing.Size(369, 159);
            this.horrorBox.TabIndex = 11;
            // 
            // groupBox5
            // 
            this.groupBox5.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("groupBox5.BackgroundImage")));
            this.groupBox5.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.groupBox5.Controls.Add(this.childrenBox);
            this.groupBox5.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.groupBox5.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox5.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.groupBox5.Location = new System.Drawing.Point(399, 432);
            this.groupBox5.Name = "groupBox5";
            this.groupBox5.Size = new System.Drawing.Size(381, 204);
            this.groupBox5.TabIndex = 18;
            this.groupBox5.TabStop = false;
            this.groupBox5.Text = "Children of the Candy Corn";
            // 
            // teamButton
            // 
            this.teamButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.teamButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.teamButton.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.teamButton.Location = new System.Drawing.Point(399, 660);
            this.teamButton.Name = "teamButton";
            this.teamButton.Size = new System.Drawing.Size(174, 43);
            this.teamButton.TabIndex = 19;
            this.teamButton.Text = "Go to Team Page!";
            this.teamButton.UseVisualStyleBackColor = false;
            this.teamButton.Click += new System.EventHandler(this.teamButton_Click);
            // 
            // backButton
            // 
            this.backButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.backButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.backButton.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.backButton.Location = new System.Drawing.Point(49, 660);
            this.backButton.Name = "backButton";
            this.backButton.Size = new System.Drawing.Size(115, 43);
            this.backButton.TabIndex = 20;
            this.backButton.Text = "BACK!";
            this.backButton.UseVisualStyleBackColor = false;
            this.backButton.Click += new System.EventHandler(this.backButton_Click);
            // 
            // rulesButton
            // 
            this.rulesButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.rulesButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.rulesButton.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.rulesButton.Location = new System.Drawing.Point(1030, 660);
            this.rulesButton.Name = "rulesButton";
            this.rulesButton.Size = new System.Drawing.Size(115, 43);
            this.rulesButton.TabIndex = 21;
            this.rulesButton.Text = "RULES!";
            this.rulesButton.UseVisualStyleBackColor = false;
            this.rulesButton.Click += new System.EventHandler(this.rulesButton_Click);
            // 
            // Welcome
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1179, 736);
            this.Controls.Add(this.rulesButton);
            this.Controls.Add(this.backButton);
            this.Controls.Add(this.teamButton);
            this.Controls.Add(this.groupBox5);
            this.Controls.Add(this.groupBox4);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.scoreLabel);
            this.Controls.Add(this.weekBox);
            this.Controls.Add(this.draftButton);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "Welcome";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Welcome";
            this.groupBox1.ResumeLayout(false);
            this.groupBox2.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.groupBox4.ResumeLayout(false);
            this.groupBox5.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Button draftButton;
        private System.Windows.Forms.ComboBox weekBox;
        private System.Windows.Forms.Label scoreLabel;
        private System.Windows.Forms.ListBox gingerBox;
        private System.Windows.Forms.ListBox titBox;
        private System.Windows.Forms.ListBox childrenBox;
        private System.Windows.Forms.ListBox seasonBox;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.GroupBox groupBox5;
        private System.Windows.Forms.Button teamButton;
        private System.Windows.Forms.ListBox horrorBox;
        private System.Windows.Forms.Button backButton;
        private System.Windows.Forms.Button rulesButton;
    }
}