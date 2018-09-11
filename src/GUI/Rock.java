package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Rock extends Field {

    ImageView imageView;
    private Stage stage;


    public Rock(int xCoordinate, int yCoordinate, boolean ant, int food, Stage stage){
        this.stage = stage;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

        this.polygon = null;
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
        polygon.setOnMouseClicked(e -> showInfoPopup());

        this.polygon = polygon;


        Image rock = new Image("/Ressources/meteor.png",this.side*3,this.side*3,true,true);

        ImageView imageview = new ImageView();

        imageview.setImage(rock);
        imageview.setOnMouseClicked(e -> showInfoPopup());

        this.imageView = imageview;
    }

    public ImageView getRock(){
        return imageView;

    }

    private void showInfoPopup() {

        this.stage.setTitle("Type : Rock");

        Button button = new Button("Close");
        button.setOnAction(e -> stage.close());
        Label coordinate = new Label("x : " + this.xCoordinate + " y : " + this.yCoordinate);
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(coordinate,button);
        Scene scene = new Scene(layout);

        stage.setScene(scene);
        stage.show();
    }
}
