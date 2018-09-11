package GUI;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static java.lang.Math.sqrt;

public class PlayingBoard {


    private Group playingBoard = new Group();
    private GridPane root;
    private Field[][] board;
    private Stage fieldWindow;


    public PlayingBoard(double side, int width, int height, FieldInfo[][] fieldInfo, Stage fieldWindow) {

        this.fieldWindow = fieldWindow;
    this.board = new Field[width][height];
    GridPane root = new GridPane();
    root.setPadding(new Insets(10,10,10,10));
    root.setHgap(10);
    root.setVgap(10);
    GridPane.setConstraints(playingBoard,1,1);
    root.getChildren().addAll(playingBoard);


        Field field;

    for (int j = 0 ; j < height ; j++){
        for (int i = 0 ; i < width ; i++){

            char type = fieldInfo[i][j].getType();
            int x = fieldInfo[i][j].getX();
            int y = fieldInfo[i][j].getY();
            int food = fieldInfo[i][j].getFood();


            if (j % 2 == 0) {

                // field is a base
                if (type -'A' >= 0 && type-'A' <=26) {
                    field = new Base(x,y,false,food,type,this.fieldWindow,null);
                    field.setField(i * getH(side) * 2 + getH(side), j * side * 1.5 + side * 2, side);
                    playingBoard.getChildren().add(field.polygon);
                }

                // field is a plain
                else if ('.'-type == 0){
                    field = new Plain(x,y,false,food,this.fieldWindow);
                    field.setField(i * getH(side) * 2 + getH(side), j * side * 1.5 + side * 2, side);
                    playingBoard.getChildren().add(field.polygon);
                }

                //field is a rock
                else if ('#'-type == 0){
                    field = new Rock(x, y, false, food,this.fieldWindow);
                    field.setField(i * getH(side) * 2 + getH(side) * 2, j * side * 1.5 + side * 2, side);
                    ((Rock)field).getRock().setX(field.xPosition - side / 0.65);
                    ((Rock)field).getRock().setY(field.yPosition - side / 0.90);
                    playingBoard.getChildren().add(field.polygon);
                    playingBoard.getChildren().add(((Rock)field).getRock());
                }

                //field is an antlion
                else{
                    field = new Antlion(x,y,false,food,this.fieldWindow);
                    field.setField(i * getH(side) * 2 + getH(side) * 2, j * side * 1.5 + side * 2, side);
                    ((Antlion)field).getAntlion().setX(field.xPosition - side / 1.3);
                    ((Antlion)field).getAntlion().setY(field.yPosition - side / 1.7);
                    playingBoard.getChildren().add(field.polygon);
                    playingBoard.getChildren().add(((Antlion)field).getAntlion());
                }


                this.board[i][j] = field;


            }
            else {

                // field is a base
                if (type - 'A' >= 0 &&  type - 'A' <= 26) {
                    field = new Base(x, y, false, food,type,this.fieldWindow,null);
                    field.setField(i * getH(side) * 2 + getH(side) * 2, j * side * 1.5 + side * 2, side);
                    playingBoard.getChildren().add(field.polygon);
                }

                // field is a plain
                else if ('.' - type == 0) {
                    field = new Plain(x, y, false, food,this.fieldWindow);
                    field.setField(i * getH(side) * 2 + getH(side) * 2, j * side * 1.5 + side * 2, side);
                    playingBoard.getChildren().add(field.polygon);
                }

                //field is a rock
                else if ('#' - type == 0) {
                    field = new Rock(x, y, false, food,this.fieldWindow);
                    field.setField(i * getH(side) * 2 + getH(side) * 2, j * side * 1.5 + side * 2, side);
                    ((Rock)field).getRock().setX(field.xPosition - side / 0.65);
                    ((Rock)field).getRock().setY(field.yPosition - side / 0.90);
                    playingBoard.getChildren().add(field.polygon);
                    playingBoard.getChildren().add(((Rock)field).getRock());
                }

                //field is an antlion
                else {
                    field = new Antlion(x, y, false, food,this.fieldWindow);
                    field.setField(i * getH(side) * 2 + getH(side) * 2, j * side * 1.5 + side * 2, side);
                    ((Antlion)field).getAntlion().setX(field.xPosition - side / 1.3);
                    ((Antlion)field).getAntlion().setY(field.yPosition - side / 1.7);
                    playingBoard.getChildren().add(field.polygon);
                    playingBoard.getChildren().add(((Antlion)field).getAntlion());
                }

                this.board[i][j] = field;


            }


        }
    }
    this.root = root;


}

    private double getH(double side) {
        return ((sqrt(3)/2)*side);
    }


    public GridPane getBoard(){
        return root;
    }

    public Group getPlayingBoard(){
        return this.playingBoard;
    }

    public Field getField(int x, int y){
        return board[x][y];
    }
}
