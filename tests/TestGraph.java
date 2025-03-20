package tests;
import data_structures.*;

public class TestGraph {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "C", 6);
        graph.addEdge("A", "D", 5);
        graph.addEdge("B", "D", 15);
        graph.addEdge("C", "D", 4);
        
        System.out.println("Testing the Graph : ");
        graph.printGraph();

    }
}
