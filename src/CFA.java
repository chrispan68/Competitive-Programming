import java.util.*;
import java.io.*;

public class CFA {
    static int n , m;
    static Voter[] v;
    static int[] cnt;
    public static void main(String[] args) throws Exception {
        FastScanner in = new FastScanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        v = new Voter[n];
        cnt = new int[m];
        for(int i = 0;i < n; i++){
            v[i] = new Voter(in.nextInt() - 1 , in.nextInt());
            cnt[v[i].choice]++;
        }
        Arrays.sort(v, new Comparator<Voter>() {
            @Override
            public int compare(Voter o1, Voter o2) {
                return Long.compare(o1.bribe , o2.bribe);
            }
        });
        int nm;
        long min = Long.MAX_VALUE / 2;
        long cur;
        for(int i = 1; i <= n; i++){
            reset();
            cur = 0;
            nm = i - cnt[0];
            for(int j = 0;j < n; j++){
                if(v[j].choice != 0 && cnt[v[j].choice] >= i){
                    v[j].chosen = true;
                    cur += v[j].bribe;
                    nm--;
                    cnt[v[j].choice]--;
                }
            }
            if(nm < 0)continue;
            int nd = 0;
            while(nm > 0 && nd < n){
                if(v[nd].choice != 0 && !v[nd].chosen){
                    cur += v[nd].bribe;
                    nm--;
                }
                nd++;
            }
            min = Math.min(min , cur);
        }
        System.out.println(min);
    }
    static void reset(){
        cnt = new int[m];
        for(int i = 0;i < n; i++) {
            v[i].chosen = false;
            cnt[v[i].choice]++;
        }
    }
    static class Voter{
        int choice;
        long bribe;
        boolean chosen;
        public Voter(int a, long b){
            choice = a;
            bribe = b;
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
