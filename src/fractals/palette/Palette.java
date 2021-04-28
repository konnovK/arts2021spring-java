package fractals.palette;

import javafx.scene.paint.Color;

public interface Palette {

    /**
     * возвращает цвет для числа от 0 до 1
     * @param v число от 0 до 1
     * @return цвет
     */
    int colorize(double v);
}
