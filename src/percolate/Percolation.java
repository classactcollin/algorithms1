package percolate; /**
 * Created by colli_000 on 7/21/2017.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] planeArray;
    private int N;



    private WeightedQuickUnionUF wquuf;

    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException("n <=0 not allowed");
        }

            planeArray = new boolean[((n*n)+1)];
        N=n;
        wquuf =new WeightedQuickUnionUF(n*n+2);
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
            }
            if (row == N) {
                wquuf.union(openNode, N * N + 1);
            }

            int upIndex, leftIndex, rightIndex, downIndex;
            try {

                upIndex = getNodeIndex(row, col - 1);
                if (isOpen(row, col - 1)) {

                    wquuf.union(openNode, upIndex);
                }
            } catch (IllegalArgumentException e) {

            }
            try {

                leftIndex = getNodeIndex(row - 1, col);
                if (isOpen(row - 1, col)) {
                    wquuf.union(openNode, leftIndex);
                }
            } catch (IllegalArgumentException e) {

            }
            try {

                rightIndex = getNodeIndex(row + 1, col);
                if (isOpen(row + 1, col)) {
                    wquuf.union(openNode, rightIndex);
                }
            } catch (IllegalArgumentException e) {

            }

            try {
                downIndex = getNodeIndex(row, col + 1);
                if (isOpen(row, col + 1)) {
                    wquuf.union(openNode, downIndex);
                }
            } catch (IllegalArgumentException e) {

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
        return wquuf.connected(0, getNodeIndex(row,col));}
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
        int count= 1;
        while(count<=n*n){
            System.out.print(count);

            if(count%n==0){
                System.out.println();
            }
            count++;
        }

        int row;
        int col;
        while (perk.percolates()==false){
            row= StdRandom.uniform(n)+1;
            col=StdRandom.uniform(n)+1;
            System.out.println("Opening "+row+ " "+col);
            perk.open(row,col);
            perk.isFull(row,col);
            System.out.println("PERK?: "+perk.percolates());
        }

        for(int x=1;x<=n;x++){
            for(int y=1;y<=n;y++){
                System.out.print("x: "+ x + " y: "+y +" = ");
                System.out.print(perk.getNodeIndex(x,y)+" ");
            }
            System.out.println();

        }








    }

}