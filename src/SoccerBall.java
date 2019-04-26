import java.util.LinkedList;

public class SoccerBall {
    private LinkedList<Polygon> availablePieces;
    private LinkedList<Polygon> orderedPieces;


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
            Polygon polygon = getNextAvailablePiece(null);
            addOrderedPiece(polygon, Data.CONNECTIONS[0]);

            polygon = getNextAvailablePiece(polygon);
            addOrderedPiece(polygon, Data.CONNECTIONS[1]);
            restoreLastOrderedPiece();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void reccursif(){

    }

    private LinkedList<Polygon> getConnectedPieces(Polygon piece, int[] connections) {
        LinkedList<Polygon> connectedPieces = new LinkedList<>();

        for (Polygon orderedPiece : this.orderedPieces) {
            for (int connection : connections) {
                for (int k = 0; k < orderedPiece.getConnections().length; ++k) {
                    if (orderedPiece.getConnections()[k] == connection) {
                        connectedPieces.add(orderedPiece);
                    }
                }
            }
        }

        return connectedPieces;
    }

    private Polygon getNextAvailablePiece(Polygon polygon) {

        if (polygon instanceof Pentagon) {
            for (Polygon piece : this.availablePieces) {
                if (piece instanceof Hexagon) {
                    return piece;
                }
            }
        } else if (polygon instanceof Hexagon || polygon == null) {
            for (Polygon piece : this.availablePieces) {
                if (piece instanceof Pentagon) {
                    return piece;
                }
            }
        }

        return null;
    }

    private void printOrderedPieces() {
        for (Polygon orderedPiece : orderedPieces) {
            orderedPiece.printPiece();
        }
    }

    private void addOrderedPiece(Polygon polygon, int[] connections) throws Exception {
        int id = this.availablePieces.indexOf(polygon);

        if (id == -1) {
            throw new Exception("The required piece is not available");
        }

        polygon.setConnections(connections);
        polygon.setPosition(orderedPieces.size() + 1);

        orderedPieces.add(polygon);
        availablePieces.remove(id);
    }

    private void restoreLastOrderedPiece() {
        Polygon polygon = this.orderedPieces.getLast();
        polygon.setPosition(0);
        polygon.setRotation(0);
        polygon.setConnections(null);

        this.availablePieces.add(polygon);
        this.orderedPieces.removeLast();
    }
}




/*
TODO -> Check nb element dans Data.CONNECTIONS pour déterminer si Penta ou Hexa
TODO -> Verifier piece par piece si 2 poly colle entre eux (convex -> concav)
TODO -> Placer la piece ajouté dans un tablea de "Back up" (-> piece placée)
TODO -> Delete de la liste le polygone ajouté (Si erreur, le rajouté à la fin et le del du tableau back up)
 */