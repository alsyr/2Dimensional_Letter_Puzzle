/**
 * This class starts the program with the main function.
 * CS 146-08 Fall 2014
 *
 * Team: UNITED4
 * @author AlSyR
 * Edited by Phuc Nguyen
 */

import java.io.IOException;

//Implementation
public class TwoDimPuzzle {
  public static void main(String[] args) throws IOException {
    InputManager theInput = new InputManager("input.txt");
    Puzzle myPuzzle = new Puzzle(theInput);
    PuzzleSolver solver = new PuzzleSolver(theInput, myPuzzle);
  }
}