package fractals.palette;

import javafx.scene.paint.Color;

public class HsbPalette implements Palette {
    @Override
    public int colorize(double v) {
        var qq = Color.hsb(360. * v, 1, 1);
        int r = (int) (255 * qq.getRed());
        int g = (int) (255 * qq.getGreen());
        int b = (int) (255 * qq.getBlue());
        return (r << 16) + (g << 8) + b;
    }
}
