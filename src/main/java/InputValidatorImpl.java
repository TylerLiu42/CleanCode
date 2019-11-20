public class InputValidatorImpl implements InputValidator {

    private boolean isLeadingCoefficientZero(String input) {
        String firstCoefficientStr = input.substring(0, input.indexOf(','));
        String regex = "0(.0+)?";
        return firstCoefficientStr.matches(regex);
    }

    private boolean isProperLength(String input) {
        String[] coefficients = input.split(",", 0);
        return coefficients.length == 3;
    }

    private boolean isNumericCoefficients(String input) {
        String[] coefficients = input.split(",", 0);
        for (String coefficient : coefficients) {
            if (!isNumeric(coefficient.trim())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValid(String input) throws InvalidInputException {
        if (!isProperLength(input)) {
            throw new InvalidInputException.InvalidNumberOfCoefficientsException("INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION");
        }
        else if (!isNumericCoefficients(input)) {
            throw new InvalidInputException.NonNumericCoefficientsException("NON_NUMERIC_COEFFICIENTS_EXCEPTION");
        }
        else if (isLeadingCoefficientZero(input)) {
            throw new InvalidInputException.LeadingCoefficientZeroException("LEADING_COEFFICIENT_ZERO_EXCEPTION");
        }
        return true;
    }

    private boolean isNumeric(String input) {
        String regex = "-?\\d+(\\.\\d+)?";
        return input.matches(regex);
    }
}
