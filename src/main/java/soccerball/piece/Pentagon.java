package soccerball.piece;

public class Pentagon extends Piece {
    /**
     * Nombre de cotés a la piece
     */
    public final static int SIDES = 5;

    /**
     * Constructeur de Pentagon
     *
     * @param element   L'id de l'element
     * @param concavity Le tableau de concavité
     * @throws Exception Le pentagone ne peut être crée
     */
    public Pentagon(int element, int[] concavity) throws Exception {
        super(element, concavity);
        if (this.concavity.size() != SIDES) {
            throw new Exception("soccerball.piece.Pentagon concavity array must have 5 elements");
        }

        if (this.element < 11 || this.element > 14) {
            throw new Exception("soccerball.piece.Pentagon element must have a value between 11 and 14");
        }
    }
}