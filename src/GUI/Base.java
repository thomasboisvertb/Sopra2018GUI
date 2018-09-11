package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.List;

public class Base extends Field {

    protected boolean Ant;
    protected int food;
    protected char swarm;
    private Stage stage;

    public Base(int xCoordinate, int yCoordinate, boolean ant, int food,char swarm,Stage fieldWindow,List<Marker> markers){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.Ant = ant;
        this.food = food;
        this.swarm = swarm;
        this.polygon = null;
        this.stage = fieldWindow;
        this.markers = markers;
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
        polygon.setOnMouseClicked(e -> showInfoPopup());


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

    public void SetAnt(boolean isAnt) {
        this.Ant = isAnt;

    }

    @Override
    public void setFood(int food) {
        this.food = food;

    }

    private void showInfoPopup() {

        this.stage.setTitle("Type : Base"+swarm);

        Button button = new Button("Close");
        button.setOnAction(e -> stage.close());


        Label food = new Label("Food :" + this.food);
        Label coordinate = new Label("x : " + this.xCoordinate + " y : " + this.yCoordinate);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(food, coordinate);

        //add the markers
        for (int i = 0 ; i<this.markers.size() ; i++){
            //get one marker
            Marker oneMarker = (Marker)this.markers.get(i);
            //get the id
            String swarm = Character.toString(oneMarker.getId());
            boolean[] registers = oneMarker.getMarkers();
            Label markers = new Label("Markers "+swarm+" :"+registers[0]+","+registers[1]+","+registers[2]+","
                    +registers[3]+","+registers[4]+","+registers[5]+","+registers[6]);

            layout.getChildren().addAll(markers);
        }
        layout.getChildren().add(button);
        Scene scene = new Scene(layout);

        stage.setScene(scene);
        stage.show();
    }
}
