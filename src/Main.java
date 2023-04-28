import ecs100.UI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public double X = 200;
    public double Y = 50;
    public double size = 100;
    public double rotation = 45;
    public double[] orig_xPoints = {X, X+size, X+size, X};
    public double[] orig_yPoints = {Y, Y, Y+size, Y+size};
    public double theta = 0;
    public static void main(String[] args) {
        UI.initialise();
        Main main = new Main();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(main::drawSquare, 0, 1, TimeUnit.MILLISECONDS);
    }

    public void drawSquare(){
        if(theta<360) {
                double[] xPoints = new double[4];
                double[] yPoints = new double[4];
                //UI.erasePolygon(xPoints, yPoints, 4);
                for (int i = 0; i < orig_yPoints.length; ++i) {
                    double[] buffer = this.rotation(orig_xPoints[i], orig_yPoints[i], theta, i);
                    xPoints[i] = buffer[0];
                    yPoints[i] = buffer[1];
                }
                UI.drawPolygon(xPoints, yPoints, 4);
                ++theta;
            }
        }

    public double[] rotation(double x, double y, double theta, int i){
        double rotX = (X+(x * Math.cos(theta)) - (Y+(y * Math.sin(theta))));
        double rotY = (X+(x * Math.sin(theta)) + (Y+(y * Math.cos(theta))));
        System.out.printf("X: %2f | Y: %2f \n", rotX, rotY);
        return new double[]{rotX, rotY};
    }
}