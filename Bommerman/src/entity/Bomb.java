package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Bomb{
    GamePanel gp;
    BufferedImage image1, image2, image3;


    public int x, y;
    public int timer = 0;
    public final int duration = 120; // thời gian tồn tại (2 giây nếu 60FPS)
    public boolean exploded = false;
    int spriteCounter = 0;
    int spriteNum = 1;

    public Bomb(int x, int y, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.gp = gp;
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/bomb1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/bomb2.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Sprite/bomb3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        timer++;
        if (timer >= duration) {
            exploded = true;
            int tileSize = gp.TILE_SIZE;
            gp.flames.add(new Flame(x, y, "center", gp));
            gp.flames.add(new Flame(x - tileSize, y, "left", gp));
            gp.flames.add(new Flame(x + tileSize, y, "right", gp));
            gp.flames.add(new Flame(x, y - tileSize, "up", gp));
            gp.flames.add(new Flame(x, y + tileSize, "down", gp));
        }

        spriteCounter++;
        if (spriteCounter > 10) {  // đổi frame mỗi 10 tick
            spriteNum++;
            if (spriteNum > 3) spriteNum = 1;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage currentImage = image1;
        switch (spriteNum) {
            case 1: currentImage = image1; break;
            case 2: currentImage = image2; break;
            case 3: currentImage = image3; break;
        }
        g2.drawImage(currentImage, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
