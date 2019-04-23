import java.util.LinkedList;

class PolygonFactory {
    private LinkedList<Polygon> pieces = new LinkedList<>();

    PolygonFactory() {
        int nb = 0;
        Polygon polygon = null;

        for (int i = 0; i < Data.ELEMENTS_SIDES.length; ++i) {
            for (int j = 0; j < Data.NB_ELEMENTS[i]; ++j) {
                if (Data.ELEMENTS_SIDES[i].length == 5) {
                    this.pieces.addLast(new Pentagon(Data.ELEMENTS_SIDES[i]));
                } else if (Data.ELEMENTS_SIDES[i].length == 6) {
                    this.pieces.addLast(new Hexagon(Data.ELEMENTS_SIDES[i]));
                }
            }
        }
    }
}