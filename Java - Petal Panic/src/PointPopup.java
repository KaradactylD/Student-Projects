
import java.awt.Image;
import java.awt.Color;

/* Kara Crumpton
 * CPT 236 - Final Project
 * Pictures from Freepik.com and OpenArt AI
 * Sound Effects from Uppbeat
 */


// stuff for the pop up
public class PointPopup 
{
    public int x, y; 
    public int points; 
    public int life; // how long it stays on the screen
    private static final int MAX_LIFE = 60; 
    public float rotation; // Sparkle rotation angle 

    public PointPopup(int x, int y, int points) 
    {
        this.x = x;
        this.y = y;
        this.points = points;
        this.life = MAX_LIFE;
        this.rotation = 0;
    }

    public void update() 
    {
        if (life > 0) 
        {
            life--;
            y -= 1; 
            rotation += 5; 
        }
    }

    public boolean isAlive() 
    {
        return life > 0;
    }

    // drawing the sparkle image. couldn't get it to look right so had to look all this up to get the settings right
    public void draw(java.awt.Graphics g, Image sparkleImage) 
    {
        if (!isAlive()) return;

        // Calculate fade and the scale
        float alpha = (float) life / MAX_LIFE;
        int size = 30; // Base sparkle size
        float scale = 0.5f + (life / (float) MAX_LIFE) * 0.5f; // Scale from 0.5 to 1.0
        int scaledSize = (int) (size * scale);

        // Draw sparkle (rotated and scaled)
        if (sparkleImage != null) 
        {
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
            g2d.rotate(Math.toRadians(rotation), x + scaledSize / 2, y + scaledSize / 2);
            g2d.setComposite(java.awt.AlphaComposite.getInstance(
                java.awt.AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(sparkleImage, x, y, scaledSize, scaledSize, null);
            g2d.setComposite(java.awt.AlphaComposite.getInstance(
                java.awt.AlphaComposite.SRC_OVER, 1.0f)); // Reset alpha
            g2d.rotate(-Math.toRadians(rotation), x + scaledSize / 2, y + scaledSize / 2); // Reset rotation
        } 
        else 
        {
            // draw a white star, put this in in case mine doesn't load
            g.setColor(new Color(255, 255, 255, (int) (alpha * 255)));
            int[] xPoints = {x + scaledSize / 2, x + scaledSize, x + scaledSize / 2, x};
            int[] yPoints = {y, y + scaledSize / 2, y + scaledSize, y + scaledSize / 2};
            g.fillPolygon(xPoints, yPoints, 4);
        }

        // drawing the text that shows the points
        g.setColor(new Color(255, 255, 0, (int) (alpha * 255))); // this is yellow... 
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        String text = "+" + points;
        g.drawString(text, x + scaledSize + 5, y + scaledSize / 2);
    }
}