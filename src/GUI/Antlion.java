package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Antlion extends Field {

    private ImageView imageView;


    public Antlion(int xCoordinate, int yCoordinate, boolean ant, int food){


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

        polygon.setFill(Color.CHOCOLATE);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(5);

        this.polygon = polygon;

        Image antlion = new Image("/Ressources/antlion.png",this.side*1.5,this.side*1.5,true,true);

        ImageView imageview = new ImageView();

        imageview.setImage(antlion);

        imageview.setPreserveRatio(true);

        this.imageView = imageview;
    }

    public ImageView getAntlion(){
        return imageView;
    }
}
