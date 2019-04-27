import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SoccerBallTest {
    @Test
    public void solve() {
        SoccerBall soccerBall = new SoccerBall();

        try {
            if (!soccerBall.solve()) {
                System.out.println("No solution found.");
            } else {
                for (Piece orderedPiece : soccerBall.getOrderedPieces()) {
                    int[] concavityArray = SoccerBall.getConcavityArray(orderedPiece, soccerBall.getOrderedPieces());

                    for (int i = 0; i < concavityArray.length; ++i) {
                        assertEquals(
                                concavityArray[i],
                                orderedPiece.getConcavity().get(i),
                                String.format(
                                        "[Position %d - Element %d - Orientation %d] n'est pas placé correctement. " +
                                                "La concavité devrait être %s mais est %s",
                                        orderedPiece.getPosition(),
                                        orderedPiece.getElement(),
                                        orderedPiece.getOrientation(),
                                        Arrays.toString(concavityArray),
                                        orderedPiece.getConcavity().toString()));
                    }
                }
            }
        } catch (Exception e) {
            assertThrows(e);
            fail("Exception " + e.getMessage());
        }

    }

    private void assertThrows(Exception e) {
    }
}
