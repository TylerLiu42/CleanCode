class InputParserImpl implements InputParser {
    @Override
    public QuadraticCoefficients getCoefficients(String inputCoefficients) throws InvalidInputException {
        if (!isValid(inputCoefficients)) {
            throw new InvalidInputException("input is invalid");
        }
        String regex = ",";
        String[] arrOfCoefficients = inputCoefficients.split(regex, 0);
        return constructQuadraticCoefficients(arrOfCoefficients);
    }

    private boolean isValid(String str) {
        String matcherRegex = "-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?";
        return str.matches(matcherRegex);
    }

    private QuadraticCoefficients constructQuadraticCoefficients(String[] arrayOfCoefficients) {
        double leadingCoeff = Double.parseDouble(arrayOfCoefficients[0]);
        double middleCoeff = Double.parseDouble(arrayOfCoefficients[1]);
        double trailingCoeff = Double.parseDouble(arrayOfCoefficients[2]);
        return new QuadraticCoefficients(leadingCoeff, middleCoeff, trailingCoeff);
    }
}
