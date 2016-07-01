package org.gordynol.algos.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.gordynol.algos.chess.BoardPlacement.emptyBoardPlacement;

public class ChessBoard {
    private final int m;
    private final int n;
    private final BoardPlacement placement;
    private final Map<Figure, Map<BoardPosition, BoardPlacement>> placementMap;

    public ChessBoard(int m, int n) {
        this.m = m;
        this.n = n;

        placement = emptyBoardPlacement(m, n);
        placementMap = new HashMap<>();
        for (Figure figure : Figure.values()) {
            Map<BoardPosition, BoardPlacement> figurePlacementMap = new HashMap<>();
            positionsStream().forEach(position ->
                    figurePlacementMap.put(position, BoardPlacement.boardWithSingleFigure(m, n, position, figure)));

            placementMap.put(figure, figurePlacementMap);
        }
    }

    private ChessBoard(int m, int n, Map<Figure, Map<BoardPosition, BoardPlacement>> placementMap, BoardPlacement placement) {
        this.m = m;
        this.n = n;
        this.placementMap = placementMap;
        this.placement = placement;
    }

    public Stream<BoardPlacement> placementsForFigure(Figure figure) {
        return positionsStream()
                .filter(p -> placement.isOrderedFigurePlacement(figure, p))
                .map(p -> placementMap.get(figure).get(p))
                .filter(p -> canPutFigure(p));
    }

    private boolean canPutFigure(BoardPlacement figurePlacement) {
        return !placement.overlaps(figurePlacement);
    }

    private Stream<BoardPosition> positionsStream() {
        return Stream.iterate(new BoardPosition(0, 0),
                p -> ((p.getY() == n - 1) ? new BoardPosition(p.getX() + 1, 0) : new BoardPosition(p.getX(), p.getY() + 1)))
                .limit(m * n);
    }

    public ChessBoard add(BoardPlacement figurePlacement) {
        return new ChessBoard(m, n, placementMap, placement.add(figurePlacement));
    }
}
