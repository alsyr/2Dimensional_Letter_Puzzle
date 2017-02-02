/**
 * This class solves the puzzle
 * CS 146-08 Fall 2014
 *
 * Team: UNITED4
 * @author AlSyR
 * Edited by Phuc Nguyen
 */
import java.util.ArrayList;

public class PuzzleSolver {

  //Declaration of members variables
  private int _rows; //Number of rows of the puzzle
  private int _columns; //Number of columns of the puzzle
  private InputManager _theInput;
  private ArrayList<String> _listOfWords; //The list of words to search for
  private Puzzle _thePuzzle; //The puzzle itself

  /**
   * ***********************************************************************
   * Constructor
   * ************************************************************************
   */
  public PuzzleSolver(InputManager theInput, Puzzle thePuzzle) {
    this._theInput = theInput;
    this._thePuzzle = thePuzzle;
    this._rows = _thePuzzle.getRows();
    this._columns = _thePuzzle.getColumns();

    //Start searching for words
    findListOfWords();
  }

  /**
   * ***********************************************************************
   * Traversing the list of
   * words and searching them in the puzzle
   * ************************************************************************
   */
  public void findListOfWords() {
    _listOfWords = _theInput.getListOfWords();
    for (String temp : _listOfWords)
      findWord(temp);
  }

  /**
   * ***********************************************************************
   * Traversing the puzzle
   * in a particular direction
   * ************************************************************************
   */
  private void traversingPuzzle(int currentWordSize, int wordLength, int rows, int columns,
                                boolean stopLoop, boolean firstCondition, boolean secondCondition,
                                int firstDecInc, int secondDecInc, int iPosCorr, int jPosCorr,
                                char[] tempArray, int iBegin, int jBegin,
                                ArrayList<int[]> thePositions) {
    int newCount = 0;
    int[] aPosition = new int[Consts.ARRAY_SIZE_TWO];

    while (currentWordSize < wordLength && !stopLoop && firstCondition && secondCondition) {
      if (currentWordSize == 1) {
        rows = rows + firstDecInc;
        columns = columns + secondDecInc;
        newCount++;
      }
      if (tempArray[newCount] == _thePuzzle.getPuzzle()[rows][columns]) {
        currentWordSize++;
        rows = rows + firstDecInc;
        columns = columns + secondDecInc;
        newCount++;
        if (currentWordSize == wordLength) {
          aPosition[Consts.FIRST_POSITION_ARRAY] = rows + iPosCorr;
          aPosition[Consts.SECOND_POSITION_ARRAY] = columns + jPosCorr;
        }
      } else {
        aPosition[Consts.FIRST_POSITION_ARRAY] = Consts.SAME;
        aPosition[Consts.SECOND_POSITION_ARRAY] = Consts.SAME;
        stopLoop = true;
      }
    }
    addPosition(aPosition, iBegin, jBegin, thePositions);
  }

  /**
   * ***********************************************************************
   * Adding position of an
   * occurrence into an ArrayList
   * ************************************************************************
   */
  private void addPosition(int[] tempList, int beginRow, int beginCol,
                           ArrayList<int[]> thePositions) {
    if (tempList[Consts.FIRST_POSITION_ARRAY] != 0) {
      int[] aPosition = {beginRow, beginCol, tempList[Consts.FIRST_POSITION_ARRAY],
          tempList[Consts.SECOND_POSITION_ARRAY]};
      thePositions.add(aPosition);
    }
  }

  /**
   * ***********************************************************************
   * Printing positions of
   * all occurrences of a word
   * ************************************************************************
   */
  private void printPositions(ArrayList<int[]> thePositions, String inWord) {
    System.out.print(inWord + ": ");
    if (!thePositions.isEmpty()) {
      for (int[] a : thePositions)
        System.out.print("[(" + a[Consts.FIRST_POSITION_ARRAY] + "," +
            a[Consts.SECOND_POSITION_ARRAY] + ")-(" + a[Consts.THIRD_POSITION_ARRAY] +
            "," + a[Consts.FOURTH_POSITION_ARRAY] + ")]");
    }
    System.out.print("\n");
  }

