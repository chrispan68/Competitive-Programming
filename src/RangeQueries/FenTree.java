package RangeQueries;

import java.util.*;
import java.io.*;

public class FenTree {
    int n;
    long[] bit;

    public FenTree(int a) {
        n = a;
        bit = new long[n + 1];
    }

    public void inc(int ind, long val) {
        ind++;
        while (ind <= n) {
            bit[ind] += val;
            ind += ind & (-ind);
        }
    }

    public long sum(int l, int r) {
        return sum(r) - sum(l - 1);
    }

    public long sum(int hi) {
        if(hi < 0)return 0;
        hi++;
        long ret = 0;
        while (hi > 0) {
            ret += bit[hi];
            hi -= hi & (-hi);
        }
        return ret;
    }
}