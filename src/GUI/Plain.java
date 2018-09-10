package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Plain extends Field {

    protected boolean Ant;
    protected int Food;

    public Plain(int xCoordinate, int yCoordinate, boolean ant, int food){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.Ant = ant;
        this.Food = food;

        this.polygon = null;
    }

    public void setField(double xPosition, double yPosition, double side){

        this.side = side;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        //Creating the points for the hexagon
        Hexagon hexagon = new Hexagon(side, xPosition, yPosition);

        //creating the actual hexagon
        Polygon polygon = new Polygon(hexagon.getPoints());

        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(5);

        this.polygon = polygon;

    }

    @Override
    public void SetAnt(boolean isAnt) {
        this.Ant = isAnt;

    }

    @Override
    public void setFood(int food) {
        this.Food = food;

    }
}
