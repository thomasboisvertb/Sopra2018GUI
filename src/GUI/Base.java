package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Base extends Field {

    protected boolean Ant;
    protected int Food;
    protected char swarm;

    public Base(int xCoordinate, int yCoordinate, boolean ant, int food,char swarm){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.Ant = ant;
        this.Food = food;
        this.swarm = swarm;
        this.polygon = null;
    }
    @Override
    public void setField(double xPosition, double yPosition, double side){

        this.side = side;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        //Creating the points for the hexagon
        Hexagon hexagon = new Hexagon(side, xPosition, yPosition);

        //creating the actual hexagon
        Polygon polygon = new Polygon(hexagon.getPoints());

        switch (swarm){

            case 'A' : polygon.setFill(Color.RED); break;

            case 'B' : polygon.setFill(Color.BLUE); break;

            case 'C' : polygon.setFill(Color.GREEN); break;

            case 'D' : polygon.setFill(Color.YELLOW); break;

            case 'E' : polygon.setFill(Color.ORANGE); break;

            case 'F' : polygon.setFill(Color.PURPLE); break;

            case 'G' : polygon.setFill(Color.BROWN); break;
        }

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
