package GUI;

import java.util.ArrayList;
import java.util.List;

public class FieldInfo {

    private int x;
    private int y;
    private char type;
    private int food;
    private Ant ant;
    private List markers = new ArrayList<Marker>(2);

    public FieldInfo(int x, int y, char type, int food){
        this.x = x;
        this.y = y;
        this.type = type;
        this.food = food;
    }

    public int getX() {
        return x;
    }

    public char getType() {
        return type;
    }

    public int getY() {
        return y;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    public List getMarkers() {
        return markers;
    }

    public void addMarkers(Marker marker) {
        if (this.markers.isEmpty()){
            this.markers.add(marker);
        }
        else{

        for (Object marks : this.markers){
            Marker mark = (Marker) marks;

            if (mark.getId() == marker.getId()){
                this.markers.remove(mark);
            }
        }
        this.markers.add(marker);
        }

    }
}
