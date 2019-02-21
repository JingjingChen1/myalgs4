package com.chenjingj.algs4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n,int trials) {
        validate(n, trials);
        double[] openthresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            openthresholds[i] = (double)percolation.numberOfOpenSites()/(n*n);
        }
        mean = StdStats.mean(openthresholds);
        stddev = StdStats.stddev(openthresholds);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(trials);
        confidenceHi=mean+1.96 * stddev / Math.sqrt(trials);
    }

    public double mean() {
        return mean;
    }
    public double stddev() {
        return stddev ;
    }
    public double confidenceLo() {
        return confidenceLo;
    }
    public double confidenceHi() {
        return confidenceHi;
    }


    private void validate(int n,int trails) {
        if (n <= 0 || trails <= 0)
            throw new IllegalArgumentException("n or trails should be >0");
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        PercolationStats percoSta = new PercolationStats(n,trails);
        StdOut.println("mean                    = "+percoSta.mean());
        StdOut.println("stddev                  = "+percoSta.stddev());
        StdOut.println("95% confidence interval = [" + percoSta.confidenceLo() + ", " + percoSta.confidenceHi() + "]");
    }
}
