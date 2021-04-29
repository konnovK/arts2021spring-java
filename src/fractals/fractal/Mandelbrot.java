package fractals.fractal;

public class Mandelbrot implements Fractal {
    private final double NUMBER_OF_STEPS = 100;
    private final double MAX_VALUE_OF_SERIES_ELEMENT = 4d;
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
            var _xz = xz * xz - yz * yz + x;
            var _yz = 2 * xz * yz + y;
            xz = _xz;
            yz = _yz;
            if (xz * xz + yz * yz > MAX_VALUE_OF_SERIES_ELEMENT * MAX_VALUE_OF_SERIES_ELEMENT) {
                // если написать через sqrt, точность не улучшается
                // если выражение справа сделать long, точность не улучшается

                return i / NUMBER_OF_STEPS;
            }
        }
        return 1;
    }
}
