package soccerball.piece;

import soccerball.piece.exceptions.ConcavityException;
import soccerball.piece.exceptions.ConcavitySizeArrayException;
import soccerball.piece.exceptions.ElementException;

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
     * @throws ConcavitySizeArrayException La taille du tableau de concavité est incorrete
     * @throws ConcavityException La concavité est incorrecte
     * @throws ElementException L'element est negatif
     */
    public Hexagon(int element, int[] concavity) throws ConcavitySizeArrayException, ConcavityException, ElementException {
        super(element, concavity);

        if (concavity.length != SIDES) {
            throw new ConcavitySizeArrayException(this.concavity.size(), SIDES);
        }
    }
}