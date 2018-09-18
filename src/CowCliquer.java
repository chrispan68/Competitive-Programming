import java.io.*;
import java.util.*;

public class CowCliquer extends Grader {
    // Copy these exactly:
    public static void main(String args[]) throws IOException { new CowCliquer().run(); }

    @Override
    public Grader newInstance() { return new CowCliquer(); }

    static class tm{
        int val, time;
        public tm(int a , int b){
            val = a;
            time = b;
        }
    }
    // Implement these:


    static HashMap<String , Integer> names = new HashMap<String , Integer>();
    static int n = 0;
    static int mx = 200000;
    static ArrayList<tm>[] par = new ArrayList[mx];
    static ArrayList<tm>[] sz = new ArrayList[mx];
    static Comparator<tm> cmp = new Comparator<tm>() {
        @Override
        public int compare(tm o1, tm o2) {
            return o1.time - o2.time;
        }
    };
    @Override
    public void addFriend(String cow1, String cow2, int timestamp) {
        int a , b;
        if(!names.containsKey(cow1)){
            a = n;
            par[n] = new ArrayList<tm>();
            sz[n] = new ArrayList<tm>();
            sz[n].add(new tm(1 , -1));
            names.put(cow1 , n++);
        } else {
            a = names.get(cow1);
        }
        if(!names.containsKey(cow2)){
            b = n;
            par[n] = new ArrayList<tm>();
            sz[n] = new ArrayList<tm>();
            sz[n].add(new tm(1 , -1));
            names.put(cow2 , n++);
        } else {
            b = names.get(cow2);
        }
        tm tmp = new tm(0 , timestamp);
        a = head(a , timestamp);
        b = head(b , timestamp);
        if(a == b)return;
        int sza = flor(Collections.binarySearch(sz[a] , tmp , cmp));
        sza = sza < 0 ? 0 : sz[a].get(sza).val;
        int szb = flor(Collections.binarySearch(sz[b] , tmp , cmp));
        szb = szb < 0 ? 0 : sz[b].get(szb).val;
        if(szb > sza){
            int temp = a;
            a = b;
            b = temp;
        }
        sz[a].add(new tm(sza + szb , timestamp));
        par[b].add(new tm(a , timestamp));

    }
    public int head(int ind , int tm){
        tm tmp = new tm(0 , tm);
        int k = 1;
        while(true){
            k =  flor(Collections.binarySearch(par[ind] , tmp , cmp));
            if(k < 0) return ind;
            ind = par[ind].get(k).val;
        }
    }
    public int flor(int ind){
        if(ind < 0)ind = -1*ind - 2;
        return ind;
    }
    @Override
    public boolean checkFriend(String cow1, String cow2, int timestamp) {
        int a , b;
        if(!names.containsKey(cow1)){
            a = n;
            par[n] = new ArrayList<tm>();
            names.put(cow1 , n++);
        } else {
            a = names.get(cow1);
        }
        if(!names.containsKey(cow2)){
            b = n;
            par[n] = new ArrayList<tm>();
            names.put(cow2 , n++);
        } else {
            b = names.get(cow2);
        }
        a = head(a , timestamp);
        b = head(b , timestamp);
        return a == b;
    }
    @Override
    public int getNumberOfFriends(String cow, int timestamp) {
        int a;
        if(!names.containsKey(cow)){
            a = n;
            names.put(cow, n++);
        } else {
            a = names.get(cow);
        }
        a = head(a , timestamp);
        return sz[a].get(flor(Collections.binarySearch(sz[a] , new tm(0 , timestamp) , cmp))).val;
    }
}
