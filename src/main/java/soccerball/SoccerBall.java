package soccerball;

import soccerball.piece.Hexagon;
import soccerball.piece.Pentagon;
import soccerball.piece.Piece;
import soccerball.piece.exceptions.ConcavityException;
import soccerball.piece.exceptions.ConcavitySizeArrayException;
import soccerball.piece.exceptions.ElementException;
import soccerball.piece.exceptions.OrientationException;
import soccerball.utils.ConcavityMask;
import soccerball.utils.Data;
import soccerball.utils.PolygonFactory;

import java.util.LinkedList;

/**
 * Classe principale permettant de generer et resoudre le placement des pieces d'un ballon
 */
public class SoccerBall {
    /**
     * Connections d'un ballon
     */
    private final static int[][] connections = Data.CONNECTIONS;
    /**
     * Pieces disponibles pour la résolution
     */
    private LinkedList<Piece> availablePieces;
    /**
     * Pieces déja résolues
     */
    private LinkedList<Piece> orderedPieces;

    private boolean clockwise;

    /**
     * Constructeur du ballon
     *
     * @param concavityArray La matrive de concavités
     * @param nbElements     Le nombre de fois que chaque soccerball.piece est présente
     */
    public SoccerBall(int[][] concavityArray, int[] nbElements) throws ConcavitySizeArrayException, ElementException, ConcavityException {
        this.availablePieces = PolygonFactory.createPieces(concavityArray, nbElements);
        this.orderedPieces = new LinkedList<>();
    }

    /**
     * Fonction main de résolution du ballon. Résout le balon avec les données par défaut et imprime le resultat
     *
     * @param args Arguments du programme
     * @throws Exception Probleme avec la resolution du ballon
     */
    public static void main(String[] args) {
        SoccerBall soccerBall = null;
        try {
            soccerBall = new SoccerBall(Data.ELEMENTS_SIDES, Data.NB_ELEMENTS);
        } catch (ConcavitySizeArrayException | ElementException | ConcavityException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        if (args.length != 0) {
            String arg = args[0];
            switch (arg) {
                case "--clockwise":
                    if (!soccerBall.solve(true)) {
                        System.out.println("No solution found.");
                        System.exit(-1);
                    }
                    break;
                case "--anti-clockwise":
                    if (!soccerBall.solve(false)) {
                        System.out.println("No solution found.");
                        System.exit(1);
                    }
                    break;
                case "--help":
                    printHelp();
                    System.exit(1);
                default:
                    System.out.printf("Argument %s not recognized!%n", arg);
                    printHelp();
                    System.exit(1);
            }

        } else {
            if (!soccerBall.solve()) {
                System.out.println("No solution found.");
                System.exit(1);
            }
        }

        soccerBall.printOrderedPieces();
        System.exit(0);

    }

    private static void printHelp() {
        System.out.println("usage: java -cp build/classes soccerball.SoccerBall [options]\n");
        System.out.println("Options:");
        System.out.println("--clockwise         Solve clockwise");
        System.out.println("--anti-clockwise    Solve anti-clockwise");
        System.out.println("--help              Help");
    }

    /**
     * Résout le placement des pieces depuis le début avec la fonction solve(0)
     *
     * @return true si la solution a été trouvée, false si aucune reponse n'est possible.
     * @throws Exception Problème d'index
     */
    public boolean solve() {
        return solve(true);
    }

    public boolean solve(boolean clockwise) {
        this.clockwise = clockwise;
        return solve(0);
    }

    /**
     * Retourne le masque de concavité
     *
     * @param piece La soccerball.piece dont on souhaite le masque de concavité
     * @return Le masque de concavité
     */
    private int[] getConcavityMask(Piece piece) {
        return ConcavityMask.getConcavityMask(piece, this.orderedPieces);
    }

    /**
     * Résout le placement des pieces recursivement
     *
     * @param index L'index de la soccerball.piece a résoudre
     * @return true si la solution a été trouvée, false si aucune reponse n'est possible.
     * @throws Exception Probleme d'index
     */
    private boolean solve(int index) {
        boolean found = false;
        int[] concavityArray;
        boolean rotated;
        Piece piece;
        int retry = 0;
        int firstElement = 0;

        while (!found) {
            piece = getNextAvailablePiece(connections[index].length, retry);
            if (piece == null) {
                return false;
            }

            this.addOrderedPiece(piece, connections[index]);

            concavityArray = getConcavityMask(piece);

            if (retry > 0) {
                if (piece.getElement() == firstElement || !piece.rotateToMatchConcavity(concavityArray, this.clockwise)) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    continue;
                }
            } else {
                rotated = piece.rotateToMatchConcavity(concavityArray, this.clockwise);
                if (!rotated) {
                    this.restoreLastOrderedPiece();
                    retry++;
                    firstElement = piece.getElement();
                    continue;
                }
            }

            if ((index == connections.length - 1) || solve(index + 1)) {
                found = true;
            } else {
                this.restoreLastOrderedPiece();
                retry++;
                firstElement = piece.getElement();
            }
        }
        return true;
    }

    /**
     * Imprime le resultat de chaque soccerball.piece dans la console
     */
    private void printOrderedPieces() {
        for (Piece orderedPiece : orderedPieces) {
            orderedPiece.printPiece();
        }
    }

    /**
     * Renvoie la prochaine soccerball.piece ordonnée avec le nombre de cotés entrée et le nombre de pieces a sauter.
     *
     * @param sides Nombre de cotés de la soccerball.piece souhaitée
     * @param skip  Nombre de pieces à sauter
     * @return La prochaine soccerball.piece ordonée si trouvée, null si aucune n'a été trouvée
     */
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

    /**
     * Ajoute une soccerball.piece ordonée et la retire des pieces disponibles
     *
     * @param piece                             Piece a ajouter
     * @param connections                       Les connections de cette nouvelle soccerball.piece
     * @throws ConnectionArraySizeException     Le tableau de connections n'est pas compatible avec la piece
     */
    private void addOrderedPiece(Piece piece, int[] connections) {
        int id = this.availablePieces.indexOf(piece);

        piece.setConnections(connections);
        piece.setPosition(orderedPieces.size() + 1);

        orderedPieces.add(piece);
        availablePieces.remove(id);
    }

    /**
     * Getter des pieces ordonnées
     *
     * @return La liste des pieces ordonnées
     */
    public LinkedList<Piece> getOrderedPieces() {
        return orderedPieces;
    }

    /**
     * Deplace la derniere soccerball.piece ordonée dans la liste des soccerball.piece
     * disponible dans l'ordre et reinitialise son orientation et ces connections
     */
    private void restoreLastOrderedPiece() {
        Piece piece = this.orderedPieces.getLast();
        try {
            piece.setPosition(0);
            piece.setOrientation(0, this.clockwise);
            piece.setConnections(null);
        } catch (OrientationException e) {
            return;
        }

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
}
