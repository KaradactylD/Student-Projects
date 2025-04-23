using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Data.SQLite;
using System.Drawing;
using System.Drawing.Text;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.ProgressBar;
using System.Media;

/* Kara Crumpton - CPT 206 - Final Project
 * Friday the 13th Slot Machine
 * Pics from OpenArt AI Image Generator 
 * Sound Effects from Soundboard 
 * started: Feb 19th 2025 - finished: Apr 20 2025
 */

namespace KCrumpton_CPT_206_Final_Project_Slot_Machine
{

    public partial class Game : Form

    {
        // Had to use WMP for the background music, using it with SoundPlayer messed it up when the winning/mom effects played
        private WMPLib.WindowsMediaPlayer _backgroundPlayer; 

        // Can't have 2 SoundPlayers playing at one time. These play at different times, so it's fine
        private SoundPlayer _winSoundPlayer; // the Winning sound effect
        private SoundPlayer _momSoundPlayer; // sound effect for "Mom" trigger

        private string currentUsername;

        // getting the pics to use for random. Pics in debug folder, named whatever.jpg
        private Random random = new Random();
        private string[] symbols = { "9", "10", "jack", "queen", "king", "ace", "J1", "J2", "J3", "J4", "mom" };

        // setting animation to make it look cooler when it spins. Had to look this up. Still not super happy with it... I'll keep messing around.
        private Timer spinTimer;
        private int spinCount = 0;
        private const int maxSpinCount = 11; // Number of times it'll spin before stopping. 

        // highlighting winning boxes so you see where you won at
        private List<PictureBox> winningBoxes = new List<PictureBox>(); // Tracks winning boxes to highlight


        public Game(string username)
        {
            InitializeComponent();
            SetPictureBoxZoom();
            InitializeSpinTimer();
            currentUsername = username;
            UpdateBalanceLabel(currentUsername);
            UpdateBalanceLabel2(currentUsername);
            _backgroundPlayer = new WMPLib.WindowsMediaPlayer();
            _backgroundPlayer.URL = Path.Combine(Application.StartupPath, "night.wav");
            _backgroundPlayer.settings.setMode("loop", true); // Enable looping for the background music
            _winSoundPlayer = new SoundPlayer(System.IO.Path.Combine(Application.StartupPath, "win.wav"));
            _momSoundPlayer = new SoundPlayer(System.IO.Path.Combine(Application.StartupPath, "theme.wav"));


            // Sets the picture boxes on Load - they were blank and I didn't like the way it looked. Starting the game starts the random that pulls the new pics. 
            slotBox00.Image = Image.FromFile(Application.StartupPath + @"\ace.jpg");
            slotBox01.Image = Image.FromFile(Application.StartupPath + @"\9.jpg");
            slotBox02.Image = Image.FromFile(Application.StartupPath + @"\mom.jpg");
            slotBox03.Image = Image.FromFile(Application.StartupPath + @"\J1.jpg");
            slotBox04.Image = Image.FromFile(Application.StartupPath + @"\king.jpg");
            slotBox05.Image = Image.FromFile(Application.StartupPath + @"\10.jpg");
            slotBox06.Image = Image.FromFile(Application.StartupPath + @"\9.jpg");
            slotBox07.Image = Image.FromFile(Application.StartupPath + @"\ace.jpg");
            slotBox08.Image = Image.FromFile(Application.StartupPath + @"\J3.jpg");
            slotBox09.Image = Image.FromFile(Application.StartupPath + @"\jack.jpg");
            slotBox10.Image = Image.FromFile(Application.StartupPath + @"\mom.jpg");
            slotBox11.Image = Image.FromFile(Application.StartupPath + @"\queen.jpg");
            slotBox12.Image = Image.FromFile(Application.StartupPath + @"\10.jpg");
            slotBox13.Image = Image.FromFile(Application.StartupPath + @"\J2.jpg");
            slotBox14.Image = Image.FromFile(Application.StartupPath + @"\mom.jpg");
        }


        // Gets the user's starting Credit
        private int GetUserCredit(string UserName)
        {
            // Each new user gets $50,000 to play with
            int credit = 0;
            using (SQLiteConnection conn = new SQLiteConnection(AppConfig.ConnectionString))
            {
                conn.Open();
                string query = "SELECT Credit FROM SlotUsers WHERE UserName = @username";

                // had a horrible time getting the user's credits to work. Console Writelines were to help me figure out what was wrong. Leaving them in, just in case.
                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    // Gets the username
                    Console.WriteLine($"Searching for UserName: '{UserName}'");

                    cmd.Parameters.AddWithValue("@username", UserName);

                    var result = cmd.ExecuteScalar();

                    Console.WriteLine($"Fetched Credit: {result}");

                    if (result != null && result != DBNull.Value)
                    {
                        credit = Convert.ToInt32(result);
                    }
                    else
                    {
                        Console.WriteLine("No data found for this user.");
                    }
                }
            }
            return credit;
        }

