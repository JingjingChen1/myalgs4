package com.chenjingj.algs4;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int e; //为方阵边长
    private final WeightedQuickUnionUF siteUF;
    private boolean[] isOpen; //0 is not available.
    private int numberOfOpen;


    public Percolation(int n){
//表示该sites大小为n by n
        if (n <= 0) {
            throw new IllegalArgumentException("n could not be less than 1");
        }
        e=n;
        numberOfOpen =0;
        int a=n*n;
        siteUF=new WeightedQuickUnionUF(a+2);
        isOpen =new boolean[a+1];
        //将首尾两个虚点和第一排及最后一排的点连上
        for(int i=1;i<=n;i++){
            siteUF.union(0,i);
            siteUF.union(a+1,a-n+i);
        }
    }
    public int numberOfOpenSites(){
        return numberOfOpen ;
    }
    public boolean isFull(int row,int col){
        validate(row, col);
        //这个位置很容易错！不是connected就可以了，第一排一开始就是connected，但是它并没有open
        return siteUF.connected(0,xyTo1D(row,col))&&isOpen(row, col);
    }
    public boolean isOpen(int row,int col){
        validate(row, col);
        return isOpen[xyTo1D(row,col)];
    }
    public boolean percolates(){
        //容易出错，要注意n=1的情况
        if (e==1) return isOpen(1,1);
        return siteUF.connected(0,e*e+1);
    }
    public void open(int row,int col){
        //若索引有效，则打开一个site，检查四周是否有open site，若有，则建立连接
        validate(row,col);
        //注意，如果打开的是重复的site，数量不增加
        if (isOpen(row, col)) {
            return;
        }
        isOpen[xyTo1D(row,col)]=true;
        numberOfOpen++;
        if(hasSite(row-1,col) && isOpen(row-1,col) )
            siteUF.union(xyTo1D(row,col),xyTo1D(row-1,col));
        if(hasSite(row+1,col) && isOpen(row+1,col))
            siteUF.union(xyTo1D(row,col),xyTo1D(row+1,col));
        if(hasSite(row,col-1) && isOpen(row,col-1) )
            siteUF.union(xyTo1D(row,col),xyTo1D(row,col-1));
        if(hasSite(row,col+1) && isOpen(row,col+1) )
            siteUF.union(xyTo1D(row,col),xyTo1D(row,col+1));
    }

    //返回目标行列site对应的结点索引
    private int xyTo1D(int row,int col){
        return e*(row-1)+col;
    }
    private void validate(int row,int col) {
        if (row < 1 || row > e)
            throw new IllegalArgumentException("row index out of bounds");
        if (col < 1 || col > e)
            throw new IllegalArgumentException("col index out of bounds");
    }
    private boolean hasSite(int row,int col){
        if (row < 1 || row > e)
            return false ;
        if (col < 1 || col > e)
            return false ;
        return true ;
    }

}
