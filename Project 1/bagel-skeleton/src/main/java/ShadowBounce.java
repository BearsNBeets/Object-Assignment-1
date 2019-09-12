import bagel.*;

public class ShadowBounce extends AbstractGame{
    private static final int NUMBEROFPEGS = 50;
    private BallObject ball = new BallObject();
    private PegObject[] allPegPoints = new PegObject[NUMBEROFPEGS];

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
        //Check whether collisions occur and only draw images which don't have collisions
        for (int i = 0; i < NUMBEROFPEGS; i++){
            //Skip collided pegs
            if (allPegPoints[i].getCollided()){
                continue;
            }
            //Draw non-collided pegs
            allPegPoints[i].drawPeg();
            //Check collision between ball and peg
            if (ball.boundingBox().intersects(allPegPoints[i].getBoundingBox())){
                //Record which pegs have collided with ball to ensure they aren't drawn again
                allPegPoints[i].setCollided(true);
            }
        }

        //Spawn ball if offscreen and left click
        ball.ballOffscreen(input);
        //Calculate velocity and location for next frame
        ball.calculateNextFrame();
    }
}
