public class Polynomial {
    private double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial p) {
        int maxLength = Math.max(this.coefficients.length, p.coefficients.length);
        double[] result = new double[maxLength];

        for (int i = 0; i < this.coefficients.length; i++) {
            result[i] = this.coefficients[i];
        }

        for (int i = 0; i < p.coefficients.length; i++) {
            result[i] += p.coefficients[i];
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
