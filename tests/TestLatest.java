package tests;


import data_structures.Graph;
import data_structures.MSTManager;


public class TestLatest {

    public static void main(String[] args) {
        

        Graph graph = new Graph();

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "E", 10);
        graph.addEdge("D", "E", 2);

        MSTManager mstManager = new MSTManager(graph, "A");
        mstManager.primsAlgorithmToBuildMST();
        mstManager.printMST();

    }
}
