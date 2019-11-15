class OutputConstructorImpl implements OutputConstructor {

    @Override
    public String constructOutput(QuadraticRoots roots) {
        if (roots.firstRoot == roots.secondRoot) {
            return String.valueOf(roots.firstRoot);
        }
        else {
            return roots.firstRoot + "," + roots.secondRoot;
        }
    }
}
