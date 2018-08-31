import java.util.*;
import java.io.*;

public class CFB {
    static int n;
    static FastScanner in;
    public static void main(String[] args) throws Exception {
        in = new FastScanner(System.in);
        n = in.nextInt();
        int a = query(1);
        if(((a % 2)+2) % 2== 1){
            System.out.println("! -1");
            return;
        }
        if(a == 0){
            System.out.println("! 1");
            return;
        }
        bins(1 , n/2 + 1 , a , -a);
    }
    static void bins(int lo , int hi , int losign , int hisign){
        int mid = (lo + hi)/2;
        int k = query(mid);
        if(k == 0){
            System.out.println("! " + mid);
            System.exit(0);
        }
        if(k > 0 && losign > 0 || k < 0 && losign < 0){
            bins(mid , hi , k , hisign);
        }
        else {
            bins(lo , mid , losign , k);
        }

    }
    static int query(int i){
        System.out.println("? " + i);
        int a1 = in.nextInt();
        System.out.println("? " + ((i + (n/2)) > n ? (i - (n/2)) : (i + (n/2))));
        int a2 = in.nextInt();
        return a1 - a2;
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
