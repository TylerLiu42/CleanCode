import java.util.ArrayList;
import java.util.Arrays;

class InputParser {
    protected ArrayList<String> getCoefficients(String inputRPN) {
        String regex = "[a-z|*|+]+";
        String[] strArr = inputRPN.split(regex, 0);
        return new ArrayList<>(Arrays.asList(strArr));
    }
}
