
import java.awt.Image;
import java.awt.Rectangle;

/* Kara Crumpton
 * CPT 236 - Final Project
 * Pictures from Freepik.com and OpenArt AI
 * Sound Effects from Uppbeat
 */

// stuff for the Flowers
public class Flower 
{
    Rectangle rect;  
    Image image;     
    int points;      

    // Constructor to initialize the flower's stuff
    public Flower(Rectangle rect, Image image, int points) 
    {
        this.rect = rect;
        this.image = image;
        this.points = points;
    }

    // Getting the points for the flowers
    public int getPoints() 
    {
        return points;
    }

    // setting the points for the flowers
    public void setPoints(int points) 
    {
        this.points = points;
    }
}