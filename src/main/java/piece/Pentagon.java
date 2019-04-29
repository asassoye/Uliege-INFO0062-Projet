package piece;

public class Pentagon extends Piece {

    public final static int SIDES = 5;

    public Pentagon(int type, int[] concavity) throws Exception {
        super(type, concavity);
        if (this.concavity.size() != SIDES) {
            throw new Exception("piece.Pentagon concavity array must have 5 elements");
        }

        if (this.element < 11 || this.element > 14) {
            throw new Exception("piece.Pentagon element must have a value between 11 and 14");
        }
    }
}