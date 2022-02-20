import java.util.Scanner;

class Node {
    int value;
    Node right;
    Node left;

    Node(int val) {
        value = val;
    }
}

public class BTree {
    Node root;
    static int start;

    BTree() {
        root = null;
        start = 0;
    }

    public static void main(String args[]) {
        BTree tree = new BTree();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the bracket representation of the binary tree");
            System.out.println("Enter -1 to exit");
            String input = sc.nextLine();

            if (input.equals("-1")) {
                break;
            }

            tree.callCreateTree(input);

            while (true) {
                System.out.println("Choose 1, 2 or 3");
                System.out.println("1 - print every path from root to leaf node");
                System.out.println("2 - mirror tree and print node preorder");
                System.out.println("3 - remove all nodes and start again");
                String input2 = sc.nextLine();

                if (input2.equals("1")) {
                    System.out.println("Paths from root");
                    tree.printPaths();
                } else if (input2.equals("2")) {
                    System.out.println("Mirror tree");
                    tree.mirror();
                    printPreOrder(tree.root);
                    System.out.println();
                } else if (input2.equals("3")) {
                    tree.deleteAllNodes();
                    break;
                }
            }
        }

        String input2 = "4(2(3)(1))(6(5))";
        // 4
        // / \
        // 2 6
        // / \ /
        // 3 1 5
    }

    private void callCreateTree(String input) {
        root = createTree(input);
    }

    private void deleteAllNodes() {
        root = null;
        start = 0;
    }

    private void mirror() {
        root = mirrorRec(root);
    }

    private Node mirrorRec(Node node) {
        if (node == null)
            return node;

        /* do the subtrees */
        Node left = mirrorRec(node.left);
        Node right = mirrorRec(node.right);

        /* swap the left and right pointers */
        node.left = right;
        node.right = left;

        return node;
    }

    private static void printPreOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    private static Node createTree(String s) {
        if (s.length() == 0 || s == null) {
            return null;
        }

        if (start >= s.length())
            return null;

        // Boolean variable to check
        // for negative numbers
        boolean neg = false;

        // Condition to check for negative number
        if (s.charAt(start) == '-') {
            neg = true;
            start++;
        }

        // This loop basically construct the
        // number from the continuous digits
        int num = 0;
        while (start < s.length() && Character.isDigit(s.charAt(start))) {
            int digit = Character.getNumericValue(s.charAt(start));
            num = num * 10 + digit;
            start++;
        }

        // If string contains - minus sign
        // then append - to the number;
        if (neg)
            num = -num;

        // Create the node object i.e. root of
        // the tree with value = num;
        Node node = new Node(num);

        if (start >= s.length()) {
            return node;
        }

        // Check for open bracket and add the
        // value to the left subtree recursively
        if (start < s.length() && s.charAt(start) == '(') {
            start++;
            node.left = createTree(s);
        }

        if (start < s.length() && s.charAt(start) == ')') {
            start++;
            return node;
        }

        // Check for open bracket and add the value
        // to the right subtree recursively
        if (start < s.length() && s.charAt(start) == '(') {
            start++;
            node.right = createTree(s);
        }

        if (start < s.length() && s.charAt(start) == ')') {
            start++;
            return node;
        }
        return node;
    }

    void printPaths() {
        int path[] = new int[1000];
        printPathsRecur(root, path, 0);
    }

    void printPathsRecur(Node node, int path[], int pathLen) {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.value;
        pathLen++;

        /* it's a leaf, so print the path that led to here */
        if (node.left == null && node.right == null) {
            printArray(path, pathLen);

        } else {
            /* otherwise try both subtrees */
            printPathsRecur(node.left, path, pathLen);
            printPathsRecur(node.right, path, pathLen);
        }
    }

    /* Utility that prints out an array on a line */
    void printArray(int ints[], int len) {
        int i;

        for (i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }

        System.out.println("");
    }

}
