abstract class Polygon {
    private int[] concavity;
    private int rotation;
    private int type;

    Polygon(int type, int[] concavity) {
        this.concavity = concavity;
        this.type = type;
    }

    public void rotate() {
        if(this.isRotable()){
            int tmp = this.concavity[0];
            int j = 1;

            while(j < this.concavity.length){
                this.concavity[j-1] = this.concavity[j];
                j++;
            }
            this.concavity[j] = tmp;
            this.rotation++;
        }
    }

    public boolean isRotable() {
        return this.rotation < this.concavity.length;
    }
}