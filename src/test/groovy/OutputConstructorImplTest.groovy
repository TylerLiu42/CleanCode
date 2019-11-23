import org.junit.jupiter.api.Test
import static StatusCodes.*
import static org.assertj.core.api.Assertions.*

class OutputConstructorImplTest {
    OutputConstructorImpl outputConstructor = new OutputConstructorImpl()

    @Test
    void 'outputs a single distinct root'() {
        QuadraticRoots roots = new QuadraticRoots(-2, -2)
        String expected = '-2.0,-2.0,0'
        String actual = outputConstructor.constructOutput(roots, SUCCESS)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs correct output for two distinct roots'() {
        QuadraticRoots roots = new QuadraticRoots(-2, -3)
        String expected = "-2.0,-3.0,0"
        String actual = outputConstructor.constructOutput(roots, SUCCESS)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message for the case with no real roots'() {
        String expected = "null,null,${NO_ROOTS_EXCEPTION.statusCode},${NO_ROOTS_EXCEPTION.errorMessage}"
        String actual = outputConstructor.constructOutput(null, NO_ROOTS_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message when coefficients are not numeric'() {
        String expected = "null,null,${NON_NUMERIC_COEFFICIENTS_EXCEPTION.statusCode},${NON_NUMERIC_COEFFICIENTS_EXCEPTION.errorMessage}"
        String actual = outputConstructor.constructOutput(null, NON_NUMERIC_COEFFICIENTS_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message for the case of improper input length'() {
        String expected = "null,null,${INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION.statusCode},${INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION.errorMessage}"
        String actual = outputConstructor.constructOutput(null, INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs output with correct error code and message when leading coefficient is zero'() {
        String expected = "null,null,${LEADING_COEFFICIENT_ZERO_EXCEPTION.statusCode},${LEADING_COEFFICIENT_ZERO_EXCEPTION.errorMessage}"
        String actual = outputConstructor.constructOutput(null, LEADING_COEFFICIENT_ZERO_EXCEPTION)
        assertThat(actual).isEqualTo(expected)
    }
}
