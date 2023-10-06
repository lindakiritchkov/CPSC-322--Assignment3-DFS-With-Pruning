import java.util.*;

public class Solver {

    public ArrayList<Node> solutions;
    public int countOfBranchesPruned;
    public Node lastBranch;

    public Solver() {
        this.solutions = new ArrayList<>();
        this.countOfBranchesPruned = 0;
        this.lastBranch = new Node();

        Node startNode = new Node();

        System.out.println("Search tree: ");

        search(startNode);

        System.out.println(" ");
        System.out.println("Number of branches pruned: " + countOfBranchesPruned);
        System.out.println(" ");
        System.out.println("Number of solutions found: " + solutions.size());
        for (Node sol : solutions) {
            sol.printVariableAssignments();
            System.out.println(" ");
        }
    }

    public void search(Node node) {

        // check if node violates any constraints
        if (!node.areConstraintsSatisfied()) {
            printBranch(node, "failure");
            countOfBranchesPruned++;
        }

        // check if node is a leaf
        else if (node.isLeaf()) {
            printBranch(node, "success");
            solutions.add(node);
        }

        // continue searching
        else {
            ArrayList<Node> nextNodes = node.generateNextNodes();
            for (Node next : nextNodes) {
                search(next);
            }
        }
    }

    public void printBranch(Node currNode, String outcome) {

        // Go through each variable/value assignment for this node.
        // If the variable/value assignment is the same as the last finished branch, print placeholder spaces.
        // If the variable/value assignment is not the same as the finished branch, print the letter and value.

        String startLetter = "A";

        for(String currLetter : currNode.variableAssignments.keySet()) {
            Integer currValue = currNode.variableAssignments.get(currLetter);

            if (!lastBranch.variableAssignments.isEmpty()) {
                if(lastBranch.variableAssignments.containsKey(currLetter)) {
                    Integer lastValue = lastBranch.variableAssignments.get(currLetter);

                    if (currValue.equals(lastValue)) {
                        System.out.print("    ");
                    } else {
                        startLetter = currLetter;
                        break;
                    }
                }
            }
        }

        SortedMap<String, Integer> toBePrinted = currNode.variableAssignments.tailMap(startLetter);

        for (String letter : toBePrinted.keySet()) {
            int value = toBePrinted.get(letter);
            System.out.print(letter + "=" + value + " ");
        }

        System.out.println("  " + outcome);

        lastBranch = currNode;
    }
}
