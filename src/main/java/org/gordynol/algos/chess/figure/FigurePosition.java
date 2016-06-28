package org.gordynol.algos.chess.figure;

import org.gordynol.algos.chess.BoardPosition;

public interface FigurePosition {
    boolean threatens(BoardPosition pos);
}
