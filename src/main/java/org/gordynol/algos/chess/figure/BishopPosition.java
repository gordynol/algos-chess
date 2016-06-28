package org.gordynol.algos.chess.figure;

import com.google.common.base.Preconditions;
import org.gordynol.algos.chess.BoardPosition;

public class BishopPosition implements FigurePosition {
    private final BoardPosition figurePosition;
    public BishopPosition(BoardPosition position) {
        this.figurePosition = Preconditions.checkNotNull(position);
    }

    @Override
    public boolean threatens(BoardPosition pos) {
        return figurePosition.relativeTo(pos).isDiagonal();
    }
}
