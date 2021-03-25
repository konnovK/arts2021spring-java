package circle;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DynamicCircle extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Типа мессенджер");

        Parent ui = createUi();
        primaryStage.setScene(new Scene(ui, 800, 600));

        primaryStage.show();
    }

    private final Circle circle = new Circle();
    private final Slider slider = new Slider();
    private final ColorPicker circleColorPicker = new ColorPicker();
    private final ColorPicker backgroundColorPicker = new ColorPicker();

    private Parent createUi() {
        GridPane leftPane = new GridPane();
        Pane rightPane = new Pane();
        GridPane ui = new GridPane();

        leftPane.add(slider, 0, 0);
        leftPane.add(circleColorPicker, 0, 1);
        leftPane.add(backgroundColorPicker, 0, 2);

        rightPane.getChildren().add(circle);

        ui.add(leftPane, 0, 0);
        ui.add(rightPane, 1, 0);


        leftPane.setStyle("-fx-border-color: gray; -fx-border-radius: 1px");
        rightPane.setStyle("-fx-border-color: gray; -fx-border-radius: 1px");


        var columnConstraints1 = new ColumnConstraints();
        var columnConstraints2 = new ColumnConstraints();
        var rowConstraints = new RowConstraints();
        columnConstraints1.setMinWidth(150);
        columnConstraints2.setHgrow(Priority.ALWAYS);
        rowConstraints.setVgrow(Priority.ALWAYS);

        ui.getColumnConstraints().addAll(columnConstraints1, columnConstraints2);
        ui.getRowConstraints().add(rowConstraints);

        return ui;
    }
}
