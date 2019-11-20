import org.junit.jupiter.api.Test
import static StatusCodeUtils.*
import static org.assertj.core.api.Assertions.*

class OutputConstructorImplTest {
    OutputConstructorImpl outputConstructor = new OutputConstructorImpl()

    @Test
    void 'outputs a single distinct root'() {
        QuadraticRoots roots = new QuadraticRoots(-2, -2)
        String expected = '-2.0,-2.0,0'
        String actual = outputConstructor.constructOutput(roots, StatusCodes.SUCCESS)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs correct output for two distinct roots'() {
        QuadraticRoots roots = new QuadraticRoots(-2, -3)
        String expected = "-2.0,-3.0,0"
        String actual = outputConstructor.constructOutput(roots, StatusCodes.SUCCESS)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message for the case with no real roots'() {
        String expected = "null,null,${NO_ROOTS.code},${NO_ROOTS.message}"
        String actual = outputConstructor.constructOutput(null, StatusCodes.NO_ROOTS_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message when coefficients are not numeric'() {
        String expected = "null,null,${NON_NUMERIC_COEFFICIENTS.code},${NON_NUMERIC_COEFFICIENTS.message}"
        String actual = outputConstructor.constructOutput(null, StatusCodes.NON_NUMERIC_COEFFICIENTS_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message for the case of improper input length'() {
        String expected = "null,null,${INVALID_NUMBER_OF_COEFFICIENTS.code},${INVALID_NUMBER_OF_COEFFICIENTS.message}"
        String actual = outputConstructor.constructOutput(null, StatusCodes.INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message when leading coefficient is zero'() {
        String expected = "null,null,${LEADING_COEFFICIENT_ZERO.code},${LEADING_COEFFICIENT_ZERO.message}"
        String actual = outputConstructor.constructOutput(null, StatusCodes.LEADING_COEFFICIENT_ZERO_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
}
