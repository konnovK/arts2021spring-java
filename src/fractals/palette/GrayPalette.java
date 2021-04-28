package fractals.palette;

public class GrayPalette implements Palette {
    @Override
    public int colorize(double v) {

        int colorValue = (int) (256. * v);
        if (colorValue >= 1) {
            colorValue -= 1;
        }
        return (colorValue << 16) + (colorValue << 8) + colorValue;
    }
}
