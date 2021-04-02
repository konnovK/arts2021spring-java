package circle;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DynamicCircle extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Типа Круг");

        Parent ui = initInterface();
        primaryStage.setScene(new Scene(ui, 800, 600));

        initActions();

        primaryStage.show();
    }


    private final Circle circle = new Circle();
    private final Slider slider = new Slider();
    private final ColorPicker circleColorPicker = new ColorPicker();
    private final ColorPicker backgroundColorPicker = new ColorPicker();

    private final GridPane leftPane = new GridPane();
    private final Pane rightPane = new Pane();
    private final GridPane ui = new GridPane();


    private Parent initInterface() {

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
        columnConstraints1.setHgrow(Priority.NEVER);
        columnConstraints2.setHgrow(Priority.ALWAYS);
        rowConstraints.setVgrow(Priority.ALWAYS);

        ui.getColumnConstraints().addAll(columnConstraints1, columnConstraints2);
        ui.getRowConstraints().add(rowConstraints);

        return ui;
    }

    private void initActions() {
        // radius
        circle.radiusProperty().bind(slider.valueProperty());


        // position
        circle.centerXProperty().bind(rightPane.widthProperty().divide(2));
        circle.centerYProperty().bind(rightPane.heightProperty().divide(2));


        // CircleColor
        circle.fillProperty().bind(circleColorPicker.valueProperty());


        // BackgroundColor
        backgroundColorPicker.addEventHandler(EventType.ROOT, event -> {
            Color backgroundColor = backgroundColorPicker.getValue();
            Background background = new Background(new BackgroundFill(backgroundColor, new CornerRadii(0), new Insets(0)));
            rightPane.setBackground(background);
        });


        // maxRadius
        slider.maxProperty().bind(rightPane.widthProperty().get() > rightPane.heightProperty().get() ? rightPane.widthProperty().divide(2) : rightPane.heightProperty().divide(2));
    }
}