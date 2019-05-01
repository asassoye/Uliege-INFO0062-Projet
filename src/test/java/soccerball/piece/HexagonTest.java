package soccerball.piece;

import org.junit.jupiter.api.Test;
import soccerball.piece.exceptions.ConcavityException;
import soccerball.piece.exceptions.ConcavitySizeArrayException;
import soccerball.piece.exceptions.ElementException;
import soccerball.utils.Data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexagonTest {
    @Test
    public void RotateConcavity() {
        Integer[] initConcavity = null;
        Piece piece = null;
        try {
            for (int[] concavityArray : Data.ELEMENTS_SIDES) {
                if (concavityArray.length == Hexagon.SIDES) {
                    piece = new Hexagon(1, concavityArray);
                    initConcavity = piece.getConcavity();
                    for (int i = 1; i <= Hexagon.SIDES; ++i) {
                        boolean end = !piece.rotateConcavity(true);
                        if (!end) {
                            assertEquals(piece.getOrientation(), i);
                        } else {
                            assertEquals(piece.getOrientation(), 0);
                        }

                    }
                }
            }
            assert piece != null;
            assertArrayEquals(initConcavity, piece.getConcavity());
        } catch (ConcavityException | ElementException | ConcavitySizeArrayException e) {
            e.printStackTrace();
        }
    }
}
