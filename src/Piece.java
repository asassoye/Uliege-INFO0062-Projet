import java.util.LinkedList;

abstract class Piece {
    private int position;
    private int rotation;
    protected int type;
    protected LinkedList<Integer> concavity = new LinkedList<>();
    private int[] connections;

    Piece(int type, int[] concavityArray) {
        this.position = 0;
        this.rotation = 0;
        for (int concavity : concavityArray) {
            this.concavity.add(concavity);
        }

        this.type = type;
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
            this.rotation++;
            return true;
        } else {
            this.rotation = 0;
            return false;
        }
    }

    private boolean isRotatable() {
        return this.rotation < this.concavity.size();
    }

    public void printPiece() {
        System.out.println("Position " + this.position + " - Element " + this.type + " - Orientation " + this.rotation);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        while (this.rotation != rotation) {
            this.rotateConcavity();
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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