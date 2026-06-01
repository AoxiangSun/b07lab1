import java.io.*;

public class Polynomial {

    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                line = line.replace("-", "+-");
                if (line.startsWith("+-")) line = line.substring(1); 
                String[] terms = line.split("\\+");

                this.coefficients = new double[terms.length];
                this.exponents = new int[terms.length];

                for (int i = 0; i < terms.length; i++) {
                    String term = terms[i];
                    if (term.contains("x")) {
                        String[] parts = term.split("x");
                        
                        if (parts.length == 0 || parts[0].isEmpty() || parts[0].equals("+")) {
                            coefficients[i] = 1.0;
                        } else if (parts[0].equals("-")) {
                            coefficients[i] = -1.0;
                        } else {
                            coefficients[i] = Double.parseDouble(parts[0]);
                        }

                        if (parts.length > 1 && !parts[1].isEmpty()) {
                            exponents[i] = Integer.parseInt(parts[1]);
                        } else {
                            exponents[i] = 1;
                        }
                    } else {
                        coefficients[i] = Double.parseDouble(term);
                        exponents[i] = 0;
                    }
                }
            }
        } catch (IOException e){
            System.out.println("File not found");
            this.coefficients = new double[]{0};
            this.exponents = new int[]{0};
        }
    }

    public void saveToFile(String filename) {
        StringBuilder sb = new StringBuilder(); 

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] == 0) continue;
            
            if (coefficients[i] > 0 && sb.length() > 0) {
                sb.append("+");
            }
            
            sb.append(coefficients[i]);
            
            if (exponents[i] > 0) {
                sb.append("x");
                if (exponents[i] > 1) {
                    sb.append(exponents[i]);
                }
            }
        }
        
        if (sb.length() == 0) sb.append("0");

        try(PrintWriter pw = new PrintWriter(filename)) {
            pw.print(sb.toString().replace(".0x", "x").replace(".0+", "+"));
        } catch (IOException e){
            System.out.println("File not found");
        }
    }

    public Polynomial add(Polynomial p) {
        int maxExp = Math.max(this.exponents[this.exponents.length - 1], p.exponents[p.exponents.length - 1]);
        double[] newCoeffs = new double[maxExp + 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            int exp = this.exponents[i];
            newCoeffs[exp] += this.coefficients[i];
        }

        for (int j = 0; j < p.coefficients.length; j++) {
            int exp = p.exponents[j];
            newCoeffs[exp] += p.coefficients[j];
        }

        return createNonZeroPoly(newCoeffs);
    }

    public Polynomial multiply(Polynomial p) {
        int maxExp = this.exponents[this.exponents.length - 1] + p.exponents[p.exponents.length - 1];
        double[] newCoeffs = new double[maxExp + 1];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < p.coefficients.length; j++) {
                int exp = this.exponents[i] + p.exponents[j];
                double coeff = this.coefficients[i] * p.coefficients[j];
                newCoeffs[exp] += coeff;
            }
        }
        return createNonZeroPoly(newCoeffs);
    }

    public Polynomial createNonZeroPoly(double[] coefficients) {
        int nonZero = 0;
        for (double coeff : coefficients) {
            if (coeff != 0) {
                nonZero++;
            }
        }
        
        if(nonZero == 0) return new Polynomial();

        double[] resCoeffs = new double[nonZero];
        int[] resExp = new int[nonZero];

        int idx = 0;
        for (int k = 0; k < coefficients.length; k++) {
            if (coefficients[k] != 0) {
                resCoeffs[idx] = coefficients[k];
                resExp[idx] = k;
                idx += 1;
            }
        }
        return new Polynomial(resCoeffs, resExp);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]); 
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }
}