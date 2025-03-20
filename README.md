# Graph MST Optimizer


## How to Compile and Run the Program

1. **Clone project**
    ```sh
    git clone https://github.com/korhansevinc/GraphMSTOptimizer
    ```
2. **Navigate to the project directory:**
    ```sh
    cd /path/to/project
    ```
3. **Compile all Java files:**
    ```sh
    javac -d out Main.java utils/*.java data_structures/*.java tests/*.java
    ```
4. **Run the Program:**
    ```sh
   java -cp out Main your_input_txt_file_here.txt desired_output_file_name_here.txt

You can also find some example usage of this program w. test*.txt's and output*.txt's.
   ```


## Known Bugs and Limitations
- The program assumes that the user provides two valid .txt filenames. If the files are incorrect or missing, the program may not work as expected.
- Currently tested only on Java 17; Compatibilty with other versions is unknown.
- No GUI, only CLI based executions.


## File Directory
```The files are in different packages, below is the demonstration of the pushed version of file directory. You can find the Test*.java files as well which does not contribute to the data structures or utilities so you can ignore them.
/project-directory/ 
│── Main.java                                               # Entry point of the program 
├── data_structures/  
│   ├── Graph.java                                          # Graph data structure implementation 
│   ├── DisjointSet.java                                    # Disjoint Set (Union-Find) implementation 
│   ├── MinHeap.java                                        # MinHeap data structure implementation 
│   ├── MSTNode.java                                        # Node representation for MST with parent-child structure 
├── tests/
│   ├── TestGraph.java                                      # Test cases for Graph implementation  
│   ├── TestLatest.java                                     # Test for Prim’s algorithm 
│   ├── TestMSTManager.java                                 # Test for MSTManager utilities 
│   ├── TestMSTNode.java                                    # Test for MSTNode structure 
├── utils/ 
│   ├── DirectiveProcessor.java                             # Handles directive-related utilities 
│   ├── IOUtilities.java                                    # File input/output operations
│   ├── MSTManager.java                                     # Functions to create or update the MST

```
- **Main.java**: The main execution file containing the `main` method.
- **DirectiveProcessor.java**: Provides utilities for handling directives in the program.
- **DisjointSet.java**: Implements the Disjoint Set (Union-Find) data structure.
- **Graph.java**: Implements a Graph data structure and its related operations.
- **IOUtilities.java**: Contains utility methods for file input and output operations.
- **MinHeap.java**: Implements a MinHeap data structure for priority queue operations.
- **MSTManager.java**: Handles the creation and updates of a Minimum Spanning Tree (MST).
- **MSTNode.java**: Represents a node in the MST, supporting a two-way parent-child edge structure.
- **TestGraph.java**: Unit test for the `Graph` class functionality.
- **TestLatest.java**: Unit test for verifying Prim’s algorithm implementation.
- **TestMSTManager.java**: Unit test for `MSTManager` utilities.
- **TestMSTNode.java**: Unit test for `MSTNode` structure and behavior.


## Citation  

If you use this project or find it useful, please cite it as follows:  
```
Korhan Sevinc, GraphMSTOptimizer, GitHub repository, 2024.
Available at: https://github.com/korhansevinc/GraphMSTOptimizer
```

