package Homeworks.Homework_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
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
        primaryStage.setTitle("Tetoris Symbol Generator");

        BorderPane stage = new BorderPane();
        app.getChildren().add(stage);

        Pane resultPane = new Pane();
        resultPane.setLayoutX(WINDOW_WIDHT);
        resultPane.setLayoutY(WINDOW_HEIGHT);
        stage.setCenter(resultPane);

        Pane btnPane = new Pane();
        btnPane.setLayoutX(WINDOW_WIDHT/2);
        stage.setBottom(btnPane);

        Button btn = new Button();
        btn.setText("Get New Symbol!");

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    resultPane.getChildren().clear();
                    System.out.println("Generated!");
                    Figure figure = generator.createFigure();
                    resultPane.getChildren().addAll(figure);
                } catch (Exception e) {

                }
            }
        });
        btn.setLayoutX(WINDOW_WIDHT/2 - 30);
        btn.setLayoutY(-30);
        btnPane.getChildren().add(btn);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

