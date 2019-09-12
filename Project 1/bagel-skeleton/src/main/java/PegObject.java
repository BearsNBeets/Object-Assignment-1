import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class PegObject {
    private Image pegImage = new Image("res/peg.png");
    private Boolean collided = false;
    //Initialise a new random point for a peg
    private Point point = new Point(randomLocation('x'), randomLocation('y'));

    //Generate a random x value or y value
    private double randomLocation(char axis){
        double newLocation = 0;
        //Generate random value for y
        int HALFPEGIMAGESIZE = 16;
        if (axis == 'y'){
            int maxHeight = 768 - HALFPEGIMAGESIZE;
            int minHeight = 100;
            newLocation = ThreadLocalRandom.current().nextInt(minHeight, maxHeight);
            //If part of the image goes beyond the top edge, regenerate random y value
            while (newLocation - HALFPEGIMAGESIZE < 0){
                newLocation = ThreadLocalRandom.current().nextInt(minHeight, maxHeight);
            }
        //Generate random value for x
        } else {
            int maxWidth = 1024 - HALFPEGIMAGESIZE;
            int minWidth = 0;
            newLocation = ThreadLocalRandom.current().nextInt(minWidth, maxWidth);
            //If part of the image goes beyond the left edge, regenerate random x value
            while (newLocation - HALFPEGIMAGESIZE < 0){
                newLocation = ThreadLocalRandom.current().nextInt(minWidth, maxWidth);
            }
        }
        return newLocation;
    }

    public Rectangle boundingBox(){
        return pegImage.getBoundingBoxAt(point);
    }

    //Random point for peg getter
    public Point getPoint() {
        return point;
    }

    //Peg image getter
    public Image getPegImage() {
        return pegImage;
    }

    public Boolean getCollided() {
        return collided;
    }

    public void setCollided(Boolean collided) {
        this.collided = collided;
    }
}
