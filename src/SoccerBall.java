public class SoccerBall {
    private PieceCollection availablePieces;
    private PieceCollection orderedPieces;


    public SoccerBall() {
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

    public static int[] getConcavityArray(Piece piece, PieceCollection placedPieces) {
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

    public boolean solve() throws Exception {
        return solve(0);
    }

    private boolean solve(int index) throws Exception {
        boolean found = false;
        int[] concavityArray;
        boolean rotated = false;
        Piece piece;
        int retry = 0;
        int firstType = 0;

        if (index > Data.CONNECTIONS.length - 1) {
            throw new Exception("index out of limit index:" + index + "/" + (Data.CONNECTIONS.length - 1));
        }

        while (!found) {
            piece = getNextAvailablePiece(Data.CONNECTIONS[index].length);
            if (piece == null)
                throw new Exception("No available piece");

            this.addOrderedPiece(piece, Data.CONNECTIONS[index]);

            concavityArray = getConcavityArray(piece);

            if (retry > 0) {
                if (retry >= this.availablePieces.numberOf(piece.getClass())) {
                    this.restoreLastOrderedPiece();
                    return false;
                }

                if (!piece.rotateToMatchConcavity(concavityArray, rotated)) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    continue;
                }
            } else {
                rotated = piece.rotateToMatchConcavity(concavityArray);
                if (!rotated) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    firstType = piece.element;
                    continue;
                }
            }

            if ((index == Data.CONNECTIONS.length - 1) || solve(index + 1)) {
                found = true;
            } else {
                this.restoreLastOrderedPiece();
                retry++;
                firstType = piece.element;
            }
        }
        return true;
    }

    public int[] getConcavityArray(Piece piece) {
        return getConcavityArray(piece, this.orderedPieces);
    }

    private Piece getNextAvailablePiece(int sides) {

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

        if (connections.length != piece.getConcavity().length) {
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
        piece.setOrientation(0);
        piece.setConnections(null);

        this.orderedPieces.removeLast();
        this.availablePieces.addLast(piece);
    }

    public PieceCollection getOrderedPieces() {
        return orderedPieces;
    }
}
