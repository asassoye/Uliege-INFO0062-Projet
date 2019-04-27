class Hexagon extends Piece {

    public final static int SIDES = 6;

    Hexagon(int type, int[] concavity) throws Exception {
        super(type, concavity);

        if (concavity.length != SIDES) {
            throw new Exception("Hexagon concavity array must have 6 elements");
        }

        if (type < 1 || type > 10) {
            throw new Exception("Hexagon element must have a value between 11 and 14");
        }
    }
}