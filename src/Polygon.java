abstract class Polygon {
    private int[] concavity;
    private int rotation;
    private int type;

    Polygon(int type, int[] concavity) {
        this.concavity = concavity;
        this.type = type;
    }

    public void rotate() {
        //TODO Rotate
    }

    public boolean isRotable() {
        return this.rotation < this.concavity.length;
    }
}