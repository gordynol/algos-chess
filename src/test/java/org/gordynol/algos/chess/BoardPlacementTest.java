package org.gordynol.algos.chess;

import com.google.common.base.Joiner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.gordynol.algos.chess.BoardPlacement;
import org.gordynol.algos.chess.BoardPosition;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.gordynol.algos.chess.BoardPlacement.boardWithSingleFigure;
import static org.gordynol.algos.chess.BoardPlacement.emptyBoardPlacement;
import static org.gordynol.algos.chess.Figure.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BoardPlacementTest {

    @Test
    public void twoEmptyPlacementsDoNotOverlap() {
        BoardPlacement p1 = emptyBoardPlacement(3, 4);
        BoardPlacement p2 = emptyBoardPlacement(3, 4);

        assertThat(p1.overlaps(p2)).isFalse();
    }

    @Test
    @Parameters({"0, 0, 1, 1",
            "1, 1, 1, 2",
            "1, 1, 2, 1",
            "1, 1, 1, 0",
            "1, 1, 0, 1"})
    public void kingsOverlapInImmediateDistance(int x1, int y1, int x2, int y2) {
        BoardPlacement p1 = boardWithSingleFigure(3, 4, new BoardPosition(x1, y1), KING);
        BoardPlacement p2 = boardWithSingleFigure(3, 4, new BoardPosition(x2, y2), KING);
        assertThat(p1.overlaps(p2)).isTrue();
    }

    @Test
    @Parameters({"0, 0, 1, 2",
            "0, 0, 2, 2"})
    public void kingsDoNotOverlapIfFarAway(int x1, int y1, int x2, int y2) {
        BoardPlacement p1 = boardWithSingleFigure(3, 4, new BoardPosition(x1, y1), KING);
        BoardPlacement p2 = boardWithSingleFigure(3, 4, new BoardPosition(x2, y2), KING);
        assertThat(p1.overlaps(p2)).isFalse();
    }

    @Test
    public void kingAndKnightOverlap() {
        BoardPlacement p1 = boardWithSingleFigure(5, 5, new BoardPosition(0, 0), KING);
        BoardPlacement p2 = boardWithSingleFigure(5, 5, new BoardPosition(4, 4), KNIGHT);

        assertThat(p1.add(p2).toString())
                .isEqualTo(Joiner.on(System.lineSeparator()).join("K+---", "++---", "---+-", "--+--", "----N", ""));

    }

    @Test
    public void kingOnEdgePlacement() {
        assertThat(boardWithSingleFigure(5, 5, new BoardPosition(0, 0), KING).toString())
                .isEqualTo(Joiner.on(System.lineSeparator()).join("K+---", "++---", "-----", "-----", "-----", ""));
    }

    @Test
    public void kingInCenterPlacement() {
        assertThat(boardWithSingleFigure(5, 5, new BoardPosition(2, 2), KING).toString())
                .isEqualTo(Joiner.on(System.lineSeparator()).join("-----", "-+++-", "-+K+-", "-+++-", "-----", ""));
    }

    @Test
    public void queenInCenterPlacement() {
        assertThat(boardWithSingleFigure(5, 5, new BoardPosition(2, 2), QUEEN).toString())
                .isEqualTo(Joiner.on(System.lineSeparator()).join("+-+-+", "-+++-", "++Q++", "-+++-", "+-+-+", ""));
    }

    @Test
    public void bishopInCenterPlacement() {
        assertThat(boardWithSingleFigure(5, 5, new BoardPosition(2, 2), BISHOP).toString())
                .isEqualTo(Joiner.on(System.lineSeparator()).join("+---+", "-+-+-", "--B--", "-+-+-", "+---+", ""));
    }

    @Test
    public void rookInCenterPlacement() {
        assertThat(boardWithSingleFigure(5, 5, new BoardPosition(2, 2), ROOK).toString())
                .isEqualTo(Joiner.on(System.lineSeparator()).join("--+--", "--+--", "++R++", "--+--", "--+--", ""));
    }

    @Test
    public void knightInCenterPlacement() {
        assertThat(boardWithSingleFigure(5, 5, new BoardPosition(2, 2), KNIGHT).toString())
                .isEqualTo(Joiner.on(System.lineSeparator()).join("-+-+-", "+---+", "--N--", "+---+", "-+-+-", ""));
    }
}