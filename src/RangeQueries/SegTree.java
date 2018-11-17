package RangeQueries;

import java.util.*;
import java.io.*;

public class SegTree {
    static int n;
    static int[] lo, hi;
    static long[] delta;
    static Node[] nd;
    public SegTree(int size){
        n = size;
        lo = new int[2*n];
        hi = new int[2*n];
        delta = new long[2*n];
        nd = new Node[2*n];
        //zero all nodes
        init(1, 0 , n-1);
    }
    public static void init(int ind , int l , int h){
        lo[ind] = l;
        hi[ind] = h;
        if(l != h){
            int mid = (l+h)/2;
            init(ind*2 , l , mid);
            init(ind*2 + 1, mid+1 , h);
        }
    }
    public static void update(int l , int h , long v){
        update(1 , l, h, v);
    }
    public static void update(int ind , int l, int h, long v){
        if(lo[ind] > h || hi[ind] < l)return;
        if(lo[ind] > l && hi[ind] < h){ delta[ind] += v; return;}
        push(ind);
        update(2*ind , l , h , v);
        update(2*ind+1 , l , h , v);
        pull(ind);
    }
    public static void push(int ind){
        delta[2*ind] += delta[ind];
        delta[2*ind + 1] += delta[ind];
        delta[ind] = 0;
    }
    public static void pull(int ind){
        //do some operation on node[2*ind] and node[2*ind]
    }
    public static long query(int l , int h){
        return query(1 , l , h);
    }
    public static long query(int ind , int l , int h){
        if(lo[ind] > h || hi[ind] < l)return 0;
        if(lo[ind] > l && hi[ind] < h){
            //actually put something here for nodes
            return 0;
        }
        push(ind);
        long lower = query(2*ind , l , h);
        long higher = query(2*ind+1 , l , h);
        pull(ind);
        return lower + higher;
    }
    static class Node {
        //inc method

        //query method
    }
}
