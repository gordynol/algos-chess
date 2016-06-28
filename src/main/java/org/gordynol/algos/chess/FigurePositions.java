package org.gordynol.algos.chess;

import org.gordynol.algos.chess.figure.*;

public class FigurePositions {
    public static FigurePosition positionOf(Figure figure, BoardPosition position) {
        switch (figure) {
            case BISHOP:
                return new BishopPosition(position);
            case KING:
                return new KingPosition(position);
            case KNIGHT:
                return new KnightPosition(position);
            case QUEEN:
                return new QueenPosition(position);
            case ROOK:
                return new RookPosition(position);
            default:
                throw new IllegalArgumentException();
        }
    }
}
