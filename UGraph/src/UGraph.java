import java.util.ArrayList;

public class UGraph {
    int total_vertices = 7;
    ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();

    public UGraph(int val) {
        total_vertices = val;

        for (int i = 0; i <= total_vertices; i++) {
            arr.add(new ArrayList<Integer>());
            for (int j = 0; j <= total_vertices; j++) {
                arr.get(i).add(0);
            }
        }
    }

    public static void main(String[] args) {
        UGraph graph = new UGraph(7);
        //   1
        //  / \
        //  2  3
        // /\  /\
        // 4 5 6 7
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);

        System.out.println("Printing matrix:");
        graph.printGraph();
        System.out.println("Checking connection from node 3 to 7: " + graph.checkEdge(3, 7));
        System.out.println("Removing connection from node 3 to 7");
        graph.removeEdge(3, 7);
        System.out.println("Checking connection from node 3 to 7: " + graph.checkEdge(3, 7));
        System.out.println("Printing matrix:");
        graph.printGraph();

        boolean[] visited = new boolean[graph.total_vertices + 1];
        System.out.println("DFS: ");
        graph.dfs(1, visited);
    }

    private void addEdge(int i, int j) {
        arr.get(i).set(j, 1);
        arr.get(j).set(i, 1);
    }

    private void removeEdge(int i, int j) {
        arr.get(i).set(j, 0);
        arr.get(j).set(i, 0);
    }

    private boolean checkEdge(int i, int j) {
        return arr.get(i).get(j) == 1;
    }

    private void printGraph() {
        for (int i = 1; i <= total_vertices; i++) {
            for (int j = 1; j <= total_vertices; j++) {
                System.out.print(arr.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private boolean allEven() {
        return true;
    }

    private void dfs(int source, boolean[] visited) {
        // As its a void method so no base return case is needed
        visited[source] = true;
        System.out.print(source + " ");

        for (int i = 1; i <= total_vertices; i++) {
            if (arr.get(source).get(i) == 1 && !visited[i]) {
                dfs(i, visited);
            }
        }
    }
}
