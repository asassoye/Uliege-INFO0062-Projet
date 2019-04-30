package soccerball.utils;

import soccerball.piece.Piece;

import java.util.LinkedList;

public final class ConcavityMask {
    public static int[] getConcavityMask(Piece piece, LinkedList<Piece> placedPieces) {
        int[] concavityArray = new int[piece.getConcavity().length];

        nextPiece:
        for (Piece placedPiece : placedPieces) {
            if (piece == placedPiece) {
                continue;
            }

            for (int j = 0; j < placedPiece.getConnections().length; ++j) {
                int orderedPieceConnection = placedPiece.getConnections()[j];
                for (int k = 0; k < piece.getConnections().length; ++k) {
                    int pieceConnection = piece.getConnections()[k];

                    if (orderedPieceConnection == pieceConnection) {
                        concavityArray[k] = placedPiece.getConcavity()[j] * -1;
                        continue nextPiece;
                    }
                }
            }
        }

        return concavityArray;
    }

    public static boolean compare(Integer[] concavityArray, int[] mask) {
        for (int i = 0; i < concavityArray.length; ++i) {
            if (mask[i] != 0 && concavityArray[i] != mask[i]) {
                return false;
            }
        }
        return true;
    }
}
