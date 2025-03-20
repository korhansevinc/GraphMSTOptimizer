package data_structures;
import java.util.* ;

public class MSTManager {
    
    private Map<String, MSTNode> mstNodes;
    private Graph graph;
    private String root;
    

    public MSTManager(Graph graph, String root){
        this.graph = graph;
        this.root = root;
        this.mstNodes = new HashMap<>();
        
    }

    private static class EdgePair {
        String u;
        String v;
        double weight;

        EdgePair(String source, String destination, double weight){
            this.u = source;
            this.v = destination;
            this.weight = weight;
        }
    }


    public void processSelectedEdges(List<EdgePair> selectedEdges) {
        if (!mstNodes.containsKey(root)) {
            mstNodes.put(root, new MSTNode(root));
        }
        
        MSTNode rootNode = mstNodes.get(root);
        rootNode.key = 0;
    
        Iterator<EdgePair> iterator = selectedEdges.iterator();
        while (iterator.hasNext()) {
            EdgePair edge = iterator.next();
            
            if (edge.u.equals(root)) {
                String childID = edge.v;
                MSTNode childNode = mstNodes.computeIfAbsent(childID, id -> new MSTNode(id));
                rootNode.children.add(childNode);  
                childNode.parent = rootNode;     
                childNode.key = edge.weight;
                iterator.remove();            
            } else if (edge.v.equals(root)) {
                String childID = edge.u;
                MSTNode childNode = mstNodes.computeIfAbsent(childID, id -> new MSTNode(id));
                rootNode.children.add(childNode);  
                childNode.parent = rootNode;     
                childNode.key = edge.weight;
                iterator.remove();           
            }
        }
        
        for (MSTNode child : rootNode.children) {
            processChildrenEdges(child, selectedEdges);
        }
    }
    

    private void processChildrenEdges(MSTNode parentNode, List<EdgePair> selectedEdges) {
        Iterator<EdgePair> iterator = selectedEdges.iterator();
        while (iterator.hasNext()) {
            EdgePair edge = iterator.next();
            
            if (edge.u.equals(parentNode.getVertexID())) {
                String childID = edge.v;
                MSTNode childNode = mstNodes.computeIfAbsent(childID, id -> new MSTNode(id));
                parentNode.children.add(childNode);  
                childNode.parent = parentNode;   
                childNode.key = edge.weight;
                iterator.remove();   
            } else if (edge.v.equals(parentNode.getVertexID())) {
                String childID = edge.u;
                MSTNode childNode = mstNodes.computeIfAbsent(childID, id -> new MSTNode(id));
                parentNode.children.add(childNode);  
                childNode.parent = parentNode;       
                childNode.key = edge.weight;
                iterator.remove();              
            }
        }
    
        for (MSTNode child : parentNode.children) {
            processChildrenEdges(child, selectedEdges);
        }
    }


    public void primsAlgorithmToBuildMST() {
        for (String vertex : graph.getAdjList().keySet()) {
            mstNodes.put(vertex, new MSTNode(vertex));
        }

        MSTNode rootNode = mstNodes.get(this.root);
        rootNode.key = 0;
        MinHeap minHeap = new MinHeap();
        minHeap.add(rootNode);

        Set<String> visitedMSTNodes = new HashSet<>();

        while (!minHeap.isEmpty()) {
            MSTNode u = minHeap.poll();
            visitedMSTNodes.add(u.vertexID);

            for (Graph.Edge edge : graph.getAdjList().get(u.vertexID)) {
                if (!visitedMSTNodes.contains(edge.destination)) {
                    MSTNode v = mstNodes.get(edge.destination);
                    if (edge.weight < v.key) {
                        v.key = edge.weight;
                        v.parent = u;
                        u.children.add(v);
                        minHeap.decreaseKey(v, edge.weight);
                    }
                }
            }
        }
        processChildList();
    }
    

    public void processChildList() {
        for (MSTNode node : mstNodes.values()) {
            node.children.clear();
        }
    
        for (MSTNode node : mstNodes.values()) {
            if (node.parent != null) {
                node.parent.children.add(node);
            }
        }
    }


