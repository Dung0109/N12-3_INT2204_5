package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Monster extends Entity  {

    GamePanel gp;
    public int directionX =1, directionY=0;
    public boolean alive = true;

    public Monster(GamePanel gp,int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.gp = gp;
        setDefaultValues();
        getMonsterImage();
    }

    public void setDefaultValues() {
        speed = 2;
        direction = "right";
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

        directionX += speed * directionX;
        directionY += speed * directionY;

        // Đảo chiều khi va vào biên
        if (directionX <= 100 || directionX >= 500 - gp.TILE_SIZE) {
            directionX *= -1;
        }
        if (directionY <= 100 || directionY >= 500 - gp.TILE_SIZE) {
            directionY *= -1;
        }

        // Cập nhật animation
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }


    }

    public void draw(Graphics2D g2) {


        BufferedImage image = null;
        if (directionX > 0) {
            image = (spriteNum == 1) ? right1 : right2;
        } else if (directionX < 0) {
            image = (spriteNum == 1) ? left1 : left2;
        }

        g2.drawImage(image, worldX, worldY, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }

}
