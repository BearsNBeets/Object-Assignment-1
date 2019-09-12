import bagel.Image;
import bagel.Input;
import bagel.MouseButtons;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public class BallObject {
    private static final Image ballImage = new Image("res/ball.png");
    private static final Vector2 gravity = new Vector2(0,0.15);
    private static final Point defaultPoint = new Point(512, 32);
    private Point currentLocation = new Point(-40,-40);
    private Vector2 velocity = gravity;

    //Calculate values for location and velocity at next frame
    public void calculateNextFrame(){
        //If the ball hits either side of the window, reverse horizontal direction
        if (currentLocation.x < 0 || currentLocation.x > Window.getWidth()){
            double tempVelocityX = velocity.asPoint().x * -1;
            Point tempVelocityPoint = new Point(tempVelocityX, velocity.asPoint().y);
            velocity = tempVelocityPoint.asVector();
        }
        //Calculate vector for total gravity on ball at next frame. Increase affect of gravity
        velocity = velocity.add(gravity);
        //Calculate ball location at next frame
        currentLocation = velocity.add(currentLocation.asVector()).asPoint();
    }

    //Create bounding box around ball
    public Rectangle boundingBox(){
        return ballImage.getBoundingBoxAt(currentLocation);
    }

    //Check whether ball was offscreen and respawn ball if clicked
    public void ballOffscreen(Input input){
        //Reload ball if not on screen and mouse is clicked
        if (input.wasPressed(MouseButtons.LEFT) && (currentLocation.y > Window.getHeight() || currentLocation.y < 0)) {
            currentLocation = defaultPoint;
            //Calculate new velocity of ball depending on mouse position. Divide vector to mouse into increments of 10
            // so each frame is movement of 10 pixels in mouse direction
            Vector2 mouseVector = input.directionToMouse(currentLocation);
            double mouseDistanceCut = mouseVector.length() / 10;
            velocity = gravity.add(mouseVector.div(mouseDistanceCut));
            drawBall();
        } else {
            //Otherwise draw ball using previously calculated position
            drawBall();
        }
    }

    //Draw ball in Window
    public void drawBall(){
        getBallImage().draw(currentLocation.x, currentLocation.y);
    }

    public Image getBallImage() {
        return ballImage;
    }
}
