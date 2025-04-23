import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* Kara Crumpton
 * CPT 236 - Final Project
 * Pictures from Freepik.com and OpenArt AI
 * Sound Effects from Uppbeat
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener 
{
	
	private Font customFont;
		
    private enum GameState { INTRO, ABOUT, PLAYING, GAME_OVER, WIN }
    private GameState gameState = GameState.INTRO;

    private Timer timer;
    private Fairy fairy;
    private int score = 0;
    private int level = 1;
    private int highScore = 0;
    
    private boolean up, down, left, right;

    private ArrayList<Flower> flowers;
    private ArrayList<Enemy> enemies;
    private Random rand = new Random();

    private Image grassImage;
    private Image fairyImage;
    private Image titleBackgroundImage;
    private Image winBackgroundImage;
    private Image loseBackgroundImage;
    private Image aboutImage;
    private Image sparkleImage;
    private Image mushroomImage;
    
    private ArrayList<Image> flowerImages;
    private List<Poof> poofs = new ArrayList<>();
    private List<PointPopup> pointPopups = new ArrayList<>();
    private ArrayList<Rectangle> mushrooms = new ArrayList<>();
    
    // How many points each Flower is worth
    private static final int[] FLOWER_POINTS = {1, 3, 5, 10, 15}; // Purple, Pink, Orange, Blue, Red
    
    // Percentages for how often each color spawns. I wanted higher point flowers to show up less often
    private static final int[] FLOWER_WEIGHTS = {33, 32, 25, 8, 2}; // Purple, Pink, Orange, Blue, Red
    private static final int TOTAL_WEIGHT = 100;

    public GamePanel() 
    {
        setFocusable(true);
        addKeyListener(this);
        
        // Load images first
        grassImage = new ImageIcon(getClass().getResource("/grass.jpg")).getImage();
        fairyImage = new ImageIcon(getClass().getResource("/fairy.png")).getImage();
        titleBackgroundImage = new ImageIcon(getClass().getResource("/gameBG.jpg")).getImage();
        winBackgroundImage = new ImageIcon(getClass().getResource("/win.jpg")).getImage();
        loseBackgroundImage = new ImageIcon(getClass().getResource("/lose.jpg")).getImage();
        aboutImage = new ImageIcon(getClass().getResource("/about.jpg")).getImage();
        sparkleImage = new ImageIcon(getClass().getResource("/sparkle.png")).getImage();
        mushroomImage = new ImageIcon(getClass().getResource("/mushroom.png")).getImage();

        flowerImages = new ArrayList<>();
        flowerImages.add(new ImageIcon(getClass().getResource("/flower1.png")).getImage());
        flowerImages.add(new ImageIcon(getClass().getResource("/flower2.png")).getImage());
        flowerImages.add(new ImageIcon(getClass().getResource("/flower3.png")).getImage());
        flowerImages.add(new ImageIcon(getClass().getResource("/flower4.png")).getImage());
        flowerImages.add(new ImageIcon(getClass().getResource("/flower5.png")).getImage());
        
        // This loads a custom font. It's always a pain to do this... Had to look it up.
        try {
            
            InputStream fontStream = getClass().getResourceAsStream("/WhimsicalFountain-Regular.ttf");
            if (fontStream == null) 
            {
                fontStream = getClass().getClassLoader().getResourceAsStream("WhimsicalFountain-Regular.ttf");
            }
            if (fontStream == null) 
            {
                
                File fontFile = new File("resources/WhimsicalFountain-Regular.ttf");
                if (!fontFile.exists()) 
                {
                    throw new IOException("Font file not found at resources/WhimsicalFountain-Regular.ttf");
                }
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(48f);
            } 
            else 
            {
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(48f);
                fontStream.close();
            }
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);
            System.out.println("Custom font loaded: " + customFont.getFamily());
        } 
        catch (Exception e) 
        {
            System.err.println("Font loading error: " + e.getMessage());
            e.printStackTrace();
            customFont = new Font("Serif", Font.BOLD, 48); // Fallback font
            System.out.println("Failed to load custom font, using fallback: " + customFont.getFamily());
        }
        setDoubleBuffered(true);

        // Initialize the Fairy
        if (fairyImage == null) 
        { 
        	// in case the image doesn't load for whatever reason
            System.err.println("Error: fairyImage failed to load from /fairy.png");
        }
        
        // Setting up the Fairy - her starting coordinates, speed, size and picture
        fairy = new Fairy(100, 100, 5, 150, fairyImage, new Rectangle(0, 0, 720, 500));

        // getting the flowers, setting the images and points
        flowers = new ArrayList<>();
        for (int i = 0; i < 10; i++) 
        {
            int x = rand.nextInt(700);
            int y = rand.nextInt(500);
            int imageIndex = getWeightedRandomIndex();
            Image img = flowerImages.get(imageIndex);
            int points = FLOWER_POINTS[imageIndex];
            flowers.add(new Flower(new Rectangle(x, y, 50, 50), img, points));
        }
        
        // getting the random enemies
        enemies = new ArrayList<>();
        for (int i = 0; i < 5; i++) 
        {
            spawnEnemySafely();
        }

        timer = new Timer(16, this);
        timer.start();
    }
    
    // gets the random flowers, using their weights to spawn higher pointed ones less often
    private int getWeightedRandomIndex() 
    {
        int randomValue = rand.nextInt(TOTAL_WEIGHT);
        int cumulativeWeight = 0;
        for (int i = 0; i < FLOWER_WEIGHTS.length; i++) 
        {
            cumulativeWeight += FLOWER_WEIGHTS[i];
            if (randomValue < cumulativeWeight) 
            {
                return i;
            }
        }
        return FLOWER_WEIGHTS.length - 1;
    }
    
    // to play my sound effects
    public void playSound(String fileName) 
    {
        try 
        {
            System.out.println("Attempting to play sound: " + fileName); // put this in to check because it wasn't working at first. Leaving it in. 
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/" + fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } 
        catch (Exception e) 
        {
            System.err.println("Error playing sound: " + fileName);
            e.printStackTrace();
        }
    }
    
    // drawing all the stuff
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // had to add this to use my custom font

        // Enable anti-aliasing for smooth text
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (gameState == GameState.INTRO) 
        {
            g.drawImage(titleBackgroundImage, 0, 0, getWidth(), getHeight(), this);
        } 
        else if (gameState == GameState.ABOUT) 
        {
            g.drawImage(aboutImage, 0, 0, getWidth(), getHeight(), this);
        } 
        else if (gameState == GameState.PLAYING) 
        {
            int tileSize = 100;
            for (int x = 0; x < getWidth(); x += tileSize) 
            {
                for (int y = 0; y < getHeight(); y += tileSize) 
                {
                    g.drawImage(grassImage, x, y, tileSize, tileSize, this);
                }
            }

            for (Flower flower : flowers) 
            {
                g.drawImage(flower.image, flower.rect.x, flower.rect.y, flower.rect.width, flower.rect.height, this);
            }
            
            for (Rectangle mushroom : mushrooms) 
            {
                Graphics2D g2d1 = (Graphics2D) g;
                g2d1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Glow around the mushroom
                double pulse = Math.sin(System.currentTimeMillis() / 100.0) * 0.2 + 0.8; // 100 = faster / 500 = slower
                int glowSize = (int) (mushroom.width * 1.1); // 1.1 x mushroom size
                int glowOffset = (glowSize - mushroom.width) / 2; // Center glow
                
                // Semi-transparent glow
                g2d1.setColor(new Color(0, 255, 255, (int) (100 * pulse))); // Green
                g2d1.fillOval(
                    mushroom.x - glowOffset,
                    mushroom.y - glowOffset,
                    glowSize,
                    glowSize
                );
                
                // Draw mushroom image on top
                g2d1.drawImage(mushroomImage, mushroom.x, mushroom.y, mushroom.width, mushroom.height, this);
            }

            fairy.draw(g);

            for (Enemy enemy : enemies) 
            {
                enemy.draw(g);
            }
            
            for (Poof p : poofs) 
            {
                g.setColor(new Color(255, 255, 255, p.life * 12));
                g.fillOval(p.x, p.y, 30, 30);
            }
            
            for (PointPopup p : pointPopups) 
            {
                p.draw(g, sparkleImage);
            }

            // Use custom font for Game Screen stuff 
            g2d.setFont(customFont != null ? customFont.deriveFont(38f) : new Font("Arial", Font.BOLD, 28)); // will use Arial if it's not loaded
            
            g2d.setColor(Color.WHITE);
            
            // Score
            g2d.drawString("Score: " + score, 20, 40);
            
            // Level
            String levelText = "Level: " + level;
            int levelWidth = g2d.getFontMetrics().stringWidth(levelText);
            g2d.drawString(levelText, getWidth() - levelWidth - 20, 30);
            
            // High Score
            String highScoreText = "High Score: " + highScore;
            int highScoreWidth = g2d.getFontMetrics().stringWidth(highScoreText);
            g2d.drawString(highScoreText, getWidth() - highScoreWidth - 20, 70);
            
        } 
        else if (gameState == GameState.GAME_OVER) 
        {
            g.drawImage(loseBackgroundImage, 0, 0, getWidth(), getHeight(), this);
            
            g2d.setFont(customFont != null ? customFont : new Font("Serif", Font.BOLD, 68));
            System.out.println("LOSE state font: " + g2d.getFont().getFamily());
            g2d.setColor(Color.WHITE);
            String winMessage = "You Got Caught By the Dragons!";
            int winWidth = g2d.getFontMetrics().stringWidth(winMessage);
            g2d.drawString(winMessage, (getWidth() - winWidth) / 2, getHeight() / 2 - 80);
            
            
            // Instructions with Arial
            g2d.setFont(customFont != null ? customFont : new Font("Arial", Font.BOLD, 48));
            g2d.setColor(Color.WHITE);
            String nextLevel = "Press 'R' to Try Again!";
            int nextWidth = g2d.getFontMetrics().stringWidth(nextLevel);
            g2d.drawString(nextLevel, (getWidth() - nextWidth) / 2, getHeight() / 2 - 15);
        } 
        else if (gameState == GameState.WIN) 
        {
        	g.drawImage(winBackgroundImage, 0, 0, getWidth(), getHeight(), this);
        	
            // Use custom font with fallback
            g2d.setFont(customFont != null ? customFont : new Font("Serif", Font.BOLD, 68));
            System.out.println("WIN state font: " + g2d.getFont().getFamily());
            g2d.setColor(Color.WHITE);
            String winMessage = "Level Completed!";
            int winWidth = g2d.getFontMetrics().stringWidth(winMessage);
            g2d.drawString(winMessage, (getWidth() - winWidth) / 2, getHeight() / 2 - 150);
            
            
            // Instructions with Arial
            g2d.setFont(customFont != null ? customFont : new Font("Arial", Font.BOLD, 48));
            g2d.setColor(Color.WHITE);
            String nextLevel = "Press 'R' to play Level " + level;
            int nextWidth = g2d.getFontMetrics().stringWidth(nextLevel);
            g2d.drawString(nextLevel, (getWidth() - nextWidth) / 2, getHeight() / 2 - 100);
        }
    }
    
    // the dragons started out right on top of me sometimes so I immediately lost before even starting. This fixes that. Had to look it up.
    private void spawnEnemySafely() 
    {
        int safeDistance = 250;
        int maxWidth = 700;
        int maxHeight = 500;

        int x, y;
        do 
        {
            x = rand.nextInt(maxWidth);
            y = rand.nextInt(maxHeight);
        } 
        while (distance(fairy.getX(), fairy.getY(), x, y) < safeDistance);

        enemies.add(new Enemy(x, y));
    }
    
    // same deal with the mushroom, starting out right on top of me
    private void spawnMushroomSafely() 
    {
        int safeDistance = 350;
        int maxWidth = 700;
        int maxHeight = 500;
        int size = 60;

        int x, y;
        do 
        {
            x = rand.nextInt(maxWidth - size);
            y = rand.nextInt(maxHeight - size);
        } 
        while (distance(fairy.getX(), fairy.getY(), x, y) < safeDistance);

        mushrooms.add(new Rectangle(x, y, size, size));
    }
    
    // still part of the "spawn safely" stuff... I honestly have no idea what this means...
    private double distance(int x1, int y1, int x2, int y2) 
    {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    // all the stuff that happens during the game
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (score > highScore) 
        {
            highScore = score;
        }
        
        if (gameState != GameState.PLAYING) return;

        fairy.move(up, down, left, right);
        fairy.update();

        for (Enemy enemy : enemies) 
        {
            enemy.chase(fairy.getX(), fairy.getY(), 2, getWidth(), getHeight());
        }
        
        Rectangle fairyRect = fairy.getCollisionRect();
        
        // when you get a flower - it does the points and all that jazz
        Iterator<Flower> iterator = flowers.iterator();
        while (iterator.hasNext()) 
        {
            Flower flower = iterator.next();
            if (fairyRect.intersects(flower.rect)) 
            {
                iterator.remove();
                score += flower.getPoints();
                pointPopups.add(new PointPopup(flower.rect.x, flower.rect.y, flower.getPoints()));
                playSound("sparkle.wav");
            }
        }
        
        // stuff for when an enemy destroys a flower - it does the "ppof" and plays the sound, etc
        Iterator<Flower> flowerIterator = flowers.iterator();
        while (flowerIterator.hasNext()) 
        {
            Flower flower = flowerIterator.next();
            Rectangle flowerRect = flower.rect;
            for (Enemy enemy : enemies) 
            {
                Rectangle enemyRect = new Rectangle(
                    enemy.x + 20, enemy.y + 20, 
                    enemy.size - 40, enemy.size - 40);
                
                if (enemyRect.intersects(flowerRect)) 
                {
                    poofs.add(new Poof(flowerRect.x, flowerRect.y));
                    playSound("poof.wav");
                    flowerIterator.remove();
                    break;
                }
            }
        }
        
        // stuff for the "Poof"
        Iterator<Poof> poofIterator = poofs.iterator();
        while (poofIterator.hasNext()) 
        {
            Poof p = poofIterator.next();
            p.update();
            if (!p.isAlive()) 
            {
                poofIterator.remove();
            }
        }
        
        // the pop up for the points from the flowers
        Iterator<PointPopup> popupIterator = pointPopups.iterator();
        while (popupIterator.hasNext()) 
        {
            PointPopup p = popupIterator.next();
            p.update();
            if (!p.isAlive()) 
            {
                popupIterator.remove();
            }
        }
        
        // when you get the invincibility mushroom
        Iterator<Rectangle> mushroomIterator = mushrooms.iterator();
        while (mushroomIterator.hasNext()) 
        {
            Rectangle mushroom = mushroomIterator.next();
            if (fairyRect.intersects(mushroom)) 
            {
                mushroomIterator.remove();
                fairy.activateInvincibility();
                playSound("powerup.wav");
            }
        }
        
        // enemy stuff
        for (Enemy enemy : enemies) 
        {
            int buffer2 = 60;
            Rectangle enemyRect = new Rectangle(
                enemy.x + buffer2 / 2,
                enemy.y + buffer2 / 2,
                enemy.size - buffer2,
                enemy.size - buffer2);
            
            // if you collide
            if (fairyRect.intersects(enemyRect) && !fairy.isInvincible()) 
            {
                gameState = GameState.GAME_OVER;
            
                timer.stop();
            }
        }
        
        // once all the flowers are gone (collected or destroyed)
        if (flowers.isEmpty()) 
        {
            level++;
            gameState = GameState.WIN;
            timer.stop();
        }
        
        if (gameState == GameState.GAME_OVER) 
        {
            if (score > highScore) 
            {
                highScore = score;
            }
            score = 0;
        }

        repaint();
    }
    
    // starting over - because you died or moved to a new level, resets the stuff
    private void resetGame() 
    {
        fairy.setPosition(100, 100);
        fairy.setSpeed(Math.min(4 + level, 10)); // she starts out with speed level 4, and it increases for every level after that until she gets to speed 10
        fairy.resetInvincibility();

        flowers.clear();
        for (int i = 0; i < 10; i++) 
        {
            int x = rand.nextInt(700);
            int y = rand.nextInt(500);
            int imageIndex = getWeightedRandomIndex();
            Image img = flowerImages.get(imageIndex);
            int points = FLOWER_POINTS[imageIndex];
            flowers.add(new Flower(new Rectangle(x, y, 50, 50), img, points));
        }
        
        mushrooms.clear();
        spawnMushroomSafely();

        enemies.clear();
        int enemyCount = Math.min(level, 5); // set the maximum number of dragons to 5. Any more than that and you just die over and over...
        for (int i = 0; i < enemyCount; i++) 
        {
            spawnEnemySafely();
        }
        
        timer.start();
    }
    
    // the key stuff
    @Override
    public void keyPressed(KeyEvent e) 
    {
    	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    	
        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> 
            {
                if (gameState == GameState.INTRO) 
                {
                    gameState = GameState.ABOUT; // I made it so "A" goes to the About screen
                    repaint();
                }
                else if (gameState == GameState.PLAYING) 
                {
                    left = true;
                }
            }
            case KeyEvent.VK_D -> right = true;
            
            case KeyEvent.VK_ENTER -> 
            {
            	// Wanted to make my Title Screen and "About" screen bigger than what I originally set the panes as. 
            	// Tried to just change it and broke EVERYTHING... I almost cried. Anyway, this is what's going on here...
                if (gameState == GameState.INTRO || gameState == GameState.ABOUT) 
                {
                	// Set state to PLAYING and clear stale data
                    gameState = GameState.PLAYING;
                    level = 1;
                    resetGame(); // Reset dragons and other entities immediately
                    // Force immediate repaint to draw the game
                    repaint();
                    paintImmediately(getBounds()); // Ensure intro screen is gone
                    frame.getContentPane().revalidate();
                    frame.getContentPane().repaint();
                    // Resize after repaint to avoid delays
                    frame.setSize(800, 600); // Game size
                    frame.setLocationRelativeTo(null);
                } 
                else if (gameState == GameState.WIN) 
                {
                    level++;
                    gameState = GameState.PLAYING;
                    resetGame();
                    repaint();
                }
            }
            case KeyEvent.VK_R -> 
            {
                if (gameState == GameState.GAME_OVER) 
                {
                    level = 1;
                }
                if (gameState == GameState.GAME_OVER || gameState == GameState.WIN) 
                {
                    resetGame();
                    gameState = GameState.PLAYING;
                }
            }
            case KeyEvent.VK_ESCAPE -> 
            {
                if (gameState == GameState.ABOUT) 
                {
                    gameState = GameState.INTRO; // On the About screen, you press escape to go back
                    repaint();
                }
            }
        }
    }
    
    // letting go of the keys
    @Override
    public void keyReleased(KeyEvent e) 
    {
        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}