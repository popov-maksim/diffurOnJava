package sample.Model;

public class ImprovedEulerMethod extends NumericMethod{

    public ImprovedEulerMethod(double x0, double y0, double X, int N) {
        super(x0, y0, X, N);
    }

    @Override
    protected double delta(double xPrev, double yPrev) {
        return step * derivativeValue(xPrev + step / 2,
                yPrev + step / 2 * derivativeValue(xPrev, yPrev));
    }

}
