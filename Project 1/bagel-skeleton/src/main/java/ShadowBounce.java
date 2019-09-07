import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public class ShadowBounce extends AbstractGame{
    private Point[] allPegPoints = new Point[50];
    private boolean[] allPegCollisions = new boolean[50];
    private final Point defaultPoint = new Point(512, 32);
    private Point currentBallLocation = defaultPoint;
    private final Vector2 gravity = new Vector2(0,0.15);
    private Vector2 velocity = gravity;
    private BallObject ballObject = new BallObject();

    //Initialise random location of 50 pegs on screen
    private void spawnAllPegs(){
        for (int i = 0; i < 50; i++){
            PegObject peg = new PegObject();
            allPegPoints[i] = peg.getRandomPoint();
            allPegCollisions[i] = false;
        }
    }


    private ShadowBounce(){
        spawnAllPegs();
    }

    //Run game
    public static void main(String[] args) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }



    @Override
    protected void update(Input input) {
        BallObject ball = new BallObject();
        Rectangle ballRectangle = ball.getBallImage().getBoundingBoxAt(currentBallLocation);
        //Check whether collisions occur and only load images which don't have collisions
        for (int i = 0; i < 50; i++){
            if (allPegCollisions[i]){
                continue;
            }
            PegObject peg = new PegObject();
            peg.getPegImage().draw(allPegPoints[i].x, allPegPoints[i].y);
            Rectangle pegRectangle = peg.getPegImage().getBoundingBoxAt(allPegPoints[i]);
            if (ballRectangle.intersects(pegRectangle)){
                allPegCollisions[i] = true;
            }
        }

        System.out.println(currentBallLocation.toString());
        //Reload ball if no on screen and mouse is clicked
        if (input.wasPressed(MouseButtons.LEFT) && (currentBallLocation.y > Window.getHeight() || currentBallLocation.y < 0)) {
            currentBallLocation = defaultPoint;
            velocity = gravity;
            ball.getBallImage().draw(currentBallLocation.x, currentBallLocation.y);
        } else {
            ball.getBallImage().draw(currentBallLocation.x, currentBallLocation.y);
        }
        System.out.println(velocity.toString());
        velocity = ballObject.nextVelocity(currentBallLocation,velocity);
        currentBallLocation = velocity.add(currentBallLocation.asVector()).asPoint();
        

    }
}
