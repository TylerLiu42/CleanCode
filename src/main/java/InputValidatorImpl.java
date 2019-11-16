public class InputValidatorImpl implements InputValidator {

    private boolean isCommaSeparated(String input) {
        String regex = ".+(,.+)+";
        return input.matches(regex);
    }

    private boolean isLeadingCoefficientZero(String input) {
        String firstCoefficientStr = input.substring(0, input.indexOf(','));
        String regex = "0(.0+)?";
        return firstCoefficientStr.matches(regex);
    }

    private boolean isProperLength(String input) {
        String[] coefficients = input.split(",+");
        return coefficients.length == 3;
    }

    private boolean isNumericCoefficients(String input) {
        String[] coefficients = input.split(",+");
        for (String coefficient : coefficients) {
            if (!isNumeric(coefficient.trim())) {
                return false;
            }
        }
        return true;
    }

    private boolean isWhitespacePresent(String input) {
        return input.chars().mapToObj(c -> (char) c).anyMatch(c -> c.equals(' '));
    }
    @Override
    public boolean isValid(String input) throws InvalidInputException {
        String matcherRegex = "-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?";
        if (!isCommaSeparated(input)) {
            throw new InvalidInputException.NoCommaSeparationException(ExceptionMessageUtils.NO_COMMA_SEPARATION);
        }
        else if (isLeadingCoefficientZero(input)) {
            throw new InvalidInputException.LeadingCoefficientZeroException(ExceptionMessageUtils.LEADING_COEFFICIENT_ZERO);
        }
        else if (!isProperLength(input)) {
            throw new InvalidInputException.InvalidNumberOfCoefficientsException(ExceptionMessageUtils.INVALID_NUM_OF_COEFFICIENTS);
        }
        else if (!isNumericCoefficients(input)) {
            throw new InvalidInputException.NonNumericCoefficientsException(ExceptionMessageUtils.NON_NUMERIC_COEFFICIENTS);
        }
        else if (isWhitespacePresent(input)) {
            throw new InvalidInputException.WhitespaceException(ExceptionMessageUtils.HAS_WHITESPACE);
        }
        else if (!input.matches(matcherRegex)) {
            throw new InvalidInputException(ExceptionMessageUtils.UNKNOWN_VALIDATION_ERROR);
        }
        return true;
    }

    private boolean isNumeric(String input) {
        String regex = "-?\\d+(\\.\\d+)?";
        return input.matches(regex);
    }
}
