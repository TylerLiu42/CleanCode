import static org.assertj.core.api.Assertions.*

import org.junit.jupiter.api.Test

class InputParserTest {
    InputParser inputParser = new InputParser()

    @Test
    void 'integer coefficients extracted correctly'() {
        String inputRPN = "10x*x*20x*+30+"
        ArrayList<String> parsedCoefficients = inputParser.getCoefficients(inputRPN)
        ArrayList<String> expected = ["10", "20", "30"]
        assertThat(parsedCoefficients).containsExactlyElementsOf(expected)
    }
    @Test
    void 'floating point coefficients extracted correctly'() {
        String inputRPN = "10.56x*x*20.78x*+30.12+"
        ArrayList<String> parsedCoefficients = inputParser.getCoefficients(inputRPN)
        ArrayList<String> expected = ["10.56", "20.78", "30.12"]
        assertThat(parsedCoefficients).containsExactlyElementsOf(expected)
    }
}
