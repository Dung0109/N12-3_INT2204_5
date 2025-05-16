@startuml

class Entity {
    - worldX : int
    - worldY : int
    - speed : int
    - up1, up2, down1, down2, left1, left2, right1, right2 : BufferedImage
    - direction : String
    - spriteCounter : int
    - spriteNum : int
    - collisionOn : boolean
    - solidArea : Rectangle
    + Entity()
}

class Player extends Entity {
    - gp : Gamepanel
    - speed : int
    - keyH : KeyHandler
    - bomb, bomb1, bomb2 : BufferedImage
    + Player(Gamepanel, KeyHandler)
    + setDefaultValues() : void
    + getPlayerImage() : void
    + update() : void
    + draw(Graphics2D) : void
}

class Monster extends Entity {
    - gp : Gamepanel
    - alive : boolean
    + Monster(Gamepanel,int,int)
    + setDefaultValues() : void
    + getMonsterImage() : void
    + update() : void
    + draw(Graphics2D) : void
}

class Bomb extends Entity {
    - gp : Gamepanel
    - image1, image2, image3 : BufferedImage
    - timer : int
    - exploded : boolean
    + Bomb(int,int,Gamepanel)
    + update() : void
    + draw(Graphics2D) : void
}

class Flame extends Entity{
    - gp : Gamepanel
    - centerImg, leftImg, rightImg, upImg, downImg : BufferedImage
    - exploded : boolean
    - timer : int
    - duration : int
    - area : Rectangle
    + Flame(int,int,String,Gamepanel)
    + update() : void
    + draw(Graphics2D) : void
}

class SpeedItem extends Entity{
    - gp : Gamepanel
    - collected : boolean
    - image : BufferedImage
    - appear : boolean
    - world[][] : int
    + SpeedItem(Gamepanel)
    + getImage() : void
    + update() : void
    + draw(Graphics2D) : void
}

class Tile{
    - image : BufferedImage
    - collision : boolean
    - isPortal : boolean
    + Tile()


}
class TileManager extends Tile{
    - gp : Gamepanel
    - tile : Tile[][]
    - mapTileNum[][] int
    + TileManager(Gamepanel)
    + getTileImage() : void
    + loadMap() :void
    + draw(Graphics2D) :void
}

@enduml
