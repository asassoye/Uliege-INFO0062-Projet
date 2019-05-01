package soccerball.piece.exceptions;

public class OrientationException extends Exception {
    public OrientationException(int orientation, int maxOrientation) {
        super(String.format("Orientation must be between 0 and %d sides but %d given", maxOrientation, orientation));
    }
}
