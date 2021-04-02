package messenger;

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

        initActions();

        primaryStage.show();
    }

    private final TextArea leftTop = new TextArea();
    private final TextField leftBottomText = new TextField();
    private final Button leftBottomButton = new Button();
    private final HBox leftBottom = new HBox(leftBottomText, leftBottomButton);
    private final VBox left = new VBox(leftTop, leftBottom);
    private final Label rightLabel = new Label("Контакты");
    private final ListView<String> rightListView = new ListView<>();
    private final VBox right = new VBox(rightLabel, rightListView);
    private final HBox window = new HBox(left, right);

    private Parent createUiBoxes() {
        rightListView.getItems().addAll("Иванов", "Петров", "Мбого");
        rightListView.setMinWidth(200);
        left.setMinWidth(200);

        HBox.setHgrow(left, Priority.ALWAYS);
        HBox.setHgrow(leftBottomText, Priority.ALWAYS);

        VBox.setVgrow(leftTop, Priority.ALWAYS);
        VBox.setVgrow(rightListView, Priority.ALWAYS);

        leftTop.setEditable(false);

        leftBottomButton.setText("Отправить");
        leftBottomButton.setMinWidth(80);

        return window;
    }

    private void initActions() {
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
    }
}
