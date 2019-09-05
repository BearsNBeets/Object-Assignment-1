import bagel.*;

public class MainGame extends AbstractGame{
    private Image peg;
    private Image ball;
    public double[] allPegXPoints = new double[50];
    public double[] allPegYPoints = new double[50];

    private void test(){
        for (int i = 0; i < 50; i++){
            PegObject tester = new PegObject();
            allPegXPoints[i] = tester.getRandomX();
            allPegYPoints[i] = tester.getRandomY();
            System.out.println(allPegXPoints[i] + ", " + allPegYPoints[i]);
        }
    }

    public MainGame(){
        peg = new Image("res/peg.png");
        ball = new Image("res/ball.png");
        test();
    }

    public static void main(String[] args) {
        MainGame game = new MainGame();
        game.run();
    }



    @Override
    protected void update(Input input) {
        for (int i = 0; i < 50; i++){
            peg.draw(allPegXPoints[i], allPegYPoints[i]);
        }
    }
}
