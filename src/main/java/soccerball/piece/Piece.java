package soccerball.piece;

import soccerball.piece.exceptions.ConcavityException;
import soccerball.piece.exceptions.ElementException;
import soccerball.piece.exceptions.OrientationException;
import soccerball.utils.ConcavityMask;

import java.util.LinkedList;

/**
 * Classe piece
 */
public abstract class Piece {
    /**
     * L'id de l'element
     */
    protected int element;
    /**
     * Le tableau de concavité
     */
    protected LinkedList<Integer> concavity = new LinkedList<>();
    /**
     * La position de la piece (quand ordonné)
     */
    private int position;
    /**
     * L'orientation de la piece
     */
    private int orientation;
    /**
     * Les connections de la piece si placée
     */
    private int[] connections;

    /**
     * Constructeur de la piece
     *
     * @param element        L'id de l'element
     * @param concavityArray le tableau de concavité
     */
    Piece(int element, int[] concavityArray) throws ConcavityException, ElementException {
        this.position = 0;
        this.orientation = 0;
        for (int concavity : concavityArray) {
            if (concavity == -1 || concavity == 1) {
                this.concavity.add(concavity);
            } else {
                throw new ConcavityException(concavity);
            }

        }
        if (element > 0) {
            this.element = element;
        } else {
            throw new ElementException(element);
        }

        this.connections = null;
    }

    /**
     * Fonction de rotation de la concavité
     *
     * @param clockwize Rotation horaire (true) ou anti-horaire (false)
     * @return true si la piece à été tournée, false si elle a fait un tour complet
     */
    private boolean rotateConcavity(boolean clockwize) {
        if (clockwize) {
            int movingPiece = this.concavity.getLast();
            this.concavity.removeLast();
            this.concavity.addFirst(movingPiece);
        } else {
            int movingPiece = this.concavity.getFirst();
            this.concavity.removeFirst();
            this.concavity.addLast(movingPiece);
        }

        if (isRotatable()) {
            this.orientation++;
            return true;
        } else {
            this.orientation = 0;
            return false;
        }
    }

    /**
     * Nous dis si la pièce est tournable ou si elle arrive à un tour complet
     * @return true si tournable, false si on est a la fin de la rotation
     */
    private boolean isRotatable() {
        return this.orientation < this.concavity.size() - 1;
    }

    /**
     * Imprime le resultat de la pièce sous forme
     * Position [position] - Element [element] - Orientation [orientation]
     */
    public void printPiece() {
        System.out.printf(
                "Position %d - Element %d - Orientation %d%n",
                this.position,
                this.element,
                this.orientation
        );
    }

    /**
     * Renvoie la position de la pièce
     * @return this.position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Modifie la position de la piece
     * @param position La nouvelle position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Renvoie l'orientation
     * @return this.orientation
     */
    public int getOrientation() {
        return this.orientation;
    }

    /**
     * Regle l'orientation et fait la rotation de la concavité
     * @param orientation               Orientation souhaitée
     * @throws OrientationException     L'orientation souhaitée est incorrecte
     */
    public void setOrientation(int orientation, boolean clockwise) throws OrientationException {
        if (orientation < 0 || orientation > this.concavity.size() - 1) {
            throw new OrientationException(orientation, this.concavity.size());
        }

        while (this.orientation != orientation) {
            this.rotateConcavity(clockwise);
        }
    }

    /**
     * Renvoie l'id de l'element
     * @return this.element
     */
    public int getElement() {
        return this.element;
    }

    /**
     * Regle l'id de l'element
     * @param element   Le nouvel id de l'element
     */
    public void setElement(int element) {
        this.element = element;
    }

    /**
     * Renvoie le table de concavité
     * @return Le table de concavité converti (de LinkedList vers int[])
     */
    public Integer[] getConcavity() {
        return this.concavity.toArray(new Integer[0]);
    }

    /**
     * Renvoie le tableau de connections
     * @return Le table de connections de la piece
     */
    public int[] getConnections() {
        return connections;
    }

    /**
     * Regle le table de connections de la pièce
     * @param connections Le nouveau tableau de connection
     */
    public void setConnections(int[] connections) {
        this.connections = connections;
    }

    /**
     * Fait une rotation jusqu'a ce que la piece convienne au masque donné ou que la pièce a fait un tour complet.
     * @param concavityMask Le masque de concavité
     * @return true si la pièce a su etre tournée, false si elle a fait un tour complet sans resultat
     */
    public boolean rotateToMatchConcavity(int[] concavityMask, boolean clockwise) {
        while (!ConcavityMask.compare(this.getConcavity(), concavityMask)) {
            if (!this.rotateConcavity(clockwise)) {
                return false;
            }
        }

        return true;
    }
}