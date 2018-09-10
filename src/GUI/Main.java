package GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application{

    private Button closeButton;
    private Button start;
    private Button backward;
    private Button forward;
    private Button autoplay;
    private Button stop;
    private VBox RoundInputLayout;
    private VBox control;
    private VBox autocontrol;
    private TextField roundInput;

    private int presentRound;
    private int maxRound;
    private Timeline timeline;

    private int height;
    private int width;
    private double side;
    private JsonReader parser;
    private PlayingBoard playingBoard;
    private FieldInfo[][] fieldInfos;
    private FieldInfo[][] originalFI;
    private List ants = new ArrayList<ImageView>();
    private Stage antInfo;

    @Override
    public void start(Stage primaryStage){

        antInfo = new Stage();
        antInfo.setTitle("id :  ");
        //antInfo.setMinHeight(300);
        //antInfo.setMinWidth(250);
        antInfo.setIconified(true);
        antInfo.setAlwaysOnTop(true);

        primaryStage.setTitle("Sopra 2018");



        final FileChooser fileChooser = new FileChooser();

        final Button openButton = new Button("Open a Json File...");
        GridPane.setConstraints(openButton,0,2);


        openButton.setOnAction( e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                String optFile= file.getAbsolutePath();
                try {
                    this.parser = new JsonReader(optFile,antInfo);
                } catch (IOException e1) {
                    Alert alert = new Alert();
                    alert.display("Error","file not found");

                } catch (ParseException e1) {
                    Alert alert = new Alert();
                    alert.display("Error","Invalid file format");
                }
            }

            this.width = parser.getWidth();
                this.height = parser.getHeight();
                this.side = parser.calculateSide(width, height);
                this.maxRound = parser.getRounds().size();

        });
        start = new Button("Start");
        start.setPrefSize(100,30);
        GridPane.setConstraints(start,0,1);

        //boarderPane setting the board when pressing start
        start.setOnAction( e-> {

            if (this.parser == null){
                Alert alert = new Alert();
                alert.display("Missing file","Please select a file");
            }
            else {
                //building playing board
                this.playingBoard = new PlayingBoard(side, width, height, parser.getBoard());

                //initializing the two FieldInfo arrays
                originalFI = new FieldInfo[width][height];
                this.fieldInfos = parser.getBoard();

                //saving the original fieldInfo for the backwards function
                for (int i = 0; i < width; i++) {
                    System.arraycopy(fieldInfos[i], 0, originalFI[i], 0, height);
                }

                //board scene setup and ant setup
                playingBoard.getBoard().getChildren().addAll(control,autocontrol, closeButton,RoundInputLayout);
                Scene mainScene = new Scene(playingBoard.getBoard());

                setAnts(fieldInfos, playingBoard);
                primaryStage.setScene(mainScene);
                primaryStage.centerOnScreen();
            }});




        autoplay = new Button("Autoplay");
        autoplay.setOnAction(e-> {
            if (presentRound < maxRound) {
                timeline = new Timeline(new KeyFrame(
                        Duration.millis(600),
                        ae -> {
                            if (presentRound==maxRound-1) timeline.stop();
                            calculateNextRound(((ArrayList<Round>) parser.getRounds()).get(this.presentRound), fieldInfos);
                            presentRound++;
                            setAnts(fieldInfos, playingBoard);


                        }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            }
                }
        );

        stop = new Button("Stop");

        stop.setOnAction(e->{
            if (this.timeline != null){
                timeline.stop();
                timeline = null;
            }
        });








        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        Label top = new Label("Welcome to\nSopra 2018");
        HBox welcome = new HBox(top);
        GridPane.setConstraints(welcome,0,0);
        welcome.setAlignment(Pos.CENTER);

        HBox startBox = new HBox(start);
        startBox.setAlignment(Pos.CENTER);
        GridPane.setConstraints(startBox,0,1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().addAll(openButton,startBox,welcome);



        //creating new scene
        Scene theScene = new Scene(gridPane,300, 400 );
        //adding the scene to the Stage
        primaryStage.setScene( theScene );
        primaryStage.centerOnScreen();
        primaryStage.show();



        //initializing my boarderPane where the buttons and the playingboard go
        stop.setPrefSize(100,30);
        autoplay.setPrefSize(100,30);
        autocontrol = new VBox();
        GridPane.setConstraints(autocontrol,0,0);
        autocontrol.getChildren().addAll(autoplay,stop);


        forward = new Button("Forward");
        forward.setPrefSize(100,30);

        backward = new Button("Backward");
        backward.setPrefSize(100,30);

        control = new VBox();
        GridPane.setConstraints(control,2,0);
        control.getChildren().addAll(forward,backward);

        Button setRound = new Button("Set Round");
        setRound.setPrefSize(100,30);

        roundInput  = new TextField();
        roundInput.setMaxWidth(100);
        roundInput.setText(""+presentRound);

        roundInput.setOnKeyPressed(e -> {
                if (e.getCode().equals(KeyCode.ENTER)){
                    int index = presentRound;
                    try {
                        index = Integer.parseInt(roundInput.getText());
                    } catch (NumberFormatException e1){
                        Alert alert = new Alert();
                        alert.display("Error","Please input a number");
                        return;
                    }
                    if (index>=0&&index<=(maxRound)) {

                        chooseRound(parser.getRounds(), fieldInfos, index);
                        presentRound = index;
                        setAnts(fieldInfos, playingBoard);
                    }
                    else {
                        Alert alert = new Alert();
                        alert.display("Error","Please input a valid number");
                        return;
                    }

                }
        });


        setRound.setOnAction(e->{
            int index = presentRound;

            try {
                index = Integer.parseInt(roundInput.getText());
            } catch (NumberFormatException e1){
                Alert alert = new Alert();
                alert.display("Error","Please input a number");
                return;
            }
            if (index>=0&&index<=(maxRound)) {

                chooseRound((ArrayList<Round>) parser.getRounds(), fieldInfos, index);
                presentRound = index;
                setAnts(fieldInfos, playingBoard);
            }
            else {
                Alert alert = new Alert();
                alert.display("Error","Please input a valid number");
                return;
            }
        });

        RoundInputLayout = new VBox();
        RoundInputLayout.getChildren().addAll(setRound,roundInput);
        gridPane.setConstraints(RoundInputLayout,0,2);





        closeButton = new Button("Close");
        closeButton.setPrefSize(100,30);
        GridPane.setConstraints(closeButton,2,2);


        backward.setOnAction(e -> {
            if (presentRound >0){
                backward(parser.getRounds(),fieldInfos);
                setAnts(fieldInfos,playingBoard);
            }
            else {
                Alert alert = new Alert();
                alert.display("","No more rounds\n    to display!");
            }

        });


        forward.setOnAction(e -> {
                            if (presentRound < maxRound) {
                        calculateNextRound(((ArrayList<Round>)parser.getRounds()).get(this.presentRound),fieldInfos);
                        presentRound++;
                            setAnts(fieldInfos,playingBoard); }
                            else {
                                Alert alert = new Alert();
                                alert.display("","No more rounds\n    to display!");
                            }
        });

        closeButton.setOnAction(e-> Platform.exit());



    }


    public static void main(String[] args) {
        launch(args);
    }

    private void removeAnts(Group gridPane){
        for (Object ants : this.ants){
            ImageView ant = (ImageView)ants;
            gridPane.getChildren().remove(ant);

        }
        this.ants.clear();
    }


    public void calculateNextRound(Round round, FieldInfo[][] fieldInfo){

        List<FieldInfo> changes = round.getFieldInfos();

        for (FieldInfo change : changes){
            int x = change.getX();
            int y = change.getY();

            fieldInfo[x][y] = change;
        }
    }


        private void setAnts (FieldInfo[][] fieldInfos, PlayingBoard playingBoard){

       removeAnts(playingBoard.getPlayingBoard());

            for (int j = 0 ; j < height ; j++){
                for (int i = 0 ; i < width ; i++) {


                    if (fieldInfos[i][j].getAnt()!=null) {
                        ImageView ant = fieldInfos[i][j].getAnt().getImageView();
                        //building ants
                        ant.setX(playingBoard.getField(i,j).xPosition - side / 1.6);
                        ant.setY(playingBoard.getField(i,j).yPosition - side / 1.6);

                        playingBoard.getPlayingBoard().getChildren().add(ant);
                        this.ants.add(ant);

                    }

                    int food = fieldInfos[i][j].getFood();

                    if (food>0){
                        Food burger = new Food(this.side,i,j,playingBoard.getField(i,j).xPosition,playingBoard.getField(i,j).yPosition,food);
                        playingBoard.getPlayingBoard().getChildren().add(burger.getImageView());

                    }
                }
            }
            roundInput.setText(""+presentRound);
            roundInput.positionCaret(presentRound+1);

        }

        private void backward(List<Round> rounds, FieldInfo[][] fieldInfo){

            for (int i = 0 ; i < width ; i++){
                System.arraycopy(originalFI[i],0,fieldInfo[i],0,height);
            }


            for (int i = 0; i<presentRound-1; i++){

                Round round = rounds.get(i);
                calculateNextRound(round,fieldInfo);
            }
            presentRound-- ;


        }

    private void chooseRound(List<Round> rounds, FieldInfo[][] fieldInfo, int index){

        if (index<0){
            Alert alert = new Alert();
            alert.display("Error","Please input a positive number");
            return;
        }

        for (int i = 0 ; i < width ; i++){
            System.arraycopy(originalFI[i],0,fieldInfo[i],0,height);
        }


        try {
            for (int i = 0; i < index; i++) {

                Round round = rounds.get(i);

                calculateNextRound(round, fieldInfo);
            }
        }catch (IndexOutOfBoundsException e1){
            Alert alert = new Alert();
            alert.display("Error","This round doesn't exist");
        }


    }



    }


