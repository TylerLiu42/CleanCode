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
        assertThat(inputValidator.isProperLength(inputTooManyCoefficients)).isFalse()
        assertThat(inputValidator.isProperLength(inputTooFewCoefficients)).isFalse()
    }
    @Test
    void 'coefficients must be real numbers'() {
        String inputWithLetterCoefficients = 'A,B,C'
        String inputWithGarbageCoefficients = '%3$*,()^,89*'
        String inputWithNumericCoefficients = '10.2,20,-30.3'
        assertThat(inputValidator.isNumericCoefficients(inputWithLetterCoefficients)).isFalse()
        assertThat(inputValidator.isNumericCoefficients(inputWithGarbageCoefficients)).isFalse()
        assertThat(inputValidator.isNumericCoefficients(inputWithNumericCoefficients)).isTrue()
    }
    @Test
    void 'input should not have whitespace'() {
        String inputWithWhitespace = '10, 20, 30'
        String inputWithoutWhitespace = '10,20,30'
        assertThat(inputValidator.isWhitespacePresent(inputWithWhitespace)).isTrue()
        assertThat(inputValidator.isWhitespacePresent(inputWithoutWhitespace)).isFalse()
    }
}
