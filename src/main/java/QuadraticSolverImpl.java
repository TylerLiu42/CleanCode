public class QuadraticSolverImpl implements QuadraticSolver {

    private InputValidator inputValidator;
    private InputParser inputParser;
    private CalculationEngine calculationEngine;
    private OutputConstructor outputConstructor;

    public QuadraticSolverImpl(InputValidator inputValidator, InputParser inputParser, CalculationEngine calculationEngine,
                               OutputConstructor outputConstructor) {
        this.inputParser = inputParser;
        this.calculationEngine = calculationEngine;
        this.outputConstructor = outputConstructor;
        this.inputValidator = inputValidator;
    }

    @Override
    public String solve(String inputQuadratic) {
        try {
            inputValidator.isValid(inputQuadratic);
        }
        catch(InvalidInputException e) {
            return e.getMessage();
        }
        QuadraticCoefficients coefficients;
        coefficients = inputParser.getCoefficients(inputQuadratic);
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
