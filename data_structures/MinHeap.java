package data_structures;
import java.util.*;

class MinHeap {
    private List<MSTNode> heap;
    private Map<String, Integer> indexMap;

    public MinHeap() {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
    }

    public void add(MSTNode node) {
        heap.add(node);
        int index = heap.size() - 1;
        indexMap.put(node.vertexID, index);
        siftUp(index);
    }

    public MSTNode poll() {
        if (heap.size() == 0) {
            return null;
        }
        MSTNode root = heap.get(0);
        MSTNode lastElement = heap.remove(heap.size() - 1);
        if (heap.size() > 0) {
            heap.set(0, lastElement);
            indexMap.put(lastElement.vertexID, 0);
            siftDown(0);
        }
        indexMap.remove(root.vertexID);
        return root;
    }

    public void decreaseKey(MSTNode node, double newKey) {
        Integer index = indexMap.get(node.vertexID);
        if (index != null) {
            node.key = newKey;
            siftUp(index);
        } else {
            add(node);
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).key >= heap.get(parentIndex).key) {
                break;
            }
            Collections.swap(heap, index, parentIndex);
            indexMap.put(heap.get(index).vertexID, index);
            indexMap.put(heap.get(parentIndex).vertexID, parentIndex);
            index = parentIndex;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < size && heap.get(leftChild).key < heap.get(smallest).key) {
                smallest = leftChild;
            }
            if (rightChild < size && heap.get(rightChild).key < heap.get(smallest).key) {
                smallest = rightChild;
            }
            if (smallest == index) {
                break;
            }

            Collections.swap(heap, index, smallest);
            indexMap.put(heap.get(index).vertexID, index);
            indexMap.put(heap.get(smallest).vertexID, smallest);
            index = smallest;
        }
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }
    
    public int size() {
        return heap.size();
    }
}
