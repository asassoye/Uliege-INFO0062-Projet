class Hexagon extends Polygon {

    Hexagon(int type, int[] concavity) throws Exception {
        super(type, concavity);

        if (concavity == null || concavity.length != 6) {
            throw new Exception("Hexagon concavity array must have 6 elements");
        }

        if (type < 1 || type > 10) {
            throw new Exception("Hexagon type must have a value between 11 and 14");
        }
    }
}