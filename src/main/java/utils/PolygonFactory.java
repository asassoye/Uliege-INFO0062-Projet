package utils;

import piece.Hexagon;
import piece.Pentagon;
import piece.PieceCollection;

public final class PolygonFactory {
    public static PieceCollection createPieces() {
        PieceCollection pieces = new PieceCollection();

        for (int i = 0; i < Data.ELEMENTS_SIDES.length; ++i) {
            for (int j = 0; j < Data.NB_ELEMENTS[i]; ++j) {
                if (Data.ELEMENTS_SIDES[i].length == 5) {
                    try {
                        pieces.addLast(new Pentagon(i + 1, Data.ELEMENTS_SIDES[i]));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if (Data.ELEMENTS_SIDES[i].length == 6) {
                    try {
                        pieces.addLast(new Hexagon(i + 1, Data.ELEMENTS_SIDES[i]));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }

        return pieces;
    }
}