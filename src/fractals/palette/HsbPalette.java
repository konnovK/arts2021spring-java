package fractals.palette;

public class HsbPalette implements Palette {
    @Override
    public int colorize(double v) {
        double h = 360. * v;
        int s = 100;
        int b = 100;

        int _h =(int)  (h / 60.) % 6;
//        System.out.println(_h);

        double a = b * ((((int) h) % 60) / 60.);

        int red = 0;
        int green = 0;
        int blue = 0;
        switch (_h) {
            case 0 -> {
                red = b;
                green = (int) (b + a);
                blue = 0;
            }
            case 1 -> {
                green = b;
                blue = 0;
                red = (int) (b - a);
            }
            case 2 -> {
                green = b;
                blue = (int) (b + a);
                red = 0;
            }
            case 3 -> {
                red = 0;
                green = (int) (b - a);
                blue = b;
            }
            case 4 -> {
                red = (int) (b + a);
                green = 0;
                blue = b;
            }
            case 5 -> {
                red = b;
                green = 0;
                blue = (int) (b - a);
            }
        }
        red = red % 256;
        green = green % 256;
        blue = blue % 256;
//        System.out.print(" r = " + red);
//        System.out.print(" g = " + green);
//        System.out.print(" b = " + blue);
//        System.out.println();
        return (red << 16) + (green << 8) + blue;
    }
}
