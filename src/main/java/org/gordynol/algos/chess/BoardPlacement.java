package org.gordynol.algos.chess;

import org.gordynol.algos.chess.figure.FigurePosition;

import java.util.Arrays;

public class BoardPlacement {
    private final int m, n;

    final PlacementType[] placement;
    final Figure[] figuresPlacement;

    public BoardPlacement(int m, int n) {
        this.m = m;
        this.n = n;

        placement = new PlacementType[m * n];
        Arrays.fill(placement, PlacementType.VACANT);
        figuresPlacement = new Figure[m * n];
    }

    public BoardPlacement(BoardPlacement p1, BoardPlacement p2) {
        this(p1.m, p1.n);

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                int pos = position(x, y);
                placement[pos] = merge(p1.placement[pos], p2.placement[pos]);
                figuresPlacement[pos] = p1.figuresPlacement[pos] != null ? p1.figuresPlacement[pos] : p2.figuresPlacement[pos];
            }
        }
    }

    private PlacementType merge(PlacementType p1, PlacementType p2) {
        switch (p1) {
            case FIGURE:
                return PlacementType.FIGURE;
            case IN_REACH:
                return (p2 == PlacementType.FIGURE ? PlacementType.FIGURE : PlacementType.IN_REACH);
            case VACANT:
                return p2;
            default:
                throw new IllegalArgumentException("Unknown type: " + p1);
        }
    }


    public static BoardPlacement emptyBoardPlacement(int m, int n) {
        return new BoardPlacement(m, n);
    }

    public static BoardPlacement boardWithSingleFigure(int m, int n, BoardPosition p, Figure f) {
        return new BoardPlacement(m, n).addInternal(p, f);
    }

    private BoardPlacement addInternal(BoardPosition p, Figure f) {
        figuresPlacement[position(p)] = f;
        placement[position(p)] = PlacementType.FIGURE;

        FigurePosition figurePosition = FigurePositions.positionOf(f, p);

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                BoardPosition anotherPos = new BoardPosition(x, y);
                if (p.equals(anotherPos)) {
                    placement[position(p)] = PlacementType.FIGURE;
                } else {
                    placement[position(anotherPos)] = figurePosition.threatens(anotherPos) ? PlacementType.IN_REACH : PlacementType.VACANT;
                }
            }
        }

        return this;
    }

    private int position(BoardPosition p) {
        return position(p.getX(), p.getY());
    }

    public boolean overlaps(BoardPlacement anotherPlacement) {
        return overlaps(anotherPlacement.placement) || anotherPlacement.overlaps(placement);
    }

    private boolean overlaps(PlacementType[] thatPlacement) {
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (placement[position(x, y)] == PlacementType.FIGURE && thatPlacement[position(x, y)] != PlacementType.VACANT) {
                    return true;
                }
            }
        }
        return false;
    }

    public BoardPlacement add(BoardPlacement thatPlacement) {
        if (overlaps(thatPlacement)) {
            throw new IllegalArgumentException("Figure can't be added");
        }

        return new BoardPlacement(this, thatPlacement);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                PlacementType posPlacement = placement[position(x, y)];
                sb.append(posPlacement == PlacementType.FIGURE ? toText(figuresPlacement[position(x, y)]) : toText(posPlacement));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private char toText(Figure figure) {
        switch (figure) {
            case KING:
                return 'K';
            case KNIGHT:
                return 'N';
            case BISHOP:
                return 'B';
            case QUEEN:
                return 'Q';
            case ROOK:
                return 'R';
            default:
                throw new IllegalArgumentException("Bad figure:" + figure);
        }
    }

    private char toText(PlacementType placementType) {
        switch (placementType) {
            case IN_REACH:
                return '+';
            case VACANT:
                return '-';
            default:
                throw new IllegalArgumentException("Bad type: " + placementType);
        }
    }

    private int position(int x, int y) {
        return x * n + y;
    }

    public boolean isOrderedFigurePlacement(Figure f, BoardPosition p) {
        return (noSimilarFiguresOnBoard(f) || getLastFigurePosition(f) < position(p));
    }

    private int getLastFigurePosition(Figure f) {
        for (int x = m-1; x >= 0; x--) {
            for (int y = n-1; y >=0; y--) {
                if (figuresPlacement[position(x, y)] == f) {
                    return position(x, y);
                }
            }
        }
        throw new IllegalArgumentException("No such figure: " + f);
    }

    private boolean noSimilarFiguresOnBoard(Figure f) {
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (figuresPlacement[position(x, y)] == f) {
                    return false;
                }
            }
        }
        return true;
    }
}
