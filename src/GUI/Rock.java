package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Rock extends Field {

    ImageView imageView;


    public Rock(int xCoordinate, int yCoordinate, boolean ant, int food){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        this.polygon = null;
    }
    @Override
    public void SetAnt(boolean isAnt) {
        return;

    }

    @Override
    public void setFood(int food) {
        return;

    }

    @Override
    public void setField(double xPosition, double yPosition, double side) {
        this.side = side;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        //Creating the points for the hexagon
        Hexagon hexagon = new Hexagon(side, xPosition, yPosition);

        //creating the actual hexagon
        Polygon polygon = new Polygon(hexagon.getPoints());

        polygon.setFill(Color.DARKGRAY);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(5);

        this.polygon = polygon;


        Image rock = new Image("/Ressources/meteor.png",this.side*3,this.side*3,true,true);

        ImageView imageview = new ImageView();

        imageview.setImage(rock);

        this.imageView = imageview;
    }

    public ImageView getRock(){
        return imageView;

    }
}
