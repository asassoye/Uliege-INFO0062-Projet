package soccerball.utils;

import soccerball.piece.Hexagon;
import soccerball.piece.Pentagon;
import soccerball.piece.Piece;
import soccerball.piece.exceptions.ConcavityException;
import soccerball.piece.exceptions.ConcavitySizeArrayException;
import soccerball.piece.exceptions.ElementException;

import java.util.LinkedList;

public final class PolygonFactory {
    public static LinkedList<Piece> createPieces(int[][] concavityArray, int[] nbElements) throws ConcavitySizeArrayException, ElementException, ConcavityException {
        LinkedList<Piece> pieces = new LinkedList<>();

        for (int i = 0; i < concavityArray.length; ++i) {
            for (int j = 0; j < nbElements[i]; ++j) {
                if (concavityArray[i].length == Pentagon.SIDES) {

                        pieces.addLast(new Pentagon(i + 1, concavityArray[i]));

                } else if (concavityArray[i].length == Hexagon.SIDES) {

                        pieces.addLast(new Hexagon(i + 1, concavityArray[i]));

                } else {
                    return null;
                }
            }
        }

        return pieces;
    }
}