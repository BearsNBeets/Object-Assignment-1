import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;

public class BallObject {
    private Image ballImage = new Image("res/ball.png");

    //Calculate position of ball at next frame using velocity and current location
    public Point nextPoint(Point currentBallLocation, Vector2 velocity){
        Vector2 pointAsVector = currentBallLocation.asVector();
        velocity = velocity.normalised().add(pointAsVector);
        return velocity.asPoint();
    }

    //Ball image getter
    public Image getBallImage() {
        return ballImage;
    }
}
