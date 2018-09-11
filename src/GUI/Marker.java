package GUI;

public class Marker {

    private char id;
    private boolean[] markers = new boolean[7];

    public Marker(char id, boolean[] markers) {

        this.id = id;
        this.markers = markers;
    }
    public boolean[] getMarkers() {
        return markers;
    }

    public void setMarkers(boolean markers, int index) {
        this.markers[index] = markers;
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }
}
