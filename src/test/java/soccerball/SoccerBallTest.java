package soccerball;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import soccerball.piece.Piece;
import soccerball.piece.exceptions.ConcavityException;
import soccerball.piece.exceptions.ConcavitySizeArrayException;
import soccerball.piece.exceptions.ElementException;
import soccerball.utils.ConcavityMask;
import soccerball.utils.Data;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SoccerBallTest {
    private SoccerBall soccerBall;

    public SoccerBallTest() {
        try {
            this.soccerBall = new SoccerBall(Data.ELEMENTS_SIDES, Data.NB_ELEMENTS);
        } catch (ConcavitySizeArrayException | ElementException | ConcavityException e) {
            e.printStackTrace();
        }


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
            int[] concavityArray = ConcavityMask.getConcavityMask(orderedPiece, soccerBall.getOrderedPieces());

            for (int i = 0; i < concavityArray.length; ++i) {
                try {
                    assertEquals(
                            concavityArray[i],
                            orderedPiece.getConcavity()[i],
                            String.format(
                                    "[Position %d - Element %d - Orientation %d] n'est pas placé correctement. " +
                                            "La concavité devrait être %s mais est %s",
                                    orderedPiece.getPosition(),
                                    orderedPiece.getElement(),
                                    orderedPiece.getOrientation(),
                                    Arrays.toString(concavityArray),
                                    Arrays.toString(orderedPiece.getConcavity())));
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
}
