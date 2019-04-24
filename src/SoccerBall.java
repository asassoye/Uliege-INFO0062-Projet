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
        this.orderedPieces.add(this.availablePieces.get(20));

        this.orderedPieces.getFirst().setConnections(Data.CONNECTIONS[0]);
        this.orderedPieces.getFirst().setPosition(1);

        this.orderedPieces.add(this.availablePieces.get(1));

        this.orderedPieces.get(1).setConnections(Data.CONNECTIONS[1]);
        this.orderedPieces.get(1).setPosition(2);

        int[] connections = Data.CONNECTIONS[3];
        int lenght = connections.length;

        Polygon piece = this.getNextType(0, lenght);
        LinkedList<Polygon> connectedPieces = this.getConnectedPieces(piece, connections);

        System.out.println("Coucou");


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


    private Polygon getNextType(int actual, int sides) {
        for (Polygon piece : this.availablePieces) {
            if (piece.getType() > actual) {
                if (sides == 5 && piece instanceof Pentagon) {
                    return piece;
                } else if (sides == 6 && piece instanceof Hexagon) {
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
}




/*
TODO -> Check nb element dans Data.CONNECTIONS pour déterminer si Penta ou Hexa
TODO -> Verifier piece par piece si 2 poly colle entre eux (convex -> concav)
TODO -> Placer la piece ajouté dans un tablea de "Back up" (-> piece placée)
TODO -> Delete de la liste le polygone ajouté (Si erreur, le rajouté à la fin et le del du tableau back up)
 */