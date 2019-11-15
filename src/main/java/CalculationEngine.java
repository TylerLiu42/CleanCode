public interface CalculationEngine {
    QuadraticRoots findRoots(QuadraticCoefficients coefficients) throws NoRootsException;
}
