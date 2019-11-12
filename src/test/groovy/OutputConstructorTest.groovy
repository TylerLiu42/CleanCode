import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.*

class OutputConstructorTest {
    OutputConstructor outputConstructor = new OutputConstructor()

    @Test
    void 'outputs a single distinct root'() {
        double root = -2.0
        String expected = "-2.0"
        String actual = outputConstructor.constructOutputRPN([root] as ArrayList<Double>)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs correct RPN for two distinct roots'() {
        double firstRoot = -2
        double secondRoot = -3
        String expected = "-2.0-3.0&"
        String actual = outputConstructor.constructOutputRPN([firstRoot, secondRoot] as ArrayList<Double>)
        assertThat(actual).isEqualTo(expected)
    }
}
