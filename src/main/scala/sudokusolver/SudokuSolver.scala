package sudokusolver

class SudokuSolver {

  val ROWS = Vector(Vector(0, 1, 2, 3, 4, 5, 6, 7, 8),
                    Vector(9, 10, 11, 12, 13, 14, 15, 16, 17),
                    Vector(18, 19, 20, 21, 22, 23, 24, 25, 26),
                    Vector(27, 28, 29, 30, 31, 32, 33, 34, 35),
                    Vector(36, 37, 38, 39, 40, 41, 42, 43, 44),
                    Vector(45, 46, 47, 48, 49, 50, 51, 52, 53),
                    Vector(54, 55, 56, 57, 58, 59, 60, 61, 62),
                    Vector(63, 64, 65, 66, 67, 68, 69, 70, 71),
                    Vector(72, 73, 74, 75, 76, 77, 78, 79, 80))

  val COLS = Vector(Vector(0, 9, 18, 27, 36, 45, 54, 63, 72),
                    Vector(1, 10, 19, 28, 37, 46, 55, 64, 73),
                    Vector(2, 11, 20, 29, 38, 47, 56, 65, 74),
                    Vector(3, 12, 21, 30, 39, 48, 57, 66, 75),
                    Vector(4, 13, 22, 31, 40, 49, 58, 67, 76),
                    Vector(5, 14, 23, 32, 41, 50, 59, 68, 77),
                    Vector(6, 15, 24, 33, 42, 51, 60, 69, 78),
                    Vector(7, 16, 25, 34, 43, 52, 61, 70, 79),
                    Vector(8, 17, 26, 35, 44, 53, 62, 71, 80))

  val BOXES = Vector(Vector(0, 1, 2, 9, 10, 11, 18, 19, 20),
                     Vector(3, 4, 5, 12, 13, 14, 21, 22, 23),
                     Vector(6, 7, 8, 15, 16, 17, 24, 25, 26),
                     Vector(27, 28, 29, 36, 37, 38, 45, 46, 47),
                     Vector(30, 31, 32, 39, 40, 41, 48, 49, 50),
                     Vector(33, 34, 35, 42, 43, 44, 51, 52, 53),
                     Vector(54, 55, 56, 63, 64, 65, 72, 73, 74),
                     Vector(57, 58, 59, 66, 67, 68, 75, 76, 77),
                     Vector(60, 61, 62, 69, 70, 71, 78, 79, 80))

  val UNITS = ROWS ++ COLS ++ BOXES

  /** Produce the solution for a given board or None if board is invalid or there is no solution */
  def solveBoard(board: Vector[Int]): Option[Vector[Int]] = {
    def _solveBoard(board: Vector[Int]): Option[Vector[Int]] = {
      if (isSolved(board)) Some(board)
      else _solveListOfBoard(nextBoards(board))
    }

    def _solveListOfBoard(listOfBoards: Vector[Vector[Int]]): Option[Vector[Int]] = {
      def attempt = _solveBoard(listOfBoards.head)

      if (listOfBoards.isEmpty) None
      else attempt match {
        case Some(attempt) => Some(attempt)
        case None => _solveListOfBoard(listOfBoards.tail)
      }
    }

    /** Given a Board, produce 0 - 9 valid next boards
     *
     * This function finds the first empty square in param board and generates 9 new boards,
     * filling the first empty square with 1 - 9. Only valid boards are returned.
     */
    def nextBoards(board: Vector[Int]): Vector[Vector[Int]] = {
      // probably could re-factor to use some type of generator
      def generateBoards(openPos: Int, board: Vector[Int]): Vector[Vector[Int]] = {
        def generateBoard(index: Int): Vector[Int] = fillSquare(board, openPos, index + 1)

        Vector.tabulate(9)(generateBoard)
      }

      def pruneInvalidBoards(listOfBoards: Vector[Vector[Int]]): Vector[Vector[Int]] =
        listOfBoards.filter(isValid)

      pruneInvalidBoards(generateBoards(firstOpenPos(board), board))
    }

    _solveBoard(board)
  }

  // TODO - put in Board class
  /** Finds the current value for a given Board position */
  def readSquare(board: Vector[Int], pos: Int): Int = board(pos)

  // TODO - put in Board class
  /** Produce a Board with newValue at given position */
  def fillSquare(board: Vector[Int], pos: Int, newValue: Int): Vector[Int] = {
    board.take(pos) ++ Vector(newValue) ++ board.drop(pos + 1)
  }

  // TODO - put in Board class
  def firstOpenPos(board: Vector[Int]): Int = board.indexOf(0)

  // TODO - put in Board class
  /** Produce true if no unit on the boards has the same value twice */
  def isValid(board: Vector[Int]): Boolean = {
    def areValidUnits(units: Vector[Vector[Int]]) = units.forall(isValidUnit)
    def isValidUnit(unit: Vector[Int]) = {
      containsDuplicates(keepNonZeroValues(readUnit(unit)))
    }
    // needed because unit is a list of pos, not values
    def readUnit(unit: Vector[Int]): Vector[Int] = unit.map(readPos)
    def readPos(pos: Int): Int = readSquare(board, pos)
    def keepNonZeroValues(values: Vector[Int]): Vector[Int] = values.filter(x => x != 0)
    def containsDuplicates(values: Vector[Int]): Boolean = values.distinct.size == values.size

    areValidUnits(UNITS)
  }

  // Probably keep in solver
  /** Produce true if the board is solved
   *
   * Because invalid Boards are immediately pruned, this function assumes that the param
   * board is a valid Board. Building on that assumption, if a board is full, it is solved.
   */
  def isSolved(board: Vector[Int]): Boolean = !board.contains(0)
}