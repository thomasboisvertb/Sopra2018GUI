package GUI;

import javafx.scene.layout.Region;

import static java.lang.Math.sqrt;

public class Hexagon {

    private double [] points;
    private double centerX;
    private double centerY;


    public Hexagon(double side, double x, double y){
        this.centerX = x;
        this.centerY = y;
        points = new double[12];
        //     X                          Y
        points[0] = x;              points[1] = y+side;
        points[2] = x+getH(side);   points[3] = y+side/2;
        points[4] = x+getH(side);   points[5] = y-side/2;
        points[6] = x;              points[7] = y-side;
        points[8] = x-getH(side);   points[9] = y-side/2;
        points[10] = x-getH(side);  points[11] = y+side/2;

    }

    private double getH(double side) {
        return ((sqrt(3)/2)*side);
    }

    public double getX() {
            return this.centerX;
    }

    public double getY() {
            return this.centerY;
    }

    public double [] getPoints(){
        return points;
    }
}
