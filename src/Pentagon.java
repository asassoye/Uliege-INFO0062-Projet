class Pentagon extends Polygon {

    Pentagon(int type, int[] concavity) throws Exception {
        super(type, concavity);
        if (this.concavity == null || this.concavity.length != 5) {
            throw new Exception("Pentagon concavity array must have 5 elements");
        }

        if (this.type < 11 || this.type > 14) {
            throw new Exception("Pentagon type must have a value between 11 and 14");
        }
    }
}