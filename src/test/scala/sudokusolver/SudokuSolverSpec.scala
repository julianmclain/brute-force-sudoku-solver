package sudokusolver

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class SudokuSolverSpec extends AnyFunSuite with BeforeAndAfter {

  val BOARD_ONE = Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

  val BOARD_TWO = Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

  val BOARD_THREE = Vector(1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0)

  // Rated easy
  val BOARD_FOUR = Vector(2, 7, 4, 0, 9, 1, 0, 0, 5, 1, 0, 0, 5, 0, 0, 0, 9, 0, 6, 0, 0, 0, 0, 3, 2, 8, 0, 0, 0, 1, 9, 0, 0, 0, 0, 8, 0, 0, 5, 1, 0, 0, 6, 0, 0, 7, 0, 0, 0, 8, 0, 0, 0, 3, 4, 0, 2, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 7, 0, 8, 0, 0, 3, 4, 9, 0, 0, 0)
  val BOARD_FOUR_SOLUTION = Vector(2, 7, 4, 8, 9, 1, 3, 6, 5,
                     1, 3, 8, 5, 2, 6, 4, 9, 7,
                     6, 5, 9, 4, 7, 3, 2, 8, 1,
                     3, 2, 1, 9, 6, 4, 7, 5, 8,
                     9, 8, 5, 1, 3, 7, 6, 4, 2,
                     7, 4, 6, 2, 8, 5, 9, 1, 3,
                     4, 6, 2, 7, 5, 8, 1, 3, 9,
                     5, 9, 3, 6, 1, 2, 8, 7, 4,
                     8, 1, 7, 3, 4, 9, 5, 2, 6)

  // Rated difficult
  val BOARD_FIVE = Vector(5, 0, 0, 0, 0, 4, 0, 7, 0, 0, 1, 0, 0, 5, 0, 6, 0, 0, 0, 0, 4, 9, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 7, 5, 0, 0, 1, 8, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 8, 0, 6, 0, 0, 8, 0, 0, 0, 9, 0, 0, 8, 0, 7, 0, 0, 3, 1)
  val BOARD_FIVE_SOLUTION = Vector(5, 3, 9, 1, 6, 4, 8, 7, 2, 8, 1, 2, 7, 5, 3, 6, 9, 4, 6, 7, 4, 9, 2, 8, 3, 1, 5, 2, 9, 6, 4, 1, 7, 5, 8, 3, 1, 8, 7, 2, 3, 5, 9, 4, 6, 3, 4, 5, 8, 9, 6, 1, 2, 7, 9, 2, 3, 5, 4, 1, 7, 6, 8, 7, 6, 1, 3, 8, 2, 4, 5, 9, 4, 5, 8, 6, 7, 9, 2, 3, 1) // solution to 0OARD5

  // "Readers who spend hours grappling in vain with the Telegraph's daily sudoku puzzles should look away now...."
  // The "Hardest sudoku ever" according to Dr. Arto Inkala
  // https://www.telegraph.co.uk/news/science/science-news/9359579/Worlds-hardest-sudoku-can-you-crack-it.html
  // TODO: link to solution 404s
  val BOARD_SIX = Vector(0, 0, 5, 3, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 2, 0, 0, 7, 0, 0, 1, 0, 5, 0, 0, 4, 0, 0, 0, 0, 5, 3, 0, 0, 0, 1, 0, 0, 7, 0, 0, 0, 6, 0, 0, 3, 2, 0, 0, 0, 8, 0, 0, 6, 0, 5, 0, 0, 0, 0, 9, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 9, 7, 0, 0)

  // No solution
  val BOARD_SEVEN = Vector(1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 9)

  val solver = new SudokuSolver()

  before {
    println("ok boss, getting ready for a tests...")
  }

  test("readSquare") {
    assert(solver.readSquare(BOARD_ONE, 0) == 0)
    assert(solver.readSquare(BOARD_TWO, 8) == 9)
    assert(solver.readSquare(BOARD_FOUR_SOLUTION, 60) == 1)
  }

  test("fillSquare") {
    assert(solver.fillSquare(BOARD_ONE, 0, 8) == Vector(8) ++ BOARD_ONE.tail)
  }

  test("firstOpenPos") {
    assert(solver.firstOpenPos(BOARD_ONE) == 0)
    assert(solver.firstOpenPos(BOARD_FOUR) == 3)
  }

  test("isValid") {
    assert(solver.isValid(BOARD_ONE) == true)
    assert(solver.isValid(BOARD_FOUR) == true)
    assert(solver.isValid(2 +: BOARD_TWO.tail) == false)
  }

  test("isSolved") {
    assert(solver.isSolved(BOARD_ONE) == false)
    assert(solver.isSolved(BOARD_FOUR_SOLUTION) == true)
  }

  test("solveBoard") {
    assert(BOARD_FOUR_SOLUTION == solver.solveBoard(BOARD_FOUR).getOrElse(false))
    assert(BOARD_FIVE_SOLUTION == solver.solveBoard(BOARD_FIVE).getOrElse(false))
    assert(false == solver.solveBoard(BOARD_SEVEN).getOrElse(false))
  }
}
