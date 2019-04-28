package piece;

import java.util.LinkedList;

public abstract class Piece {
    private int position;
    protected int element;
    private int orientation;
    protected LinkedList<Integer> concavity = new LinkedList<>();
    private int[] connections;

    Piece(int element, int[] concavityArray) {
        this.position = 0;
        this.orientation = 0;
        for (int concavity : concavityArray) {
            this.concavity.add(concavity);
        }

        this.element = element;
        this.connections = null;
    }

    private boolean rotateConcavity() {
        return rotateConcavity(false);
    }

    private boolean rotateConcavity(boolean clockwize) {
        if (clockwize) {
            int movingPiece = this.concavity.getFirst();
            this.concavity.removeFirst();
            this.concavity.addLast(movingPiece);
        } else {
            int movingPiece = this.concavity.getLast();
            this.concavity.removeLast();
            this.concavity.addFirst(movingPiece);
        }

        if (isRotatable()) {
            this.orientation++;
            return true;
        } else {
            this.orientation = 0;
            return false;
        }
    }

    private boolean isRotatable() {
        return this.orientation < this.concavity.size();
    }

    public void printPiece() {
        System.out.printf(
                "Position %d - Element %d - Orientation %d%n",
                this.position,
                this.element,
                this.orientation
        );
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        while (this.orientation != orientation) {
            this.rotateConcavity();
        }
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public Integer[] getConcavity() {
        return this.concavity.toArray(new Integer[0]);
    }

    public int[] getConnections() {
        return connections;
    }

    public void setConnections(int[] connections) {
        this.connections = connections;
    }

    public boolean rotateToMatchConcavity(int[] concavityMask) {
        while (!utils.concavityMaskUtils.compare(this.getConcavity(), concavityMask)) {
            if (!this.rotateConcavity()) {
                return false;
            }
        }

        return true;
    }
}