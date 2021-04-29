package fractals.palette;

import javafx.scene.paint.Color;

public class HsbColorPalette implements ColorPalette {
    @Override
    public Color colorize(double v) {
        return Color.hsb((360 * 8 * v) % 360, 1, 1);
    }
}
