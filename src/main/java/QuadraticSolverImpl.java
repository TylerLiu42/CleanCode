public class QuadraticSolverImpl implements QuadraticSolver {

    private InputParser inputParser;
    private CalculationEngine calculationEngine;
    private OutputConstructor outputConstructor;

    public QuadraticSolverImpl(InputParser inputParser, CalculationEngine calculationEngine, OutputConstructor outputConstructor) {
        this.inputParser = inputParser;
        this.calculationEngine = calculationEngine;
        this.outputConstructor = outputConstructor;
    }

    @Override
    public String solve(String inputQuadratic) {
        QuadraticCoefficients coefficients;
        try {
            coefficients = inputParser.getCoefficients(inputQuadratic);
        }
        catch (InvalidInputException e) {
            return "java.lang.InvalidInputException";
        }
        QuadraticRoots roots;
        try {
            roots = calculationEngine.findRoots(coefficients);
        }
        catch (NoRootsException e) {
            return e.getMessage();
        }
        return outputConstructor.constructOutput(roots);
    }
}
