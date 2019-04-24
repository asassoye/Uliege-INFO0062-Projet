import java.util.LinkedList;

final class PolygonFactory {
    private PolygonFactory() {
    }

    static LinkedList<Polygon> createPieces() {
        LinkedList<Polygon> pieces = new LinkedList<>();

        for (int i = 0; i < Data.ELEMENTS_SIDES.length; ++i) {
            for (int j = 0; j < Data.NB_ELEMENTS[i]; ++j) {
                if (Data.ELEMENTS_SIDES[i].length == 5) {
                    pieces.addLast(new Pentagon(i + 1, Data.ELEMENTS_SIDES[i]));
                } else if (Data.ELEMENTS_SIDES[i].length == 6) {
                    pieces.addLast(new Hexagon(i + 1, Data.ELEMENTS_SIDES[i]));
                }
            }
        }

        return pieces;
    }
}