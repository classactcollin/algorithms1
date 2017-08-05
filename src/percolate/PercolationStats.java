package percolate;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private double[] arrayOut;
    private int totalSpots;
    private int totalTrials;
    public PercolationStats(int n, int trials){
        arrayOut = new double[trials];
        totalSpots=n*n;
        int trial = 0;
        totalTrials = trials;
        int row;
        int col;
        while(trial < trials) {
            Percolation perk = new Percolation(n);

            while (!perk.percolates()) {

                row=StdRandom.uniform(n)+1;
                col=StdRandom.uniform(n)+1;
                perk.open(row,col);
            }
            arrayOut[trial] = (double) perk.numberOfOpenSites()/totalSpots;
            trial++;
        }

    }
    public double mean(){
        return StdStats.mean(arrayOut);


    }
    public double stddev(){
        return StdStats.stddev(arrayOut);
    }
    public double confidenceLo(){

        return mean()-1.96*stddev()/Math.sqrt(totalTrials);

    }
    public double confidenceHi(){
        return mean()+1.96*stddev()/Math.sqrt(totalTrials);
    }

    public static void main(String[] args){
        int gridSize=0;
        int trials = 0;

        try {
            gridSize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {

            System.out.println("Number format exception "+e);
        }

        try {
            trials = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Number format exception "+e);
        }



        PercolationStats stats = new PercolationStats(gridSize,trials);

        System.out.println("mean                    = "+ stats.mean());
        System.out.println("stddev                  = "+ stats.stddev());
        System.out.println("95% confidence interval = ["+stats.confidenceLo()+", "+stats.confidenceHi()+"]");
        System.out.printf("");


    }       // test client (described below)
}