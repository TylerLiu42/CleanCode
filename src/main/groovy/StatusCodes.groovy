package utils;

enum StatusCodes {

    LEADING_COEFFICIENT_ZERO_EXCEPTION("1","Invalid input: leading coefficient cannot be zero"),
    INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION("2","Invalid input: too many/too few coefficients"),
    NON_NUMERIC_COEFFICIENTS_EXCEPTION("3","Invalid input: coefficients must be numeric"),
    NO_ROOTS_EXCEPTION("4","Calculation failed because quadratic has no real roots"),
    SUCCESS("0", "");

    private String statusCode;
    private String errorMessage;

    StatusCodes(String statusCode, String errorMsg) {
        this.statusCode = statusCode;
        this.errorMessage = errorMsg;
    }

    String getStatusCode() {
        return this.statusCode;
    }

    String getErrorMessage() {
        return this.errorMessage;
    }
}


