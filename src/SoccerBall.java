import java.util.LinkedList;

public class SoccerBall {
    private LinkedList<Piece> availablePieces;
    private LinkedList<Piece> orderedPieces;


    private SoccerBall() {
        this.availablePieces = PolygonFactory.createPieces();
        this.orderedPieces = new LinkedList<>();
    }

    public static void main(String[] args) {
        SoccerBall soccerBall = new SoccerBall();
        soccerBall.solve();
        soccerBall.printOrderedPieces();
    }

    private void solve() {

        try {
            Piece piece = getNextAvailablePiece(Data.CONNECTIONS[0].length);
            addOrderedPiece(piece, Data.CONNECTIONS[0]);

            piece = getNextAvailablePiece(Data.CONNECTIONS[1].length);
            addOrderedPiece(piece, Data.CONNECTIONS[1]);

            int[] concavityArray = this.getConcavityArray(piece);

            piece.rotateToMatchConcavity(concavityArray);

            piece = getNextAvailablePiece(Data.CONNECTIONS[1].length);
            addOrderedPiece(piece, Data.CONNECTIONS[2]);

            concavityArray = this.getConcavityArray(piece);

            piece.rotateToMatchConcavity(concavityArray);

            //restoreLastOrderedPiece();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void reccursif(int position) {

    }

    private int[] getConcavityArray(Piece piece) {
        Piece[] connectedPieces = new Piece[piece.getConnections().length];
        int[] concavityArray = new int[piece.getConcavity().length];

        nextPiece:
        for (Piece orderedPiece : this.orderedPieces) {
            if (piece == orderedPiece) {
                continue;
            }

            for (int j = 0; j < orderedPiece.getConnections().length; ++j) {
                int orderedPieceConnection = orderedPiece.getConnections()[j];
                for (int k = 0; k < piece.getConnections().length; ++k) {
                    int pieceConnection = piece.getConnections()[k];

                    if (orderedPieceConnection == pieceConnection) {
                        concavityArray[k] = orderedPiece.getConcavity()[j] * -1;
                        continue nextPiece;
                    }
                }
            }
        }

        return concavityArray;
    }

    private Piece getNextAvailablePiece(int sides) throws Exception {

        if (sides == Pentagon.SIDES) {
            for (Piece piece : this.availablePieces) {
                if (piece instanceof Pentagon) {
                    return piece;
                }
            }
        } else if (sides == Hexagon.SIDES) {
            for (Piece piece : this.availablePieces) {
                if (piece instanceof Hexagon) {
                    return piece;
                }
            }
        } else {
            throw new Exception("No available pieces.");
        }

        return null;
    }

    private void printOrderedPieces() {
        for (Piece orderedPiece : orderedPieces) {
            orderedPiece.printPiece();
        }
    }

    private Piece addOrderedPiece(Piece piece, int[] connections) throws Exception {
        int id = this.availablePieces.indexOf(piece);

        if (id == -1) {
            throw new Exception("The required piece is not available");
        }

        piece.setConnections(connections);
        piece.setPosition(orderedPieces.size() + 1);

        orderedPieces.add(piece);
        availablePieces.remove(id);

        return piece;
    }

    private void restoreLastOrderedPiece() {
        Piece piece = this.orderedPieces.getLast();
        piece.setPosition(0);
        piece.setRotation(0);
        piece.setConnections(null);

        this.availablePieces.add(piece);
        this.orderedPieces.removeLast();
    }
}




/*
TODO -> Check nb element dans Data.CONNECTIONS pour déterminer si Penta ou Hexa
TODO -> Verifier piece par piece si 2 poly colle entre eux (convex -> concav)
TODO -> Placer la piece ajouté dans un tablea de "Back up" (-> piece placée)
TODO -> Delete de la liste le polygone ajouté (Si erreur, le rajouté à la fin et le del du tableau back up)
 */