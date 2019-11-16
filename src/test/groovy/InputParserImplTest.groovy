import static org.assertj.core.api.Assertions.*

import org.junit.jupiter.api.Test

class InputParserImplTest {
    InputParserImpl inputParserImpl = new InputParserImpl()

    @Test
    void 'integer coefficients extracted correctly'() {
        String input = '10,-20,30'
        QuadraticCoefficients parsedCoefficients = inputParserImpl.getCoefficients(input)
        QuadraticCoefficients expected = new QuadraticCoefficients(10, -20, 30)
        assertThat(parsedCoefficients).isEqualToComparingFieldByField(expected)
    }
    @Test
    void 'floating point coefficients extracted correctly'() {
        String input = '10.56,20.78,-30.12'
        QuadraticCoefficients parsedCoefficients = inputParserImpl.getCoefficients(input)
        QuadraticCoefficients expected = new QuadraticCoefficients(10.56, 20.78, -30.12)
        assertThat(parsedCoefficients).isEqualToComparingFieldByField(expected)
    }
}
