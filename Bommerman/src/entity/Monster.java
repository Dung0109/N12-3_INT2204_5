package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Monster extends Entity {

    GamePanel gp;
    public boolean alive = true;

    public Monster(GamePanel gp, int x, int y) {
        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        setDefaultValues();
        getMonsterImage();
    }

    public void setDefaultValues() {
        speed = 2;
        direction = "right";
        solidArea = new Rectangle(8, 8, 32, 32);
    }

    public void getMonsterImage() {
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/monster1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/monster2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/monster3.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/monster4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        collisionOn = false;

        // Kiểm tra va chạm tile
        gp.checker.checkTile(this);

        if (collisionOn) {
            if (direction.equals("right")) {
                direction = "left";
            } else {
                direction = "right";
            }
        } else {
            if (direction.equals("right")) {
                worldX += speed;
            } else if (direction.equals("left")) {
                worldX -= speed;
            }
        }

        // Animation
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }

        // Kiểm tra va chạm Player
        Rectangle monsterRect = new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
        Rectangle playerRect = new Rectangle(gp.player.worldX + gp.player.solidArea.x, gp.player.worldY + gp.player.solidArea.y, gp.player.solidArea.width, gp.player.solidArea.height);

        if (monsterRect.intersects(playerRect)) {
            gp.player.alive = false;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (direction.equals("right")) {
            image = (spriteNum == 1) ? right1 : right2;
        } else {
            image = (spriteNum == 1) ? left1 : left2;
        }

        g2.drawImage(image, worldX, worldY, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
