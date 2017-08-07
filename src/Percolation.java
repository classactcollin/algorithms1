

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] planeArray;
    private int N;



    private WeightedQuickUnionUF wquuf;
    private WeightedQuickUnionUF wquuf_1;

    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException("n <=0 not allowed");
        }

            planeArray = new boolean[((n*n)+1)];
        N=n;
        wquuf =new WeightedQuickUnionUF(n*n+2);
        wquuf_1 =new WeightedQuickUnionUF(n*n+1);
    }
    public void open(int row, int col){
        if(!isOpen(row,col)) {

            int localIndex = getNodeIndex(row, col);


            if (row <= 0 || row > N) {
                throw new IllegalArgumentException("row " + row + " is not between 1 and " + N);
            }
            if (col <= 0 || col > N) {
                throw new IllegalArgumentException("col " + col + " is not between 1 and " + N);
            }
            int openNode = getNodeIndex(row, col);
            if (row == 1) {
                wquuf.union(openNode, 0);
                wquuf_1.union(openNode, 0);
            }


            int upIndex, leftIndex, rightIndex, downIndex;
            try {

                upIndex = getNodeIndex(row, col - 1);
                if (isOpen(row, col - 1)) {

                    wquuf.union(openNode, upIndex);
                    wquuf_1.union(openNode, upIndex);
                }
            } catch (IllegalArgumentException e) {

            }
            try {

                leftIndex = getNodeIndex(row - 1, col);
                if (isOpen(row - 1, col)) {
                    wquuf.union(openNode, leftIndex);
                    wquuf_1.union(openNode, leftIndex);
                }
            } catch (IllegalArgumentException e) {

            }
            try {

                rightIndex = getNodeIndex(row + 1, col);
                if (isOpen(row + 1, col)) {
                    wquuf.union(openNode, rightIndex);
                    wquuf_1.union(openNode, rightIndex);
                }
            } catch (IllegalArgumentException e) {

            }

            try {
                downIndex = getNodeIndex(row, col + 1);
                if (isOpen(row, col + 1)) {
                    wquuf.union(openNode, downIndex);
                    wquuf_1.union(openNode, downIndex);
                }
            } catch (IllegalArgumentException e) {

            }

            if (row == N) {

                wquuf.union(openNode, N * N + 1);

            }


            planeArray[localIndex] = true;
        }
    }
    public boolean isOpen(int row, int col){

        if (row <= 0 || row > N) {
            throw new IllegalArgumentException("row " + row + " is not between 0 and " + (N-1));
        }
        if (col <= 0 || col > N) {
            throw new IllegalArgumentException("col " + col + " is not between 0 and " + (N-1));
        }
        return planeArray[getNodeIndex(row,col)];}
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > N) {
            throw new IllegalArgumentException("row " + row + " is not between 1 and " + N);
        }
        if (col <= 0 || col > N) {
            throw new IllegalArgumentException("col " + col + " is not between 0 and " + (N-1));
        }
        return wquuf_1.connected(0, getNodeIndex(row,col));}
    public     int numberOfOpenSites(){
        int openCount=0;
        for(boolean bool:planeArray){
            if (bool)
                openCount++;

        }
        return openCount;}       // number of open sites
    public boolean percolates(){

        return wquuf.connected(0,N*N+1);}              // does the system percolate?


    private void validate(int i, int j){

        if (i <= 0 || j<=0 || i > N || j > N) {
            throw new IllegalArgumentException("index " + i + "or "+ j+ " is not between 0 and " + (N-1));
        }

    }
    private int getNodeIndex(int i, int j){
        validate(i,j);
        return i+(N*(j-1));
    }



    public static void main(String[] args){
        int n =6;

        Percolation perk = new Percolation(n);
        for(int i=1;i<=6;i++) {
            perk.open(i, 1);
            System.out.println("Perk?: "+ perk.percolates());

        }
        System.out.println("Is 6,6 full?: "+ perk.isFull(6,6));
        perk.open(6,6);
        System.out.println("Is 6,6 full?: "+ perk.isFull(6,6));

        for(int i=1;i<=5;i++) {
            perk.open(i, 6);
            System.out.println("Perk?: "+ perk.percolates());

        }
        System.out.println("Is 6,6 full?: "+ perk.isFull(6,6));









    }

}