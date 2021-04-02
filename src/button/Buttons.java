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

        initActions();

        primaryStage.show();
    }

    private final Button button = new Button();
    private final Label label = new Label();
    private final ImageView imageView = new ImageView();
    private final HBox hb = new HBox();

    private Parent initInterface() {

        imageView.setImage(new Image("http://www.hedfiles.net/spideyblast12.gif"));
        button.setText("НАЖМИ МЕНЯ!!!!!1111111");
        hb.getChildren().add(button);

        return hb;
    }

    private void initActions() {
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
                    hb.getChildren().addAll(imageView, label);
                }
            }
            buttonHandlerCounter++;
        };

        button.addEventHandler(ActionEvent.ACTION, customButtonActionHandler);
    }
}
