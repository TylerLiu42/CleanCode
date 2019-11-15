import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.*
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.ArgumentMatchers.isA
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

class QuadraticSolverImplTest {

    private InputParser inputParser = mock(InputParser)

    private CalculationEngine calculationEngine = mock(CalculationEngine)

    private OutputConstructor outputConstructor = mock(OutputConstructor)

    QuadraticSolver quadraticSolver = new QuadraticSolverImpl(inputParser, calculationEngine, outputConstructor)

    @Test
    void 'given comma separated quadratic coefficients in order, with distinct roots, output roots'() {
        when(inputParser.getCoefficients(anyString())).thenReturn(new QuadraticCoefficients(1, 1, -6))
        when(calculationEngine.findRoots(isA(QuadraticCoefficients))).thenReturn(new QuadraticRoots(3, -2))
        when(outputConstructor.constructOutput(isA(QuadraticRoots))).thenReturn("3,-2")
        String input = '1,1,-6'
        String output = quadraticSolver.solve(input)
        String expectedOutput = "3,-2"
        assertThat(output).isEqualTo(expectedOutput)
    }
    @Test
    void 'error returned when invalid input exception thrown by input parser'() {
        when(inputParser.getCoefficients(anyString())).thenThrow(InvalidInputException)
        String input = 'bob, fred, dave'
        String expectedOutput = 'java.lang.InvalidInputException'
        assertThat(quadraticSolver.solve(input)).isEqualTo(expectedOutput)
    }
    @Test
    void 'error returned when no roots exception thrown by calc engine'() {

    }
}
