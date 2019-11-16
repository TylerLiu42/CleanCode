public class InvalidInputException extends Exception {

    InvalidInputException(String message) {
        super(message);
    }

    public static class NoCommaSeparationException extends InvalidInputException {
        NoCommaSeparationException(String message) { super(message); }
    }

    public static class LeadingCoefficientZeroException extends InvalidInputException {
        LeadingCoefficientZeroException(String message) { super(message); }
    }

    public static class InvalidNumberOfCoefficientsException extends InvalidInputException {
        InvalidNumberOfCoefficientsException(String message) { super(message); }
    }

    public static class NonRealCoefficientsException extends InvalidInputException {
        NonRealCoefficientsException(String message) { super(message); }
    }

    public static class WhitespaceException extends InvalidInputException {
        WhitespaceException(String message) { super(message); }
    }
}
