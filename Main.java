import ecs100.UI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Main {
    public double X = 200;
    public double Y = 300;
    public double size = 100;
    public double[] orig_xPoints = {X, X+size, X+size, X};
    public double[] orig_yPoints = {Y, Y, Y+size, Y+size};

    //use these for if you want to manually specify the origin
    public double originX = X + size/2;
    public double originY = Y + size/2;

    /*Use this if you want the origin dynamically allocated with the average of the points*/
    /*public double originX = (orig_xPoints[0]+orig_xPoints[1]+orig_xPoints[2]+orig_xPoints[3])/4;
    public double originY = (orig_yPoints[0]+orig_yPoints[1]+orig_yPoints[2]+orig_yPoints[3])/4;*/
    public double theta = 0;
    public static void main(String[] args) {
        UI.initialise();
        Main main = new Main();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(main::drawSquare, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void drawSquare(){
        if(theta<360) {
                double[] xPoints = new double[4];
                double[] yPoints = new double[4];
                for (int i = 0; i < orig_yPoints.length; ++i) {
                    double[] buffer = this.rotation(orig_xPoints[i], orig_yPoints[i], theta, i);
                    xPoints[i] = buffer[0];
                    yPoints[i] = buffer[1];
                }
                UI.clearGraphics();
                UI.drawPolygon(xPoints, yPoints, 4);
                ++theta;
            }
        }

    public double[] rotation(double x, double y, double theta, int i){
        x -= originX;
        y -= originY;
        double rotX = (x * Math.cos(Math.toRadians(theta))) - (y * Math.sin(Math.toRadians(theta)));
        double rotY = (x * Math.sin(Math.toRadians(theta))) + (y * Math.cos(Math.toRadians(theta)));
        rotX+=originX;
        rotY+=originY;
        System.out.printf("X: %2f | Y: %2f | ANG: %2f | I: %2f \n", rotX, rotY, theta, (double)i);
        return new double[]{rotX, rotY};
    }
}
