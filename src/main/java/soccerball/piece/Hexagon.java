package soccerball.piece;

/**
 * Classe Hexagon
 */
public class Hexagon extends Piece {
    /**
     * Nombre de cotés a la piece
     */
    public final static int SIDES = 6;

    /**
     * Constructeur d'un hexagon
     *
     * @param element   L'id de l'element
     * @param concavity Le tableau de concavité
     * @throws Exception L'Hexagon ne peut être crée
     */
    public Hexagon(int element, int[] concavity) throws Exception {
        super(element, concavity);

        if (concavity.length != SIDES) {
            throw new Exception("soccerball.piece.Hexagon concavity array must have 6 elements");
        }

        if (element < 1 || element > 10) {
            throw new Exception("soccerball.piece.Hexagon element must have a value between 11 and 14");
        }
    }
}