package fractals.fractal;

public interface Fractal {

    /**
     * Для каждой точки выдает число от 0 до 1
     * @param x абсцисса точки
     * @param y ордината точки
     * @return число от 0 до 1
     */
    double evaluate(double x, double y);
}