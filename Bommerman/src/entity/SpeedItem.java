package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpeedItem extends Entity {
    GamePanel gp;
    public boolean collected = false;
    public BufferedImage image;
    public boolean appear = false;
    public int world[][];

    public SpeedItem(GamePanel gp) {
        this.gp = gp;
        worldX = 100;
        worldY = 150;
        solidArea = new Rectangle(0, 0, 35, 40);
        world = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
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
            gp.player.speed = 8; 
            gp.player.speedBoostTimer = 300; 
            gp.tileM.mapTileNum[gp.item.worldX/ gp.TILE_SIZE][gp.item.worldY/ gp.TILE_SIZE] = 0;
        }

            if (gp.tileM.mapTileNum[gp.item.worldX/ gp.TILE_SIZE][gp.item.worldY/ gp.TILE_SIZE] == 4) {
                gp.item.appear = true;
        }

    }

    public void draw(Graphics2D g2) {
            if (!collected) {
                g2.drawImage(image, worldX, worldY, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
    }
}
