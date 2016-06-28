package org.gordynol.algos.chess;

import java.util.LinkedList;

public class RemainingFigures {
    private final LinkedList<Figure> remainingFigures;

    public RemainingFigures() {
        remainingFigures = new LinkedList<>();
    }

    private RemainingFigures(LinkedList<Figure> tail) {
        remainingFigures = tail;
    }

    public void add(Figure aFigure, int count) {
        for (int i = 0; i < count; i++) {
            remainingFigures.push(aFigure);
        }
    }

    public Figure next() {
        return remainingFigures.peek();
    }

    public RemainingFigures remaining() {
        LinkedList<Figure> tail = new LinkedList<>(remainingFigures);
        tail.pop();
        return new RemainingFigures(tail);
    }

    public boolean isEmpty() {
        return remainingFigures.isEmpty();
    }
}
