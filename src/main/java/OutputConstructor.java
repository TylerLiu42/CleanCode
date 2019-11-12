import java.util.ArrayList;

class OutputConstructor {
    protected String constructOutputRPN(ArrayList<Double> roots) {
        if (roots.size() == 1) {
            return roots.get(0).toString();
        }
        else {
            return roots.get(0).toString() + roots.get(1).toString() + "&";
        }
    }
}
