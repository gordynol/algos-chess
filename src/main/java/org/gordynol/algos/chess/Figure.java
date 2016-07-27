package org.gordynol.algos.chess;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public enum Figure implements FigureRelation {
    KING {
        @Override
        public boolean threatens(BoardPosition figurePos, BoardPosition otherPos) {
            return new BoardRelation(figurePos, otherPos).unilateralDistance() == 1;
        }
    }, QUEEN {
        @Override
        public boolean threatens(BoardPosition figurePos, BoardPosition otherPos) {
            BoardRelation relation = new BoardRelation(figurePos, otherPos);
            return relation.isDiagonal() || relation.isSameAxis();
        }
    }, BISHOP {
        @Override
        public boolean threatens(BoardPosition figurePos, BoardPosition otherPos) {
            return new BoardRelation(figurePos, otherPos).isDiagonal();
        }
    }, ROOK {
        @Override
        public boolean threatens(BoardPosition figurePos, BoardPosition otherPos) {
            return new BoardRelation(figurePos, otherPos).isSameAxis();
        }
    }, KNIGHT {
        @Override
        public boolean threatens(BoardPosition figurePos, BoardPosition otherPos) {
            BoardRelation relation = new BoardRelation(figurePos, otherPos);
            return relation.unilateralDistance() == 2 && !relation.isDiagonal() && !relation.isSameAxis();
        }
    };


    public static class BoardRelation {
        private final int x, y;

        public BoardRelation(BoardPosition pos1, BoardPosition pos2) {
            this.x = abs(pos1.getX() - pos2.getX());
            this.y = abs(pos1.getY() - pos2.getY());
        }

        public boolean isDiagonal() {
            return x == y;
        }

        public int unilateralDistance() {
            return abs(x - y) + min(x, y);
        }

        public boolean isSameAxis() {
            return x == 0 || y == 0;
        }
    }
}
