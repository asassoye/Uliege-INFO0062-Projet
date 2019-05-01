package soccerball.piece.exceptions;

public class ConcavitySizeArrayException extends Exception {
    public ConcavitySizeArrayException(int sides, int expectedSides) {
        super(String.format("ConcavityArray must have %d sides but %d given", expectedSides, sides));
    }
}
