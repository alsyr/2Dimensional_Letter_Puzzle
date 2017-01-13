/**This class creates a 2D puzzle from the InputManager
 * CS 146-08 Fall 2014
 *
 * Team: UNITED4
 * @author AlSyR
 * Edited by Phuc Nguyen
 */

import java.util.ArrayList;

public class Puzzle {

    //Declaration of members variables
    private int _rows; //Number of rows of the puzzle
    private int _columns; //Number of columns of the puzzle
    private InputManager _theInput;
    private char[][] _thePuzzle; //The puzzle itself    


    /**
     * ***********************************************************************
     * Constructor that create a puzzle from the input manager.
     * ***********************************************************************
     */
    public Puzzle(InputManager theInput) {
        this._theInput = theInput;

        createPuzzle();
    }


    /**
     * This method creates the puzzle by using rows and columns from the
     * InputManager. Then breaks the word into characters and stores them in
     * the 2D char array
     */

    public void createPuzzle() {
        ArrayList<String> arrayRows = _theInput.getArrayRows();
        _rows = arrayRows.size();
        _thePuzzle = new char[_rows][];

        int count = 0;

        for (String tempString : arrayRows) {
            char[] tempArray;
            tempArray = Consts.convertStringToChar(tempString);
            _thePuzzle[count] = tempArray;
            count++;
        }

        _columns = _thePuzzle[Consts.FIRST_POSITION_ARRAY].length;
    }

    //Default getters

    public int getRows() {
        return _rows;
    }


    public int getColumns() {
        return _columns;
    }


    public char[][] getPuzzle() {
        return _thePuzzle;
    }
}