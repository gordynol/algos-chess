package org.gordynol.algos.chess;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static org.gordynol.algos.chess.Figure.*;

@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 3)
public class ChessPlacementProblemSolverMicroBenchmark {
    @Test
    public void benchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void chessBoardSizeN() {
        new ChessPlacementProblemSolver(2, 4)
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize2N() {
        new ChessPlacementProblemSolver(4, 4)
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize3N() {
        new ChessPlacementProblemSolver(6, 4)
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize4N() {
        new ChessPlacementProblemSolver(8, 4)
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize5N() {
        new ChessPlacementProblemSolver(10, 4)
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures2() {
        new ChessPlacementProblemSolver(6, 4)
                .withFigures(KNIGHT, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures3() {
        new ChessPlacementProblemSolver(6, 4)
                .withFigures(KNIGHT, 3)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures4() {
        new ChessPlacementProblemSolver(6, 4)
                .withFigures(KNIGHT, 4)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures5() {
        new ChessPlacementProblemSolver(6, 4)
                .withFigures(KNIGHT, 5)
                .countOfUniquePlacements();

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void board6x9_K2Q1B1R1N1() {
        new ChessPlacementProblemSolver(6, 9)
                .withFigures(KING, 2)
                .withFigure(QUEEN)
                .withFigure(BISHOP)
                .withFigure(ROOK)
                .withFigure(KNIGHT)
                .countOfUniquePlacements();
    }
}
