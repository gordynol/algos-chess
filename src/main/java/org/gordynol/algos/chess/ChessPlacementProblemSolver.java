package org.gordynol.algos.chess;

public class ChessPlacementProblemSolver {
    private final RemainingFigures initialSetOfFiguresFigures = new RemainingFigures();
    private final ChessBoard board;

    public ChessPlacementProblemSolver(int m, int n) {
        board = new ChessBoard(m, n);
    }


    public ChessPlacementProblemSolver withFigures(Figure aFigure, int count) {
        initialSetOfFiguresFigures.add(aFigure, count);
        return this;
    }

    public ChessPlacementProblemSolver withFigure(Figure aFigure) {
        initialSetOfFiguresFigures.add(aFigure, 1);
        return this;
    }

    public int countOfUniquePlacements() {
        if (initialSetOfFiguresFigures.isEmpty()) {
            return 0;
        } else {
            return countOfUniquePlacements(board, initialSetOfFiguresFigures);
        }
    }

    private int countOfUniquePlacements(ChessBoard board, RemainingFigures remainingFigures) {
        if (remainingFigures.isEmpty()) {
            return 1;
        } else {
            Figure next = remainingFigures.next();
            RemainingFigures remaining = remainingFigures.remaining();

            return board.placementsForFigure(next)
                    .mapToInt(nextPlacement -> countOfUniquePlacements(board.add(nextPlacement), remaining))
                    .sum();
        }
    }
}
