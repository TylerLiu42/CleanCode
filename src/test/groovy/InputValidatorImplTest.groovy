import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.*

class InputValidatorImplTest {
    InputValidatorImpl inputValidator = new InputValidatorImpl()

    @Test
    void 'input coefficients should be comma separated'() {
        String inputNotCommaSeparated = '10.12 20.23 30.34'
        assertThatExceptionOfType(InvalidInputException.NoCommaSeparationException).isThrownBy({ ->
            inputValidator.isValid(inputNotCommaSeparated)
        })
    }
    @Test
    void 'the leading coefficient must be non-zero'() {
        String inputWithLeadingIntegerZero = '0,10,20'
        String inputWithLeadingFloatZero = '0.00,10,20'
        assertThatExceptionOfType(InvalidInputException.LeadingCoefficientZeroException).isThrownBy({ ->
            inputValidator.isValid(inputWithLeadingIntegerZero)
        })
        assertThatExceptionOfType(InvalidInputException.LeadingCoefficientZeroException).isThrownBy({ ->
            inputValidator.isValid(inputWithLeadingFloatZero)
        })
    }
    @Test
    void 'input should have strictly 3 coefficients'() {
        String inputTooManyCoefficients = '10,20,30,40,50'
        String inputTooFewCoefficients = '10,20'
        assertThatExceptionOfType(InvalidInputException.InvalidNumberOfCoefficientsException).isThrownBy({ ->
            inputValidator.isValid(inputTooManyCoefficients)
        })
        assertThatExceptionOfType(InvalidInputException.InvalidNumberOfCoefficientsException).isThrownBy({ ->
            inputValidator.isValid(inputTooFewCoefficients)
        })
    }
    @Test
    void 'coefficients must be real numbers'() {
        String inputWithLetterCoefficients = 'A,B,C'
        String inputWithGarbageCoefficients = '%3$*,()^,89*'
        String inputWithNumericCoefficients = '10.2,20,-30.3'
        assertThatExceptionOfType(InvalidInputException.NonNumericCoefficientsException).isThrownBy({ ->
            inputValidator.isValid(inputWithLetterCoefficients)
        })
        assertThatExceptionOfType(InvalidInputException.NonNumericCoefficientsException).isThrownBy({ ->
            inputValidator.isValid(inputWithGarbageCoefficients)
        })
        assertThat(inputValidator.isValid(inputWithNumericCoefficients)).isTrue()
    }
    @Test
    void 'input should not have whitespace'() {
        String inputWithWhitespace = '10, 20, 30'
        String inputWithoutWhitespace = '10,20,30'
        assertThatExceptionOfType(InvalidInputException.WhitespaceException).isThrownBy({ ->
            inputValidator.isValid(inputWithWhitespace)
        })
        assertThat(inputValidator.isValid(inputWithoutWhitespace)).isTrue()
    }
    @Test
    void 'input has some unidentifiable syntactic problem, throw generic exception'() {
        String garbageInput = '-20,,30,,,40'
        assertThatExceptionOfType(InvalidInputException).isThrownBy({ ->
            inputValidator.isValid(garbageInput)
        })
    }
}
