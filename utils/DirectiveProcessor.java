package utils;
import java.util.*;
import data_structures.*;

public class DirectiveProcessor {
    private Graph graph;
    private List<String> graphNodes;
    private List<String> laterOnDirectives;
    private MSTManager mstManager;
    private List<String> resultStrings;


    public DirectiveProcessor(Graph graph){
        this.graph = graph;
        this.graphNodes = new ArrayList<>();
        this.laterOnDirectives = new ArrayList<>();
        this.mstManager = null;
        this.resultStrings = new ArrayList<>();
    }

    
    public void runLaterOnDirectives(){
        System.out.println("Later on Directives are going to be handled.");
        System.out.println("\nHere are the directives that has been given: ");
        for(int i = 0 ; i< this.laterOnDirectives.size(); i++){
            System.out.println("directive : [" + this.laterOnDirectives.get(i) + "]");
        }
        System.out.println();

        for(int i=0; i< this.laterOnDirectives.size(); i++){
            String currentDirective = laterOnDirectives.get(i);

            StringBuilder currentAnswer = new StringBuilder();
            currentAnswer.append("Directive-----------------> " + currentDirective + "\n");
            if(currentDirective.contains("print-mst")){

                String vertexID = currentDirective.split(" ")[1];
                if(mstManager.getRoot().equals(vertexID)){
                    currentAnswer.append(this.mstManager.getAndPrintMST(vertexID));
                }
                else{
                    System.out.println("The given node is not the root node. Using evert() function to make it root.");
                    mstManager.evert(vertexID);
                    currentAnswer.append(this.mstManager.getAndPrintMST(vertexID));
                }
                //currentAnswer.append("\n");
            }
            else if(currentDirective.contains("path")){

                String[] tempArgs = currentDirective.split(" ");
                String vertexID1 = tempArgs[1];
                String vertexID2 = tempArgs[2];
                System.out.println("Vertex 1 : " + vertexID1);
                System.out.println("Vertex 2 : " + vertexID2);

                List<String> pathFromV1toV2 = mstManager.findPath(vertexID1, vertexID2);
                for(int j = 0; j< pathFromV1toV2.size(); j++){
                    if(j != pathFromV1toV2.size()-1){
                        currentAnswer.append(pathFromV1toV2.get(j) + ", ");
                    }else{
                        currentAnswer.append(pathFromV1toV2.get(j));                        
                    }
                    
                }
                currentAnswer.append("\n");
            }
            else if(currentDirective.contains("insert-edge")){

                String[] tempArgs = currentDirective.split(" ");
                String vertexID1 = tempArgs[1];
                String vertexID2 = tempArgs[2];
                double weight = Double.parseDouble(tempArgs[3]);

                if( graph.getEdgeWeight(vertexID1, vertexID2) != -1){
                    currentAnswer.append("Invalid Operation\n");
                }else{
                    mstManager.insertEdge(vertexID1, vertexID2, weight);
                }
            }
            else if(currentDirective.contains("decrease-weight")){

                String[] tempArgs = currentDirective.split(" ");
                String vertexID1 = tempArgs[1];
                String vertexID2 = tempArgs[2];
                double amount = Double.parseDouble(tempArgs[3]);

                if(graph.getEdgeWeight(vertexID1, vertexID2) == -1 ){
                    currentAnswer.append("Invalid Operation\n");
                }else{
                    mstManager.decreaseWeight(vertexID1, vertexID2, amount);
                }
            }
            else if(currentDirective.contains("quit")){

                System.out.println("Quit directive has been given. The program has been finalized.");
                return;
            }else{

                System.out.println("WARNING : An undefined directive has been given : ");
                System.out.println(currentDirective);
                System.out.println("Skipping the current directive.");
            }

            this.resultStrings.add(currentAnswer.toString());
        }

    }


    public void processDirectives(List<String> directives){
        int idx=0;
        int nodeCount = Integer.parseInt(directives.get(idx++));
        for(int i = 0 ; i< nodeCount; i++){
            this.graphNodes.add(directives.get(idx++));
        }
        
        int edgeCount = Integer.parseInt(directives.get(idx++));
        for(int i = 0; i< edgeCount; i++){
            String[] edgeInfo = directives.get(idx++).split(" ");
            String node1 = edgeInfo[0];
            String node2 = edgeInfo[1];
            double weight = Double.parseDouble(edgeInfo[2]);
            graph.addEdge(node1, node2, weight);
        }
        
        for(int i=idx; i<directives.size();i++){
            String directive = directives.get(i);
            laterOnDirectives.add(directive);
        }
    }

    public Graph getGraph(){
        return this.graph;
    }

    public List<String> getGraphNodes(){
        return this.graphNodes;
    }

    public List<String> getLaterOnDirectives(){
        return this.laterOnDirectives;
    }
    
    public MSTManager getMSTManager(){
        return this.mstManager;
    }
    
    public void setMSTManager(MSTManager mstManager){
        this.mstManager = mstManager;
    }

    public List<String> getResultStrings(){
        return this.resultStrings;
    }

}
