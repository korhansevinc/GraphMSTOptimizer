package tests;
import data_structures.*;

public class TestMSTNode {
    public static void main(String[] args) {
        MSTNode mstnode = new MSTNode("Some Vertex");
        System.out.println(mstnode.getVertexID());
        System.out.println(mstnode.getParent());
        System.out.println(mstnode.getChildren());
        System.out.println(mstnode.getKey());
        // This must print Some Vertex, null, [], and MAX_INT value.
    }
}
