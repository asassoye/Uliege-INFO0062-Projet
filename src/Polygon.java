abstract class Polygon {
    private int position;
    private int rotation;
    protected int type;
    protected int[] concavity;
    private int[] connections;

    Polygon(int type, int[] concavity) {
        this.position = 0;
        this.rotation = 0;
        this.concavity = concavity;
        this.type = type;
        this.connections = null;
    }

    public boolean rotate() {
        int tmp = this.concavity[0];
        int j = 1;

        while (j < this.concavity.length) {
            this.concavity[j - 1] = this.concavity[j];
            j++;
        }
        this.concavity[j - 1] = tmp;

        if (this.isRotable()) {
            this.rotation++;
            return true;
        } else {
            this.rotation = 0;
            return false;
        }

    }


    private boolean isRotable() {
        System.out.println(this.concavity.length);

        return this.rotation < this.concavity.length - 1;
    }

    int getType() {
        return this.type;
    }

    void printPiece() {
        System.out.println("Position " + this.position + " - Element " + this.type + " - Orientation " + this.rotation);
    }

    int[] getConnections() {
        return connections;
    }

    void setConnections(int[] connections) {
        this.connections = connections;
    }

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }
}