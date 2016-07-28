package org.gordynol.algos.chess;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.util.Statistics;

import java.util.Collection;

import static org.gordynol.algos.chess.Figure.*;

@Fork(1)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class ChessPlacementProblemSolverMicroBenchmark {
    @Test
    public void benchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-Xmx256m")
                .build();

        Collection<RunResult> results = new Runner(opt).run();

        Statistics basic1KOperations = getStatsForTest(results, "basicOperations1K");
        Statistics chessS24F1 = getStatsForTest(results, "chessS24F1");
        Statistics chessS48F1 = getStatsForTest(results, "chessS48F1");
        Statistics chessS24F2 = getStatsForTest(results, "chessS24F2");
        Statistics board6x9_K2Q1B1R1N1 = getStatsForTest(results, "board6x9_K2Q1B1R1N1");

        SoftAssertions assertions = new SoftAssertions();

        double threshold = 0.5;

        assertions.assertThat(basic1KOperations.getStandardDeviation() / basic1KOperations.getMean())
                .describedAs("Benchmark is precise enough").isLessThan(0.3);

        assertions.assertThat(chessS24F1.getMean() / basic1KOperations.getMean())
                .as("Board 24 squares, 1 figure. Should take no more than 24 1K operations").isLessThan(24 / threshold);

        assertions.assertThat(chessS48F1.getMean() / chessS24F1.getMean())
                .as("Doubling board square is quadratic").isGreaterThan(0.25 * threshold);

        assertions.assertThat(chessS24F1.getMean() / chessS24F2.getMean())
                .as("Adding one more figure has factorial complexity").isGreaterThan((1. / 24) * threshold);

        assertions.assertThat(board6x9_K2Q1B1R1N1.getMean())
                .as("Acceptance scenario must run less than 1 minute (+- threshold)").isLessThan(60 / threshold);

        assertions.assertAll();
    }

    private Statistics getStatsForTest(Collection<RunResult> results, String testName) {
        return results.stream()
                .filter(r -> r.getParams().getBenchmark().contains(testName))
                .map(r -> r.getPrimaryResult().getStatistics())
                .findFirst().get();
    }

    @Benchmark
    public void chessS24F1() {
        new ChessPlacementProblemSolver(6, 4)
                .withFigures(ROOK, 1)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessS48F1() {
        new ChessPlacementProblemSolver(8, 8)
                .withFigures(ROOK, 1)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessS24F2() {
        new ChessPlacementProblemSolver(6, 4)
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void basicOperations1K() {
        long r = 0;
        for (long i = 0; i < 1000; i++) {
            r += i;
        }
        Assertions.assertThat(r).isEqualTo(499500L);
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
