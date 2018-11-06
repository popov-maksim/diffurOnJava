package sample.Model;

public class Simulator {

    private double[] globalError;
    private int[] globalN;

    private double getMax(double[] arr) {
        double res = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > res) {
                res = arr[i];
            }
        }

        return res;
    }

    public int[] getGlobalN() { return globalN; }
    public double[] getGlobalError() { return globalError; }

    public void computeGlobalError(NumericMethod method, int nInit, int nFin) {
        globalError = new double[nFin - nInit + 1];
        globalN = new int[nFin - nInit + 1];
        method.compute();
        double[] x = method.getArrayOfX();
        double[] y = method.getArrayOfY();
        for (int n = nInit, j = 0; n <= nFin; ++n, ++j) {
            method.setNewParams(x[0], y[0], x[x.length - 1], n);
            method.compute();
            double[] localErrors = method.getLocalError();
            globalError[j] = getMax(localErrors);
            globalN[j] = n;
        }
    }

}