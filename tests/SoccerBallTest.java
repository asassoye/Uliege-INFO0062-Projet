import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SoccerBallTest {
    private final int NUMBER_OF_PIECES = Data.CONNECTIONS.length;
    private SoccerBall soccerBall;

    public SoccerBallTest() {
        this.soccerBall = new SoccerBall();


        try {
            this.soccerBall.solve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void VerifyResult() {
        boolean failed = false;
        for (Piece orderedPiece : soccerBall.getOrderedPieces()) {
            int[] concavityArray = SoccerBall.getConcavityArray(orderedPiece, soccerBall.getOrderedPieces());


            for (int i = 0; i < concavityArray.length; ++i) {
                try {
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
                } catch (AssertionFailedError error) {
                    System.out.println(error.getMessage());
                    failed = true;
                    break;
                }


            }


        }
        if (failed) {
            fail("Resultat incorrecte");
        }
    }


    private void assertThrows(Exception e) {
    }
}