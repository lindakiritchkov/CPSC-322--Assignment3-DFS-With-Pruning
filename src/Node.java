import java.util.*;

public class Node {
    public TreeMap<String, Integer> variableAssignments;

    public Node() {
        variableAssignments = new TreeMap<>();
    }

    // clones the nodeToBeCopied, and returns the clone
    public Node(Node nodeToBeCopied) {
        this.variableAssignments = new TreeMap<>(nodeToBeCopied.variableAssignments);
    }

    public ArrayList<Node> generateNextNodes() {

        // first variable assignment
        String variableToBeAssignedNext = "A";

        // other variable assignments
        if (!variableAssignments.isEmpty()) {
            // get the last variable assigned, and increment it to get the next letter
            String lastVariableAssigned = variableAssignments.lastKey();
            int charValue = lastVariableAssigned.charAt(0);
            variableToBeAssignedNext = String.valueOf( (char) (charValue + 1));
        }

        ArrayList<Node> nextNodes = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Node newNode = new Node(this);
            newNode.variableAssignments.put(variableToBeAssignedNext, i);
            nextNodes.add(newNode);
        }

        return nextNodes;
    }

    public boolean isLeaf() {
        if (variableAssignments.isEmpty()) {
            return false;
        }
        String lastVariableAssigned = variableAssignments.lastKey();
        return lastVariableAssigned.equals("H");
    }

    public boolean areConstraintsSatisfied() {

        // each constraint is represented by one of the following booleans
        // boolean names relate to which 2 variables are involved in the constraint
        boolean ag = isConstAGSatisfied();
        boolean ah = isConstAHSatisfied();
        boolean fb = isConstFBSatisfied();
        boolean gh = isConstGHSatisfied();
        boolean gc = isConstGCSatisfied();
        boolean hc = isConstHCSatisfied();
        boolean hd = isConstHDSatisfied();
        boolean dg = isConstDGSatisfied();
        boolean dc = isConstDCSatisfied();
        boolean ec = isConstECSatisfied();
        boolean ed = isConstEDSatisfied();
        boolean eh = isConstEHSatisfied();
        boolean gf = isConstGFSatisfied();
        boolean hf = isConstHFSatisfied();
        boolean cf = isConstCFSatisfied();
        boolean df = isConstDFSatisfied();
        boolean ef = isConstEFSatisfied();

        /*
        If any constraint is false (not satisfied), this will return false.
        Otherwise, all constraints are satisfied (or a variable is not yet assigned),
        and this will return true, meaning that this branch should not be pruned
        */

        return  ag &&
                ah &&
                fb &&
                gh &&
                gc &&
                hc &&
                hd &&
                dg &&
                dc &&
                ec &&
                ed &&
                eh &&
                gf &&
                hf &&
                cf &&
                df &&
                ef;
    }

    /*

    ***************** CONSTRAINT HELPER FUNCTIONS ******************

    - Each isConstXYSatisfied helper function below first checks
      if both variables have been assigned.

    - If either has not yet been assigned, then true will be returned,
      indicating that the constraint is not violated

    - If both variables have been assigned, the function will check if
      the constraint is satisfied.

    */

    // A > G
    private boolean isConstAGSatisfied() {
        Integer A = variableAssignments.get("A");
        Integer G = variableAssignments.get("G");
        if (A == null || G == null) {
            return true;
        }

        return A > G;
    }

    // A <= H
    private boolean isConstAHSatisfied() {
        Integer A = variableAssignments.get("A");
        Integer H = variableAssignments.get("H");
        if (A == null || H == null) {
            return true;
        }

        return A <= H;
    }

    // |F-B| = 1
    private boolean isConstFBSatisfied() {
        Integer F = variableAssignments.get("F");
        Integer B = variableAssignments.get("B");
        if (F == null || B == null) {
            return true;
        }

        return Math.abs(F - B) == 1;
    }

    // G < H
    private boolean isConstGHSatisfied() {
        Integer H = variableAssignments.get("H");
        Integer G = variableAssignments.get("G");
        if (H == null || G == null) {
            return true;
        }

        return G < H;
    }

    // |G - C| = 1
    private boolean isConstGCSatisfied() {
        Integer C = variableAssignments.get("C");
        Integer G = variableAssignments.get("G");
        if (C == null || G == null) {
            return true;
        }

        return Math.abs(G - C) == 1;
    }

    // |H -C| is even
    private boolean isConstHCSatisfied() {
        Integer H = variableAssignments.get("H");
        Integer C = variableAssignments.get("C");
        if (H == null || C == null) {
            return true;
        }
        return Math.abs(H - C) % 2 == 0;
    }

    // H != D
    private boolean isConstHDSatisfied() {
        Integer H = variableAssignments.get("H");
        Integer D = variableAssignments.get("D");
        if (H == null || D == null) {
            return true;
        }

        return H != D;
    }

    // D ≥ G
    private boolean isConstDGSatisfied() {
        Integer D = variableAssignments.get("D");
        Integer G = variableAssignments.get("G");
        if (D == null || G == null) {
            return true;
        }
        return D >= G;
    }


    // D != C
    private boolean isConstDCSatisfied() {
        Integer D = variableAssignments.get("D");
        Integer C = variableAssignments.get("C");
        if (D == null || C == null) {
            return true;
        }
        return D != C;
    }

    // E != C
    private boolean isConstECSatisfied() {
        Integer E = variableAssignments.get("E");
        Integer C = variableAssignments.get("C");
        if (E == null || C == null) {
            return true;
        }
        return E != C;
    }

    // E < D - 1
    private boolean isConstEDSatisfied() {
        Integer D = variableAssignments.get("D");
        Integer E = variableAssignments.get("E");
        if (E == null || D == null) {
            return true;
        }
        return E < D - 1;
    }

    // E != H - 2
    private boolean isConstEHSatisfied() {
        Integer E = variableAssignments.get("E");
        Integer H = variableAssignments.get("H");
        if (E == null || H == null) {
            return true;
        }
        return E != H - 2;
    }

    // G != F
    private boolean isConstGFSatisfied() {
        Integer F = variableAssignments.get("F");
        Integer G = variableAssignments.get("G");
        if (G == null || F == null) {
            return true;
        }
        return G != F;
    }

    // H != F
    private boolean isConstHFSatisfied() {
        Integer F = variableAssignments.get("F");
        Integer H = variableAssignments.get("H");
        if (H == null || F == null) {
            return true;
        }
        return H != F;
    }

    // C != F
    private boolean isConstCFSatisfied() {
        Integer C = variableAssignments.get("C");
        Integer F = variableAssignments.get("F");
        if (C == null || F == null) {
            return true;
        }
        return C != F;
    }

    // D != F - 1
    private boolean isConstDFSatisfied() {
        Integer F = variableAssignments.get("F");
        Integer D = variableAssignments.get("D");
        if (D == null || F == null) {
            return true;
        }
        return D != F - 1;
    }

    // |E-F| is odd
    private boolean isConstEFSatisfied() {
        Integer E = variableAssignments.get("E");
        Integer F = variableAssignments.get("F");
        if (E == null || F == null) {
            return true;
        }
        return Math.abs(E - F) % 2 == 1;
    }


    public void printVariableAssignments() {
        for (String variableLetter : variableAssignments.keySet()) {
            int assignedValue = variableAssignments.get(variableLetter);
            System.out.print(variableLetter + "=" + assignedValue + " ");
        }
    }
}
