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
        QuadraticRoots roots;
        try {
            inputValidator.isValid(inputQuadratic);
        }
        catch(InvalidInputException e) {
            return outputConstructor.constructOutput(null, StatusCodes.valueOf(e.getMessage()));
        }
        QuadraticCoefficients coefficients;
        coefficients = inputParser.getCoefficients(inputQuadratic);
        try {
            roots = calculationEngine.findRoots(coefficients);
        }
        catch (NoRootsException e) {
            return outputConstructor.constructOutput(null, StatusCodes.valueOf(e.getMessage()));
        }
        return outputConstructor.constructOutput(roots, StatusCodes.SUCCESS);
    }
}
