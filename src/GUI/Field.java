package GUI;

import javafx.scene.shape.Polygon;

public abstract class Field {

    // coordinate values on the actual playing board
    protected int xCoordinate;
    protected int yCoordinate;
    // position on the gui
    protected double xPosition;
    protected double yPosition;

    protected double side;

    protected Polygon polygon;


    public abstract void SetAnt(boolean isAnt);

    public abstract void setFood(int food);

    public Polygon getPolygon() {
        return this.polygon;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public abstract void setField (double xPosition, double yPosition, double side);

}
