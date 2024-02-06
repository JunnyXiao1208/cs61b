package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private double[] percolateTimes;
    private double mean;
    private double stddev;

    // 执行 T 次独立实验在 N x N 的网格上
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }

        percolateTimes = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            percolateTimes[i] = percolateTimesCalculator(p, N);
        }
        mean = StdStats.mean(percolateTimes);
        stddev = StdStats.stddev(percolateTimes);
    }

    private int percolateTimesCalculator(Percolation p, int N) {
        int openSites = 0;
        while (!p.percolates()) {
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            if (!p.isOpen(row, col)) {
                p.open(row, col);
                openSites++;
            }
        }
        return openSites;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return mean - (CONFIDENCE_95 * stddev) / Math.sqrt(percolateTimes.length);
    }

    public double confidenceHigh() {
        return mean + (CONFIDENCE_95 * stddev) / Math.sqrt(percolateTimes.length);
    }
}
