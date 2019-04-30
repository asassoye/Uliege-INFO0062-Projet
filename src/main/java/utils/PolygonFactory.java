package utils;

import piece.Hexagon;
import piece.Pentagon;
import piece.PieceCollection;

public final class PolygonFactory {
    public static PieceCollection createPieces(int[][] concavityArray, int[] nbElements) {
        PieceCollection pieces = new PieceCollection();

        for (int i = 0; i < concavityArray.length; ++i) {
            for (int j = 0; j < nbElements[i]; ++j) {
                if (concavityArray[i].length == 5) {
                    try {
                        pieces.addLast(new Pentagon(i + 1, concavityArray[i]));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if (concavityArray[i].length == 6) {
                    try {
                        pieces.addLast(new Hexagon(i + 1, concavityArray[i]));
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