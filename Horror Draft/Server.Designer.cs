namespace Horror_Draft
{
    partial class Server
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Server));
            this.karaButton = new System.Windows.Forms.Button();
            this.notKaraButton = new System.Windows.Forms.Button();
            this.statusLabel = new System.Windows.Forms.Label();
            this.goButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // karaButton
            // 
            this.karaButton.Font = new System.Drawing.Font("Microsoft YaHei", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.karaButton.Location = new System.Drawing.Point(182, 208);
            this.karaButton.Name = "karaButton";
            this.karaButton.Size = new System.Drawing.Size(230, 73);
            this.karaButton.TabIndex = 1;
            this.karaButton.UseVisualStyleBackColor = true;
            this.karaButton.Click += new System.EventHandler(this.karaButton_Click);
            // 
            // notKaraButton
            // 
            this.notKaraButton.Font = new System.Drawing.Font("Microsoft YaHei", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.notKaraButton.Location = new System.Drawing.Point(534, 220);
            this.notKaraButton.Name = "notKaraButton";
            this.notKaraButton.Size = new System.Drawing.Size(340, 71);
            this.notKaraButton.TabIndex = 2;
            this.notKaraButton.UseVisualStyleBackColor = true;
            this.notKaraButton.Click += new System.EventHandler(this.notKaraButton_Click);
            // 
            // statusLabel
            // 
            this.statusLabel.Font = new System.Drawing.Font("Chiller", 27.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.statusLabel.Location = new System.Drawing.Point(205, 372);
            this.statusLabel.Name = "statusLabel";
            this.statusLabel.Size = new System.Drawing.Size(669, 183);
            this.statusLabel.TabIndex = 3;
            this.statusLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // goButton
            // 
            this.goButton.Font = new System.Drawing.Font("Microsoft YaHei", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.goButton.Location = new System.Drawing.Point(459, 576);
            this.goButton.Name = "goButton";
            this.goButton.Size = new System.Drawing.Size(121, 60);
            this.goButton.TabIndex = 4;
            this.goButton.UseVisualStyleBackColor = true;
            this.goButton.Click += new System.EventHandler(this.goButton_Click);
            // 
            // Server
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1117, 663);
            this.Controls.Add(this.goButton);
            this.Controls.Add(this.statusLabel);
            this.Controls.Add(this.notKaraButton);
            this.Controls.Add(this.karaButton);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "Server";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Server";
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Button karaButton;
        private System.Windows.Forms.Button notKaraButton;
        private System.Windows.Forms.Label statusLabel;
        private System.Windows.Forms.Button goButton;
    }
}