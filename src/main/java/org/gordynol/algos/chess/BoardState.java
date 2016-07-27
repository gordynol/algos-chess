package org.gordynol.algos.chess;

import java.util.Arrays;

public class BoardState {
    final StateType[] state;
    final Figure[] figuresPlacement;
    private final int m, n;

    public BoardState(int m, int n) {
        this.m = m;
        this.n = n;

        state = new StateType[m * n];
        Arrays.fill(state, StateType.VACANT);
        figuresPlacement = new Figure[m * n];
    }

    public BoardState(BoardState p1, BoardState p2) {
        this(p1.m, p1.n);

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                int pos = position(x, y);
                state[pos] = merge(p1.state[pos], p2.state[pos]);
                figuresPlacement[pos] = p1.figuresPlacement[pos] != null ? p1.figuresPlacement[pos] : p2.figuresPlacement[pos];
            }
        }
    }

    public static BoardState emptyBoardPlacement(int m, int n) {
        return new BoardState(m, n);
    }

    public static BoardState boardWithSingleFigure(int m, int n, BoardPosition p, Figure f) {
        return new BoardState(m, n).addInternal(p, f);
    }

    private StateType merge(StateType p1, StateType p2) {
        switch (p1) {
            case FIGURE:
                return StateType.FIGURE;
            case IN_REACH:
                return (p2 == StateType.FIGURE ? StateType.FIGURE : StateType.IN_REACH);
            case VACANT:
                return p2;
            default:
                throw new IllegalArgumentException("Unknown type: " + p1);
        }
    }

    private BoardState addInternal(BoardPosition p, Figure f) {
        figuresPlacement[position(p)] = f;
        state[position(p)] = StateType.FIGURE;

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                BoardPosition anotherPos = new BoardPosition(x, y);
                if (p.equals(anotherPos)) {
                    state[position(p)] = StateType.FIGURE;
                } else {
                    state[position(anotherPos)] = f.threatens(p, anotherPos) ? StateType.IN_REACH : StateType.VACANT;
                }
            }
        }

        return this;
    }

    private int position(BoardPosition p) {
        return position(p.getX(), p.getY());
    }

    public boolean overlaps(BoardState anotherPlacement) {
        return overlaps(anotherPlacement.state) || anotherPlacement.overlaps(state);
    }

    private boolean overlaps(StateType[] thatPlacement) {
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (state[position(x, y)] == StateType.FIGURE && thatPlacement[position(x, y)] != StateType.VACANT) {
                    return true;
                }
            }
        }
        return false;
    }

    public BoardState add(BoardState thatPlacement) {
        if (overlaps(thatPlacement)) {
            throw new IllegalArgumentException("Figure can't be added");
        }

        return new BoardState(this, thatPlacement);

    }

    private int position(int x, int y) {
        return x * n + y;
    }

    public boolean isOrderedFigurePlacement(Figure f, BoardPosition p) {
        return (noSimilarFiguresOnBoard(f) || getLastFigurePosition(f) < position(p));
    }

    private int getLastFigurePosition(Figure f) {
        for (int x = m - 1; x >= 0; x--) {
            for (int y = n - 1; y >= 0; y--) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                StateType posPlacement = state[position(x, y)];
                sb.append(posPlacement == StateType.FIGURE ? toText(figuresPlacement[position(x, y)]) : toText(posPlacement));
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

    private char toText(StateType stateType) {
        switch (stateType) {
            case IN_REACH:
                return '+';
            case VACANT:
                return '-';
            default:
                throw new IllegalArgumentException("Bad type: " + stateType);
        }
    }

    enum StateType {
        FIGURE, VACANT, IN_REACH;
    }
}
