import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public class Escape {

    // Before submitting, make sure the main method hasn't been changed!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("EscapeIN.txt"));

        while (br.ready()) {
            String[] data = br.readLine().split(" ");
            long N = Long.parseLong(data[0]);
            long M = Long.parseLong(data[1]);
            System.out.println(calculate(N, M));
        }
        br.close();
    }

    public static long f(long p, int k) {
        if (p <= 1) return 1;
        long pw = 1;
        for (int i = 0; i < k; i++) pw *= p;
        return pw + (pw/p)*(p-1)*(long)k;
    }

    // Fill out the body of this method
    public static long calculate(long N, long M) {
        // Let f(x) be the sum of gcd(i,x) for i from 1 to x. Then, we want to
        // calculate f(N) (mod M).

        // Lemma 1: If A and B are coprime, then f(A * B) = f(A) * f(B)
        // Proof: Note that gcd(C, A * B) = gcd(C, A) * gcd(C, B). Also, defining g(X, Y) to be
        // the smallest positive integer such that g(X, Y) = X (mod A) and g(X, Y) = Y (mod B), we
        // have that gcd(g(X, Y), A * B) = gcd(g(X, Y), A) * gcd(g(X, Y), B) = gcd(X, A) * gcd(Y, B).
        // Thus, we have that:
        // f(A) * f(B) = (gcd(1, A) + gcd(2, A) + ... + gcd(A, A)) * (gcd(1, B) + ... + gcd(B, B))
        // = gcd(g(1, 1), A * B) + gcd(g(1, 2), A * B) + ... + gcd(g(1, A), A * B) + ...
        // + gcd(g(2, 1), A * B) + ... + gcd(g(A, B), A * B). By the Chinese Remainder Theorem,
        // g(i, j), with i taken over 1 to A and j taken over 1 to B, has a unique solution from 1
        // to A * B. Thus, the desired sum runs from 1 to A * B, so is equal to f(A * B).

        // Alternatively, if this was too hard to prove, it was possible to observe by
        // printing out small values of f(N) and having a bit of luck.

        // Lemma 2: For some prime p and some integer k, we have that
        // f(p^k) = p^k + p^(k-1) * (p-1) * k
        // Proof: For the integers from 1 to p^k, we have that p^k of them
        // are divisible by 1, p^(k-1) of them are divisible by p, p^(k-2) of them
        // are divisible by p^2, and so on. Thus, since each gcd must also be a
        // prime power, we have that f(p^k) = p^k + p^(k-1) * (p-1) + p^(k-2) + (p^2 - p)
        // + ... = p^k + k * p^(k-1) * (p-1), as desired.

        // Thus, all we need to do is factorize N into powers of primes, using, say
        // the Sieve of Erastothenes, then calculate each f using Lemma 2 and multiply
        // them.
        long F = (long) Math.ceil(Math.sqrt((double)N));
        boolean [] sieve = new boolean[(int)(F+1)];
        long ret = 1;
        for (int i = 0; i < sieve.length; i++) sieve[i] = true;
        for (long i = 2; i <= F; i++) {
            if (!sieve[(int)i]) continue;
            for (long j = 2 * i; j < sieve.length; j += i) {
                sieve[(int)j] = false;
            }
            int k = 0;
            while (N % i == 0) {
                N /= i;
                k++;
            }
            ret *= f(i, k);
            ret %= M;
        }
        ret *= f(N, 1);
        ret %= M;
        return ret;
    }
}
