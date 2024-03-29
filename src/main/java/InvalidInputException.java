public class InvalidInputException extends Exception {

    InvalidInputException(String message) {
        super(message);
    }

    public static class LeadingCoefficientZeroException extends InvalidInputException {
        LeadingCoefficientZeroException(String message) { super(message); }
    }

    public static class InvalidNumberOfCoefficientsException extends InvalidInputException {
        InvalidNumberOfCoefficientsException(String message) { super(message); }
    }

    public static class NonNumericCoefficientsException extends InvalidInputException {
        NonNumericCoefficientsException(String message) { super(message); }
    }

}
