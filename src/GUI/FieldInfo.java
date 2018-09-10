package GUI;

public class FieldInfo {

    private int x;
    private int y;
    private char type;
    private int food;
    private Ant ant;

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
}
