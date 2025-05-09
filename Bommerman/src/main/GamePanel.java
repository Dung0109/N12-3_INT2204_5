package main;

import entity.Player;
import entity.SpeedItem;
import tile.TileManager;
import entity.Monster;
import entity.Flame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
    public int TILE_SIZE = 48;
    public int MAX_SCREEN_COL = 20;
    public int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    final int FPS = 60;
    public static final int TITLE_STATE = 0;
    public static final int PLAY_STATE = 1;
    public static final int WIN_STATE = 2;
    public int gameState = PLAY_STATE;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public CollisionChecker checker = new CollisionChecker(this);
    Thread gameThread;
    public Player player = new Player(this,keyH);
    public Monster monster = new Monster(this,50,100);
    SpeedItem item = new SpeedItem(this);
    public ArrayList<Flame> flames = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                Update();
                repaint();
                delta--;
            }
        }
    }
    public void Update(){
        if(monster.alive){
        monster.update();
        }
        if (player.alive) {
            player.update();
        }
        item.update();
        for (int i = 0; i < flames.size(); i++) {
            flames.get(i).update();
            if (flames.get(i).exploded) {
                flames.remove(i);
                i--;
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;


        if (gameState == TITLE_STATE) {
            // vẽ menu
        } else if (gameState == PLAY_STATE) {
            if (player.alive) {
                tileM.draw(g2);

                player.draw(g2);
                if(monster.alive){
                monster.draw(g2);
                }
                item.draw(g2);
                for (Flame flame : flames) {
                    flame.draw(g2);
                }
            }else drawLostScreen(g2);
            // vẽ các đối tượng khác
        } else if (gameState == WIN_STATE) {
            drawWinScreen(g2);
        }
    }

    public void winGame() {
        gameState = WIN_STATE;
    }

    public void drawWinScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String text = "You Win!";
        int x = getXCentered(text, g2);
        int y = SCREEN_HEIGHT / 2;
        g2.drawString(text, x, y);
    }

    // Hàm căn giữa dòng chữ
    private int getXCentered(String text, Graphics2D g2) {
        FontMetrics fm = g2.getFontMetrics();
        return (SCREEN_WIDTH - fm.stringWidth(text)) / 2;
    }
    public void drawLostScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String text = "You Lost!";
        int x = getXCentered(text, g2);
        int y = SCREEN_HEIGHT / 2;
        g2.drawString(text, x, y);
    }
}


