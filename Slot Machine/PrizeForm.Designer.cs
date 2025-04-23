namespace KCrumpton_CPT_206_Final_Project_Slot_Machine
{
    partial class PrizeForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(PrizeForm));
            this.prizeLabel = new System.Windows.Forms.Label();
            this.prizeBox = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.prizeBox)).BeginInit();
            this.SuspendLayout();
            // 
            // prizeLabel
            // 
            this.prizeLabel.Font = new System.Drawing.Font("Viner Hand ITC", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.prizeLabel.ForeColor = System.Drawing.SystemColors.ButtonHighlight;
            this.prizeLabel.Location = new System.Drawing.Point(125, 27);
            this.prizeLabel.Name = "prizeLabel";
            this.prizeLabel.Size = new System.Drawing.Size(996, 265);
            this.prizeLabel.TabIndex = 6;
            this.prizeLabel.TextAlign = System.Drawing.ContentAlignment.BottomCenter;
            // 
            // prizeBox
            // 
            this.prizeBox.Location = new System.Drawing.Point(457, 295);
            this.prizeBox.Name = "prizeBox";
            this.prizeBox.Size = new System.Drawing.Size(357, 313);
            this.prizeBox.TabIndex = 7;
            this.prizeBox.TabStop = false;
            // 
            // PrizeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1233, 675);
            this.Controls.Add(this.prizeBox);
            this.Controls.Add(this.prizeLabel);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "PrizeForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "PrizeForm";
            this.Load += new System.EventHandler(this.PrizeForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.prizeBox)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label prizeLabel;
        private System.Windows.Forms.PictureBox prizeBox;
    }
}