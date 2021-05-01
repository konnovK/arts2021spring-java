package fractals;

import fractals.fractal.Circle;
import fractals.fractal.Fractal;
import fractals.fractal.Mandelbrot;
import fractals.palette.*;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Типа изображения на imageView");

        Parent ui = initInterface();
        draw();
        primaryStage.setScene(new Scene(ui, 1280, 800));

        initActions();

        primaryStage.show();
    }

    private final int width = 800;
    private final int height = 800;

    private final WritableImage image = new WritableImage(width, height);
    private final PixelWriter pixelWriter = image.getPixelWriter();

    private final ImageView imageView = new ImageView(image);
    private final Label coords = new Label();

    private final Fractal fractal = new Mandelbrot();
    private final Palette palette = new GrayPalette();
    private final ColorPalette colorPalette = new HsbColorPalette();

    private final boolean isIntPalette = true;

    private double x0 = -2;
    private double y0 = 2;
    private double sizeOfView = 4;

    private Parent initInterface() {
        GridPane globalPane = new GridPane();
        coords.setText("(" + (x0 + (sizeOfView / 2)) + ";" + (y0 - (sizeOfView / 2)) + "), size = " + sizeOfView);

        globalPane.add(imageView, 0, 0);
        globalPane.add(coords, 1, 0);

        return globalPane;
    }

    private void initActions() {
        EventHandler<MouseEvent> approximationHandler = (event) -> {
            double _x, _y, _sizeOfView;

            if (!event.isControlDown()) {
                _x = event.getX();
                _y = event.getY();
                _sizeOfView = sizeOfView / 2;
            } else {
                _x = event.getX();
                _y = event.getY();
                _sizeOfView = sizeOfView * 2;
            }

            x0 = x0 + _x * (sizeOfView / width) - _sizeOfView / 2;
            y0 = y0 - _y * (sizeOfView / width) + _sizeOfView / 2;
            sizeOfView = _sizeOfView;

            coords.setText("(" + (x0 + (sizeOfView / 2)) + ";" + (y0 - (sizeOfView / 2)) + "), size = " + sizeOfView);
            drawFractal(x0, y0, sizeOfView);
        };

        imageView.onMouseClickedProperty().setValue(approximationHandler);
    }

    private void draw() {
        drawFractal(x0, y0, sizeOfView);
    }

    private void drawFractal(double x0, double y0, double sizeOfView) {
        double d = sizeOfView / width;

        for (int xScreen = 0; xScreen < width; xScreen++) {
            for (int yScreen = 0; yScreen < height; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;

                Color color = Color.web(webColorStringFromInt(palette.colorize(fractal.evaluate(x, y))));
                if (!isIntPalette) {
                    color = colorPalette.colorize(fractal.evaluate(x,y));
                }
                pixelWriter.setColor(xScreen, yScreen, color);
            }
        }
    }

    private String webColorStringFromInt(int colorValue) {
        if (colorValue == 0) {
            return "0x000000";
        }
        String color ="000000" + Integer.toHexString(colorValue);
        return "0x" + color.substring(color.length() - 6);
    }
}
