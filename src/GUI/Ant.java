package GUI;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ant {

    private boolean food;
    private int id;
    private int pc;
    private int restTime;
    private char Swarm;
    private DIRECTION direction;
    private boolean[] registers = new boolean[6];
    private int xCoordinate;
    private int yCoordinate;
    private Stage stage;
    private double side;


    public Ant (double side, DIRECTION directions, Stage stage, char swarm) {
        this.Swarm = swarm;
        this.stage = stage;
        this.side = side;
        this.direction = directions;

    }

    public Ant (double side, String directions, Stage stage,char swarm) {

        this.stage = stage;
        this.Swarm = swarm;
        this.side = side;

        DIRECTION dir = DIRECTION.NORTHWEST;

        if
        ("northwest".equals(directions)) {dir = DIRECTION.NORTHWEST;}
        else if
        ("northeast".equals(directions)) {dir = DIRECTION.NORTHEAST;}
        else if
        ("east".equals(directions)) {dir = DIRECTION.EAST;}
        else if
        ("southeast".equals(directions)) {dir = DIRECTION.SOUTHEAST;}
        else if
        ("southwest".equals(directions)) {dir = DIRECTION.SOUTHWEST;}
        else if
        ("west".equals(directions)) {dir = DIRECTION.WEST;}



        this.direction = dir;

    }

    private void showInfoPopup() {




        this.stage.setTitle("ant:"+this.id);
        Label food = new Label("Food :"+this.food);
        Label swarm = new Label("Swarm :"+this.Swarm);
        Label pc = new Label ("Pc :"+this.pc);
        Label restTime = new Label("Rest Time :"+this.restTime );
        Label registers = new Label("registers :"+this.registers[0]+","+this.registers[1]+","+this.registers[2]+","
                +this.registers[3]+","+this.registers[4]+","+this.registers[5]);


        Button button = new Button("Close");
        button.setOnAction(e->stage.close());

        VBox layout = new VBox();
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(pc,food,swarm,restTime,registers,button);



        Scene scene = new Scene(layout);

        stage.setScene(scene);
        stage.show();


    }

    public ImageView getImageView(){

        Image ant;

        if (this.food) {
            try {
                ant = new Image("/Ressources/ant" + Character.toString(Swarm) + "food.png", this.side * 1.2, this.side * 1.2, true, true);
            } catch ( NullPointerException e) {
                ant = new Image("/Ressources/antG.png", this.side * 1.2, this.side * 1.2, true, true);
            }
            catch ( IllegalArgumentException e) {
                ant = new Image("/Ressources/antG.png", this.side * 1.2, this.side * 1.2, true, true);
            }

        }
        else {
            try {
                ant = new Image("/Ressources/ant" + Character.toString(Swarm) + ".png", this.side * 1.2, this.side * 1.2, true, true);
            } catch ( NullPointerException e) {
                ant = new Image("/Ressources/antG.png", this.side * 1.2, this.side * 1.2, true, true);
            }
            catch ( IllegalArgumentException e) {
                ant = new Image("/Ressources/antG.png", this.side * 1.2, this.side * 1.2, true, true);
            }
        }


        ImageView imageview = new ImageView();

        imageview.setImage(ant);

        imageview.setOnMouseClicked(e -> showInfoPopup());

        switch (this.direction) {

            case NORTHWEST: imageview.setRotate(-30); break;
            case NORTHEAST: imageview.setRotate(30); break;
            case EAST: imageview.setRotate(90); break;
            case SOUTHEAST: imageview.setRotate(150); break;
            case SOUTHWEST: imageview.setRotate(210); break;
            case WEST: imageview.setRotate(270); break;
        }


        return imageview;
    }

    public void SetDirection(DIRECTION directions){
        this.direction = directions;
    }

    public void setSwarm(char swarm) {
        Swarm = swarm;
    }

    public char getSwarm() {
        return Swarm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFood() {
        return food;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public boolean[] getRegisters() {
        return registers;
    }

    public void setRegisters(int index, boolean value) {
        this.registers[index] = value;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(String directions) {

        DIRECTION dir = DIRECTION.SOUTHEAST;

        if
        ("northwest".equals(directions)) {dir = DIRECTION.NORTHWEST;}
        else if
        ("northeast".equals(directions)) {dir = DIRECTION.NORTHEAST;}
        else if
        ("east".equals(directions)) {dir = DIRECTION.EAST;}
        else if
        ("southeast".equals(directions)) {dir = DIRECTION.SOUTHEAST;}
        else if
        ("southwest".equals(directions)) {dir = DIRECTION.SOUTHWEST;}
        else if
        ("west".equals(directions)) {dir = DIRECTION.WEST;}
        this.direction = dir;

    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }
}
