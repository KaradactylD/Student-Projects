
import java.awt.*;
import java.awt.Image;

/* Kara Crumpton
 * CPT 236 - Final Project
 * Pictures from Freepik.com and OpenArt AI
 * Sound Effects from Uppbeat
 */

public class Fairy 
{
    private int x, y;
    private int speed;
    private int size;
    private boolean isInvincible;
    private int invincibilityTimer;
    private Image fairyImage;
    private Rectangle bounds; // to keep her from going off screen
    private boolean facingRight;

    public Fairy(int x, int y, int speed, int size, Image fairyImage, Rectangle bounds) 
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.fairyImage = fairyImage;
        this.bounds = bounds;
        this.isInvincible = false;
        this.invincibilityTimer = 0;
    }

    // this is how you move the fairy, and also flips the image so she's facing the right way
    public void move(boolean up, boolean down, boolean left, boolean right) 
    {
    	if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;
        
                
        if (left && !right) 
        {
            facingRight = false;
        } 
        else if (right && !left) 
        {
            facingRight = true;
        }

        keepInBounds();
    }

    // to keep her on screen - Had to look this up
    private void keepInBounds() 
    {
    	int buffer = 60; 
        int collisionOffset = buffer / 1; // 
        x = Math.max(bounds.x, Math.min(x, bounds.width - collisionOffset)); // 700 - 30 = 670
        y = Math.max(bounds.y, Math.min(y, bounds.height - collisionOffset)); // 500 - 30 = 470
    }

    // this updates if she's invincible
    public void update() 
    {
        if (isInvincible) 
        {
            invincibilityTimer--;
            if (invincibilityTimer <= 0) 
            {
                isInvincible = false;
            }
        }
    }

    // Activate invincibility
    public void activateInvincibility() 
    {
        isInvincible = true;
        invincibilityTimer = 300; // how long it lasts.. it goes by frames, so 60fps this should be 5 seconds
    }
    
    public void resetInvincibility() 
    {
        isInvincible = false;
        invincibilityTimer = 0;
    }

    // when she collides with an enemy or a flower. How close/far she has to be for it to count
    public Rectangle getCollisionRect() 
    {
        int buffer = 60;
        return new Rectangle(x + buffer / 2, y + buffer / 2, size - buffer, size - buffer);
    }

    // Added a glow effect for when she's invisible. 
    public void draw(Graphics g) 
    {
    	if (facingRight) 
    	{
            g.drawImage(fairyImage, x, y, size, size, null);
        } 
    	else 
    	{
            g.drawImage(fairyImage, x + size, y, -size, size, null);
        }
        if (isInvincible) 
        {
            g.setColor(new Color(255, 255, 100, 100));
            g.fillOval(x - 10, y - 10, size + 20, size + 20);
        }
    }

    // Getters and setters
    public int getX() { return x; }
    public int getY() { return y; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    public void setSpeed(int speed) { this.speed = speed; }
    public boolean isInvincible() { return isInvincible; }
}
