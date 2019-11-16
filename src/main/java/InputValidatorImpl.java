import java.math.BigDecimal;

public class InputValidatorImpl implements InputValidator {

    private boolean isCommaSeparated(String input) {
        String regex = ".+,.+,.+";
        return input.matches(regex);
    }

    protected boolean isLeadingCoefficientZero(String input) {
        BigDecimal firstCoefficient = new BigDecimal(input.trim().substring(0, input.indexOf(',')));
        BigDecimal zero = new BigDecimal(0);
        return firstCoefficient.compareTo(zero) == 0;
    }

    protected boolean isProperLength(String input) {
        String[] coefficients = input.split(",");
        return coefficients.length == 3;
    }

    protected boolean isNumericCoefficients(String input) {
        String[] coefficients = input.split(",");
        for (String coefficient : coefficients) {
            if (!isNumeric(coefficient)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isWhitespacePresent(String input) {
        return input.chars().mapToObj(c -> (char) c).anyMatch(c -> c.equals(' '));
    }
    @Override
    public boolean isValid(String input) throws InvalidInputException {
        String matcherRegex = "-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?";
        if (!isCommaSeparated(input)) {
            throw new InvalidInputException.NoCommaSeparationException("Invalid input: coefficients must be comma separated");
        }
        else if (isLeadingCoefficientZero(input)) {
            throw new InvalidInputException.LeadingCoefficientZeroException("Invalid input: leading coefficient cannot be zero");
        }
        else if (!isProperLength(input)) {
            throw new InvalidInputException.InvalidNumberOfCoefficientsException("Invalid input: too many/too few coefficients");
        }
        else if (!isNumericCoefficients(input)) {
            throw new InvalidInputException.NonRealCoefficientsException("Invalid input: coefficients must be numeric");
        }
        else if (isWhitespacePresent(input)) {
            throw new InvalidInputException.WhitespaceException("Invalid input: no whitespace allowed");
        }
        else if (!input.matches(matcherRegex)) {
            throw new InvalidInputException("Invalid input: Unknown reason");
        }
        return true;
    }

    private boolean isNumeric(String input) {
        String regex = "-?\\d+(\\.\\d+)?";
        return input.matches(regex);
    }
}
