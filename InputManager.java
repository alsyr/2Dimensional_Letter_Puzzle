import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class read and process the input file
 * CS 146-08 Fall 2014
 *
 * Team: UNITED4
 * @author AlSyR
 * Edited by Phuc Nguyen
 */

public class InputManager {
  private String _fileName;
  private String _lastLine;
  private ArrayList<String> _tempArrayInput;
  private ArrayList<String> _arrayRows;
  private ArrayList<String> _listOfWords; //The list of words to search for

  /**
   * The constructor to process the input file
   *
   * @param fileName a file name in .txt
   */
  public InputManager(String fileName) throws IOException {
    this._fileName = fileName;

    //Start reading the file
    readFile();
    createArrayRows();
    createListOfWords();
  }

  /**
   * ***********************************************************************
   * Read a file and store the first lines which correspond to the table
   * into the puzzle variable and then the last line
   * into an Arraylist of String The repertory of the file is given as argument
   * ************************************************************************
   */
  private void readFile() throws IOException {
    File inFile = new File(_fileName);
    BufferedReader reader = new BufferedReader(new FileReader(inFile));
    String sline;

    //To store the first lines temporarily
    _tempArrayInput = new ArrayList<String>();

    //Storing temporarily the table
    while (!(sline = reader.readLine()).isEmpty()) {
      _tempArrayInput.add(sline);
    }
    _lastLine = reader.readLine(); //Storing temporarily the last line
  }

  //Transferring data from tempArrayInput to _thePuzzle by deleting the spaces
  private void createArrayRows() {
    _arrayRows = new ArrayList<String>();
    for (String tempString : _tempArrayInput) {
      while (tempString.contains(" ")) {
        StringBuffer bTemp = new StringBuffer(tempString);
        int spaceIndex = bTemp.indexOf(" ");
        bTemp = bTemp.deleteCharAt(spaceIndex);
        tempString = bTemp.toString();
      }
      _arrayRows.add(tempString);
    }
  }

  //Transferring data from sline to _listOfWords by separating the different words
  private void createListOfWords() {
    _listOfWords = new ArrayList<String>();
    while (_lastLine.contains(" ")) {
      StringBuffer bTemp = new StringBuffer(_lastLine);
      int spaceIndex = bTemp.indexOf(" ");
      _listOfWords.add(bTemp.substring(0, spaceIndex));
      StringBuffer bbTemp = bTemp.delete(0, spaceIndex + 1);
      _lastLine = bbTemp.toString();
    }
    _listOfWords.add(_lastLine); //Adding the last word
  }

  //Getters
  public ArrayList<String> getListOfWords() {
    return _listOfWords;
  }

  public ArrayList<String> getArrayRows() {
    return _arrayRows;
  }
}