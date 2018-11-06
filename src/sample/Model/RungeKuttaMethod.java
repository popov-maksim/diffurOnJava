package sample.Model;

public class RungeKuttaMethod extends  NumericMethod{

    public RungeKuttaMethod(double x0, double y0, double X, int N) {
        super(x0, y0, X, N);
    }

    @Override
    protected double delta(double xPrev, double yPrev) {
        double k1 = derivativeValue(xPrev, yPrev);
        double k2 = derivativeValue(xPrev + step / 2, yPrev + step * k1 / 2);
        double k3 = derivativeValue(xPrev + step / 2, yPrev + step * k2 / 2);
        double k4 = derivativeValue(xPrev + step, yPrev + step * k3);

        return (step / 6) * (k1 + 2 * k2 + 2 * k3 + k4);
    }

}
