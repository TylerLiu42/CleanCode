import java.util.ArrayList;

public class QuadraticSolver {
    public static void main(String[] args) throws NoRootsException {
        String inputRPN = "1x*x*10x*+1+";
        ArrayList<String> coefficients = new InputParser().getCoefficients(inputRPN);
        double a = Double.parseDouble(coefficients.get(0));
        double b = Double.parseDouble(coefficients.get(1));
        double c = Double.parseDouble(coefficients.get(2));
        ArrayList<Double> roots = new CalculationEngine().findRoots(a, b, c);
        String output = new OutputConstructor().constructOutputRPN(roots);
        System.out.print(output);
    }
}
