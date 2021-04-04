package messenger;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import java.util.function.Supplier;

public class MessengerGrid extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Типа мессенджер");

        Parent ui = createUiBoxes();
        primaryStage.setScene(new Scene(ui, 800, 600));

        primaryStage.show();
    }

    private final TextArea messagesArea = new TextArea();
    private final TextField inputField = new TextField();
    private final Button submitButton = new Button("Отправить");
    private final Label contactsLabel = new Label("Контакты");
    private final ListView<String> contacts = new ListView<String>();

    private Parent createUiBoxes() {
        GridPane ui = new GridPane();

        ui.add(messagesArea, 0, 0, 2, 2);
        ui.add(inputField, 0, 2);
        ui.add(submitButton, 1, 2);
        ui.add(contactsLabel, 2, 0);
        ui.add(contacts, 2, 1, 1, 2);

        ui.getColumnConstraints().addAll(
                ((Supplier<ColumnConstraints>) () -> {
                    ColumnConstraints col = new ColumnConstraints();
                    col.setHgrow(Priority.ALWAYS);
                    return col;
                }).get(),
                new ColumnConstraints(),
                new ColumnConstraints()
        );
        ui.getRowConstraints().addAll(
                new RowConstraints(),
                ((Supplier<RowConstraints>) () -> {
                    RowConstraints row = new RowConstraints();
                    row.setVgrow(Priority.ALWAYS);
                    return row;
                }).get(),
                ((Supplier<RowConstraints>) () -> {
                    RowConstraints row = new RowConstraints();
                    row.setMaxHeight(25);
                    return row;
                }).get()
        );

        return ui;
    }
}