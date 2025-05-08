package main;

import entity.Player;
import entity.SpeedItem;
import tile.TileManager;
import entity.Monster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable {
    public int TILE_SIZE = 48;
    public int MAX_SCREEN_COL = 20;
    public int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    final int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public CollisionChecker checker = new CollisionChecker(this);
    Thread gameThread;
    public Player player = new Player(this,keyH);
    public UI ui = new UI(this);
    Monster monster = new Monster(this,this.keyH);
    SpeedItem item = new SpeedItem(this);

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
        monster.update();
        player.update();
        item.update();
    }
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        ui.draw(g2);
        monster.draw(g2);
        item.draw(g2);
        
    }
}

