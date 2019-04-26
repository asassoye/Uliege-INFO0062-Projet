abstract class Piece {
    private int position;
    private int rotation;
    protected int type;
    protected int[] concavity;
    private int[] connections;

    Piece(int type, int[] concavity) {
        this.position = 0;
        this.rotation = 0;
        this.concavity = concavity;
        this.type = type;
        this.connections = null;
    }

    private boolean rotate() {
        int tmp = this.concavity[0];
        int j = 1;

        while (j < this.concavity.length) {
            this.concavity[j - 1] = this.concavity[j];
            j++;
        }
        this.concavity[j - 1] = tmp;

        if (this.isRotatable()) {
            this.rotation++;
            return true;
        } else {
            this.rotation = 0;
            return false;
        }
    }

    private boolean isRotatable() {
        return this.rotation < this.concavity.length - 1;
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
        this.rotation = rotation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int[] getConcavity() {
        return concavity;
    }

    public void setConcavity(int[] concavity) {
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
            if (concavity != 0) {
                if (this.concavity[i] != concavity) {

                    if (!this.rotate()) {
                        return false;
                    }
                    i = 0;
                }
            }
        }
        return true;
    }
}