package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;
    public int speedBoostTimer = 0;
    ArrayList<Bomb> bombs = new ArrayList<>();
    BufferedImage bomb, bomb1, bomb2;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        solidArea = new Rectangle(0, 0, 45, 40);
    }

    public void setDefaultValues() {
        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            bomb = ImageIO.read(getClass().getResourceAsStream("/Sprite/bomb1.png"));
            bomb1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/bomb2.png"));
            bomb2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/bomb3.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Sprite/player_right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (speedBoostTimer > 0) {
            speedBoostTimer--;
            if (speedBoostTimer == 0) {
                speed = 4; // hết hiệu lực, trở lại bình thường
            }
        }

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }else if (keyH.enterPressed) {
                direction = "enter";
                if (bombs.size() == 0) {
                    bombs.add(new Bomb(worldX, worldY, gp));
                }
            }

            collisionOn = false;
            gp.checker.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
            if (bombs.get(i).exploded) {
                bombs.remove(i);
                i--;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
            case "enter":
                image = (spriteNum == 1) ? down1 : down2;
                break;
        }

        g2.drawImage(image, worldX, worldY, gp.TILE_SIZE, gp.TILE_SIZE, null);

        for (Bomb bomb : bombs) {
            bomb.draw(g2);
        }
    }
}

