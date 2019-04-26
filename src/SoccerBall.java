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
            addOrderedPiece(this.availablePieces.get(20), Data.CONNECTIONS[0]);
            addOrderedPiece(this.availablePieces.get(1), Data.CONNECTIONS[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Polygon piece = this.getNextType(0, length);
        //LinkedList<Polygon> connectedPieces = this.getConnectedPieces(piece, connections);
        //if(connectedPieces == null){
        //	System.out.println(this.orderedPieces.size());
        //	int id = this.availablePieces.indexOf(piece);
        //	System.out.println(id);
        //	this.orderedPieces.add(this.availablePieces.get(id));
        //	System.out.println(this.orderedPieces.size());
        //	this.availablePieces.remove(id);
        //	System.out.println(this.availablePieces.size());
        //}

        System.out.println("Coucou");


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
}




/*
TODO -> Check nb element dans Data.CONNECTIONS pour déterminer si Penta ou Hexa
TODO -> Verifier piece par piece si 2 poly colle entre eux (convex -> concav)
TODO -> Placer la piece ajouté dans un tablea de "Back up" (-> piece placée)
TODO -> Delete de la liste le polygone ajouté (Si erreur, le rajouté à la fin et le del du tableau back up)
 */