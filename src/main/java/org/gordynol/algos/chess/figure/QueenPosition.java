package org.gordynol.algos.chess.figure;

import com.google.common.base.Preconditions;
import org.gordynol.algos.chess.BoardPosition;
import org.gordynol.algos.chess.BoardRelation;

public class QueenPosition implements FigurePosition {
    private final BoardPosition figurePosition;
    public QueenPosition(BoardPosition position) {
        this.figurePosition = Preconditions.checkNotNull(position);
    }

    @Override
    public boolean threatens(BoardPosition pos) {
        BoardRelation relation = figurePosition.relativeTo(pos);
        return relation.isDiagonal() || relation.isSameAxis();
    }
}
