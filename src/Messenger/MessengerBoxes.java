package Messenger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessengerBoxes extends Application {
    public static final String NAME = "Коннов";

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Типа мессенджер");

        Parent ui = createUiBoxes();
        primaryStage.setScene(new Scene(ui, 800, 600));

        primaryStage.show();
    }

    private static Parent createUiBoxes() {


        TextArea leftTop = new TextArea();
        TextField leftBottomText = new TextField();
        Button leftBottomButton = new Button();
        HBox leftBottom = new HBox(leftBottomText, leftBottomButton);
        VBox left = new VBox(leftTop, leftBottom);
        Label rightLabel = new Label("Контакты");
        ListView<String> rightListView = new ListView<>();
        rightListView.getItems().addAll("Иванов", "Петров", "Мбого");
        VBox right = new VBox(rightLabel, rightListView);
        HBox window = new HBox(left, right);


        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(leftBottomText, Priority.ALWAYS);

        VBox.setVgrow(leftTop, Priority.ALWAYS);
        VBox.setVgrow(rightListView, Priority.ALWAYS);

        leftTop.setEditable(false);
//        leftBottom.setMinHeight(200);

        leftBottomButton.setText("Отправить");
        leftBottomButton.setMinWidth(80);


        EventHandler<Event> customButtonActionHandler = event -> {
            String textAfterChanges = leftTop.getText();
            if (!leftTop.getText().equals("")) {
                textAfterChanges += "\n\n";
            }
            textAfterChanges += NAME + "> " + leftBottomText.getText();
            leftTop.setText(textAfterChanges);
            leftBottomText.setText("");
        };

        leftBottomButton.addEventHandler(ActionEvent.ACTION, customButtonActionHandler);


        return window;
    }
}
