import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public class BallObject {
    private Image ballImage = new Image("res/ball.png");
    private final Vector2 gravity = new Vector2(0,0.15);
    private Vector2 velocity;

    //Calculate vector for total gravity on ball at next frame
    public Vector2 nextGravity(Point currentBallLocation, Vector2 velocity){
        Vector2 pointAsVector = currentBallLocation.asVector();
        velocity = velocity.add(gravity);
        return velocity;
    }

    public Rectangle boundingBox(Point currentBallLocation){
        return ballImage.getBoundingBoxAt(currentBallLocation);
    }

    public void newBall(){

    }

    //Ball image getter
    public Image getBallImage() {
        return ballImage;
    }
}
