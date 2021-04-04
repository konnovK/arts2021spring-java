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
import javafx.util.Pair;

import java.util.function.Function;

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
                (new Function<Pair<ColumnConstraints, Priority>, ColumnConstraints>() {
                    @Override
                    public ColumnConstraints apply(Pair<ColumnConstraints, Priority> columnConstraintsPriorityPair) {
                        columnConstraintsPriorityPair.getKey().setHgrow(columnConstraintsPriorityPair.getValue());
                        return columnConstraintsPriorityPair.getKey();
                    }
                }).apply(new Pair<>(new ColumnConstraints(), Priority.ALWAYS)),
                new ColumnConstraints(),
                new ColumnConstraints()
        );
        ui.getRowConstraints().addAll(
                new RowConstraints(),
                (new Function<Pair<RowConstraints, Priority>, RowConstraints>() {
                    @Override
                    public RowConstraints apply(Pair<RowConstraints, Priority> rowConstraintsPriorityPair) {
                        rowConstraintsPriorityPair.getKey().setVgrow(rowConstraintsPriorityPair.getValue());
                        return rowConstraintsPriorityPair.getKey();
                    }
                }).apply(new Pair<>(new RowConstraints(), Priority.ALWAYS)),
                (new Function<Pair<RowConstraints, Double>, RowConstraints>() {
                    @Override
                    public RowConstraints apply(Pair<RowConstraints, Double> rowConstraintsDoublePair) {
                        rowConstraintsDoublePair.getKey().setMaxHeight(rowConstraintsDoublePair.getValue());
                        return rowConstraintsDoublePair.getKey();
                    }
                }).apply(new Pair<>(new RowConstraints(), new Double(25)))
        );

        return ui;
    }
}