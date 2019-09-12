import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class PegObject {
    private static final Image pegImage = new Image("res/peg.png");
    private Point point = randomLocation();
    private Boolean collided = false;
    private Rectangle boundingBox = pegImage.getBoundingBoxAt(point);

    //Generate a random point (x, y)
    private Point randomLocation(){
        int HALF_PEG_IMAGE_SIZE = 16;
        int maxHeight = 768 - HALF_PEG_IMAGE_SIZE;
        int minHeight = 100;
        int maxWidth = 1024 - HALF_PEG_IMAGE_SIZE;
        int minWidth = 0;
        return new Point(ThreadLocalRandom.current().nextInt(minWidth + HALF_PEG_IMAGE_SIZE, maxWidth),
                ThreadLocalRandom.current().nextInt(minHeight, maxHeight));
    }

    //Draw peg in Window
    public void drawPeg(){
        getPegImage().draw(point.x, point.y);
    }

    public Image getPegImage() {
        return pegImage;
    }

    public Boolean getCollided() {
        return collided;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setCollided(Boolean collided) {
        this.collided = collided;
    }
}
