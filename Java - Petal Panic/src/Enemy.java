import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/* Kara Crumpton
 * CPT 236 - Final Project
 * Pictures from Freepik.com and OpenArt AI
 * Sound Effects from Uppbeat
 */

// all the stuff for the enemies (size, spawning, their speed, etc...
public class Enemy 
{
    int x, y, size = 120;
    private static final Image[] dragonImages;
    private static final Image[] dragonImagesFlipped; 
    private Image image;
    private Image imageFlipped;

    static 
    {
    	dragonImages = new Image[6];
        dragonImagesFlipped = new Image[6];

        for (int i = 0; i < 6; i++) 
        {
            dragonImages[i] = new ImageIcon(Enemy.class.getResource("/dragon" + (i+1) + ".png")).getImage();
            dragonImagesFlipped[i] = flipImageHorizontally(dragonImages[i]);
        }
    }
    
    // getting the enemies, where they start, loading the random pictures
    public Enemy(int x, int y) 
    {
    	this.x = x;
        this.y = y;
        int index = new Random().nextInt(dragonImages.length);
        this.image = dragonImages[index];
        this.imageFlipped = dragonImagesFlipped[index];
    }
    
    // had to flip the images depending on what way they're moving or they would go backwards.
    private boolean facingRight = false; 
    
    // the enemies move to "follow" the fairy
    public void chase(int targetX, int targetY, int speed, int panelWidth, int panelHeight) 
    {
    	
    	facingRight = x < targetX;

        if (x < targetX) x += speed;
        if (x > targetX) x -= speed;
        if (y < targetY) y += speed;
        if (y > targetY) y -= speed;

        keepInBounds(panelWidth, panelHeight);
    }
    
    // to keep them from going off screen - had to look this up. 
    private void keepInBounds(int panelWidth, int panelHeight) 
    {
        x = Math.max(0, Math.min(x, panelWidth - size));
        y = Math.max(0, Math.min(y, panelHeight - size));
    }

    public void draw(Graphics g) 
    {
    	Image toDraw = facingRight ? imageFlipped : image;
        g.drawImage(toDraw, x, y, size, size, null);
    }
    
    // flipping the images so they face the right direction
    private static Image flipImageHorizontally(Image img) 
    {
        int w = img.getWidth(null);
        int h = img.getHeight(null);

        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = flipped.createGraphics();
        g2.drawImage(img, w, 0, -w, h, null); 
        g2.dispose();

        return flipped;
    }
}