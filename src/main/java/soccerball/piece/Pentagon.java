package soccerball.piece;

import soccerball.piece.exceptions.ConcavityException;
import soccerball.piece.exceptions.ConcavitySizeArrayException;
import soccerball.piece.exceptions.ElementException;

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
     * @throws ConcavitySizeArrayException Le tableau de concavité est incorrecte
     */
    public Pentagon(int element, int[] concavity) throws ConcavitySizeArrayException, ConcavityException, ElementException {
        super(element, concavity);
        if (concavity.length != SIDES) {
            throw new ConcavitySizeArrayException(concavity.length, SIDES);
        }
    }
}