  /**
   * ***********************************************************************
   * Searching a String
   * given as parameter in the puzzle
   * ************************************************************************
   */
  public void findWord(String inWord) {
    int currentIndex = 0;
    int currentRow = 0;
    int beginRow = 0;
    int beginCol = 0;
    boolean stopLoop = false;
    ArrayList<int[]> positions = new ArrayList<int[]>();

    int wordLength = inWord.length();
    char[] tempArray = Consts.convertStringToChar(inWord);

    //Traversing the rows
    while (currentRow < _rows) {
      int currentCol = 0;

      //Traversing the columns
      while (currentCol < _columns) {

        if (tempArray[currentIndex] == _thePuzzle.getPuzzle()[currentRow][currentCol]) {
          beginRow = currentRow + 1;
          beginCol = currentCol + 1;

          //Initializing the variables used in the process
          int currentWordSize = 1;
          int nextRow = currentRow;
          int nextCol = currentCol;

          //Traversing the puzzle backward ascending diagonally
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop,
              wordLength <= currentRow + 1, wordLength <= currentCol + 1, Consts.DECREMENT,
              Consts.DECREMENT, Consts.TWO_POSITION_CORR, Consts.TWO_POSITION_CORR, tempArray,
              beginRow, beginCol, positions);

          //Traversing the puzzle forward descending diagonally
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop,
              wordLength <= (_rows - currentRow), wordLength <= (_columns - currentCol),
              Consts.INCREMENT, Consts.INCREMENT, Consts.NO_POSITION_CORR, Consts.NO_POSITION_CORR,
              tempArray, beginRow, beginCol, positions);

          //Traversing the puzzle backward descending diagonally
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop,
              wordLength <= (_rows - currentRow), wordLength <= currentCol + 1, Consts.INCREMENT,
              Consts.DECREMENT, Consts.NO_POSITION_CORR, Consts.TWO_POSITION_CORR, tempArray,
              beginRow, beginCol, positions);

          //Traversing the puzzle forward ascending diagonally
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop,
              wordLength <= currentRow + 1, wordLength <= (_columns - currentCol), Consts.DECREMENT,
              Consts.INCREMENT, Consts.TWO_POSITION_CORR, Consts.NO_POSITION_CORR, tempArray,
              beginRow, beginCol, positions);

          //Traversing the puzzle backward horizontally
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop, true,
              wordLength <= currentCol + 1, Consts.SAME, Consts.DECREMENT, Consts.ONE_POSITION_CORR,
              Consts.TWO_POSITION_CORR, tempArray, beginRow, beginCol, positions);

          //Traversing the puzzle forward horizontally
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop, true,
              wordLength <= (_columns - currentCol), Consts.SAME, Consts.INCREMENT,
              Consts.ONE_POSITION_CORR, Consts.NO_POSITION_CORR, tempArray, beginRow,
              beginCol, positions);

          //Traversing the puzzle backward vertically
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop,
              wordLength <= currentRow + 1, true, Consts.DECREMENT, Consts.SAME,
              Consts.TWO_POSITION_CORR, Consts.ONE_POSITION_CORR, tempArray, beginRow,
              beginCol, positions);

          //Traversing the puzzle forward vertically
          traversingPuzzle(currentWordSize, wordLength, nextRow, nextCol, stopLoop,
              wordLength <= (_rows - currentRow), true, Consts.INCREMENT, Consts.SAME,
              Consts.NO_POSITION_CORR, Consts.ONE_POSITION_CORR, tempArray, beginRow,
              beginCol, positions);
        }
        currentCol++;
      }
      currentRow++;
    }
    printPositions(positions, inWord);
  }
}