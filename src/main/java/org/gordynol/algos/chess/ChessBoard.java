package org.gordynol.algos.chess;

import java.util.stream.Stream;

import static org.gordynol.algos.chess.BoardPlacement.emptyBoardPlacement;

public class ChessBoard {
    final int m;
    final int n;

    private final BoardPlacement placement;

    public ChessBoard(int m, int n) {
        this.m = m;
        this.n = n;

        placement = emptyBoardPlacement(m, n);
    }

    ChessBoard(int m, int n, BoardPlacement placement) {
        this.m = m;
        this.n = n;
        this.placement = placement;
    }

    public Stream<BoardPosition> positionsForFigure(Figure figure) {
        return positionsStream()
                .filter(p -> canPutFigure(p, figure));
    }

    private boolean canPutFigure(BoardPosition p, Figure figure) {
        BoardPlacement figurePlacement = BoardPlacement.boardWithSingleFigure(m, n, p, figure);
        return placement.isOrderedFigurePlacement(figure, p) && !placement.overlaps(figurePlacement);
    }

    private Stream<BoardPosition> positionsStream() {
        return Stream.iterate(new BoardPosition(0, 0),
                p -> ((p.getY() == n - 1) ? new BoardPosition(p.getX() + 1, 0) : new BoardPosition(p.getX(), p.getY() + 1)))
                .limit(m * n);
    }

    public ChessBoard addFigure(Figure figure, BoardPosition position) {
        BoardPlacement figurePlacement = BoardPlacement.boardWithSingleFigure(m, n, position, figure);
        return new ChessBoard(m, n, placement.add(figurePlacement));
    }
}
