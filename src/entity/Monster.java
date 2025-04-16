package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.IOException;


public class Monster extends Entity  {

    GamePanel gp;
    public int directionX =1, directionY=0;

    public Monster(GamePanel gp,int x, int y) {
        this.x = x;
        this.y = y;
        this.gp = gp;
        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/boy_right_2.png"));
//            wall = ImageIO.read(getClass().getResourceAsStream("wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        x += speed * directionX;
        y += speed * directionY;
        if (x <= 100 || x >= 500-down1.getWidth()) {
            directionX *= -1;
        }
        if (y <= 100 || y >= 500-down1.getHeight()) {
            directionY *= -1;
        }


    }

    public void draw(Graphics2D g2) {


        BufferedImage image = null;
        image = down1;

        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }

}
