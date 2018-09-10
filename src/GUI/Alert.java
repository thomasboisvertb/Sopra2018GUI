package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {

    public void display(String title, String message){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(200);
        window.setMinHeight(200);

        window.setTitle(title);
        Label label = new Label(message);
        Button button = new Button("Close");
        button.setOnAction(e->window.close());

        VBox layout = new VBox();
        layout.setPadding(new Insets(50));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,button);



        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.setAlwaysOnTop(true);
        window.showAndWait();
    }
}
