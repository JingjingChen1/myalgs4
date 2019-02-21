package com.chenjingj.algs4;

public class WeightedQuickUnionUF_cjj {
    private int[] id; //该点连接的父节点
    private int count; //number of components
    private int[] size; //以该点为根的树的结点总数

    public WeightedQuickUnionUF_cjj(int N){

        id =new int[N];
        size=new int[N];
        for(int i=0;i<N;i++) {
            id[i] = i; //初始连通分量都是自己
            size[i]=1; //初始化结点数均为1
        }
        count=N;
    }

    public void union(int p,int q){
        if(find(p) !=find(q)) {
            if(size[p]<size[q]) {
                id[find(p)] = find(q);
                size[q]+=size[p];
            }
            else {
                id[find(q)] = find(p);
                size[p]+=size[q];
            }
            count--;
        }
    }
    public int find(int p){
        //返回连通分量
        while(id[p]!=p)
            p=id[p];
        return p;
    }
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }
    public int count(){
        return count;
    }

    private void validate(int p){

    }

}