        // Users can bet however much they want
        private int betAmount = 0;
        private int balance;
        private void confirmBtn_Click(object sender, EventArgs e)
        {
            // Make sure they entered a valid bet
            if (int.TryParse(betBox.Text, out int enteredBet) && enteredBet > 0)
            {
                // Check if they tried to bet more than what they have
                int userBalance = GetUserBalance(currentUsername);
                if (enteredBet <= userBalance)
                {
                    betAmount = enteredBet;
                    resultLabel.Text = $"Bet set to ${betAmount}";
                }
                else
                {
                    resultLabel.Text = "Bet exceeds your balance!";
                }
            }
            else
            {
                resultLabel.Text = "Invalid bet amount!";
            }
        }
    

        // Label at the top shows user name and their balance. 
        private void UpdateBalanceLabel(string username)
        {
            using (SQLiteConnection conn = new SQLiteConnection(AppConfig.ConnectionString))
            {
                conn.Open();
                string query = "SELECT Balance FROM SlotUsers WHERE username = @username";
                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@username", username);
                    object result = cmd.ExecuteScalar();
                    if (result != null)
                    {
                        balanceLabel.Text = $"{username} -  Balance: ${result.ToString()}";
                    }
                }
            }
        }

        private int GetUserBalance(string username)
        {
            int balance = 0;
            using (SQLiteConnection conn = new SQLiteConnection(AppConfig.ConnectionString))
            {
                conn.Open();
                string query = "SELECT Balance FROM SlotUsers WHERE UserName = @username";
                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@username", username);
                    var result = cmd.ExecuteScalar();
                    if (result != null && result != DBNull.Value)
                    {
                        balance = Convert.ToInt32(result);
                    }
                }
            }
            return balance;
        }

        private void SetPictureBoxZoom()
        {
            // Always have to set Zoom mode here, Properties window doesn't work
            for (int i = 0; i < 15; i++)
            {
                string pictureBoxName = "slotBox" + i.ToString("D2");
                PictureBox pictureBox = (PictureBox)this.Controls[pictureBoxName];
                pictureBox.SizeMode = PictureBoxSizeMode.Zoom;
            }
        }

        // More animation stuff
        private void InitializeSpinTimer()
        {
            spinTimer = new Timer();
            spinTimer.Interval = 80; // says 100 is the "smoothest"... I like this better tho. :)
            spinTimer.Tick += SpinTimer_Tick;
        }

        // setting the images in the boxes.
        private void SetPictureBoxImages(string[] symbolsArray)
        {
            for (int i = 0; i < 15; i++)
            {
                string pictureBoxName = "slotBox" + i.ToString("D2");
                PictureBox pictureBox = (PictureBox)this.Controls[pictureBoxName];

                // kept getting an "Out of Memory" error. Had to look this up. 
                if (pictureBox.Image != null)
                {
                    pictureBox.Image.Dispose();  // Dispose of the previous image - stops the Memory Error
                    pictureBox.Image = null;
                }


                pictureBox.Image = LoadImage(symbolsArray[i]);
            }
        }

        // Method for getting the random pics
        private string GetRandomSymbol()
        {
            int index = random.Next(symbols.Length);
            return symbols[index];
        }

        // Loading the images from the debug folder
        private Image LoadImage(string symbol)
        {
            string imagePath = Path.Combine(Application.StartupPath, symbol + ".jpg");

            if (!File.Exists(imagePath))
            {
                imagePath = Path.Combine(Application.StartupPath, "defaultImage.jpg");
                if (!File.Exists(imagePath))
                {
                    MessageBox.Show("Default image not found!");
                    return null;
                }
            }

            // kept getting "Out of Memory" error... had to look this up, but it fixed it.
            using (var tempImage = Image.FromFile(imagePath)) // Load image in a temp variable
            {
                return new Bitmap(tempImage); // Create a copy to free the original file lock
            }
        }

        // The paylines to figure out the wins, etc... 
        private int CalculatePayout(string[] symbolsArray, out string payoutDetails)
        {
            int totalPayout = 0;
            payoutDetails = ""; // String for Payout Details, used for debug when it was all jacked up...

            // The winning lines. Gotta change some of these... 
            List<int[]> paylines = new List<int[]>
            {
                new int[] { 0, 1, 2, 3, 4 },
                new int[] { 5, 6, 7, 8, 9 },
                new int[] { 10, 11, 12, 13, 14 },
                new int[] { 10, 6, 2, 8, 14 },
                new int[] { 0, 1, 7, 3, 4 },
                new int[] { 10, 11, 7, 13, 14 },
                new int[] { 5, 11, 12, 13, 9 },
                new int[] { 5, 1, 2, 3, 9 },
                new int[] { 5, 1, 7, 3, 9 },
                new int[] { 5, 11, 7, 13, 9 },
                new int[] { 0, 6, 2, 8, 4 },
                new int[] { 10, 6, 12, 8, 14 },
                new int[] { 5, 6, 2, 8, 9 },
                new int[] { 5, 6, 12, 8, 9 },
                new int[] { 0, 6, 7, 8, 4 },
                new int[] { 10, 6, 7, 8, 14 },
                new int[] { 0, 6, 12, 13, 14 },
                new int[] { 10, 6, 2, 3, 4 },
                new int[] { 0, 11, 2, 13, 4 },
                new int[] { 10, 1, 12, 3, 14 },
                new int[] { 0, 11, 12, 13, 4 },
                new int[] { 10, 1, 2, 3, 14 },
                new int[] { 0, 1, 12, 3, 4 },
                new int[] { 10, 11, 2, 13, 14 },
                new int[] { 0, 1, 7, 13, 14 },
                new int[] { 10, 11, 7, 3, 4 },
                new int[] { 5, 11, 7, 3, 9 },
                new int[] { 5, 1, 7, 13, 9 },
                new int[] { 5, 1, 12, 3, 9 },
            };

            // Iterate through each payline
            foreach (var line in paylines)
            {
                // Get the symbols on the paylines
                string[] lineSymbols = line.Select(i => symbolsArray[i]).ToArray();

                // Get the symbol at index 0 of the payline
                string firstSymbol = lineSymbols[0];

                // Check for consecutive symbols from the start of the payline
                int consecutiveCount = 0;
                for (int i = 0; i < lineSymbols.Length; i++)
                {
                    if (lineSymbols[i] == firstSymbol)
                    {
                        consecutiveCount++;
                    }
                    else
                    {
                        break; // Stop once we hit a symbol that doesn't match
                    }
                }

                // If we have 3 or more consecutive symbols, calculate the payout
                if (consecutiveCount >= 3 && payouts.ContainsKey(firstSymbol))
                {
                    // Get the payout multiplier for the number of consecutive symbols
                    int payoutMultiplier = payouts[firstSymbol][Math.Min(consecutiveCount - 3, 2)];
                    totalPayout += payoutMultiplier * betAmount;

                }
            }

            return totalPayout;
        }


        // Show the winnings in the label 
        private void DisplayPayout(Dictionary<string, int> matchedSymbols, int betAmount)
        {
            // Start payout at zero, so it doesn't hold the old totals
            int totalPayout = 0;

            // Go through to find the matches
            foreach (var pair in matchedSymbols)
            {
                string symbol = pair.Key;
                int symbolsMatched = pair.Value;

                // If there are at least 3 matches for a symbol, calculate the payout
                if (symbolsMatched >= 3)
                {
                    // Calculate the payout 
                    int payout = CalculatePayout(symbol, symbolsMatched, betAmount);

                    // Add the payout to the total payout
                    totalPayout += payout;

                    // Get the multiplier 
                    int multiplier = payouts.ContainsKey(symbol) && symbolsMatched <= 5
                        ? payouts[symbol][symbolsMatched - 3]
                        : 0;

                }
            }

            // Display the total payout after checking all matched symbols
            resultLabel.Text += $"\nTotal Payout: {totalPayout}";
        }

        // Game stuff! 
        private void spinBtn_Click(object sender, EventArgs e)
        {

            // Make sure they placed a bet
            if (betAmount <= 0)
            {
                MessageBox.Show("Please enter a valid bet amount before spinning.", "Invalid Bet", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // more animation stuff
            spinCount = 0;
            resultLabel.Text = "SPINNING...";
            spinTimer.Start();
            DeductBet(currentUsername, betAmount);
            UpdateBalanceLabel(currentUsername);
        }

        private async void SpinTimer_Tick(object sender, EventArgs e)
        {
            ResetPictureBoxHighlights(); // Make sure it clears the highlights when they first hit spin

            // Make it look like a real slot machine, with the pics going up and down
            string[] symbolsArray = new string[15];
            for (int i = 0; i < 15; i++)
            {
                int shift = (spinCount % 3 == 0) ? -1 : 1; // Moves the symbols up/down 
                int index = (i + shift + 15) % 15; // Ensures wraparound effect
                symbolsArray[i] = GetRandomSymbol();
            }

            // Update the boxes
            SetPictureBoxImages(symbolsArray);

            spinCount++;

            // Stop the animation after a certain number of spins
            if (spinCount >= maxSpinCount)
            {
                spinTimer.Stop();
                // After the animation stops, set the final pics in the boxes
                string[] finalSymbols = new string[15];
                for (int i = 0; i < 15; i++)
                {
                    finalSymbols[i] = GetRandomSymbol();
                }
                SetPictureBoxImages(finalSymbols);

                // Reset previous highlights before checking new paylines
                ResetPictureBoxHighlights();

                // Calculate payout and check paylines
                string payoutDetails;
                int totalPayout = CalculatePayout(finalSymbols, out payoutDetails);

                if (totalPayout > 0)
                {
                    resultLabel.Text = "YOU'RE A WINNER! \nPayout: $" + totalPayout;
                    AddWinnings(currentUsername, totalPayout);


                    if (await CheckPaylines(finalSymbols))
                    {
                        HighlightWinningBoxes(); // Highlight the winning boxes
                    }
                }
                else
                {
                    resultLabel.Text = "TRY AGAIN!";
                }

                UpdateBalanceLabel(currentUsername); // Update balance after spin
            }
        }
        // the winning "Paylines". Got them from an online slot machine. 
        private async Task<bool> CheckPaylines(string[] symbolsArray)
        {
            bool isWinner = false;
            bool miniGameTriggered = false; // prevents multiple triggers for the mini game
            bool winSoundPlayed = false;
            bool momSoundPlayed = false;

            // saving paylines to a list
            List<int[]> paylines = new List<int[]>
            {
                new int[] { 0, 1, 2, 3, 4 },
                new int[] { 5, 6, 7, 8, 9 },
                new int[] { 10, 11, 12, 13, 14 },
                new int[] { 10, 6, 2, 8, 14 },
                new int[] { 0, 1, 7, 3, 4 },
                new int[] { 10, 11, 7, 13, 14 },
                new int[] { 5, 11, 12, 13, 9 },
                new int[] { 5, 1, 2, 3, 9 },
                new int[] { 5, 1, 7, 3, 9 },
                new int[] { 5, 11, 7, 13, 9 },
                new int[] { 0, 6, 2, 8, 4 },
                new int[] { 10, 6, 12, 8, 14 },
                new int[] { 5, 6, 2, 8, 9 },
                new int[] { 5, 6, 12, 8, 9 },
                new int[] { 0, 6, 7, 8, 4 },
                new int[] { 10, 6, 7, 8, 14 },
                new int[] { 0, 6, 12, 13, 14 },
                new int[] { 10, 6, 2, 3, 4 },
                new int[] { 0, 11, 2, 13, 4 },
                new int[] { 10, 1, 12, 3, 14 },
                new int[] { 0, 11, 12, 13, 4 },
                new int[] { 10, 1, 2, 3, 14 },
                new int[] { 0, 1, 12, 3, 4 },
                new int[] { 10, 11, 2, 13, 14 },
                new int[] { 0, 1, 7, 13, 14 },
                new int[] { 10, 11, 7, 3, 4 },
                new int[] { 5, 11, 7, 3, 9 },
                new int[] { 5, 1, 7, 13, 9 },
                new int[] { 5, 1, 12, 3, 9 },

            };

            // look through each payline to check for matches
            foreach (var line in paylines)
            {
                string[] lineSymbols = line.Select(i => symbolsArray[i]).ToArray();
                string firstSymbol = lineSymbols[0];

                int consecutiveCount = 0;
                for (int i = 0; i < lineSymbols.Length; i++)
                {
                    if (lineSymbols[i] == firstSymbol)
                    {
                        consecutiveCount++;
                    }
                    else
                    {
                        break;
                    }
                }

                // If we have 3 or more consecutive symbols, mark the winning boxes. This was a freakin nightmare. Still not super happy with it. 
                if (consecutiveCount >= 3)
                {
                    isWinner = true;

                    // Highlight the winning boxes
                    foreach (int index in line)
                    {
                        PictureBox winningBox = (PictureBox)this.Controls["slotBox" + index.ToString("D2")];
                        if (winningBox != null)
                        {
                            winningBoxes.Add(winningBox); // Track the winning box
                        }
                    }
                }

                // Play winning sound for regular win (once per spin)
                if (!winSoundPlayed && firstSymbol != "mom")
                {
                    try
                    {
                        _winSoundPlayer.Play();
                        winSoundPlayed = true;
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show($"Error playing win sound: {ex.Message}", "Sound Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    }
                }

                // Check for mini-game trigger (4 or 5 "mom" symbols)
                int momCount = lineSymbols.Count(s => s == "mom");
                if (momCount >= 4 && !miniGameTriggered)
                {
                    miniGameTriggered = true;


                    // Highlight the "mom" symbols before the delay
                    foreach (int index in line)
                    {
                        if (symbolsArray[index] == "mom")
                        {
                            PictureBox momBox = (PictureBox)this.Controls["slotBox" + index.ToString("D2")];
                            if (momBox != null)
                            {
                                winningBoxes.Add(momBox); // Track the "mom" box
                            }
                        }
                    }
                    HighlightWinningBoxes(); // Highlight boxes for mom too

                    // Play winning sound for "mom" win (once per spin)
                    if (!momSoundPlayed)
                    {
                        try
                        {
                            _momSoundPlayer.Play();
                            momSoundPlayed = true;

                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show($"Error playing win sound: {ex.Message}", "Sound Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        }
                    }

                    // Add a 5-second delay to show the winning "mom" symbols
                    await Task.Delay(5000);

                    using (MiniGame miniGame = new MiniGame())
                    {
                        miniGame.ShowDialog();
                        if (miniGame.Bonus > 0)
                        {

                            AddWinnings(currentUsername, miniGame.Bonus);
                            resultLabel.Text = $"Mini Game Payout: " + miniGame.Bonus;
                            UpdateBalanceLabel(currentUsername);
                        }
                    }
                }
            }
            return isWinner;
        }

        // Dictionary to hold Payout Amounts. For 3, 4 or 5 matches, x Total Bet.
        private Dictionary<string, int[]> payouts = new Dictionary<string, int[]>
        {
            // Bet multiplier amounts for 3, 4, or 5 matches:
              { "9", new int[] { 1, 2, 3 } },
              { "10", new int[] { 3, 4, 5 } },
              { "jack", new int[] { 5, 6, 7 } },
              { "queen", new int[] { 7, 8, 9 } },
              { "king", new int[] { 10, 11, 12 } },
              { "ace", new int[] { 12, 13, 14 } },
              { "J1", new int[] { 15, 17, 19 } },
              { "J2", new int[] { 15, 17, 19 } },
              { "J3", new int[] {15, 17, 19 } },
              { "J4", new int[] {15, 17, 19 } }
        };
        private int[] payline;

        // Find the payout amount depending on how many they match and what symbol it is
        public int CalculatePayout(string symbol, int matchCount, int totalBet)
        {
            if (payouts.ContainsKey(symbol))
            {
                // Get the payout multipliers for 3, 4, and 5 matches
                int[] payoutMultipliers = payouts[symbol];


                if (matchCount >= 3 && matchCount <= 5)
                {
                    int multiplier = payoutMultipliers[matchCount - 3]; // matchCount - 3 gives us 0 for 3 matches, 1 for 4, and 2 for 5
                    return multiplier * totalBet;
                }
            }
            return 0; // No payout if no matches 
        }


        // subtract bet amount from their balance
        private void DeductBet(string username, int betAmount)
        {
            using (SQLiteConnection conn = new SQLiteConnection(AppConfig.ConnectionString))
            {
                conn.Open();
                string query = "UPDATE SlotUsers SET Balance = Balance - @betAmount WHERE username = @username";
                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@betAmount", betAmount);
                    cmd.Parameters.AddWithValue("@username", username);
                    cmd.ExecuteNonQuery();
                }
            }
        }

        // add winning amount to their balance
        private void AddWinnings(string currentUsername, int winnings)
        {
            string query = "UPDATE SlotUsers SET Balance = Balance + @Winnings WHERE Username = @Username";

            using (SQLiteConnection conn = new SQLiteConnection(AppConfig.ConnectionString))
            using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
            {
                cmd.Parameters.AddWithValue("@Username", currentUsername);  
                cmd.Parameters.AddWithValue("@Winnings", winnings);
                conn.Open();
                cmd.ExecuteNonQuery();
            }
        }

        // Update the label showing the balance
        private void UpdateBalanceLabel2(string username)
        {
            using (SQLiteConnection conn = new SQLiteConnection(AppConfig.ConnectionString))
            {
                conn.Open();
                string query = "SELECT Balance FROM SlotUsers WHERE username = @username";
                using (SQLiteCommand cmd = new SQLiteCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@username", username);
                    object result = cmd.ExecuteScalar();
                    if (result != null)
                    {
                        balanceLabel.Text = $"Balance: ${result.ToString()}";
                    }
                }
            }
        }

        // Struggled way too damn hard with this. Still not super happy with how it looks. 
        private void ResetPictureBoxHighlights()
        {
            for (int i = 0; i < 15; i++)
            {
                string pictureBoxName = "slotBox" + i.ToString("D2");
                PictureBox pictureBox = (PictureBox)this.Controls[pictureBoxName];
                pictureBox.BorderStyle = BorderStyle.None;
                pictureBox.BackColor = Color.Black;// Reset to no border

            }
            winningBoxes.Clear(); // Clear the list of winning boxes
        }

        // Highlight the boxes of winning pictures to see where you won at. 
        private void HighlightWinningBoxes()
        {
            foreach (PictureBox box in winningBoxes)
            {
                box.BorderStyle = BorderStyle.Fixed3D;
                box.BackColor = Color.Yellow;

            }
        }

        private void exitBtn_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void Game_Load(object sender, EventArgs e)
        {
            // Play the background music
            try
            {
                _backgroundPlayer.controls.play();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error playing background music: {ex.Message}", "Sound Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        // Goes to the page with the Paylines
        private void infoBtn_Click(object sender, EventArgs e)
        {
            Details detailsform = new Details();
            detailsform.ShowDialog();
        }

        // Put this in to show the mini game... it takes a million spins to trigger by itself, ain't nobody got time for that, but I promise it works!
        private void testBtn_Click(object sender, EventArgs e)
        {
            // Ensure a valid bet amount is set
            if (betAmount <= 0)
            {
                MessageBox.Show("Please enter a valid bet amount before testing.", "Invalid Bet", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // Deduct the bet and update balance
            DeductBet(currentUsername, betAmount);
            UpdateBalanceLabel(currentUsername);
            resultLabel.Text = "SPINNING...";

            // 
            Timer testTimer = new Timer();
            testTimer.Interval = 80; 
            int testSpinCount = 0;

            testTimer.Tick += async (s, args) =>
            {
                ResetPictureBoxHighlights(); // Clear previous highlights

                // Show random symbols during animation
                string[] tempSymbols = new string[15];
                for (int i = 0; i < 15; i++)
                {
                    tempSymbols[i] = GetRandomSymbol();
                }
                SetPictureBoxImages(tempSymbols);

                testSpinCount++;

                // Stop animation after max spins
                if (testSpinCount >= maxSpinCount)
                {
                    testTimer.Stop();
                    testTimer.Dispose(); 

                    // Set final symbols with 4 "mom" symbols on payline {0, 1, 2, 3, 4}
                    string[] finalSymbols = new string[15];
                    finalSymbols[0] = "mom";
                    finalSymbols[1] = "mom";
                    finalSymbols[2] = "mom";
                    finalSymbols[3] = "mom";
                    finalSymbols[4] = GetRandomSymbol(); // Need 4 moms, rest can be random.

                    // Fill remaining slots with random symbols
                    for (int i = 5; i < 15; i++)
                    {
                        finalSymbols[i] = GetRandomSymbol();
                    }

                    // Update picture boxes with final symbols
                    SetPictureBoxImages(finalSymbols);

                    // Calculate payout and check paylines
                    string payoutDetails;
                    int totalPayout = CalculatePayout(finalSymbols, out payoutDetails);

                    if (totalPayout > 0)
                    {
                        resultLabel.Text = "YOU'RE A WINNER! \nPayout: $" + totalPayout;
                        AddWinnings(currentUsername, totalPayout);
                    }
                    else
                    {
                        resultLabel.Text = "TESTING FOR MINI GAME";
                    }

                    // Check paylines and trigger mini-game with delay for 4+ "mom" symbols
                    if (await CheckPaylines(finalSymbols))
                    {
                        HighlightWinningBoxes(); // Highlight winning boxes
                    }

                    UpdateBalanceLabel(currentUsername); // Update balance
                }
            };

            testTimer.Start(); 
        }


    }
}
    
    


