import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public class ShadowBounce extends AbstractGame{
    private static final int NUMBEROFPEGS = 50;
    private PegObject[] allPegPoints = new PegObject[NUMBEROFPEGS];
    private static final Point defaultPoint = new Point(512, 32);
    private Point currentBallLocation = new Point(-40,-40);
    private final Vector2 gravity = new Vector2(0,0.15);
    private Vector2 velocity = gravity;
    private BallObject ball = new BallObject();

    //Initialise random location of 50 pegs on screen
    private void spawnAllPegs(){
        for (int i = 0; i < NUMBEROFPEGS; i++){
            allPegPoints[i] = new PegObject();
        }
    }

    //Initialise all peg locations before running game
    private ShadowBounce(){
        spawnAllPegs();

    }

    //Run game
    public static void main(String[] args) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }

    //Update each frame
    @Override
    protected void update(Input input) {
        //Draw ball object and rectangle for collision detection

        Rectangle ballRectangle = ball.boundingBox(currentBallLocation);

        //Check whether collisions occur and only load images which don't have collisions
        for (int i = 0; i < NUMBEROFPEGS; i++){
            if (allPegPoints[i].getCollided()){
                continue;
            }
            //Draw peg and rectangle for collision detection
            allPegPoints[i].getPegImage().draw(allPegPoints[i].getPoint().x, allPegPoints[i].getPoint().y);
            Rectangle pegRectangle = allPegPoints[i].boundingBox();
            //Record which pegs have collided with ball to ensure they aren't drawn again
            if (ballRectangle.intersects(pegRectangle)){
                allPegPoints[i].setCollided(true);
            }
        }

        System.out.println(currentBallLocation.toString());

        //Reload ball if not on screen and mouse is clicked
        if (input.wasPressed(MouseButtons.LEFT) && (currentBallLocation.y > Window.getHeight() || currentBallLocation.y < 0)) {
            //System.out.println("Respawned ball");
            currentBallLocation = defaultPoint;

            //Calculate new velocity of ball depending on mouse position. Divide vector to mouse into increments of 10
            // so each frame is
            Vector2 mouseVector = input.directionToMouse(currentBallLocation);
            double mouseDistanceCut = mouseVector.length() / 10;
            velocity = gravity.add(mouseVector.div(mouseDistanceCut));
            ball.getBallImage().draw(currentBallLocation.x, currentBallLocation.y);

        //Otherwise draw ball using previously calculated position
        } else {
            ball.getBallImage().draw(currentBallLocation.x, currentBallLocation.y);
        }

        //If the ball hits either side of the window, reverse horizontal direction
        if (currentBallLocation.x < 0 || currentBallLocation.x > Window.getWidth()){
            double tempVelocityX = velocity.asPoint().x * -1;
            Point tempVelocityPoint = new Point(tempVelocityX, velocity.asPoint().y);
            velocity = tempVelocityPoint.asVector();
        }

        System.out.println(velocity.toString());

        //Increase affect of gravity on ball and calculate position for next frame
        velocity = ball.nextGravity(currentBallLocation,velocity);
        currentBallLocation = velocity.add(currentBallLocation.asVector()).asPoint();


    }
}
