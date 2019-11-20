enum StatusCodes {

    LEADING_COEFFICIENT_ZERO_EXCEPTION(StatusCodeUtils.LEADING_COEFFICIENT_ZERO.code, StatusCodeUtils.LEADING_COEFFICIENT_ZERO.message),
    INVALID_NUMBER_OF_COEFFICIENTS_EXCEPTION(StatusCodeUtils.INVALID_NUMBER_OF_COEFFICIENTS.code, StatusCodeUtils.INVALID_NUMBER_OF_COEFFICIENTS.message),
    NON_NUMERIC_COEFFICIENTS_EXCEPTION(StatusCodeUtils.NON_NUMERIC_COEFFICIENTS.code, StatusCodeUtils.NON_NUMERIC_COEFFICIENTS.message),
    NO_ROOTS_EXCEPTION(StatusCodeUtils.NO_ROOTS.code, StatusCodeUtils.NO_ROOTS.message),
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


