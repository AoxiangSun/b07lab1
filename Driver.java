import java.io.File;

public class Driver {
    public static void main(String[] args) {
        double[] c1 = {6, -2, 5};
        int[] e1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(c1, e1); 
        
        double[] c2 = {-3, 2};
        int[] e2 = {1, 2};
        Polynomial p2 = new Polynomial(c2, e2);         
        Polynomial p3 = p1.multiply(p2);
        p3.saveToFile("output.txt");
        System.out.println("Polynomial multiplication tested and saved to output.txt");

        try {
            File testFile = new File("input.txt");
            if(testFile.createNewFile() || testFile.exists()) {
                java.io.FileWriter myWriter = new java.io.FileWriter(testFile);
                myWriter.write("5-3x2+7x8");
                myWriter.close();
                
                Polynomial p4 = new Polynomial(testFile);
                System.out.println("Read from file test, evaluate at x=1: " + p4.evaluate(1)); 
            }
        } catch (Exception e) {
            System.out.println("File testing error.");
        }
    }
}