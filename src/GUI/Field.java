package GUI;

import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public abstract class Field {

    // coordinate values on the actual playing board
    protected int xCoordinate;
    protected int yCoordinate;
    // position on the gui
    protected double xPosition;
    protected double yPosition;

    protected double side;

    protected Polygon polygon;

    protected List markers = new ArrayList<Marker>(2);

    protected int food;

    public void setMarkers(List<Marker> markers){
        this.markers = markers;
    }

    public abstract void setFood(int food);

    public abstract void setField (double xPosition, double yPosition, double side);



}
