package Homeworks.Homework_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by kenzi on 12/04/2017.
 */
public class Main extends Application {

    FigureGenerator generator = new FigureGenerator();

    int WINDOW_WIDHT = 500;
    int WINDOW_HEIGHT = 500;


    @Override
    public void start(Stage primaryStage) {
        StackPane app = new StackPane();
        primaryStage.setScene(new Scene(app, WINDOW_WIDHT, WINDOW_HEIGHT));
        primaryStage.setTitle("Tetori Symbol Generator");

        Pane stage = new Pane();
        stage.setLayoutY(WINDOW_WIDHT);
        stage.setLayoutY(WINDOW_HEIGHT);
        app.getChildren().add(stage);

        Button btn = new Button();
        btn.setText("Get New Symbol!");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Generated!");
                Figure figure = null;
                if (figure == null){
                    figure = generator.createFigure();
                    stage.getChildren().addAll(figure);
                } else {
                    figure = null;
                    stage.getChildren().remove(figure);
                }


            }
        });
        btn.setLayoutX(WINDOW_WIDHT/2 - 50);
        btn.setLayoutY(WINDOW_HEIGHT-50);
        stage.getChildren().add(btn);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

