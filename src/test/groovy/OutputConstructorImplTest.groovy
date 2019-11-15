import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.*

class OutputConstructorImplTest {
    OutputConstructorImpl outputConstructor = new OutputConstructorImpl()

    @Test
    void 'outputs a single distinct root'() {
        QuadraticRoots roots = new QuadraticRoots(-2, -2)
        String expected = '-2.0'
        String actual = outputConstructor.constructOutput(roots)
        assertThat(actual).isEqualTo(expected)
    }
    @Test
    void 'constructs correct output for two distinct roots'() {
        QuadraticRoots roots = new QuadraticRoots(-2, -3)
        String expected = "-2.0,-3.0"
        String actual = outputConstructor.constructOutput(roots)
        assertThat(actual).isEqualTo(expected)
    }
}
