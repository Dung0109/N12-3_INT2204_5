package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Flame {
    GamePanel gp;
    BufferedImage centerImg, leftImg, rightImg, upImg, downImg;

    public int x, y;
    public String direction;
    public boolean exploded = false;

    private int timer = 0;
    private final int duration = 60;
    public Rectangle area;

    public Flame(int x, int y, String direction, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.gp = gp;
        area = new Rectangle(x, y, gp.TILE_SIZE, gp.TILE_SIZE);

        try {
            centerImg = ImageIO.read(getClass().getResourceAsStream("/Effect/bomeffect_center.png"));
            leftImg   = ImageIO.read(getClass().getResourceAsStream("/Effect/bomeffect_left.png"));
            rightImg  = ImageIO.read(getClass().getResourceAsStream("/Effect/bomeffect_right.png"));
            upImg     = ImageIO.read(getClass().getResourceAsStream("/Effect/bomeffect_up.png"));
            downImg   = ImageIO.read(getClass().getResourceAsStream("/Effect/bomeffect_down.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        timer++;
        if (timer >= duration) {
            exploded = true;
        }
        
         Rectangle playerRect = new Rectangle(gp.player.worldX, gp.player.worldY, gp.TILE_SIZE, gp.TILE_SIZE);
        if (area.intersects(playerRect)) {
            gp.player.alive = false;
        }
        Rectangle monsterRect = new Rectangle(gp.monster.worldX, gp.monster.worldY, gp.TILE_SIZE, gp.TILE_SIZE);
        if (area.intersects(monsterRect)) {
            gp.monster.alive = false;
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage img;

        switch (direction) {
            case "left": img = leftImg; break;
            case "right": img = rightImg; break;
            case "up": img = upImg; break;
            case "down": img = downImg; break;
            case "center": default: img = centerImg; break;
        }

        g2.drawImage(img, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
