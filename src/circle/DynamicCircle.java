package circle;

import javafx.application.Application;
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

import java.util.function.Supplier;

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
    private final Pane rightPane = new Pane();

    private Parent initInterface() {

        rightPane.getChildren().add(circle);
        rightPane.setStyle("-fx-border-color: gray; -fx-border-radius: 1px");

        GridPane ui = new GridPane();

        ui.add(
                ((Supplier<GridPane>) () -> {
                    GridPane gp = new GridPane();
                    gp.add(slider, 0, 0);
                    gp.add(circleColorPicker, 0, 1);
                    gp.add(backgroundColorPicker, 0, 2);
                    return gp;
                }).get(),
                0, 0
        );
        ui.add(rightPane, 1, 0);

        ui.getColumnConstraints().addAll(
                ((Supplier<ColumnConstraints>) () -> {
                    ColumnConstraints col = new ColumnConstraints();
                    col.setHgrow(Priority.NEVER);
                    return col;
                }).get(),
                ((Supplier<ColumnConstraints>) () -> {
                    ColumnConstraints col = new ColumnConstraints();
                    col.setHgrow(Priority.ALWAYS);
                    return col;
                }).get()
        );
        ui.getRowConstraints().add(
                ((Supplier<RowConstraints>) () -> {
                    RowConstraints row = new RowConstraints();
                    row.setVgrow(Priority.ALWAYS);
                    return row;
                }).get()
        );

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