    public void insertEdge(String u , String v, double weight){
        graph.addEdge(u, v, weight);
        DisjointSet ds = new DisjointSet();

        for (String node : graph.getAdjList().keySet()) {
            mstNodes.put(node, new MSTNode(node));
        }

        for (String vertex : graph.getAdjList().keySet()) {
            ds.makeSet(vertex);
        }

        List<EdgePair> edges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String vertID : graph.getAdjList().keySet()) {
            for (Graph.Edge e : graph.getAdjList().get(vertID)) {
                String vertID2 = e.destination;
                if (!visited.contains(vertID2)) {
                    edges.add(new EdgePair(vertID, vertID2, e.weight));
                }
            }
            visited.add(vertID);
        }
        edges.sort(Comparator.comparingDouble(e -> e.weight));

        List<EdgePair> selectedEdges = new ArrayList<>();

        for (EdgePair edge : edges) {
            String rootU = ds.find(edge.u);
            String rootV = ds.find(edge.v);

            if (!rootU.equals(rootV)) {
                ds.union(rootU, rootV);
                selectedEdges.add(edge);                
            }
        }
        processSelectedEdges(selectedEdges);
        processChildList();

    }


    public void decreaseWeight(String u, String v, double amount){
        double currentWeight = graph.getEdgeWeight(u, v);
        if (currentWeight == -1) {
            System.out.println("Edge does not exist in the graph.");
            return ;
        }
        double newWeight = currentWeight - amount;
        graph.updateEdgeWeight(u, v, newWeight);
        
        DisjointSet ds = new DisjointSet();

        for (String node : graph.getAdjList().keySet()) {
            mstNodes.put(node, new MSTNode(node));
        }

        for (String vertex : graph.getAdjList().keySet()) {
            ds.makeSet(vertex);
        }

        List<EdgePair> edges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String vertID1 : graph.getAdjList().keySet()) {
            for (Graph.Edge e : graph.getAdjList().get(vertID1)) {
                String vertID2 = e.destination;
                if (!visited.contains(vertID2)) {
                    edges.add(new EdgePair(vertID1, vertID2, e.weight));
                }
            }
            visited.add(vertID1);
        }
        edges.sort(Comparator.comparingDouble(e -> e.weight));

        List<EdgePair> selectedEdges = new ArrayList<>();

        for (EdgePair edge : edges) {
            String rootU = ds.find(edge.u);
            String rootV = ds.find(edge.v);

            if (!rootU.equals(rootV)) {
                ds.union(rootU, rootV);
                selectedEdges.add(edge);                
            }
        }
        processSelectedEdges(selectedEdges);
        processChildList();

    }


    public String getAndPrintMST(String vertexID) {
        Set<String> visited = new HashSet<>();
        MSTNode startNode = mstNodes.get(vertexID);
        StringBuilder result = new StringBuilder();
    
        if (startNode != null) {
            buildAndPrintMST(startNode, visited, 0, result);
        }
        System.out.println();
        return result.toString();
    }
    

    private void buildAndPrintMST(MSTNode node, Set<String> visited, int depth, StringBuilder result) {
        if (node != null && !visited.contains(node.vertexID)) {
            visited.add(node.vertexID);
    
            for (int i = 0; i < depth; i++) {
                result.append(". ");
                System.out.print(". ");
            }
    
            result.append(node.vertexID).append("\n");
            System.out.println(node.vertexID);
    
            node.children.sort(Comparator.comparing(MSTNode::getVertexID));
            for (MSTNode child : node.children) {
                buildAndPrintMST(child, visited, depth + 1, result);
            }
        }
    }
    

    public void printMSTWithDepth(String vertexID){
        Set<String> visited = new HashSet<>();
        MSTNode startNode = mstNodes.get(vertexID);
        if(startNode != null){
            printMSTEdgesWithDepth(startNode, visited, 0);
        }
        System.out.println();
    }


    public void printMSTEdgesWithDepth(MSTNode node, Set<String> visited, int depth){
        if(node != null && !visited.contains(node.vertexID)){
            visited.add(node.vertexID);

            StringBuilder indent = new StringBuilder();
            for(int i = 0; i< depth; i++){
                indent.append(".");
            }
            System.out.println(indent.toString() + node.vertexID);

            for(MSTNode child : node.children){
                printMSTEdgesWithDepth(child, visited, depth + 1);
            }
        }
    }


    public void printMST(){
        Set<String> visited = new HashSet<>();
        MSTNode rootNode = mstNodes.get(this.root);
        if(rootNode != null){
            System.out.println("Node: " + rootNode.vertexID + ", Parent: None, Key: " + rootNode.key);
        }

        printMSTEdges(mstNodes.get(this.root), visited);
    }

    
    public void printMSTEdges(MSTNode node, Set<String> visited){
        if(node != null && !visited.contains(node.vertexID)){
            visited.add(node.vertexID);

            if(node.parent != null){
                System.out.println("Node: " + node.vertexID + ", Parent: " + (node.parent != null ? node.parent.vertexID : "None") + ", Key: " + node.key);
            }

            for(MSTNode child : node.children){
                printMSTEdges(child, visited);
            }
        }
    }

    
    public void printPreOrder(MSTNode node, Set<String> visited){
        if(node != null && !visited.contains(node.vertexID)){
            visited.add(node.vertexID);
            System.out.println("Node: " + node.vertexID + ", Parent: " + (node.parent != null ? node.parent.vertexID : "None") + ", Key: " + node.key);
            for( MSTNode child: node.children) {
                printPreOrder(child, visited);
            }
        }    
    }


    public void printPath(List<String> path){
        System.out.println("Here is the path : ");
        for(String node : path){
            System.out.print(node + " ");
        }
        System.out.println();
    }

    
    public List<String> findPath(String startID, String endID){
       List<String> path = new ArrayList<>();
       MSTNode startNode = mstNodes.get(startID); 
       MSTNode endNode = mstNodes.get(endID);

        if(startNode == null || endNode == null){
            return path;
        }


        Set<String> visited = new HashSet<>();
        List<String> startToRoot = new ArrayList<>();
        List<String> endToRoot = new ArrayList<>();

        MSTNode currentNode = startNode;
        while(currentNode != null){
            startToRoot.add(currentNode.vertexID);
            visited.add(currentNode.vertexID);
            currentNode = currentNode.parent;
        }

        currentNode = endNode;
        while( currentNode != null){
            endToRoot.add(currentNode.vertexID);
            if(visited.contains(currentNode.vertexID)){
                break;
            }

            currentNode = currentNode.parent;
        }
        int commonIndexOnStartToRoot = startToRoot.indexOf(currentNode.vertexID);
        for(int i=0; i<commonIndexOnStartToRoot;i++){
            path.add(startToRoot.get(i));
        }

        for(int i= endToRoot.size()-1; i>=0; i--){
            path.add(endToRoot.get(i));
        }

       return path;
    }
    

    public void evert(String u){
        MSTNode startNode = mstNodes.get(u);

        if(startNode == null){
            System.out.println("Node not found !");
            return;
        }

        List<MSTNode> pathToRoot = new ArrayList<>();
        findPathToRoot(startNode, pathToRoot);
        
        for (int i = pathToRoot.size() - 1; i > 0; i--) {
            MSTNode currentNode = pathToRoot.get(i);
            MSTNode previousNode = pathToRoot.get(i - 1);
            
            currentNode.parent = previousNode;
            previousNode.children.add(currentNode);
            currentNode.children.remove(previousNode);

            double edgeWeight = graph.getEdgeWeight(previousNode.vertexID, currentNode.vertexID);
            currentNode.key = edgeWeight;
        }

        this.root = u;
        startNode.parent = null;
        startNode.key = 0;

    }
    

    public List<MSTNode> findCycle(MSTNode u, MSTNode v) {
        List<MSTNode> pathU = new ArrayList<>();
        List<MSTNode> pathV = new ArrayList<>();
        Set<String> visited = new HashSet<>();
    
        MSTNode current = u;
        while (current != null) {
            pathU.add(current);
            visited.add(current.vertexID);
            current = current.parent;
        }
    
        current = v;
        while (!visited.contains(current.vertexID)) {
            pathV.add(current);
            current = current.parent;
        }
    
        if (!pathU.isEmpty() && pathU.get(pathU.size() - 1).vertexID.equals(current.vertexID)) {
            pathU.remove(pathU.size() - 1); 
        }
        pathV.add(current); 
        Collections.reverse(pathV);
        pathU.addAll(pathV);
        return pathU;
    }


    public boolean isInMST(String u, String v) {
        return mstNodes.containsKey(u) && mstNodes.containsKey(v) && 
               (mstNodes.get(u).parent == mstNodes.get(v) || mstNodes.get(v).parent == mstNodes.get(u));
    }
    

    public String[] getMaxWeightEdgeInPath(String u, String v) {
        MSTNode nodeU = mstNodes.get(u);
        MSTNode nodeV = mstNodes.get(v);
    
        String maxU = null, maxV = null;
        double maxWeight = -1;
    
        while (nodeU != nodeV) {
            if (nodeU.key > maxWeight) {
                maxWeight = nodeU.key;
                maxU = nodeU.vertexID;
                maxV = nodeU.parent.vertexID;
            }
            if (nodeV.key > maxWeight) {
                maxWeight = nodeV.key;
                maxU = nodeV.vertexID;
                maxV = nodeV.parent.vertexID;
            }
            if (nodeU.key > nodeV.key) nodeU = nodeU.parent;
            else nodeV = nodeV.parent;
        }
        return new String[]{maxU, maxV, String.valueOf(maxWeight)};
    }
    
    
    public void updateMSTEdgeWeight(String u, String v, double newWeight) {
        MSTNode nodeU = mstNodes.get(u);
        MSTNode nodeV = mstNodes.get(v);
        if (nodeU.parent == nodeV) nodeU.key = newWeight;
        else nodeV.key = newWeight;
    }
    

    public void addEdgeToMST(String u, String v, double weight) {
        MSTNode nodeU = mstNodes.get(u);
        MSTNode nodeV = mstNodes.get(v);
    
        if (nodeU.key > nodeV.key) {
            nodeU.parent = nodeV;
            nodeV.children.add(nodeU);
            nodeU.key = weight;
        } else {
            nodeV.parent = nodeU;
            nodeU.children.add(nodeV);
            nodeV.key = weight;
        }
    }
    

    @Deprecated
    public void cut(String u){
        MSTNode node_U = mstNodes.get(u);
        if(node_U == null || node_U.parent == null ){ 
            return ;
        }

        MSTNode oldParent = node_U.parent;

        if(oldParent.children.contains(node_U)){
            oldParent.children.remove(node_U);
        }
        node_U.parent = null;
    }


    @Deprecated
    public void link(String u, String v){
        
        
        MSTNode node_U = mstNodes.get(u);
        MSTNode node_V = mstNodes.get(v);

        if (node_U == null || node_V == null){
            return ;
        }
        
        node_U.parent = node_V;
        node_V.children.add(node_U);
       
    }


    public boolean findPathToRoot(MSTNode currentNode, List<MSTNode> path) {
        path.add(currentNode);
        if (currentNode.parent == null) {
            return true;
        }
        return findPathToRoot(currentNode.parent, path);
    }


    public boolean isInSameComponent(String u, String v) {
        MSTNode nodeU = mstNodes.get(u);
        MSTNode nodeV = mstNodes.get(v);
        
        while (nodeU.parent != null) nodeU = nodeU.parent;
        while (nodeV.parent != null) nodeV = nodeV.parent;
        
        return nodeU == nodeV;
    }


    public void removeEdgeFromMST(String u, String v) {
        MSTNode nodeU = mstNodes.get(u);
        MSTNode nodeV = mstNodes.get(v);
    
        if (nodeV.parent == nodeU) {
            nodeU.children.remove(nodeV);
            nodeV.parent = null;
            nodeV.key = Integer.MAX_VALUE;
        }
        else if (nodeU.parent == nodeV) {
            nodeV.children.remove(nodeU);
            nodeU.parent = null;
            nodeU.key = Integer.MAX_VALUE;
        }
    }


    public String getRoot(){
        return this.root;
    }
    


}
