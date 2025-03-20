package data_structures;
import java.util.* ;
public class Graph {

    /* Adjacency List */ 
    private Map<String, List<Edge>> adjList;

    static class Edge{
        String destination;
        double weight;

        Edge(String dest, double weight){
            this.destination = dest;
            this.weight = weight;
        }
    }


    public Graph(){
        this.adjList = new HashMap<>();
    }

    public void addEdge(String u, String v, double weight){

        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());

        adjList.get(u).add(new Edge(v, weight));
        adjList.get(v).add(new Edge(u, weight));
    }

    public void printGraph(){
        for(String node : adjList.keySet()) {
            System.out.print(node + ": ");
            for (Edge e: adjList.get(node)){
                System.out.print("( adj node : "+ e.destination + ", weight :  " + e.weight + ") ");
            }
            System.out.println();
        }
    }
    
    public Map<String, List<Edge>> getAdjList(){
        return adjList;
    }

    public double getEdgeWeight(String u, String v){
        List<Edge> edges = adjList.get(u);
        if(edges != null){
            for(Edge e : edges){
                if (e.destination.equals(v)){
                    return e.weight;
                }
            }
        }
        return -1;
    }

    public void removeEdge(String u, String v) {
        List<Edge> edgesU = adjList.get(u);
        List<Edge> edgesV = adjList.get(v);

        if (edgesU != null) {
            edgesU.removeIf(edge -> edge.destination.equals(v));
        }
        if (edgesV != null) {
            edgesV.removeIf(edge -> edge.destination.equals(u));
        }
    }
    

    public void updateEdgeWeight(String u, String v, double newWeight) {
        List<Edge> edgesU = adjList.get(u);
        List<Edge> edgesV = adjList.get(v);
    
        if (edgesU != null) {
            for (Edge e : edgesU) {
                if (e.destination.equals(v)) {
                    e.weight = newWeight;
                    break;
                }
            }
        }
    
        if (edgesV != null) {
            for (Edge e : edgesV) {
                if (e.destination.equals(u)) {
                    e.weight = newWeight;
                    break;
                }
            }
        }
    }
    


}