package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpeedItem extends Entity {
    GamePanel gp;
    public boolean collected = false;
    public BufferedImage image;

    public SpeedItem(GamePanel gp) {
        this.gp = gp;
        worldX = 50;
        worldY = 50;
        solidArea = new Rectangle(0, 0, 35, 40);
        getImage();
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Item/Speed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (collected) return;

        Rectangle playerRect = new Rectangle(
                gp.player.worldX + gp.player.solidArea.x,
                gp.player.worldY + gp.player.solidArea.y,
                gp.player.solidArea.width,
                gp.player.solidArea.height
        );
        Rectangle itemRect = new Rectangle(worldX, worldY, solidArea.width, solidArea.height);

        if (playerRect.intersects(itemRect)) {
            collected = true;
            gp.player.speed = 8; // tăng tốc
            gp.player.speedBoostTimer = 300; // giữ 5 giây (60 fps x 5)
        }
    }

    public void draw(Graphics2D g2) {
        if (!collected) {
            g2.drawImage(image, worldX, worldY, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }
}
