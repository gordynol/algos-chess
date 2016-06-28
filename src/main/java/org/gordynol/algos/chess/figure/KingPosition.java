package org.gordynol.algos.chess.figure;

import com.google.common.base.Preconditions;
import org.gordynol.algos.chess.BoardPosition;

public class KingPosition implements FigurePosition {
    private final BoardPosition figurePosition;
    public KingPosition(BoardPosition position) {
        this.figurePosition = Preconditions.checkNotNull(position);
    }


    @Override
    public boolean threatens(BoardPosition pos) {
        return figurePosition.relativeTo(pos).unilateralDistance() == 1;
    }
}
