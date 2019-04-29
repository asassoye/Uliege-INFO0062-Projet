import piece.Hexagon;
import piece.Pentagon;
import piece.Piece;
import piece.PieceCollection;
import utils.ConcavityMask;
import utils.Data;
import utils.PolygonFactory;

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

    public boolean solve() throws Exception {
        return solve(0);
    }

    private boolean solve(int index) throws Exception {
        boolean found = false;
        int[] concavityArray;
        boolean rotated;
        Piece piece;
        int retry = 0;
        int firstElement = 0;

        if (index > Data.CONNECTIONS.length - 1) {
            throw new Exception("index out of limit index:" + index + "/" + (Data.CONNECTIONS.length - 1));
        }

        if (index > orderedPieces.size()) {
            throw new Exception("index error");
        }

        while (!found) {
            piece = getNextAvailablePiece(Data.CONNECTIONS[index].length, retry);
            if (piece == null) {
                return false;
            }

            this.addOrderedPiece(piece, Data.CONNECTIONS[index]);

            concavityArray = getConcavityMask(piece);

            if (retry > 0) {
                if (piece.getElement() == firstElement || !piece.rotateToMatchConcavity(concavityArray)) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    continue;
                }
            } else {
                rotated = piece.rotateToMatchConcavity(concavityArray);
                if (!rotated) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    firstElement = piece.getElement();
                    continue;
                }
            }

            if ((index == Data.CONNECTIONS.length - 1) || solve(index + 1)) {
                found = true;
            } else {
                this.restoreLastOrderedPiece();
                retry++;
                firstElement = piece.getElement();
            }
        }
        return true;
    }

    private int[] getConcavityMask(Piece piece) {
        return ConcavityMask.getConcavityMask(piece, this.orderedPieces);
    }

    private Piece getNextAvailablePiece(int sides, int skip) {
        int skipped = 0;

        if (sides == Pentagon.SIDES) {
            for (Piece piece : this.availablePieces) {
                if (piece instanceof Pentagon) {
                    if (skipped == skip) {
                        return piece;
                    } else {
                        ++skipped;
                    }

                }
            }
        } else if (sides == Hexagon.SIDES) {
            for (Piece piece : this.availablePieces) {
                if (piece instanceof Hexagon) {
                    if (skipped == skip) {
                        return piece;
                    } else {
                        ++skipped;
                    }
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

    private void addOrderedPiece(Piece piece, int[] connections) throws Exception {
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
    }

    private void restoreLastOrderedPiece() throws Exception {
        Piece piece = this.orderedPieces.getLast();
        piece.setPosition(0);
        piece.setOrientation(0);
        piece.setConnections(null);

        this.orderedPieces.removeLast();

        if (this.availablePieces.size() == 0) {
            this.availablePieces.add(piece);
        } else {
            for (int i = 0; i < this.availablePieces.size(); ++i) {
                if (this.availablePieces.get(i).getElement() >= piece.getElement()) {
                    this.availablePieces.add(i, piece);
                    return;
                }
            }
            this.availablePieces.add(piece);
        }



    }

    public PieceCollection getOrderedPieces() {
        return orderedPieces;
    }
}
