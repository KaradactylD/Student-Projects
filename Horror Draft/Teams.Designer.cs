namespace Horror_Draft
{
    partial class Teams
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Teams));
            this.teamLabel = new System.Windows.Forms.Label();
            this.horrorDraftPracticeDataSet = new Horror_Draft.HorrorDraftPracticeDataSet();
            this.sheet1BindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sheet1TableAdapter = new Horror_Draft.HorrorDraftPracticeDataSetTableAdapters.Sheet1TableAdapter();
            this.picksBox = new System.Windows.Forms.ListBox();
            this.moviesLabel = new System.Windows.Forms.Label();
            this.totalLabel = new System.Windows.Forms.Label();
            this.highestLabel = new System.Windows.Forms.Label();
            this.lowestLabel = new System.Windows.Forms.Label();
            this.overallLabel = new System.Windows.Forms.Label();
            this.backButton = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.scoreBox = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.horrorDraftPracticeDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sheet1BindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // teamLabel
            // 
            this.teamLabel.Font = new System.Drawing.Font("Chiller", 48F, ((System.Drawing.FontStyle)(((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Italic) 
                | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.teamLabel.ForeColor = System.Drawing.Color.Snow;
            this.teamLabel.Location = new System.Drawing.Point(12, 9);
            this.teamLabel.Name = "teamLabel";
            this.teamLabel.Size = new System.Drawing.Size(1093, 134);
            this.teamLabel.TabIndex = 0;
            this.teamLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // horrorDraftPracticeDataSet
            // 
            this.horrorDraftPracticeDataSet.DataSetName = "HorrorDraftPracticeDataSet";
            this.horrorDraftPracticeDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // sheet1BindingSource
            // 
            this.sheet1BindingSource.DataMember = "Sheet1";
            this.sheet1BindingSource.DataSource = this.horrorDraftPracticeDataSet;
            // 
            // sheet1TableAdapter
            // 
            this.sheet1TableAdapter.ClearBeforeFill = true;
            // 
            // picksBox
            // 
            this.picksBox.BackColor = System.Drawing.SystemColors.MenuText;
            this.picksBox.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.picksBox.ForeColor = System.Drawing.Color.Snow;
            this.picksBox.FormattingEnabled = true;
            this.picksBox.ItemHeight = 27;
            this.picksBox.Location = new System.Drawing.Point(36, 199);
            this.picksBox.Name = "picksBox";
            this.picksBox.Size = new System.Drawing.Size(483, 382);
            this.picksBox.TabIndex = 1;
            // 
            // moviesLabel
            // 
            this.moviesLabel.Font = new System.Drawing.Font("Chiller", 24F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.moviesLabel.ForeColor = System.Drawing.Color.Snow;
            this.moviesLabel.Location = new System.Drawing.Point(139, 153);
            this.moviesLabel.Name = "moviesLabel";
            this.moviesLabel.Size = new System.Drawing.Size(258, 43);
            this.moviesLabel.TabIndex = 2;
            this.moviesLabel.Text = "Movies Scored So Far:";
            // 
            // totalLabel
            // 
            this.totalLabel.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalLabel.ForeColor = System.Drawing.Color.Snow;
            this.totalLabel.Location = new System.Drawing.Point(578, 241);
            this.totalLabel.Name = "totalLabel";
            this.totalLabel.Size = new System.Drawing.Size(481, 62);
            this.totalLabel.TabIndex = 3;
            // 
            // highestLabel
            // 
            this.highestLabel.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.highestLabel.ForeColor = System.Drawing.Color.Snow;
            this.highestLabel.Location = new System.Drawing.Point(578, 321);
            this.highestLabel.Name = "highestLabel";
            this.highestLabel.Size = new System.Drawing.Size(527, 62);
            this.highestLabel.TabIndex = 4;
            // 
            // lowestLabel
            // 
            this.lowestLabel.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lowestLabel.ForeColor = System.Drawing.Color.Snow;
            this.lowestLabel.Location = new System.Drawing.Point(578, 419);
            this.lowestLabel.Name = "lowestLabel";
            this.lowestLabel.Size = new System.Drawing.Size(481, 62);
            this.lowestLabel.TabIndex = 5;
            // 
            // overallLabel
            // 
            this.overallLabel.Font = new System.Drawing.Font("Chiller", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.overallLabel.ForeColor = System.Drawing.Color.Snow;
            this.overallLabel.Location = new System.Drawing.Point(578, 516);
            this.overallLabel.Name = "overallLabel";
            this.overallLabel.Size = new System.Drawing.Size(481, 62);
            this.overallLabel.TabIndex = 6;
            // 
            // backButton
            // 
            this.backButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.backButton.Font = new System.Drawing.Font("Chiller", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.backButton.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.backButton.Location = new System.Drawing.Point(944, 608);
            this.backButton.Name = "backButton";
            this.backButton.Size = new System.Drawing.Size(115, 43);
            this.backButton.TabIndex = 21;
            this.backButton.Text = "BACK!";
            this.backButton.UseVisualStyleBackColor = false;
            this.backButton.Click += new System.EventHandler(this.backButton_Click);
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.button1.Font = new System.Drawing.Font("Chiller", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.ForeColor = System.Drawing.Color.White;
            this.button1.Location = new System.Drawing.Point(57, 597);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(127, 33);
            this.button1.TabIndex = 22;
            this.button1.Text = "Enter Score:";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // scoreBox
            // 
            this.scoreBox.BackColor = System.Drawing.SystemColors.InactiveCaptionText;
            this.scoreBox.Font = new System.Drawing.Font("Chiller", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.scoreBox.ForeColor = System.Drawing.Color.White;
            this.scoreBox.Location = new System.Drawing.Point(190, 598);
            this.scoreBox.Name = "scoreBox";
            this.scoreBox.Size = new System.Drawing.Size(223, 32);
            this.scoreBox.TabIndex = 23;
            // 
            // Teams
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1117, 663);
            this.Controls.Add(this.scoreBox);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.backButton);
            this.Controls.Add(this.overallLabel);
            this.Controls.Add(this.lowestLabel);
            this.Controls.Add(this.highestLabel);
            this.Controls.Add(this.totalLabel);
            this.Controls.Add(this.moviesLabel);
            this.Controls.Add(this.picksBox);
            this.Controls.Add(this.teamLabel);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "Teams";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Teams";
            this.Load += new System.EventHandler(this.Teams_Load);
            ((System.ComponentModel.ISupportInitialize)(this.horrorDraftPracticeDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sheet1BindingSource)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label teamLabel;
        private HorrorDraftPracticeDataSet horrorDraftPracticeDataSet;
        private System.Windows.Forms.BindingSource sheet1BindingSource;
        private HorrorDraftPracticeDataSetTableAdapters.Sheet1TableAdapter sheet1TableAdapter;
        private System.Windows.Forms.ListBox picksBox;
        private System.Windows.Forms.Label moviesLabel;
        private System.Windows.Forms.Label totalLabel;
        private System.Windows.Forms.Label highestLabel;
        private System.Windows.Forms.Label lowestLabel;
        private System.Windows.Forms.Label overallLabel;
        private System.Windows.Forms.Button backButton;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox scoreBox;
    }
}