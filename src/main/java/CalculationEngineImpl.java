class CalculationEngineImpl implements CalculationEngine {

    @Override
    public QuadraticRoots findRoots(QuadraticCoefficients coefficients) throws NoRootsException {
        double a = coefficients.leading;
        double b = coefficients.middle;
        double c = coefficients.trailing;
        double discriminant = getDiscriminant(a, b, c);
        double firstRoot = (-b + Math.sqrt(discriminant))/(2*a);
        double secondRoot = (-b - Math.sqrt(discriminant))/(2*a);
        if (discriminant >= 0) {
            return new QuadraticRoots(firstRoot, secondRoot);
        }
        else {
            throw new NoRootsException(ExceptionMessageUtils.NO_REAL_ROOTS);
        }
    }
    private double getDiscriminant(double a, double b, double c) {
        return Math.pow(b, 2) - 4*a*c;
    }
}
