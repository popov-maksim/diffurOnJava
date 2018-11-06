package sample.Model;

public class EulerMethod extends NumericMethod{

    public EulerMethod(double x0, double y0, double X, int N) {
        super(x0, y0, X, N);
    }

    protected double delta(double xPrev, double yPrev) {
        return step * derivativeValue(xPrev, yPrev);
    }

}
