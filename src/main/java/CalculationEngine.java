import java.util.ArrayList;

public class CalculationEngine {
    private ArrayList<Double> roots = new ArrayList<>();

    public ArrayList<Double> findRoots(double a, double b, double c) throws NoRootsException {
        double discriminant = getDiscriminant(a, b, c);
        if (discriminant >= 0) {
            roots.add((-b + Math.sqrt(discriminant))/2*a);
        }
        if (discriminant > 0) {
            roots.add((-b - Math.sqrt(discriminant))/2*a);
        }
        else if (discriminant < 0){
            throw new NoRootsException("No real roots");
        }
        return roots;
    }
    private double getDiscriminant(double a, double b, double c) {
        return Math.pow(b, 2) - 4*a*c;
    }
}
