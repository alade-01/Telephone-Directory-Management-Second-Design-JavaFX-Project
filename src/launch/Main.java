package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/home" +
                ".fxml")));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        //Position the primaryStage.
        //primaryStage.setX(400);
        //primaryStage.setY(170);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}