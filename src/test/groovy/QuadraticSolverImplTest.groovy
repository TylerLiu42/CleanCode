import org.junit.jupiter.api.Test

import static org.mockito.ArgumentMatchers.isNull
import static StatusCodes.*

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
        when(outputConstructor.constructOutput(isA(QuadraticRoots), isA(StatusCodes))).thenReturn("3,-2,0")
        String output = quadraticSolver.solve(input)
        String expectedOutput = "3,-2,0"
        assertThat(output).isEqualTo(expectedOutput)
    }
    @Test
    void 'error message returned when input fails validation'() {
        String input = 'bob,fred,dave'
        String resultFromOutputConstructor = "null,null,$NON_NUMERIC_COEFFICIENTS_EXCEPTION.statusCode,$NON_NUMERIC_COEFFICIENTS_EXCEPTION.errorMessage"
        when(inputValidator.isValid(input)).thenThrow(new InvalidInputException.NonNumericCoefficientsException(NON_NUMERIC_COEFFICIENTS_EXCEPTION.toString()))
        when(outputConstructor.constructOutput(isNull(), isA(StatusCodes))).thenReturn(resultFromOutputConstructor)
        assertThat(quadraticSolver.solve(input)).isEqualTo('null,null,3,Invalid input: coefficients must be numeric')
    }
    @Test
    void 'error message included in output when no roots exception thrown by calc engine'() {
        when(inputParser.getCoefficients(anyString())).thenReturn(new QuadraticCoefficients(1,1,10))
        when(calculationEngine.findRoots(isA(QuadraticCoefficients))).thenThrow(new NoRootsException(NO_ROOTS_EXCEPTION.toString()))
        String resultFromOutputConstructor = "null,null,${NO_ROOTS_EXCEPTION.statusCode},${NO_ROOTS_EXCEPTION.errorMessage}"
        when(outputConstructor.constructOutput(isNull(), isA(StatusCodes))).thenReturn(resultFromOutputConstructor)
        String input = "1,1,10"
        assertThat(quadraticSolver.solve(input)).isEqualTo('null,null,4,Calculation failed because quadratic has no real roots')
    }
}
