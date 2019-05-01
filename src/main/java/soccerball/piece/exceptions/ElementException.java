package soccerball.piece.exceptions;

public class ElementException extends Exception {
    public ElementException(int element) {
        super(String.format("Element must be positive but %d given", element));
    }
}
