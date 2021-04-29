package fractals;

import fractals.fractal.Circle;
import fractals.fractal.Fractal;
import fractals.fractal.Mandelbrot;
import fractals.palette.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Типа изображения на imageView");

        Parent ui = initInterface();
        draw();
        primaryStage.setScene(new Scene(ui, 800, 800));

//        initActions();

        primaryStage.show();
    }

    private final int width = 800;
    private final int height = 800;
    private final WritableImage image = new WritableImage(width, height);
    private final PixelWriter pixelWriter = image.getPixelWriter();

    private final Fractal fractal = new Mandelbrot();
    private final Palette palette = new GrayPalette();
    private final ColorPalette colorPalette = new HsbColorPalette();

    private final boolean isIntPalette = true;

    private Parent initInterface() {
        GridPane globalPane = new GridPane();

        ImageView imageView = new ImageView(image);
        globalPane.add(imageView, 0, 0);

        return globalPane;
    }

    private void draw() {
        drawFractal(-2, 2, 4);
    }

    private void drawFractal(double x0, double y0, double sizeOfView) {
        double d = sizeOfView / width;

        for (int xScreen = 0; xScreen < width; xScreen++) {
            for (int yScreen = 0; yScreen < height; yScreen++) {
                double x = x0 + d * xScreen;
                double y = y0 - d * yScreen;

                Color color = Color.web("0x" + webColorStringFromInt(palette.colorize(fractal.evaluate(x, y))));
                if (!isIntPalette) {
                    color = colorPalette.colorize(fractal.evaluate(x,y));
                }
                pixelWriter.setColor(xScreen, yScreen, color);
            }
        }
    }

    private String webColorStringFromInt(int colorValue) {
        String color = Integer.toHexString(colorValue);
        if (colorValue == 0) {
            color = "000";
        }
        if (color.length() == 5) {
            color = "0" + color;
        }

        return color;
    }
}
