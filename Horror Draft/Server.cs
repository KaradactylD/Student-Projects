using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

/* Kara Crumpton
 * CPT 185 - Final Project - Server Page 
 * Background Image from freepik.com
 */

namespace Horror_Draft
{
    public partial class Server : Form
    {
        // Gotta add this to be able to go to the Login form
        Login loginForm = new Login();
        public Server()
        {
            InitializeComponent();
            // Make the buttons transparent
            karaButton.BackColor = Color.Transparent;
            karaButton.FlatStyle = FlatStyle.Flat;
            karaButton.FlatAppearance.BorderSize = 0;
            karaButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            karaButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            karaButton.TabStop = false; 

            notKaraButton.BackColor = Color.Transparent;
            notKaraButton.FlatStyle = FlatStyle.Flat;
            notKaraButton.FlatAppearance.BorderSize = 0;
            notKaraButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            notKaraButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            notKaraButton.TabStop = false;

            goButton.BackColor = Color.Transparent;
            goButton.FlatStyle = FlatStyle.Flat;
            goButton.FlatAppearance.BorderSize = 0;
            goButton.FlatAppearance.MouseOverBackColor = Color.Transparent;
            goButton.FlatAppearance.MouseDownBackColor = Color.Transparent;
            goButton.TabStop = false;

            statusLabel.BackColor = Color.Transparent;
            statusLabel.ForeColor = Color.White;
            
        }

        private void karaButton_Click(object sender, EventArgs e)
        {
            // It's set up on my computer, so clicking "I'm Kara" runs it as a server.
            Task.Run(() => RunAsServer());
            this.Show();

        }

        private void notKaraButton_Click(object sender, EventArgs e)
        {
            // Clicking "I'm Not Kara" runs it as a client. 
            RunAsClient();
            this.Show();

        }

        private void RunAsServer() // Had to look up every bit of this. I don't know shit about setting up servers. (Now I kinda do... it was annoying.)
        {
            int port = 64420;
            IPAddress ipAddress = IPAddress.Any;

            try
            {
                this.Invoke((MethodInvoker)delegate
                {
                    statusLabel.Text = "Attempting to start server...";
                });
                
                TcpListener server = new TcpListener(ipAddress, port);
                server.Start();
                this.Invoke((MethodInvoker)delegate
                {
                    statusLabel.Text = $"Server started on {ipAddress} : {port}";
                });
                

                while (true)
                {
                    TcpClient client = server.AcceptTcpClient();
                    this.Invoke((MethodInvoker)delegate
                    {
                        statusLabel.Text = "Client Connected!";
                    });
                    

                    Task.Run(() => HandleClient(client));
                }
            }
            catch (Exception ex)
            {
                this.Invoke((MethodInvoker)delegate
                {
                    statusLabel.Text = $"ERROR: {ex.Message}";
                });
                
            }
        }

        private void RunAsClient()
        {
            // This is the stuff it does to run as a Client.
            string serverIp = "192.168.1.151";
            int port = 64420;

            try
            {
                TcpClient client = new TcpClient(serverIp, port);
                this.Invoke((MethodInvoker)delegate
                {
                    statusLabel.Text = "Connected to the server!";
                });
               
                goButton.Enabled = true;
            }

            catch
            {
                this.Invoke((MethodInvoker)delegate
                {
                    statusLabel.Text = "Failed to connect to the server!";
                });
                
            }
        }

        private void HandleClient(TcpClient client)
        {
            // More stuff that does things to run it from my computer. 
            try
            {
                NetworkStream stream = client.GetStream();
                StreamReader reader = new StreamReader(stream);
                StreamWriter writer = new StreamWriter(stream) { AutoFlush = true };

                string message = reader.ReadLine();
                this.Invoke((MethodInvoker)delegate
                {
                    statusLabel.Text = $"Received from client: {message}";
                });
                

                writer.WriteLine("Message Received by Server");
                client.Close();
            }
            catch (Exception ex)
            {
                this.Invoke((MethodInvoker)delegate
                {
                    statusLabel.Text = $"Client Handling Error: {ex.Message}";
                });   
                
            }
        }

        private void goButton_Click(object sender, EventArgs e)
        {
            // Go to the "Login" page to say who you are
            loginForm.ShowDialog();
            
        }
    }
}
