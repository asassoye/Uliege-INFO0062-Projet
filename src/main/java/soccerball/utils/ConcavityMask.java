package soccerball.utils;

import soccerball.piece.Piece;

import java.util.LinkedList;

public final class ConcavityMask {
    /**
     * Creer un masque de concavité a partir d'une piece donnée et des pieces déja placées
     *
     * @param piece        La piece dont on doit avoir le masque de concavité
     * @param placedPieces Les pieces déja placés dont on doit extraire la concavité a un emplaement donné
     * @return Le masque de concavité
     */
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

    /**
     * Permet de comparer le tableau de concavité avec un masque de concavité
     *
     * @param concavityArray Le tableau de concavité a comparer
     * @param mask           Le masque de concavité a utiliser pour la comparaison
     * @return true si le tableau est correcte, false si il est incorrecte
     */
    public static boolean compare(Integer[] concavityArray, int[] mask) {
        for (int i = 0; i < concavityArray.length; ++i) {
            if (mask[i] != 0 && concavityArray[i] != mask[i]) {
                return false;
            }
        }
        return true;
    }
}
