import java.util.LinkedList;

public class PieceCollection extends LinkedList<Piece> {
    public int numberOf(Class pieceClass) {
        int result = 0;
        for (int i = 0; i < this.size(); ++i) {
            if (this.get(i).getClass() == pieceClass) {
                ++result;
            }
        }
        return result;
    }
}
