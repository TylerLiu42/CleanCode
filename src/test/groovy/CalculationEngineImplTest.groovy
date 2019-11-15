import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.*

class CalculationEngineImplTest {
    CalculationEngineImpl calculationEngine = new CalculationEngineImpl()

    @Test
    void 'calculates one distinct root correctly'() {
        QuadraticRoots expected = new QuadraticRoots(-2, -2)
        QuadraticRoots roots = calculationEngine.findRoots(new QuadraticCoefficients(1,4,4))
        assertThat(roots).isEqualToComparingFieldByField(expected)
    }
    @Test
    void 'calculates two distinct roots correctly'() {
        QuadraticRoots expected = new QuadraticRoots(-2,-3)
        QuadraticRoots roots = calculationEngine.findRoots(new QuadraticCoefficients(1,5,6))
        assertThat(roots).isEqualToComparingFieldByField(expected)
    }
    @Test
    void 'throws exception when no real roots exist'() {
        assertThatExceptionOfType(NoRootsException).isThrownBy({ ->
            calculationEngine.findRoots(new QuadraticCoefficients(1,1,10))
        })
    }
}
