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
        primaryStage.setScene(new Scene(ui, 800, 600));

//        initActions();

        primaryStage.show();
    }

    private final int width = 600;
    private final int height = 600;
    private final WritableImage image = new WritableImage(width, height);
    private final PixelWriter pixelWriter = image.getPixelWriter();

    private final Fractal fractal = new Mandelbrot();
    private final Palette palette = new HsbPalette();
    private final ColorPalette colorPalette = new HsbColorPalette();

    private final boolean isIntPalette = false;

    private Parent initInterface() {
        GridPane globalPane = new GridPane();

        ImageView imageView = new ImageView(image);
        globalPane.add(imageView, 0, 0);

        return globalPane;
    }

    private void draw() {

        double x0 = -2;
        double y0 = 2;
        double d = 4. / width;

        for (int xt = 0; xt < width; xt++) {
            for (int yt = 0; yt < height; yt++) {
                double x = x0 + d * xt;
                double y = y0 - d * yt;

                Color color = Color.web("0x" + webColorStringFromInt(palette.colorize(fractal.evaluate(x, y))));
                if (!isIntPalette) {
                    color = colorPalette.colorize(fractal.evaluate(x,y));
                }
                pixelWriter.setColor(xt, yt, color);
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
