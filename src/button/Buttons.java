package button;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Buttons extends Application {

    private int buttonHandlerCounter = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Пример про панельки и элементы управления");

        Parent ui = initInterface();
        primaryStage.setScene(new Scene(ui, 640, 480));

        primaryStage.show();
    }

    private Parent initInterface() {
        Button button = new Button("НАЖМИ МЕНЯ!!!!!1111111");
        Label label = new Label("");
        ImageView image = new ImageView();
        HBox hb = new HBox(button);

        image.setImage(new Image("http://www.hedfiles.net/spideyblast12.gif"));

        EventHandler<Event> customButtonActionHandler = event -> {
            switch (buttonHandlerCounter) {
                case 0 -> {
                    hb.getChildren().add(label);
                    button.setText("Не нажимай на меня!");
                    label.setText("Не нажимай больше эту кнопку");
                }
                case 1 -> label.setText("Я просил не нажимать больше эту кнопку!!!!!");
                case 2 -> label.setText("ХВАТИТ!!!!!!");
                case 3 -> label.setText("твелщравикдуыши енг пуцмкдн яцуип!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111111111");
                case 4 -> {
                    hb.getChildren().removeAll(button, label);
                    label.setText("доигрался азаза");
                    hb.getChildren().addAll(image, label);
                }
            }
            buttonHandlerCounter++;
        };

        button.addEventHandler(ActionEvent.ACTION, customButtonActionHandler);

        return hb;
    }
}
