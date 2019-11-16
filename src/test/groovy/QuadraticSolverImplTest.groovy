import org.junit.jupiter.api.Test

import static ExceptionMessageUtils.*
import static org.assertj.core.api.Assertions.*
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.ArgumentMatchers.isA
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class QuadraticSolverImplTest {

    private InputValidator inputValidator = mock(InputValidator)

    private InputParser inputParser = mock(InputParser)

    private CalculationEngine calculationEngine = mock(CalculationEngine)

    private OutputConstructor outputConstructor = mock(OutputConstructor)

    QuadraticSolver quadraticSolver = new QuadraticSolverImpl(inputValidator, inputParser, calculationEngine, outputConstructor)

    @Test
    void 'given comma separated quadratic coefficients in order with distinct roots, output correct roots'() {
        String input = '1,1,-6'
        when(inputValidator.isValid(input)).thenReturn(true)
        when(inputParser.getCoefficients(anyString())).thenReturn(new QuadraticCoefficients(1, 1, -6))
        when(calculationEngine.findRoots(isA(QuadraticCoefficients))).thenReturn(new QuadraticRoots(3, -2))
        when(outputConstructor.constructOutput(isA(QuadraticRoots))).thenReturn("3,-2")
        String output = quadraticSolver.solve(input)
        String expectedOutput = "3,-2"
        assertThat(output).isEqualTo(expectedOutput)
    }
    @Test
    void 'error returned when input fails validation due to non-numeric coefficients'() {
        String input = 'bob,fred,dave'
        when(inputValidator.isValid(input)).thenThrow(new InvalidInputException.NonNumericCoefficientsException(NON_NUMERIC_COEFFICIENTS))
        assertThat(quadraticSolver.solve(input)).isEqualTo(NON_NUMERIC_COEFFICIENTS)
    }
    @Test
    void 'error returned when no roots exception thrown by calc engine'() {
        when(inputParser.getCoefficients(anyString())).thenReturn(new QuadraticCoefficients(1,1,10))
        when(calculationEngine.findRoots(isA(QuadraticCoefficients))).thenThrow(new NoRootsException(NO_REAL_ROOTS))
        String input = "1,1,10"
        assertThat(quadraticSolver.solve(input)).isEqualTo(NO_REAL_ROOTS)
    }
    @Test
    void 'error returned when input fails validation due to bad comma separation'() {
        String input = '10,20 30'
        when(inputValidator.isValid(input)).thenThrow(new InvalidInputException.NoCommaSeparationException(NO_COMMA_SEPARATION))
        assertThat(quadraticSolver.solve(input)).isEqualTo(NO_COMMA_SEPARATION)
    }
    @Test
    void 'error returned when input fails validation due to leading coefficient being zero'() {
        String input = "0.00,20,30"
        when(inputValidator.isValid(input)).thenThrow(new InvalidInputException.NoCommaSeparationException(LEADING_COEFFICIENT_ZERO))
        assertThat(quadraticSolver.solve(input)).isEqualTo(LEADING_COEFFICIENT_ZERO)
    }
    @Test
    void 'error returned when input fails validation due to over/under 3 coefficients'() {
        String inputTooFewCoefficients = '10,20'
        when(inputValidator.isValid(inputTooFewCoefficients)).thenThrow(new InvalidInputException.InvalidNumberOfCoefficientsException(INVALID_NUM_OF_COEFFICIENTS))
        assertThat(quadraticSolver.solve(inputTooFewCoefficients)).isEqualTo(INVALID_NUM_OF_COEFFICIENTS)
    }
    @Test
    void 'error returned when input fails validation due to input having whitespace'() {
        String inputWithWhitespace = '10, 20, 30'
        when(inputValidator.isValid(inputWithWhitespace)).thenThrow(new InvalidInputException.WhitespaceException(HAS_WHITESPACE))
        assertThat(quadraticSolver.solve(inputWithWhitespace)).isEqualTo(HAS_WHITESPACE)
    }
}
