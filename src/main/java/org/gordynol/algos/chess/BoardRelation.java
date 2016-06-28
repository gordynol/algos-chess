package org.gordynol.algos.chess;

import com.google.common.base.Preconditions;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class BoardRelation {
    private final int x, y;

    public BoardRelation(int x, int y) {
        this.x = abs(x);
        this.y = abs(y);
    }

    public boolean isDiagonal() {
        return x == y;
    }

    public int unilateralDistance() {
        return abs(x-y) + min(x, y);
    }

    public boolean isSameAxis() {
        return x == 0 || y == 0;
    }
}
