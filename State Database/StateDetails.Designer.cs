namespace KCrumpton_CPT_206___Lab_3
{
    partial class StateDetails
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
            this.nameLabel = new System.Windows.Forms.Label();
            this.popLabel = new System.Windows.Forms.Label();
            this.capLabel = new System.Windows.Forms.Label();
            this.birdLabel = new System.Windows.Forms.Label();
            this.flowerLabel = new System.Windows.Forms.Label();
            this.turtleLabel = new System.Windows.Forms.Label();
            this.cityLabel = new System.Windows.Forms.Label();
            this.flagBox = new System.Windows.Forms.PictureBox();
            this.label1 = new System.Windows.Forms.Label();
            this.backBtn = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.bindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            this.incomeLabel = new System.Windows.Forms.Label();
            this.jobsLabel = new System.Windows.Forms.Label();
            this.nextBtn = new System.Windows.Forms.Button();
            this.prevBtn = new System.Windows.Forms.Button();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.flagBox)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).BeginInit();
            this.SuspendLayout();
            // 
            // nameLabel
            // 
            this.nameLabel.BackColor = System.Drawing.Color.MediumSeaGreen;
            this.nameLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.nameLabel.Font = new System.Drawing.Font("Garamond", 54.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nameLabel.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.nameLabel.Location = new System.Drawing.Point(12, 9);
            this.nameLabel.Name = "nameLabel";
            this.nameLabel.Size = new System.Drawing.Size(986, 86);
            this.nameLabel.TabIndex = 2;
            this.nameLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.nameLabel, "Current State");
            // 
            // popLabel
            // 
            this.popLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.popLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.popLabel.Location = new System.Drawing.Point(233, 120);
            this.popLabel.Name = "popLabel";
            this.popLabel.Size = new System.Drawing.Size(208, 87);
            this.popLabel.TabIndex = 3;
            this.popLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.popLabel, "State Population");
            // 
            // capLabel
            // 
            this.capLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.capLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.capLabel.Location = new System.Drawing.Point(505, 120);
            this.capLabel.Name = "capLabel";
            this.capLabel.Size = new System.Drawing.Size(208, 87);
            this.capLabel.TabIndex = 4;
            this.capLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.capLabel, "State Capital");
            // 
            // birdLabel
            // 
            this.birdLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.birdLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.birdLabel.Location = new System.Drawing.Point(551, 354);
            this.birdLabel.Name = "birdLabel";
            this.birdLabel.Size = new System.Drawing.Size(187, 115);
            this.birdLabel.TabIndex = 5;
            this.birdLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.birdLabel, "State Bird");
            // 
            // flowerLabel
            // 
            this.flowerLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.flowerLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.flowerLabel.Location = new System.Drawing.Point(770, 354);
            this.flowerLabel.Name = "flowerLabel";
            this.flowerLabel.Size = new System.Drawing.Size(203, 115);
            this.flowerLabel.TabIndex = 6;
            this.flowerLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.flowerLabel, "State Flower");
            // 
            // turtleLabel
            // 
            this.turtleLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.turtleLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.turtleLabel.Location = new System.Drawing.Point(302, 354);
            this.turtleLabel.Name = "turtleLabel";
            this.turtleLabel.Size = new System.Drawing.Size(219, 115);
            this.turtleLabel.TabIndex = 9;
            this.turtleLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.turtleLabel, "Common Turtles in State");
            // 
            // cityLabel
            // 
            this.cityLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.cityLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cityLabel.Location = new System.Drawing.Point(765, 120);
            this.cityLabel.Name = "cityLabel";
            this.cityLabel.Size = new System.Drawing.Size(208, 87);
            this.cityLabel.TabIndex = 11;
            this.cityLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.cityLabel, "Largest City in State");
            // 
            // flagBox
            // 
            this.flagBox.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.flagBox.Location = new System.Drawing.Point(12, 240);
            this.flagBox.Name = "flagBox";
            this.flagBox.Size = new System.Drawing.Size(273, 179);
            this.flagBox.TabIndex = 12;
            this.flagBox.TabStop = false;
            this.toolTip1.SetToolTip(this.flagBox, "pic from Wikipedia");
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 215);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(96, 22);
            this.label1.TabIndex = 13;
            this.label1.Text = "State Flag:";
            // 
            // backBtn
            // 
            this.backBtn.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.backBtn.Location = new System.Drawing.Point(12, 513);
            this.backBtn.Name = "backBtn";
            this.backBtn.Size = new System.Drawing.Size(96, 41);
            this.backBtn.TabIndex = 14;
            this.backBtn.Text = "Back";
            this.toolTip1.SetToolTip(this.backBtn, "Click to go back!");
            this.backBtn.UseVisualStyleBackColor = true;
            this.backBtn.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button2.Location = new System.Drawing.Point(843, 32);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(130, 41);
            this.button2.TabIndex = 15;
            this.button2.Text = "Table View";
            this.toolTip1.SetToolTip(this.button2, "Click to view Table!");
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // incomeLabel
            // 
            this.incomeLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.incomeLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.incomeLabel.Location = new System.Drawing.Point(384, 235);
            this.incomeLabel.Name = "incomeLabel";
            this.incomeLabel.Size = new System.Drawing.Size(208, 87);
            this.incomeLabel.TabIndex = 16;
            this.incomeLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.incomeLabel, "State Median Income");
            // 
            // jobsLabel
            // 
            this.jobsLabel.BackColor = System.Drawing.Color.DarkKhaki;
            this.jobsLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.jobsLabel.Location = new System.Drawing.Point(644, 235);
            this.jobsLabel.Name = "jobsLabel";
            this.jobsLabel.Size = new System.Drawing.Size(208, 87);
            this.jobsLabel.TabIndex = 17;
            this.jobsLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.jobsLabel, "State percentage of Computer Jobs");
            // 
            // nextBtn
            // 
            this.nextBtn.Location = new System.Drawing.Point(725, 513);
            this.nextBtn.Name = "nextBtn";
            this.nextBtn.Size = new System.Drawing.Size(96, 41);
            this.nextBtn.TabIndex = 18;
            this.nextBtn.Text = "Next";
            this.toolTip1.SetToolTip(this.nextBtn, "Click for next state");
            this.nextBtn.UseVisualStyleBackColor = true;
            this.nextBtn.Click += new System.EventHandler(this.nextBtn_Click);
            // 
            // prevBtn
            // 
            this.prevBtn.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.prevBtn.Location = new System.Drawing.Point(469, 513);
            this.prevBtn.Name = "prevBtn";
            this.prevBtn.Size = new System.Drawing.Size(96, 41);
            this.prevBtn.TabIndex = 19;
            this.prevBtn.Text = "Previous";
            this.toolTip1.SetToolTip(this.prevBtn, "Click for previous state");
            this.prevBtn.UseVisualStyleBackColor = true;
            this.prevBtn.Click += new System.EventHandler(this.prevBtn_Click);
            // 
            // StateDetails
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightGreen;
            this.CancelButton = this.backBtn;
            this.ClientSize = new System.Drawing.Size(1010, 568);
            this.Controls.Add(this.prevBtn);
            this.Controls.Add(this.nextBtn);
            this.Controls.Add(this.jobsLabel);
            this.Controls.Add(this.incomeLabel);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.backBtn);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.flagBox);
            this.Controls.Add(this.cityLabel);
            this.Controls.Add(this.turtleLabel);
            this.Controls.Add(this.flowerLabel);
            this.Controls.Add(this.birdLabel);
            this.Controls.Add(this.capLabel);
            this.Controls.Add(this.popLabel);
            this.Controls.Add(this.nameLabel);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "StateDetails";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "StateDetails";
            ((System.ComponentModel.ISupportInitialize)(this.flagBox)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label nameLabel;
        private System.Windows.Forms.Label popLabel;
        private System.Windows.Forms.Label capLabel;
        private System.Windows.Forms.Label birdLabel;
        private System.Windows.Forms.Label flowerLabel;
        private System.Windows.Forms.Label turtleLabel;
        private System.Windows.Forms.Label cityLabel;
        private KCrumptonStateInfoDBDataSet kCrumptonStateInfoDBDataSet;
        private System.Windows.Forms.BindingSource bindingSource1;
        private System.Windows.Forms.PictureBox flagBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button backBtn;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Label incomeLabel;
        private System.Windows.Forms.Label jobsLabel;
        private System.Windows.Forms.Button nextBtn;
        private System.Windows.Forms.Button prevBtn;
        private System.Windows.Forms.ToolTip toolTip1;
    }
}