package GUI;

import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class JsonReader {


    private int width;
    private int height;
    private FieldInfo[][] board;
    private List rounds = new ArrayList<Round>();
    private Stage stage;




    public JsonReader(String filename,Stage stage) throws IOException, ParseException {

        this.stage = stage;

        // getting our first json objects
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject jsonObject = (JSONObject) obj;
        // fetching the "init" object
        JSONObject initials = (JSONObject) jsonObject.get("init");
        //getting width und height
        this.width  = toIntExact((long)initials.get("width"));
        this.height = toIntExact((long)initials.get("height"));
        //packing the fields in a Json array
        JSONArray fieldArray = (JSONArray) initials.get("fields");

        //initialise the array of the right size
        this.board = new FieldInfo[width][height];
        //iterating in init and trying to get the other values
        for (Object fields : fieldArray){
            JSONObject field = (JSONObject) fields;

            int x = toIntExact((long) field.get("x"));
            int y = toIntExact((long) field.get("y"));
            char type = ( (String) field.get("type")).charAt(0);

            // there is no food value if there is an ant on the field
            int food = 0;
            Ant ant = null;
            if (field.get("food")!=null){
                food = toIntExact((long) field.get("food"));
            }
            if (field.get("ant")!=null) {
                JSONObject ants = (JSONObject) field.get("ant");
                char swarm = ((String) ants.get("swarm_id")).charAt(0);
                ant = new Ant(calculateSide(width,height),DIRECTION.NORTHWEST,this.stage,swarm);
                ant.setDirection(ants.get("direction").toString());
                ant.setRestTime(toIntExact((long) ants.get("rest_time")));
                ant.setxCoordinate(x);
                ant.setyCoordinate(y);
                ant.setFood("true".equals(ants.get("carries_food").toString()));
                ant.setId(toIntExact((long) ants.get("id")));
                ant.setPc(toIntExact((long) ants.get("program_counter")));

                JSONArray registers = (JSONArray) ants.get("register");
                for (int i = 0; i<registers.size();i++) {
                    boolean value = ("true".equals(registers.get(i).toString()));
                    ant.setRegisters(i,value);
                }
            }

            FieldInfo fieldInfo = new FieldInfo(x, y, type, food);
            fieldInfo.setAnt(ant);

            this.board[x][y] = fieldInfo;
            }

            //getting the changes int the fields for the rounds
        if (jsonObject.get("steps") != null){
        JSONArray stepsArray = (JSONArray) jsonObject.get("steps");

        //initialising standing and fieldInfo

        for (Object steps : stepsArray) {

            JSONObject step = (JSONObject) steps;

            Round round = new Round();
            this.rounds.add(round);

            //getting the standingArray
            JSONArray standingArray = (JSONArray) step.get("standings");
            //getting the size of the array for initialisation

            // getting the field array
            JSONArray fieldsArray = (JSONArray) step.get("fields");
            //getting the size of the array for initialisation

            //getting the standing
            for (Object standings : standingArray) {
                JSONObject standing = (JSONObject) standings;

                Standing stand = new Standing();
                stand.setAnts(toIntExact((long) standing.get("ants")));
                stand.setScore(toIntExact((long) standing.get("score")));
                stand.setSwarm_id(((String) standing.get("swarm_id")).charAt(0));

                round.getStandings().add(stand);

            }

            //getting the rounds
            for (Object fields : fieldsArray) {
                JSONObject fieldR = (JSONObject) fields;

                int x = toIntExact((long) fieldR.get("x"));
                int y = toIntExact((long) fieldR.get("y"));
                char type = ((String) fieldR.get("type")).charAt(0);


                //default values for food and ant
                int food = 0;
                Ant ant = null;

                //initialising fieldInfo
                FieldInfo fieldI = new FieldInfo(x, y, type, food);

                //getting the markers
                JSONArray markersArray = (JSONArray) fieldR.get("markers");

                for (Object markers : markersArray) {
                    JSONObject marker = (JSONObject) markers;

                    //getting the marker id
                    char id = ((String) marker.get("swarm_id")).charAt(0);
                    //getting the marker array
                    boolean[] values = new boolean[7];
                    JSONArray registers = (JSONArray) marker.get("values");
                    for (int i = 0; i<registers.size();i++) {
                        boolean value = ("true".equals(registers.get(i).toString()));
                        values[i] = value;
                    }

                    Marker finalMarker = new Marker(id,values);

                    fieldI.addMarkers(finalMarker);

                }

                if (fieldR.get("food") != null) {
                    food = toIntExact((long) fieldR.get("food"));
                    fieldI.setFood(food);
                }
                else {
                    fieldI.setFood(food);
                }

                if (fieldR.get("ant") != null) {

                    JSONObject ants = (JSONObject) fieldR.get("ant");
                    char swarm = ((String) ants.get("swarm_id")).charAt(0);
                    ant = new Ant(calculateSide(width,height),DIRECTION.NORTHWEST,this.stage,swarm);
                    ant.setDirection(ants.get("direction").toString());
                    ant.setRestTime(toIntExact((long) ants.get("rest_time")));
                    ant.setxCoordinate(x);
                    ant.setyCoordinate(y);
                    ant.setFood("true".equals(ants.get("carries_food").toString()));
                    ant.setId(toIntExact((long) ants.get("id")));
                    ant.setPc(toIntExact((long) ants.get("program_counter")));

                    JSONArray registers = (JSONArray) ants.get("register");
                    for (int i = 0; i<registers.size();i++) {
                        boolean value = ("true".equals(registers.get(i).toString()));
                        ant.setRegisters(i,value);
                    }
                }

                //put the ant in field info
                fieldI.setAnt(ant);

                round.addFieldInfos(fieldI);

            }
        }
        }
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public List getRounds() {
        return rounds;
    }


    public FieldInfo[][] getBoard() {
        return board;
    }

    public double calculateSide (int width, int height){
        return 40-0.85*height+0.005*height*width;
    }
}
