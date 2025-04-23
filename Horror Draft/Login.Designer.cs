namespace Horror_Draft
{
    partial class Login
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Login));
            this.gingerButton = new System.Windows.Forms.Button();
            this.titButton = new System.Windows.Forms.Button();
            this.horrorButton = new System.Windows.Forms.Button();
            this.childrenButton = new System.Windows.Forms.Button();
            this.seasonButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // gingerButton
            // 
            this.gingerButton.Location = new System.Drawing.Point(431, 389);
            this.gingerButton.Name = "gingerButton";
            this.gingerButton.Size = new System.Drawing.Size(230, 42);
            this.gingerButton.TabIndex = 2;
            this.gingerButton.UseVisualStyleBackColor = true;
            this.gingerButton.Click += new System.EventHandler(this.gingerButton_Click);
            // 
            // titButton
            // 
            this.titButton.Location = new System.Drawing.Point(49, 171);
            this.titButton.Name = "titButton";
            this.titButton.Size = new System.Drawing.Size(169, 46);
            this.titButton.TabIndex = 3;
            this.titButton.UseVisualStyleBackColor = true;
            this.titButton.Click += new System.EventHandler(this.titButton_Click);
            // 
            // horrorButton
            // 
            this.horrorButton.Location = new System.Drawing.Point(809, 171);
            this.horrorButton.Name = "horrorButton";
            this.horrorButton.Size = new System.Drawing.Size(184, 46);
            this.horrorButton.TabIndex = 4;
            this.horrorButton.UseVisualStyleBackColor = true;
            this.horrorButton.Click += new System.EventHandler(this.horrorButton_Click);
            // 
            // childrenButton
            // 
            this.childrenButton.Location = new System.Drawing.Point(1, 534);
            this.childrenButton.Name = "childrenButton";
            this.childrenButton.Size = new System.Drawing.Size(347, 46);
            this.childrenButton.TabIndex = 5;
            this.childrenButton.UseVisualStyleBackColor = true;
            this.childrenButton.Click += new System.EventHandler(this.childrenButton_Click);
            // 
            // seasonButton
            // 
            this.seasonButton.Location = new System.Drawing.Point(809, 534);
            this.seasonButton.Name = "seasonButton";
            this.seasonButton.Size = new System.Drawing.Size(260, 37);
            this.seasonButton.TabIndex = 6;
            this.seasonButton.UseVisualStyleBackColor = true;
            this.seasonButton.Click += new System.EventHandler(this.seasonButton_Click);
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1117, 663);
            this.Controls.Add(this.seasonButton);
            this.Controls.Add(this.childrenButton);
            this.Controls.Add(this.horrorButton);
            this.Controls.Add(this.titButton);
            this.Controls.Add(this.gingerButton);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "Login";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Login";
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Button gingerButton;
        private System.Windows.Forms.Button titButton;
        private System.Windows.Forms.Button horrorButton;
        private System.Windows.Forms.Button childrenButton;
        private System.Windows.Forms.Button seasonButton;
    }
}