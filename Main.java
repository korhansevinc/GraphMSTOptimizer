import java.util.*;

import data_structures.Graph;
import data_structures.MSTManager;
import utils.DirectiveProcessor;
import utils.IOUtilities;

public class Main{

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("The usage should be like below : ");
            System.out.println("java -cp out Main <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        IOUtilities ioUtils = new IOUtilities(inputFile, outputFile);
        List<String> inputLines = ioUtils.readInput();
        List<String> outputLines = null;

        System.out.println("Here is the input lines : ");
        for(int i=0; i< inputLines.size(); i++){
            System.out.println(inputLines.get(i));
        }

        Graph graph = new Graph();
        String sourceNodeVertexID = inputLines.get(1);
        System.out.println("Source Node Vertex ID : " + sourceNodeVertexID);
        MSTManager mstManager = new MSTManager(graph, sourceNodeVertexID);
        DirectiveProcessor dp = new DirectiveProcessor(graph);
        dp.processDirectives(inputLines);  
        dp.setMSTManager(mstManager);
        System.out.println("\nHere is the initial graph.");
        graph.printGraph(); 
        System.out.println("Building the initial minimum spanning tree from our graph assuming source node is the first(root) node.");
        mstManager.primsAlgorithmToBuildMST();
        System.out.println("Here is the initial MST.");
        mstManager.printMST();
        System.out.println("--------------------------------------------------------------");
        dp.runLaterOnDirectives();
        
        System.out.println("Saving the results in : '" + outputFile + "' " );
        System.out.println("Control of the result Strings variable : ");
        outputLines = dp.getResultStrings();
        
        for(int i = 0; i< outputLines.size(); i++){
            System.out.println( (i+1) + " 'th element : \n");
            System.out.println(outputLines.get(i));
        }
        
        ioUtils.writeOutput(outputLines);

        System.out.println("Program finished.");


    }


}
