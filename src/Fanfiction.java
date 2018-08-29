import java.util.*;
import java.io.*;

public class Fanfiction {
    static int n , t , m , cnt;
    static String[] dict;
    static boolean[] skip;
    static String book;
    static int[] cost;
    static HashSet<Integer>[] subs;
    static int mod = 1000000009;
    static HashMap<Integer , Integer> ind;
    static Node[] nodes = new Node[2501];
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        FastScanner in = new FastScanner(System.in);//new FileInputStream(new File("Fanfiction.in")));
        //PrintWriter out = new PrintWriter(new File("Fanfiction.out"));
        n = in.nextInt();
        t = in.nextInt();
        dict = new String[n];
        subs = new HashSet[n];
        skip  = new boolean[n];
        for(int i = 0;i < n; i++){
            dict[i] = in.next();
            subs[i] = new HashSet<Integer>();
            for(int j = 1; j < dict[i].length(); j++){
                long hsh = 0;
                for(int k = j; k < dict[i].length(); k++){
                    hsh *= 26;
                    hsh += dict[i].charAt(k) - 'a' + 1;
                    hsh %= mod;
                    subs[i].add((int)hsh);
                }
            }
        }
        for(int i = 0;i < n; i++){
            if(skip[i])continue;
            long hsh = 0;
            for(int j = 0;j < dict[i].length(); j++){
                hsh *= 26;
                hsh += dict[i].charAt(j) - 'a' + 1;
                hsh %= mod;
            }
            for(int j = 0;j < n; j++){
                if(subs[j].contains((int)hsh))skip[j] = true;
            }
        }
        for(int i = 0;i < n; i++){
            if(!skip[i]) System.out.print(dict[i] + " ");
        }
        book = in.next();
        m = book.length();
        cost = new int[m];
        ind.put(0 , 0);
        for(int i = 0;i < m ;i++){
            cost[i] = in.nextInt();
        }
        cnt = 1;
        nodes[0] = new Node();
        for(int i = 0; i < n; i++){
            if(!skip[i]) add(0 , dict[i] , 0);
        }
        dfs(0 , "");
        book = in.next();
        m = book.length();
        cost = new int[m];
        for(int i = 0;i < m; i++){
            cost[i] = in.nextInt();
        }
        dp = new int[n][m];
        for(int i = 0;i < n; i++){for(int j =0;j < m; j++){dp[i][j] = -1;}}
        System.out.println(dp(0 , 0));
    }
    static int dp(int node , int ind){
        if(dp[node][ind] > -1)return dp[node][ind];
        for(int i = 0;i < 26; i++){
            if(book.charAt(ind) - 'a' == i){

            }
        }
    }
    static class Node{
        int[] chil;
        int bk;
        boolean term;
        public Node(){
            chil = new int[26];
        }
    }
    public static void dfs(int ind , String word) {
        int hsh = 0;
        int mult = 1;
        for(int i = word.length() - 1; i > 0; i--){
            hsh += (mult* (word.charAt(i) - 'a' + 1))%mod;
            mult *= 27;
            mult %= mod;
            if(Fanfiction.ind.containsKey(hsh)){
                nodes[ind].bk = Fanfiction.ind.get(hsh);
            }
        }
    }
    public static void add(int ind , String word , int prvhash){
        prvhash *= 27;
        prvhash += word.charAt(0) - 'a' + 1;
        prvhash %= mod;
        int first = word.charAt(0) - 'a';
        word = word.substring(1);
        if(nodes[ind].chil[first] == 0){
            nodes[cnt] = new Node();
            nodes[ind].chil[first] = cnt;
            Fanfiction.ind.put(prvhash , cnt);
            cnt++;
            if(word.length() > 0) add(cnt - 1 , word , prvhash);
            else nodes[cnt].term = true;
        }
        else{
            if(word.length() > 0) add(nodes[ind].chil[first] , word , prvhash);
            else nodes[nodes[ind].chil[first]].term = true;
        }
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

