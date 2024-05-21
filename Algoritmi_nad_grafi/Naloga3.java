import java.io.File;
import java.util.Scanner;

public class Naloga3 {
    public static int n;

    public static void main(String[] args) throws Exception {
        // Scanner input = new Scanner(System.in);
        // input for testing
        Scanner input = new Scanner(new File("input.txt"));
        // getting first line where commands are located
        Scanner first = new Scanner(input.nextLine());
        // String word;
        // getting n of vertices
        n = Integer.parseInt(input.nextLine().trim());
        String con = "";
        // getting connections and putting them in a string
        while (input.hasNext()) {
            con += input.nextLine() + "\n";
        }
        // splitting connections into an array
        String[] allcon = con.split("\n");
        // creating a 2d array for connections

        // true == directed false == undirected
        boolean directed = false;
        String commands = "";
        // sets directed and saves commands in a string
        while (first.hasNext()) {
            String temp = first.next();
            switch (temp) {
                case "directed":
                    directed = true;
                    break;
                case "undirected":
                    directed = false;
                    break;
                default:
                    commands += temp + " ";
                    break;
            }
        }

        int[][] connections = new int[n][n];
        int[][] connectionsIncoming = new int[n][n];
        // parsing connection into array of ints
        for (int i = 0; i != allcon.length; i++) {
            String temp[] = allcon[i].split(" ");
            if (directed && (!temp[1].equals(n + "") && !temp[1].equals("-1") && !temp[0].equals(n + "") && !temp[0].equals("-1"))) {
                connectionsIncoming[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = 1;
            } else {
                if (!temp[1].equals(n + "") && !temp[1].equals("-1") && !temp[0].equals(n + "")
                        && !temp[0].equals("-1")) {
                    connections[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = 1;
                }
            }
            if (!temp[1].equals(n + "") && !temp[1].equals("-1") && !temp[0].equals(n + "") && !temp[0].equals("-1")) {
                connections[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = 1;
            }
        }


        Graph graph;
        // makes a graph based on type
        if (directed) {
            graph = new DirectedGraph(n, connections, connectionsIncoming);
        } else {
            graph = new UndirectedGraph(n, connections);
        }
        String command[] = commands.split(" ");
        switch (command[0]) {
            case "info":
                graph.info();
                break;
            case "walks":
                graph.walks(Integer.parseInt(command[1]));
                break;
            case "dfs":
                graph.dfs();
                break;
            case "bfs":
                graph.bfs();
                break;
            case "sp":
                graph.sp(Integer.parseInt(command[1]));
                break;
        }
        input.close();
    }

    public static void run() {

    }

}

class Graph implements IGraph {
    int vertices;
    int[][] connections;
    int[] visited;

    public Graph(int vertices, int[][] connections) {
        this.vertices = vertices;
        this.connections = connections;

    }

    public void type() {
        System.out.println("Graph");
    }

    public void info() {
    }

    public void walks(int k) {
    }

    public void dfs() {
    }

    public void bfs() {
    }

    public void sp(int k) {
    }
}

class UndirectedGraph extends Graph {
    int visited[];
    int time;
    int end[];
    int endI = 0;
    Queue q = new Queue();
    int count = 0;
    int distances[];

    public UndirectedGraph(int vertices, int[][] connections) {
        super(vertices, connections);
        visited = new int[vertices];
        end = new int[vertices];
        distances = new int[vertices];
    }

    public void type() {
        System.out.println("Undirected Graph");
    }

    public void info() {
        int[] visited = new int[vertices];
        int con = 0;
        for (int x = 0; x != connections.length; x++) {
            for (int y = 0; y != connections.length; y++) {
                visited[x] += connections[x][y];
                con += connections[x][y];
            }
        }

        System.out.println(vertices + " " + con / 2);
        for (int x = 0; x != visited.length; x++) {
            System.out.print(x + " " + visited[x] + "\n");
        }
    }

    public void walks(int k) {
        int[][] incidenceMatrix = connections;
        int[][] multiplyMatrix = new int[vertices][vertices];
        for (int i = 0; i != incidenceMatrix.length; i++) {
            multiplyMatrix[i] = incidenceMatrix[i].clone();
        }
        for (int i = 0; i < k - 1; i++) {
            multiplyMatrix = multiply(multiplyMatrix, incidenceMatrix);
        }
        printMatrix(multiplyMatrix);

    }

    public int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i != matrix1.length; i++) {
            for (int j = 0; j != matrix2[0].length; j++) {
                for (int k = 0; k != matrix1[0].length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs() {
        time = 0;

        try {
            for (int i = 0; i != vertices; i++) {
                if (visited[i] == 0) {
                    DFS(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        for (int i = 0; i != end.length; i++) {
            System.out.print(end[i] + " ");
        }
    }

    public void DFS(int k) throws Exception {
        // stack = new Stack();
        Stack stack = new Stack();
        time += 1;
        visited[k] = time;
        System.out.print(k + " ");
        int neigh = 0;
        for (int i = connections[k].length - 1; i != -1; i--) {
            if (connections[k][i] == 1) {
                stack.add(i);
            }
        }
        while (!stack.isEmpty()) {
            neigh = stack.remove();
            if (visited[neigh] == 0) {
                DFS(neigh);
            }
        }
        end[endI] = k;
        endI++;
    }

    public void bfs() {
        time = 0;
        time = 0;

        try {
            for (int i = 0; i != vertices; i++) {
                if (visited[i] == 0) {
                    BFS(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void BFS(int k) {

        time++;
        visited[k] = time;
        System.out.print(k + " ");
        for (int i = 0; i != connections.length; i++) {
            if (connections[k][i] == 1) {
                if (visited[i] == 0) {
                    q.addElement(i);
                }
            }
        }
        while (!q.isEmpty()) {
            int neigh = q.dequeue();
            if (visited[neigh] == 0) {
                BFS(neigh);
            }
        }
    }

    // for (int i = 0; i < V; i++) {
    // shortestPaths[i] = -1;
    // }
    // find shortest path and count the steps

    public void sp(int startNode) {
        for (int i = 0; i < vertices; i++) {
            if (distances[i] == 0 && i != startNode) {
                distances[i] = -1;
            }
        }
        distances[startNode] = 0;
        recursiveBFS(startNode, 0);
    
        // Set distances to -1 for nodes that remain unreachable
    
        // Print the results
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " " + distances[i]);
        }
    }
    
    private void recursiveBFS(int currentNode, int distance) {
        visited[currentNode] = 1;
    
        for (int i = 0; i < vertices; i++) {
            if (connections[currentNode][i] == 1) {
                // For directed graph, only update the distance if the current path is shorter
                if (distances[i] == -1 || distance + 1 < distances[i]) {
                    distances[i] = distance + 1;
                }
                if (visited[i] != 1) {
                    recursiveBFS(i, distances[i]);
                }
            }
        }
    }
    
    

}

class DirectedGraph extends Graph {
    int[][] connectionsIncoming;
    int visited[];
    int time;
    int end[];
    int[] distances;
    int endI = 0;
    Queue q = new Queue();

    public DirectedGraph(int vertices, int[][] connections, int[][] connectionsIncoming) {
        super(vertices, connections);
        this.connectionsIncoming = connectionsIncoming;
        visited = new int[vertices];
        end = new int[vertices];
        distances = new int[vertices];
    }

    public void type() {
        System.out.println("directed Graph");
    }

    public void info() {
        // visited is matrix of size number of vertices and 2 for inputs and outputs
        int[][] visited = new int[vertices][2];
        int con = 0;
        // looping over all the connections
        for (int x = 0; x != connections.length; x++) {
            for (int y = 0; y != connections.length; y++) {

                visited[x][0] += connections[x][y];
                con += connections[x][y];
                visited[x][1] += connectionsIncoming[x][y];

            }
        }

        System.out.println(vertices + " " + con);
        for (int x = 0; x != visited.length; x++) {
            System.out.print(x + " " + visited[x][0] + " " + visited[x][1] + "\n");
        }
    }

    public void walks(int k) {
        int[][] multiplyMatrix = new int[vertices][vertices];
        for (int i = 0; i != connections.length; i++) {
            multiplyMatrix[i] = connections[i].clone();
        }
        for (int i = 0; i < k - 1; i++) {
            multiplyMatrix = multiply(multiplyMatrix, connections);
        }
        printMatrix(multiplyMatrix);
    }

    // function that multiplies 2 matrixes
    public int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i != matrix1.length; i++) {
            for (int j = 0; j != matrix2[0].length; j++) {
                for (int k = 0; k != matrix1[0].length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    // function test prints a matrix
    public void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs() {
        time = 0;

        try {
            for (int i = 0; i != vertices; i++) {
                if (visited[i] == 0) {
                    DFS(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        for (int i = 0; i != end.length; i++) {
            System.out.print(end[i] + " ");
        }
    }

    public void DFS(int k) throws Exception {
        // stack = new Stack();
        Stack stack = new Stack();
        time += 1;
        visited[k] = time;
        System.out.print(k + " ");
        int neigh = 0;
        for (int i = connections[k].length - 1; i != -1; i--) {
            if (connections[k][i] == 1) {
                stack.add(i);
            }
        }
        while (!stack.isEmpty()) {
            neigh = stack.remove();
            if (visited[neigh] == 0) {
                DFS(neigh);
            }
        }
        end[endI] = k;
        endI++;
    }

    public void bfs() {
        time = 0;
        time = 0;

        try {
            for (int i = 0; i != vertices; i++) {
                if (visited[i] == 0) {
                    BFS(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void BFS(int k) {

        time++;
        visited[k] = time;
        System.out.print(k + " ");
        for (int i = 0; i != connections.length; i++) {
            if (connections[k][i] == 1) {
                if (visited[i] == 0) {
                    q.addElement(i);
                }
            }
        }
        while (!q.isEmpty()) {
            int neigh = q.dequeue();
            if (visited[neigh] == 0) {
                BFS(neigh);
            }
        }
    }

    public void sp(int startNode) {
        for (int i = 0; i < vertices; i++) {
            if (distances[i] == 0 && i != startNode) {
                distances[i] = -1;
            }
        }
        distances[startNode] = 0;
        recursiveBFS(startNode, 0);
    
        // Set distances to -1 for nodes that remain unreachable
    
        // Print the results
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " " + distances[i]);
        }
    }
    
    private void recursiveBFS(int currentNode, int distance) {
        visited[currentNode] = 1;
    
        for (int i = 0; i < vertices; i++) {
            // Check for outgoing edges in a directed graph
            if (i < connections.length && connections[currentNode][i] == 1 && (distance < distances[i] || distances[i] == -1)) {
                distances[i] = distance + 1;
                recursiveBFS(i, distance + 1);
            }
        }
    }
    

}

interface IGraph {

    public void info();

    public void walks(int k);
}

class Stack {
    int[] stack = new int[64];
    int end = 0;

    public void resize() {
        int temp[] = new int[stack.length * 2];
        for (int i = 0; i != stack.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp.clone();
    }

    public boolean isEmpty() {
        return (end == 0);
    }

    public void add(int k) {
        if (end == stack.length - 1) {
            resize();
        }

        stack[end] = k;
        end++;
    }

    public int remove() throws Exception {
        if (stack[end - 1] == -1) {
            throw new Exception("stack is empty");
        }
        int temp = stack[end - 1];
        stack[end - 1] = -1;
        end--;
        return temp;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i != end; i++) {
            str += stack[i] + " ";
        }
        return str;
    }
}

class Queue {
    int[] queue = new int[64];
    int start = 0;
    int end = 0;

    public void resize() {
        int newSize = Math.max(2 * (end - start), 64); // Choose a reasonable size
        int[] temp = new int[newSize];

        for (int i = start, j = 0; i < end; i++, j++) {
            temp[j] = queue[i];
        }

        queue = temp;
        start = 0;
        end = end - start;
    }

    public boolean isEmpty() {
        return start == end;
    }

    public void addElement(int k) {
        if (end == queue.length) {
            resize();
        }
        queue[end++] = k;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return queue[start++];
    }

    public String toString() {
        String str = "";
        for (int i = 0; i != end; i++) {
            str += queue[i] + " ";
        }
        return str;
    }
}
