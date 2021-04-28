package fractals.fractal;

public class Mandelbrot implements Fractal {
    private final double NUMBER_OF_STEPS = 1000;
    private final double MAX_VALUE_OF_SERIES_ELEMENT = 40000000000000d;
    // значения уже большие, прога работает долго

    @Override
    public double evaluate(double x, double y) {
        double xz = 0;
        double yz = 0;

        for (double i = 0; i < NUMBER_OF_STEPS; i++) {
            // z = xz + i * yz
            // c = x + i * y
            //
            // z := z * z + c == (xz + i * yz) ^ 2 + x + i * y == (xz * xz - yz * yz + x) + i * (2 * xz * yz + y)
            xz = xz * xz - yz * yz + x;
            yz = 2 * xz * yz + y;
            if (xz * xz + yz * yz > MAX_VALUE_OF_SERIES_ELEMENT * MAX_VALUE_OF_SERIES_ELEMENT) {
                // если написать через sqrt, точность не улучшается
                // если выражение справа сделать long, точность не улучшается

                return i / NUMBER_OF_STEPS;
            }
        }
        return 1;
    }
}
