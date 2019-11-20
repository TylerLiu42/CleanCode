import groovy.transform.TupleConstructor

class StatusCodeUtils {

    @TupleConstructor()
    static class Status {
        String code
        String message
    }

    public static final Status LEADING_COEFFICIENT_ZERO = new Status(code: '1', message: 'Invalid input: leading coefficient cannot be zero');
    public static final Status INVALID_NUMBER_OF_COEFFICIENTS = new Status(code: "2", message: "Invalid input: too many/too few coefficients");
    public static final Status NON_NUMERIC_COEFFICIENTS = new Status(code: "3", message: "Invalid input: coefficients must be numeric");
    public static final Status NO_ROOTS = new Status(code: "4", message: "Calculation failed because quadratic has no real roots");

}
