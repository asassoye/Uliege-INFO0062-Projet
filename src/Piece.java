import java.util.LinkedList;

abstract class Piece {
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
        return rotateConcavity(true);
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
        System.out.println("Position " + this.position + " - Element " + this.element + " - Orientation " + this.orientation);
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

    public LinkedList<Integer> getConcavity() {
        return concavity;
    }

    public void setConcavity(LinkedList<Integer> concavity) {
        this.concavity = concavity;
    }

    public int[] getConnections() {
        return connections;
    }

    public void setConnections(int[] connections) {
        this.connections = connections;
    }

    public boolean rotateToMatchConcavity(int[] concavityArray) {
        for (int i = 0; i < concavityArray.length; ++i) {
            int concavity = concavityArray[i];
            if (concavity != 0 && this.concavity.get(i).shortValue() != concavity) {
                while (this.concavity.get(i).shortValue() != concavity) {
                    if (!this.rotateConcavity()) {
                        return false;
                    }
                }
                i = 0;
            }
        }
        return true;
    }

    public boolean rotateToMatchConcavity(int[] concavityArray, boolean retry) {
        if (retry) {
            if (!this.rotateConcavity()) {
                return false;
            }
            return rotateToMatchConcavity(concavityArray);
        } else {
            return rotateToMatchConcavity(concavityArray);
        }

    }
}