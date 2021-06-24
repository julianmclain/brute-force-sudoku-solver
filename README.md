# Brute Force Sudoku Solver

This is a Scala re-write of the generative recursing coding challenge in UBC's "How to Code: Complex Data" course.

### Process

The solve function uses the following algorithm:
1. Identify the first empty square on the board
2. Generate 9 possible next boards by filling the empty square with 1 - 9
3. Prune invalid boards
4. Check if any remaining boards are complete
    - yes? We found a solution!
    - no? Keep looking....
5. Eventually we'll find a solution or the list of next possible boards will be empty, in which case
we return None :(

3 main ingredients in the solver:
- Generative recursion - for each board, the algorithm generates 0 - 9 child boards
- Arbitrary-arity tree - given a board as the root node, the depth and width of the tree is unknown at the start.
- Backtracking search - the algorithm explores the tree until it finds a full board (this is a solution since invalid
boards are immediately pruned) or there are no unvisited boards left.

### Board representation

The Sudoku board is represented as an 81 element Vector. 0 represents a blank square.

For example:
```scala
val BOARD_FOUR_SOLUTION = Vector(2, 7, 4, 8, 9, 1, 3, 6, 5,
                                 1, 3, 8, 5, 2, 6, 4, 9, 7,
                                 6, 5, 9, 4, 7, 3, 2, 8, 1,
                                 3, 2, 1, 9, 6, 4, 7, 5, 8,
                                 9, 8, 5, 1, 3, 7, 6, 4, 2,
                                 7, 4, 6, 2, 8, 5, 9, 1, 3,
                                 4, 6, 2, 7, 5, 8, 1, 3, 9,
                                 5, 9, 3, 6, 1, 2, 8, 7, 4,
                                 8, 1, 7, 3, 4, 9, 5, 2, 6)
```

### TODO
- Implement Board class
- Probably more efficient if solver immediately checks if board is valid
