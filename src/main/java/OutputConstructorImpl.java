class OutputConstructorImpl implements OutputConstructor {

    @Override
    public String constructOutput(QuadraticRoots roots) {
        if (roots.getFirstRoot() == roots.getSecondRoot()) {
            return String.valueOf(roots.getFirstRoot());
        }
        else {
            return roots.getFirstRoot() + "," + roots.getSecondRoot();
        }
    }
}
