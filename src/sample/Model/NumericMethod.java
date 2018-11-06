package sample.Model;

public abstract class NumericMethod {

    protected double[] arrayOfX = null, arrayOfY = null;
    protected double[] exactSolX = null, exactSolY = null;
    protected double[] localError = null;
    protected double step, c;
    protected boolean computed;

    protected double exactValue(double x) {
        return 2 * x - 1 + c * Math.exp(-2 * x);
    }

    protected void calculationExactSolution(double x0, double y0) {
        exactSolX = new double[arrayOfX.length];
        exactSolY = new double[exactSolX.length];
        exactSolX[0] = x0;
        exactSolY[0] = y0;
        c = (y0 - 2 * x0 + 1) * Math.exp(2 * x0);
        for (int i = 1; i < exactSolX.length; ++i) {
            exactSolX[i] = exactSolX[i - 1] + step;
            exactSolY[i] = exactValue(exactSolX[i]);
        }
    }

    protected double derivativeValue(double x, double y) {
        return -2 * y + 4 * x;
    }

    protected void newInitialX(double x0) {
        assert arrayOfX != null && arrayOfX.length > 0;

        arrayOfX[0] = x0;

        assert arrayOfX[0] == x0;
    }

    protected void newInitialY(double y0) {
        assert arrayOfY != null && arrayOfY.length > 0;

        arrayOfY[0] = y0;

        assert arrayOfY[0] == y0;
    }

    protected void newCountOfPoints(int N) {
        arrayOfX = new double[N + 1];
        arrayOfY = new double[N + 1];

        assert arrayOfX.length == N + 1 && arrayOfY.length == N + 1;
    }

    protected void newStep(double X) {
        int N = arrayOfX.length - 1;
        step = (X - arrayOfX[0]) / N;
    }

    protected abstract double delta(double xPrev, double yPrev);

    public double[] getArrayOfX() {
        assert computed;

        return arrayOfX;
    }

    public double[] getArrayOfY() {
        assert computed;

        return arrayOfY;
    }

    public double[] getExactX() {
        return exactSolX;
    }

    public double[] getExactY() {
        return exactSolY;
    }

    public double[] getLocalError() { return localError; }

    public void setNewParams(double x0, double y0, double X, int N) {
        if (arrayOfX == null || N + 1 != arrayOfX.length) {
            newCountOfPoints(N);
        }
        newInitialX(x0);
        newInitialY(y0);
        newStep(X);
        calculationExactSolution(x0, y0);
        computed = false;
    }

    public NumericMethod(double x0, double y0, double X, int N) {
        setNewParams(x0, y0, X, N);
    }

    public void compute() {
        for (int i = 1; i < arrayOfX.length; ++i) {
            arrayOfX[i] = arrayOfX[i - 1] + step;
            arrayOfY[i] = arrayOfY[i - 1] + delta(arrayOfX[i - 1], arrayOfY[i - 1]);
        }
        localError = new double[arrayOfX.length];
        for (int i = 0; i < localError.length; ++i) {
            localError[i] = Math.abs(exactSolY[i] - arrayOfY[i]);
        }
        computed = true;
    }

}
