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
    @Test
    void 'should throw exception if too many/too few coefficients are specified'() {
        String tooFewCoefficients = '-10.5,20.69'
        String tooManyCoefficients = '-20, 30, 100.202'
        assertThatExceptionOfType(InvalidInputException).isThrownBy({ ->
            inputParserImpl.getCoefficients(tooFewCoefficients)
        })
        assertThatExceptionOfType(InvalidInputException).isThrownBy( { ->
            inputParserImpl.getCoefficients(tooManyCoefficients)
        })
    }
    @Test
    void 'should throw exception if coefficients are not comma separated'() {
        String inputWithoutCommas = '10 20 30'
        assertThatExceptionOfType(InvalidInputException).isThrownBy( { ->
            inputParserImpl.getCoefficients(inputWithoutCommas)
        })
    }
    @Test
    void 'should throw exception if coefficients are not supplied as numbers'() {
        String invalidFormatCoeff = 'a,b,c'
        assertThatExceptionOfType(InvalidInputException).isThrownBy( { ->
            inputParserImpl.getCoefficients(invalidFormatCoeff)
        })
    }
}
