package tile;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager{
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/road12.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
            g2.drawImage(tile[6].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col++;
            x += gp.TILE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }

        g2.drawImage(tile[0].image, 0, 0, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawImage(tile[1].image, 100, 100, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawImage(tile[2].image, 200, 200, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawImage(tile[0].image, 300, 300, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawImage(tile[1].image, 400, 400, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawImage(tile[2].image, 500, 500, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}

