public class SoccerBall {
    private PieceCollection availablePieces;
    private PieceCollection orderedPieces;


    private SoccerBall() {
        this.availablePieces = PolygonFactory.createPieces();
        this.orderedPieces = new PieceCollection();
    }

    public static void main(String[] args) throws Exception {
        SoccerBall soccerBall = new SoccerBall();

        if (!soccerBall.solve()) {
            System.out.println("No solution found.");
        }

        soccerBall.printOrderedPieces();

    }

    private boolean solve() throws Exception {
        return solve(0);
    }

    private boolean solve(int index) throws Exception {
        boolean found = false;
        int[] concavityArray;
        boolean rotated;
        Piece piece;
        int retry = 0;
        int firstType = 0;

        while (!found) {
            piece = getNextAvailablePiece(Data.CONNECTIONS[index].length);
            this.addOrderedPiece(piece, Data.CONNECTIONS[index]);

            concavityArray = getConcavityArray(piece);

            if (retry > 0) {
                if (retry >= this.availablePieces.numberOf(piece.getClass()) - 1) {
                    return false;
                }

                if (piece.type == firstType || !piece.rotateToMatchConcavity(concavityArray, true)) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    continue;
                }
            } else {
                rotated = piece.rotateToMatchConcavity(concavityArray);
                if (!rotated) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    firstType = piece.type;
                    continue;
                }
            }

            if ((index == Data.CONNECTIONS.length - 1) || solve(index + 1)) {
                found = true;
            } else {
                this.restoreLastOrderedPiece();
                retry++;
                continue;
            }
        }
        return true;
    }

    private int[] getConcavityArray(Piece piece) {
        Piece[] connectedPieces = new Piece[piece.getConnections().length];
        int[] concavityArray = new int[piece.getConcavity().size()];

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
                        concavityArray[k] = orderedPiece.getConcavity().get(j) * -1;
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

        if (connections.length != piece.getConcavity().size()) {
            throw new Exception("The connections are not compatible with piece");
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

        this.orderedPieces.removeLast();
        this.availablePieces.addLast(piece);
    }
}




/*
TODO -> Check nb element dans Data.CONNECTIONS pour déterminer si Penta ou Hexa
TODO -> Verifier piece par piece si 2 poly colle entre eux (convex -> concav)
TODO -> Placer la piece ajouté dans un tablea de "Back up" (-> piece placée)
TODO -> Delete de la liste le polygone ajouté (Si erreur, le rajouté à la fin et le del du tableau back up)
 */