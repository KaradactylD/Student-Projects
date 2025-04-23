
/* Kara Crumpton
 * CPT 236 - Final Project
 * Pictures from Freepik.com and OpenArt AI
 * Sound Effects from Uppbeat
 */

// The "poof" animation that happens when an enemy destroys a flower
public class Poof 
{
    int x, y;
    int life = 20; // how many frames it'll be on screen for

    public Poof(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public boolean isAlive() 
    {
        return life > 0;
    }

    public void update() 
    {
        life--;
    }
}