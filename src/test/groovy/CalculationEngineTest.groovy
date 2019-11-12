import org.junit.jupiter.api.Test

import java.util.stream.Stream

import static org.assertj.core.api.Assertions.*

class CalculationEngineTest {
    CalculationEngine calculationEngine = new CalculationEngine()

    @Test
    void 'calculates one distinct root correctly'() {
        double expected = -2
        ArrayList<Double> root = calculationEngine.findRoots(1, 4, 4)
        assertThat(root).contains(expected)
    }
    @Test
    void 'calculates two distinct roots correctly'() {
        double firstRoot = -2
        double secondRoot = -3
        ArrayList<Double> roots = calculationEngine.findRoots(1, 5, 6)
        assertThat(roots).containsExactlyInAnyOrder(firstRoot, secondRoot)
    }
    @Test
    void 'throws exception when no real roots exist'() {
        assertThatExceptionOfType(NoRootsException).isThrownBy({ -> calculationEngine.findRoots(1, 1, 10) })
    }
}
