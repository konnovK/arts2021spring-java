package fractals.fractal;

public class Circle implements Fractal {

    @Override
    public double evaluate(double x, double y) {
        if (x * x + y * y <= 4) {
            return 1 - ((x * x + y * y) / 4.);
        }
        return 0;
    }
}
