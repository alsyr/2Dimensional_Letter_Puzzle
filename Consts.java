/**
 * This class stores frequently used constants for TwoDimPuzzle.
 * CS 146-08 Fall 2014
 *
 * Team: UNITED4
 * @author AlSyR
 * Edited by Phuc Nguyen
 */

public final class Consts {

    public static final int FIRST_POSITION_ARRAY = 0;
    public static final int SECOND_POSITION_ARRAY = 1;
    public static final int THIRD_POSITION_ARRAY = 2;
    public static final int FOURTH_POSITION_ARRAY = 3;
    public static final int ARRAY_SIZE_TWO = 2;
    public static final int NO_POSITION_CORR = 0;
    public static final int ONE_POSITION_CORR = 1;
    public static final int TWO_POSITION_CORR = 2;
    public static final int INCREMENT = 1;
    public static final int DECREMENT = -1;
    public static final int SAME = 0;

    private Consts() {
        //this prevents even the native class from
        //calling this constructor as well :
        throw new AssertionError();
    }

    /**
     * Convert a string to a character array
     * @param stringToConvert the word in String
     * @return a character array that hold characters for the given word
     */
    public static char[] convertStringToChar(String stringToConvert) {
        return stringToConvert.toCharArray();
    }

}
