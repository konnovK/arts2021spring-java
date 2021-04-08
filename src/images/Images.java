package images;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Images extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Типа изображения на imageView");

        Parent ui = initInterface();
        draw();
        primaryStage.setScene(new Scene(ui, 1280, 720));

        initActions();

        primaryStage.show();
    }

    private final WritableImage image1 = new WritableImage(200, 200);
    private final WritableImage image2 = new WritableImage(200, 200);
    private final WritableImage image3 = new WritableImage(200, 200);

    private final WritableImage image4 = new WritableImage(256, 256);
    private final Slider slider4 = new Slider();

    private final WritableImage image5 = new WritableImage(360, 100);
    private final Slider slider5 = new Slider();

    private Parent initInterface() {
        GridPane globalPane = new GridPane();

        // task 1
        GridPane pane1 = new GridPane();

        ImageView imageView1 = new ImageView(image1);
        Label label1 = new Label("Круг номер 1");


        pane1.add(label1, 0, 0);
        pane1.add(imageView1, 0, 1);

        globalPane.add(pane1, 0,0);

        // task 1'
        GridPane pane2 = new GridPane();

        ImageView imageView2 = new ImageView(image2);
        Label label2 = new Label("Круг номер 1'");


        pane2.add(label2, 0, 0);
        pane2.add(imageView2, 0, 1);

        globalPane.add(pane2, 1,0);

        // task 1''
        GridPane pane3 = new GridPane();

        ImageView imageView3 = new ImageView(image3);
        Label label3 = new Label("Круг номер 1''");


        pane3.add(label3, 0, 0);
        pane3.add(imageView3, 0, 1);

        globalPane.add(pane3, 2,0);


        //task 2
        GridPane pane4 = new GridPane();

        ImageView imageView4 = new ImageView(image4);
        Label label4 = new Label("Рисунок номер 2");


        pane4.add(label4, 0, 0);
        pane4.add(slider4, 0, 1);
        pane4.add(imageView4, 0, 2);

        globalPane.add(pane4, 0,1);

        //task 3
        GridPane pane5 = new GridPane();

        ImageView imageView5 = new ImageView(image5);
        Label label5 = new Label("Рисунок номер 3");


        pane5.add(label5, 0, 0);
        pane5.add(slider5, 0, 1);
        pane5.add(imageView5, 0, 2);

        globalPane.add(pane5, 1,1);

        return globalPane;
    }

    private void draw() {
        PixelWriter pixelWriter1 = image1.getPixelWriter();
        for (int x = 0; x < image1.getWidth(); x++) {
            for (int y = 0; y < image1.getHeight(); y++) {
                if (80 * 80 >= (x - 100) * (x - 100) + (y - 100) * (y - 100)) {
                    pixelWriter1.setArgb(x, y, 0xFF000000);
                } else {
                    pixelWriter1.setArgb(x, y, 0xFFFFFFFF);
                }
            }
        }

        PixelWriter pixelWriter2 = image2.getPixelWriter();
        for (int x = 0; x < image2.getWidth(); x++) {
            for (int y = 0; y < image2.getHeight(); y++) {
                int r = (x - 100) * (x - 100) + (y - 100) * (y - 100);
                if (80 * 80 >= r) {
                    pixelWriter2.setColor(x, y, Color.gray((r / (80.0 * 80.0))));
                } else {
                    pixelWriter2.setArgb(x, y, 0xFFFFFFFF);
                }
            }
        }

        PixelWriter pixelWriter3 = image3.getPixelWriter();
        for (int x = 0; x < image3.getWidth(); x++) {
            for (int y = 0; y < image3.getHeight(); y++) {
                double r = Math.sqrt((x - 100) * (x - 100) + (y - 100) * (y - 100));
                if (80 >= r) {
                    pixelWriter3.setColor(x, y, Color.gray((r / 80.)));
                } else {
                    pixelWriter3.setArgb(x, y, 0xFFFFFFFF);
                }
            }
        }

        PixelWriter pixelWriter4 = image4.getPixelWriter();
        int blue = 0;
        for (int x = 0; x < image4.getWidth(); x++) {
            for (int y = 0; y < image4.getHeight(); y++) {
                pixelWriter4.setColor(x, y, Color.rgb(x, y, blue));
            }
        }

        PixelWriter pixelWriter5 = image5.getPixelWriter();
        double brightness = 0;
        for (int x = 0; x < image5.getWidth(); x++) {
            for (int y = 0; y < image5.getHeight(); y++) {
                pixelWriter5.setColor(x, y, Color.hsb(x, y / 99., brightness));
            }
        }
    }

    private void initActions() {
        slider4.maxProperty().set(255);
        slider4.minProperty().set(0);

        PixelWriter pixelWriter4 = image4.getPixelWriter();
        slider4.valueProperty().addListener(o -> {
            int blue = slider4.valueProperty().intValue();
            for (int x = 0; x < image4.getWidth(); x++) {
                for (int y = 0; y < image4.getHeight(); y++) {
                    pixelWriter4.setColor(x, y, Color.rgb(x, y, blue));
                }
            }
        });

        slider5.maxProperty().set(1);
        slider5.minProperty().set(0);

        PixelWriter pixelWriter5 = image5.getPixelWriter();
        slider5.valueProperty().addListener(o -> {
            double brightness = slider5.valueProperty().get();
            for (int x = 0; x < image5.getWidth(); x++) {
                for (int y = 0; y < image5.getHeight(); y++) {
                    pixelWriter5.setColor(x, y, Color.hsb(x, y / 99., brightness));
                }
            }
        });
    }
}
