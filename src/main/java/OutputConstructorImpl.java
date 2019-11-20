class OutputConstructorImpl implements OutputConstructor {

    @Override
    public String constructOutput(QuadraticRoots roots, StatusCodes status) {
        if (!status.getStatusCode().equals("0")) {
            return "null,null," + status.getStatusCode() + "," + status.getErrorMessage();
        }
        else {
            return roots.getFirstRoot() + "," + roots.getSecondRoot() + "," + status.getStatusCode();
        }
    }
}
