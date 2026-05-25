public class Polynomial {

    double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial p) {
        int maxLen = Math.max(this.coefficients.length, p.coefficients.length);
        double[] newCoeffs = new double[maxLen];
        
        for (int i = 0; i < maxLen; i++) {
            double c1 = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            double c2 = (i < p.coefficients.length) ? p.coefficients[i] : 0;
            newCoeffs[i] = c1 + c2;
        }
        return new Polynomial(newCoeffs);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }
}