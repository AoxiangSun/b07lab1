public class Polynomial {
    private double[] coefficients;
    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial p) {
        int length = Math.max(this.coefficients.length, p.coefficients.length);
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {
            double thisCoeff = i < this.coefficients.length ? this.coefficients[i] : 0;
            double pCoeff = i < p.coefficients.length ? p.coefficients[i] : 0;
            result[i] = thisCoeff + pCoeff;
        }

        return new Polynomial(result);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            result += this.coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}
