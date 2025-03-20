package tests;

import java.util.List;
import data_structures.*;
public class TestMSTManager {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("a", "b", 4);
        graph.addEdge("a", "c", 8);
        graph.addEdge("b", "c", 9);
        graph.addEdge("b", "d", 8.5);
        graph.addEdge("b", "e", 10);
        graph.addEdge("c", "d", 2);
        graph.addEdge("c", "f", 1);
        graph.addEdge("d", "e", 7);
        graph.addEdge("d", "f", 9);
        graph.addEdge("e", "f", 5);
        graph.addEdge("e", "g", 6);
        graph.addEdge("f", "g", 2.5);


        System.out.println("Printing the Graph : ");
        graph.printGraph();
        
        System.out.println("Now building the MST. Using MST Manager, with Prim's Algorithm.");
        
        MSTManager mstManager = new MSTManager(graph, "a");
        mstManager.primsAlgorithmToBuildMST();
        System.out.println("Here is our initial MST : ");
        System.out.println("\n MST (Pre-Order Traversal): ");
        mstManager.printMST();

        
        // Testing the findPath functionality.
        //runFindPathTests(mstManager);

        // Testing the cut, link and evert functionality.
        //runEvertTests(mstManager);

        // Testing the insertEdge functionality.
        //runInsertEdgeTests(mstManager, graph);

        // Testing the decreaseWeight functionality.
        //runDecreaseWeightTests(mstManager);

    }

    public static void runDecreaseWeightTests(MSTManager mstManager){
        System.out.println("\nDecreasing the weight of edge (1,4) by 3 units...");
        mstManager.decreaseWeight("1", "4", 3); // 5 - 3 = 2 
        System.out.println("Here is the updated MST: ");
        mstManager.printMST();
        System.out.println("\nDecreasing the weight of edge (3,1) by 7 units...");
        mstManager.decreaseWeight("3", "1", 7); // 8 - 7 = 1
        System.out.println("Here is the updated MST: ");
        mstManager.printMST();
        System.out.println("Decrease Weight runs successfully.");
    }

    public static void runInsertEdgeTests(MSTManager mstManager, Graph graph){
        System.out.println("\nTesting the Insert Edge functionality.");
        System.out.println("\nAdding a new edge (a - f) with weight 12...");
        mstManager.insertEdge("a", "f", 12); 
        System.out.println("Here is the updated graph : ");
        graph.printGraph();
        System.out.println("\nUpdated MST:");
        System.out.println("\nMST (Pre-Order Traversal): ");
        mstManager.printMST();


        System.out.println("\nAdding a new edge (b - g) with weight 1.5...");
        mstManager.insertEdge("b", "g", 1.5); 
        System.out.println("Here is the updated graph : ");
        graph.printGraph();
        System.out.println("\nUpdated MST:");
        System.out.println("\nMST (Pre-Order Traversal): ");
        mstManager.printMST();
        System.out.println("Insert Edge runs successfully.");

    }

    public static void runEvertTests(MSTManager mstManager){
        System.out.println("Test of Evert Functionality.");
        System.out.println("\n After applying evert on node 2 (making it the new root) : ");
        mstManager.evert("2");
        mstManager.printMST();

        System.out.println("\n After applying evert on node 4 (making it the new root) : ");
        mstManager.evert("4");
        mstManager.printMST();

        System.out.println("\n After applying evert on node 1 (making it the new root) : ");
        mstManager.evert("1");
        mstManager.printMST();

        System.out.println("Evert runs successfully.");
    }

    public static void runFindPathTests(MSTManager mstManager){
        // Find Path Function Tests : 
        System.out.println("Test of Find Path Functionality.");
        System.out.println("Test 1 : ");
        List<String> path1 = mstManager.findPath("0", "4");
        mstManager.printPath(path1);
        System.out.println("Test 2 : ");
        List<String> path2 = mstManager.findPath("3", "4");
        mstManager.printPath(path2);
        System.out.println("Test 3 : ");
        List<String> path3 = mstManager.findPath("3", "2");
        mstManager.printPath(path3);
        System.out.println("Test 4 : ");
        List<String> path4 = mstManager.findPath("4", "1");
        mstManager.printPath(path4);
        System.out.println("Test 5 : ");
        List<String> path5 = mstManager.findPath("4", "2");
        mstManager.printPath(path5);
        System.out.println("Test 6 : ");
        List<String> path6 = mstManager.findPath("4", "3");
        mstManager.printPath(path6);
        System.out.println("Test 7 : ");
        List<String> path7 = mstManager.findPath("1", "0");
        mstManager.printPath(path7);
        System.out.println("Test 8 : ");
        List<String> path8 = mstManager.findPath("2", "2");
        mstManager.printPath(path8);
        System.out.println("Find Path algorithm runs successfully.");
    }
}
