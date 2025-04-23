import javax.swing.*;

/* Kara Crumpton
 * CPT 236 - Final Project
 * Pictures from Freepik.com and OpenArt AI
 * Sound Effects from Uppbeat
 */

public class MainGame 
{
	// setting up the window
    public static void main(String[] args) 
    {
    	// Opens the screen in the middle. Had to look this up. 
        SwingUtilities.invokeLater(() -> 
        {
            JFrame frame = new JFrame("Petal Panic");
            frame.setSize(1000, 700); // Width, Height
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null); 

            GamePanel panel = new GamePanel();
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
