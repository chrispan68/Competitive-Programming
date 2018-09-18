package Trash;

import java.util.*;
import java.io.*;

public class Fanfiction {
    static int n , t , sz , m;
    static String word;
    static int[][] dp;
    static int[]fail , cost;
    static int[][] chil , trans;
    static boolean[] term;
    public static void main(String[] args) throws Exception {
        FastScanner in = new FastScanner(System.in);
        n = in.nextInt();
        t = in.nextInt();
        sz = 1;
        fail = new int[2501];
        chil = new int[2501][t];
        trans = new int[2501][t];
        term = new boolean[2501];
        for(int i = 0;i < n; i++){
            add(in.next());
        }
        word = in.next();
        m = word.length();
        cost = new int[m];
        for(int i = 0;i < m ;i++){
            cost[i] = in.nextInt();
        }
        LinkedList<Integer> bfs = new LinkedList<Integer>();
        fail[0] = -1;
        bfs.add(0);
        while(!bfs.isEmpty()){
            int ind = bfs.removeFirst();
            for(int i = 0;i < t; i++){
                int cur = fail[ind];
                int nex = chil[ind][i];
                while(cur >= 0 && chil[cur][i] == 0)cur = fail[cur];
                if(nex == 0){
                    if (cur == -1) trans[ind][i] = 0;
                    else trans[ind][i] = chil[cur][i];
                }
                else {
                    trans[ind][i] = chil[ind][i];
                    if (cur == -1) fail[nex] = 0;
                    else fail[nex] = chil[cur][i];
                    if (term[fail[nex]]) term[nex] = true;
                    bfs.add(chil[ind][i]);
                }
            }
        }
        dp = new int[sz][m+1];
        for(int i = 0;i < sz; i++){
            for(int j = 0;j < m; j++){
                dp[i][j] = Integer.MAX_VALUE/2;
            }
        }
        for(int i = m-1; i >= 0; i--){
            for(int j = 0;j < sz; j++){
                if(term[j])continue;
                for(int k = 0;k < t; k++){
                    if(!term[trans[j][k]])
                    dp[j][i] = Math.min(dp[j][i] , (word.charAt(i) -'a' != k ? cost[i] : 0) + dp[trans[j][k]][i+1]);
                }
            }
        }
        System.out.println(dp[0][0] >= Integer.MAX_VALUE/2 ? -1 : dp[0][0]);
    }
    static void add(String s){
        int cur = 0;
        char[] str = s.toCharArray();
        for(char c : str){
            if(chil[cur][c-'a'] == 0){
                chil[cur][c-'a'] = sz++;
            }
            cur = chil[cur][c-'a'];
        }
        term[cur] = true;
    }

    static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public FastScanner(InputStream stream) {
            this.stream = stream;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) return -1;
            }
            return buf[curChar++];
        }

        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        boolean isEndline(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String next() {
            int c = read();
            while (isSpaceChar(c)) c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public String nextLine() {
            int c = read();
            while (isEndline(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndline(c));
            return res.toString();
        }
    }
}
