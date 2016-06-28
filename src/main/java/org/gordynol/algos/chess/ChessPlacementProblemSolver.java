package org.gordynol.algos.chess;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ChessPlacementProblemSolver {
    private final RemainingFigures figures = new RemainingFigures();
    private final ChessBoard board;

    public ChessPlacementProblemSolver(ChessBoard board) {
        this.board = board;
    }

    public ChessPlacementProblemSolver withFigures(Figure aFigure, int count) {
        figures.add(aFigure, count);
        return this;
    }

    public ChessPlacementProblemSolver withFigure(Figure aFigure) {
        figures.add(aFigure, 1);
        return this;
    }

    public int countOfUniquePlacements() {
        if (figures.isEmpty()) {
            return 0;
        } else {
            return countOfUniquePlacements(board, figures);
        }
    }

    private int countOfUniquePlacements(ChessBoard board, RemainingFigures remainingFigures) {
        if (remainingFigures.isEmpty()) {
            return 1;
        } else {
            List<ChessBoard> uniquePlacements = new LinkedList<>();
            Figure next = remainingFigures.next();
            RemainingFigures remaining = remainingFigures.remaining();

            int sum = 0;
            for (BoardPosition nextPosition : board.positionsForFigure(next).collect(Collectors.toList())) {
                sum += countOfUniquePlacements(board.addFigure(next, nextPosition), remaining);
            }
            return sum;
        }
    }
}
