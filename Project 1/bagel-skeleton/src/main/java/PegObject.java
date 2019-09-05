import java.util.concurrent.ThreadLocalRandom;

public class PegObject {
    private double randomX = randomLocation('x');
    private double randomY = randomLocation('y');
    private final int HALFPEGIMAGESIZE = 16;

    private double randomLocation(char axis){
        double newLocation = 0;
        if (axis == 'y'){
            int maxHeight = 768 - HALFPEGIMAGESIZE;
            int minHeight = 100;
            newLocation = ThreadLocalRandom.current().nextInt(minHeight, maxHeight);
            while (newLocation - HALFPEGIMAGESIZE < 0){
                newLocation = ThreadLocalRandom.current().nextInt(minHeight, maxHeight);
            }
        } else {
            int maxWidth = 1024 - HALFPEGIMAGESIZE;
            int minWidth = 0;
            newLocation = ThreadLocalRandom.current().nextInt(minWidth, maxWidth);
            while (newLocation - HALFPEGIMAGESIZE < 0){
                newLocation = ThreadLocalRandom.current().nextInt(minWidth, maxWidth);
            }
        }
        return newLocation;
    }

    public double getRandomX() {
        return randomX;
    }

    public double getRandomY() {
        return randomY;
    }
}
