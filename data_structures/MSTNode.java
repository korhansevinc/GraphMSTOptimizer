package data_structures;
import java.util.* ;

public class MSTNode {
    
    String vertexID;
    MSTNode parent;
    List<MSTNode> children;
    double key;

    public MSTNode(String vertex){
        this.vertexID = vertex;
        this.parent = null;
        this.children = new ArrayList<>();
        this.key = Double.MAX_VALUE;
    }

    public String getVertexID(){
        return this.vertexID;
    }

    public MSTNode getParent(){
        return this.parent;
    }

    public List<MSTNode> getChildren(){
        return this.children;
    }

    public double getKey(){
        return this.key;
    }
}
