namespace KCrumpton_CPT_206_Final_Project_Slot_Machine
{
    partial class Create
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Create));
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.userBox = new System.Windows.Forms.TextBox();
            this.emailBox = new System.Windows.Forms.TextBox();
            this.passBox = new System.Windows.Forms.TextBox();
            this.passBox2 = new System.Windows.Forms.TextBox();
            this.createBtn = new System.Windows.Forms.Button();
            this.exitBtn = new System.Windows.Forms.Button();
            this.nameLabel = new System.Windows.Forms.Label();
            this.emailLabel = new System.Windows.Forms.Label();
            this.passLabel = new System.Windows.Forms.Label();
            this.pass2Label = new System.Windows.Forms.Label();
            this.infoLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("pictureBox1.BackgroundImage")));
            this.pictureBox1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.pictureBox1.InitialImage = null;
            this.pictureBox1.Location = new System.Drawing.Point(0, -1);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(727, 678);
            this.pictureBox1.TabIndex = 1;
            this.pictureBox1.TabStop = false;
            // 
            // userBox
            // 
            this.userBox.Location = new System.Drawing.Point(1004, 246);
            this.userBox.Name = "userBox";
            this.userBox.Size = new System.Drawing.Size(166, 29);
            this.userBox.TabIndex = 4;
            // 
            // emailBox
            // 
            this.emailBox.Location = new System.Drawing.Point(1004, 304);
            this.emailBox.Name = "emailBox";
            this.emailBox.Size = new System.Drawing.Size(166, 29);
            this.emailBox.TabIndex = 6;
            // 
            // passBox
            // 
            this.passBox.Location = new System.Drawing.Point(1004, 362);
            this.passBox.Name = "passBox";
            this.passBox.Size = new System.Drawing.Size(166, 29);
            this.passBox.TabIndex = 8;
            this.passBox.UseSystemPasswordChar = true;
            // 
            // passBox2
            // 
            this.passBox2.Location = new System.Drawing.Point(1004, 420);
            this.passBox2.Name = "passBox2";
            this.passBox2.Size = new System.Drawing.Size(166, 29);
            this.passBox2.TabIndex = 10;
            this.passBox2.UseSystemPasswordChar = true;
            // 
            // createBtn
            // 
            this.createBtn.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.createBtn.Font = new System.Drawing.Font("Viner Hand ITC", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.createBtn.Location = new System.Drawing.Point(973, 504);
            this.createBtn.Name = "createBtn";
            this.createBtn.Size = new System.Drawing.Size(197, 40);
            this.createBtn.TabIndex = 11;
            this.createBtn.Text = "Create Account!";
            this.createBtn.UseVisualStyleBackColor = true;
            this.createBtn.Click += new System.EventHandler(this.createBtn_Click);
            // 
            // exitBtn
            // 
            this.exitBtn.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.exitBtn.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.exitBtn.Font = new System.Drawing.Font("Viner Hand ITC", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.exitBtn.Location = new System.Drawing.Point(12, 621);
            this.exitBtn.Name = "exitBtn";
            this.exitBtn.Size = new System.Drawing.Size(119, 42);
            this.exitBtn.TabIndex = 12;
            this.exitBtn.Text = "Back!";
            this.exitBtn.UseVisualStyleBackColor = true;
            this.exitBtn.Click += new System.EventHandler(this.exitBtn_Click);
            // 
            // nameLabel
            // 
            this.nameLabel.AutoSize = true;
            this.nameLabel.Font = new System.Drawing.Font("Viner Hand ITC", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nameLabel.ForeColor = System.Drawing.Color.White;
            this.nameLabel.Location = new System.Drawing.Point(848, 246);
            this.nameLabel.Name = "nameLabel";
            this.nameLabel.Size = new System.Drawing.Size(150, 39);
            this.nameLabel.TabIndex = 13;
            this.nameLabel.Text = "User Name:";
            // 
            // emailLabel
            // 
            this.emailLabel.AutoSize = true;
            this.emailLabel.Font = new System.Drawing.Font("Viner Hand ITC", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.emailLabel.ForeColor = System.Drawing.Color.White;
            this.emailLabel.Location = new System.Drawing.Point(898, 300);
            this.emailLabel.Name = "emailLabel";
            this.emailLabel.Size = new System.Drawing.Size(100, 39);
            this.emailLabel.TabIndex = 14;
            this.emailLabel.Text = "Email:";
            // 
            // passLabel
            // 
            this.passLabel.AutoSize = true;
            this.passLabel.Font = new System.Drawing.Font("Viner Hand ITC", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.passLabel.ForeColor = System.Drawing.Color.White;
            this.passLabel.Location = new System.Drawing.Point(867, 358);
            this.passLabel.Name = "passLabel";
            this.passLabel.Size = new System.Drawing.Size(131, 39);
            this.passLabel.TabIndex = 15;
            this.passLabel.Text = "Password:";
            // 
            // pass2Label
            // 
            this.pass2Label.AutoSize = true;
            this.pass2Label.Font = new System.Drawing.Font("Viner Hand ITC", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.pass2Label.ForeColor = System.Drawing.Color.White;
            this.pass2Label.Location = new System.Drawing.Point(761, 416);
            this.pass2Label.Name = "pass2Label";
            this.pass2Label.Size = new System.Drawing.Size(237, 39);
            this.pass2Label.TabIndex = 16;
            this.pass2Label.Text = "Confirm Password:";
            // 
            // infoLabel
            // 
            this.infoLabel.AutoSize = true;
            this.infoLabel.Font = new System.Drawing.Font("Viner Hand ITC", 26.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.infoLabel.ForeColor = System.Drawing.Color.White;
            this.infoLabel.Location = new System.Drawing.Point(823, 44);
            this.infoLabel.Name = "infoLabel";
            this.infoLabel.Size = new System.Drawing.Size(347, 114);
            this.infoLabel.TabIndex = 17;
            this.infoLabel.Text = "       New Users \r\nGet $50,000 Credits!";
            // 
            // Create
            // 
            this.AcceptButton = this.createBtn;
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 22F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.CancelButton = this.exitBtn;
            this.ClientSize = new System.Drawing.Size(1233, 675);
            this.Controls.Add(this.infoLabel);
            this.Controls.Add(this.pass2Label);
            this.Controls.Add(this.passLabel);
            this.Controls.Add(this.emailLabel);
            this.Controls.Add(this.nameLabel);
            this.Controls.Add(this.exitBtn);
            this.Controls.Add(this.createBtn);
            this.Controls.Add(this.passBox2);
            this.Controls.Add(this.passBox);
            this.Controls.Add(this.emailBox);
            this.Controls.Add(this.userBox);
            this.Controls.Add(this.pictureBox1);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ForeColor = System.Drawing.Color.Red;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "Create";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Create";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.TextBox userBox;
        private System.Windows.Forms.TextBox emailBox;
        private System.Windows.Forms.TextBox passBox;
        private System.Windows.Forms.TextBox passBox2;
        private System.Windows.Forms.Button createBtn;
        private System.Windows.Forms.Button exitBtn;
        private System.Windows.Forms.Label nameLabel;
        private System.Windows.Forms.Label emailLabel;
        private System.Windows.Forms.Label passLabel;
        private System.Windows.Forms.Label pass2Label;
        private System.Windows.Forms.Label infoLabel;
    }
}