package org.gordynol.algos.chess;

public interface FigureRelation {
    boolean threatens(BoardPosition figurePos, BoardPosition otherPos);
}
