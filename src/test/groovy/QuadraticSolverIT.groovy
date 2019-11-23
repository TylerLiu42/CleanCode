import org.junit.jupiter.api.Test

import static StatusCodes.*

import static org.assertj.core.api.Assertions.*

class QuadraticSolverIT {

    InputValidator inputValidator = new InputValidatorImpl()
    InputParser inputParser = new InputParserImpl()
    CalculationEngine calculationEngine = new CalculationEngineImpl()
    OutputConstructor outputConstructor = new OutputConstructorImpl()
    QuadraticSolver quadraticSolver = new QuadraticSolverImpl(inputValidator, inputParser, calculationEngine, outputConstructor)

    @Test
    void 'should output two roots with success code and no error message when input is well formed'() {
        String input = '2.5, 5.5, -6'
        String expected = '0.8,-3.0,0'
        assertThat(quadraticSolver.solve(input)).isEqualTo(expected)
    }
    @Test
    void 'should output correct error code and message when input has improper length'() {
        String input = '2.5, 5.5,'
        String expected = "null,null,${INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION.statusCode},${INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION.errorMessage}"
        assertThat(quadraticSolver.solve(input)).isEqualTo(expected)
    }
    @Test
    void 'should output correct error code and message when input has non-numeric coefficients'() {
        String input = '2.5, A, %$232'
        String expected = "null,null,${NON_NUMERIC_COEFFICIENTS_EXCEPTION.statusCode},${NON_NUMERIC_COEFFICIENTS_EXCEPTION.errorMessage}"
        assertThat(quadraticSolver.solve(input)).isEqualTo(expected)
    }
    @Test
    void 'should output correct error code and message when input has leading coefficient zero'() {
        String input = '0.00, -5.2, -100'
        String expected = "null,null,${LEADING_COEFFICIENT_ZERO_EXCEPTION.statusCode},${LEADING_COEFFICIENT_ZERO_EXCEPTION.errorMessage}"
        assertThat(quadraticSolver.solve(input)).isEqualTo(expected)
    }
    @Test
    void 'should output correct error code and message when input has no real roots'() {
        String input = '1,1,10'
        String expected = "null,null,${NO_ROOTS_EXCEPTION.statusCode},${NO_ROOTS_EXCEPTION.errorMessage}"
        assertThat(quadraticSolver.solve(input)).isEqualTo(expected)
    }
}
