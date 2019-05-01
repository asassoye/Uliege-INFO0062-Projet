package soccerball.piece.exceptions;

public class ConcavityException extends Exception {
    public ConcavityException(int concavity) {
        super(String.format("Concavity must be -1 or 1 but %d given", concavity));
    }
}
