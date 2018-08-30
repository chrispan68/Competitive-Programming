import java.util.*;
import java.io.*;

public class Fanfiction {
    static int n , t , m , cnt;
    static String[] dict;
    static String book;
    static int[] cost;
    static Integer[] perm;
    static HashSet<Integer>words;
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
        words = new HashSet<Integer>();
        for(int i = 0;i < n; i++){
            dict[i] = in.next();
            words.add(hash(dict[i]));
        }
        book = in.next();
        m = book.length();
        cost = new int[m];
        ind = new HashMap<Integer , Integer>();
        ind.put(0 , 0);
        for(int i = 0;i < m ;i++){
            cost[i] = in.nextInt();
        }
        cnt = 1;
        nodes[0] = new Node(0);
        for(int i = 0; i < n; i++){
            add(0 , dict[i] , 0 , 0);
        }
        perm = new Integer[cnt];
        for(int i = 0;i < cnt; i++){
            perm[i] = i;
        }
        Arrays.sort(perm, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(nodes[o1] == null) return 1;
                if(nodes[o2] == null) return -1;
                return nodes[o1].dep - nodes[o2].dep;
            }
        });
        dfs(0, "");
        dp = new int[cnt][m+1];
        for(int node = 0; node < cnt; node++){
            if(nodes[node].term)dp[node][m]= Integer.MAX_VALUE/2;
        }
        for(int ind = m-1; ind >= 0; ind--){
            for(int j = 0; j < cnt; j++){
                int node = perm[j];
                if(nodes[node].term){
                    dp[node][ind] = Integer.MAX_VALUE/2;
                    continue;
                }
                int min = Integer.MAX_VALUE/2;
                int cur;
                for(int i = 0;i < t; i++){
                    cur = 0;
                    if(book.charAt(ind) - 'a' != i) cur = cost[ind];
                    int nd = node;
                    while(nd > 0 && nodes[nd].chil[i] == 0) nd = nodes[nd].bk;
                    if (nodes[nd].chil[i] == 0) min = Math.min(min , cur + dp[0][ind + 1]);
                    else min = Math.min(min , cur + dp[nodes[nd].chil[i]][ind + 1]);

                }
                dp[node][ind] = min;
            }
        }
        System.out.println(dp[0][0] >= Integer.MAX_VALUE/2 ? -1 : dp[0][0]);
    }
    static int hash(String s){
        long hsh = 0;
        for(int i = 0;i < s.length(); i++){
            hsh *= (t+1);
            hsh += s.charAt(i) - 'a' + 1;
            hsh %= mod;
        }
        return (int)hsh;
    }
    static class Node{
        int[] chil;
        int dep;
        int bk;
        boolean term;
        public Node(int dep){
            chil = new int[t];
            this.dep = dep;
        }
    }
    public static void dfs(int ind , String word) {
        long hsh = 0;
        long mult = 1;
        for(int i = word.length() - 1; i > 0; i--){
            hsh += (mult* (word.charAt(i) - 'a' + 1))%mod;
            mult *= (t+1);
            mult %= mod;
            hsh %= mod;
            if(Fanfiction.ind.containsKey((int)hsh)){
                nodes[ind].bk = Fanfiction.ind.get((int)hsh);
            }
            if(words.contains((int)hsh)){
                int a = 0;
                nodes[ind].term = true;
            }
        }
        for(int i = 0;i < t; i++){
            if(nodes[ind].chil[i] != 0){
                dfs(nodes[ind].chil[i] , word + (char)(i+'a'));
            }
        }
    }
    public static void add(int ind , String word , int prvhash , int loc){
        if(word.length() == loc){
            nodes[ind].term = true;
            return;
        }
        prvhash *= (t+1);
        prvhash += word.charAt(loc) - 'a' + 1;
        prvhash %= mod;
        int first = word.charAt(loc) - 'a';
        if(nodes[ind].chil[first] == 0){
            nodes[cnt] = new Node(loc + 1);
            nodes[ind].chil[first] = cnt;
            Fanfiction.ind.put(prvhash , cnt);
            cnt++;
            add(cnt - 1 , word , prvhash , loc + 1);

        }
        else{
            add(nodes[ind].chil[first] , word , prvhash , loc + 1);
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

