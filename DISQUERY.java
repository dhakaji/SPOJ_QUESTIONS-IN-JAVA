import java.io.*;
import java.util.*;
import java.math.*;
@SuppressWarnings("unchecked")
class DhakaJi {

    InputStream is;
    PrintWriter out;
    
    long mod = (long)(1e9 + 7), inf = (long)(3e18);
    
    void solve() {
        //SZ = sieve(); //SZ = 1000001;
        int n = ni() ;
        Graph g = new Graph(n) ;
        for(int i=0;i<n-1;i++){
            int u = ni() ;
            int v = ni() ;
            int w = ni() ;
            g.addEdge(u,v,w) ;            
        }
        g.preProcess() ;
        int k = ni() ;
        for(int i=0;i<k;i++){
            int u = ni()-1 ;
            int v = ni()-1 ;
            int min = 
            sb.append()
        }
        
    }
    
    //--------- Extra Template -----------
    boolean isPrime(long n) {
        if(n <= 1)  return false;
        if(n <= 3)   return true;
        if(n%2 == 0 || n%3 == 0)    return false;
        for(long i = 5; i * i <= n; i += 6) {
            if(n % i == 0 || n % (i + 2) == 0)  return false;
        }
        return true;
    }

    int P[], S[], SZ, NP = 15485900;
    boolean isP[];    

    int log2(int data){
        return (int )(Math.log(data)/Math.log(2)) ;
    }
    int sieve() {
        int i, j, n = NP; 
        isP = new boolean[n]; 
        S = new int[n];
        for(i = 3; i < n; i += 2) {
            isP[i] = true;
            S[i-1] = 2;
            S[i] = i;
        }
        isP[2] = true;
        S[1] = 1;   //(UD)
        for(i = 3; i * i <= n; i += 2) {
            if( !isP[i] )   continue;
            for(j = i * i; j < n; j += i) {
                isP[j] = false;
                if(S[j] == j)   S[j] = i;
            }
        }
        P = new int[n];
        P[0] = 2;
        j = 1;
        for(i = 3; i < n; i += 2) {
            if ( isP[i] )   P[j++] = i;
        }
        P = Arrays.copyOf(P, j);
        return j;
    }
    long gcd(long a,long b){
        while (a!=0 && b!=0) 
        {            
            if(a>b)
               a%=b;
            else
                b%=a;
        }
        return a+b;
    }
    long mp(long b, long e, long mod) {
        b %= mod;
        long r = 1;
        while(e > 0) {
            if((e & 1) == 1) {
                r *= b; r %= mod;
            }
            b *= b; b %= mod;
            e >>= 1;
        }
        return r;
    }

    //---------- I/O Template ----------
    
    public static void main(String[] args) throws Exception { new DhakaJi().run(); }
    void run() throws Exception { 
        is = System.in; 
        out = new PrintWriter(System.out);
        // long s = System.currentTimeMillis();
        solve();
        out.flush();
        // tr((System.currentTimeMillis()-s)/1000+"s");
    }
    
    byte input[] = new byte[1024];
    int len = 0, ptr = 0;
    
    int readByte() { 
        if(ptr >= len) { ptr = 0; 
            try { len = is.read(input); } 
            catch(IOException e) { throw new InputMismatchException(); } 
            if(len <= 0) { return -1; } 
        } return input[ptr++];
    }
    boolean isSpaceChar(int c) { return !( c >= 33 && c <= 126 ); }
    int skip() { 
        int b = readByte(); 
        while(b != -1 && isSpaceChar(b)) { b = readByte(); } 
        return b;
    }
    
    char nc() { return (char)skip(); }
    String ns() { 
        int b = skip(); 
        StringBuilder sb = new StringBuilder(); 
        while(!isSpaceChar(b)) { sb.appendCodePoint(b); b = readByte(); } 
        return sb.toString();
    }
    String nLine() { 
        int b = skip(); 
        StringBuilder sb = new StringBuilder(); 
        while( !(isSpaceChar(b) && b != ' ') ) { sb.appendCodePoint(b); b = readByte(); } 
        return sb.toString();
    }
    int ni() { 
        int n = 0, b = readByte(); 
        boolean minus = false; 
        while(b != -1 && !( (b >= '0' && b <= '9') || b == '-')) { b = readByte(); } 
        if(b == '-') { minus = true; b = readByte(); } 
        if(b == -1) { return -1; }  //no input 
        while(b >= '0' && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); } 
        return minus ? -n : n;
    }
    long nl() { 
        long n = 0L;    int b = readByte(); 
        boolean minus = false; 
        while(b != -1 && !( (b >= '0' && b <= '9') || b == '-')) { b = readByte(); } 
        if(b == '-') { minus = true; b = readByte(); } 
        while(b >= '0' && b <= '9') { n = n * 10 + (b - '0'); b = readByte(); } 
        return minus ? -n : n;
    }
    double nd() { return Double.parseDouble(ns()); }
    float nf() { return Float.parseFloat(ns()); }
    int[] na(int n) { 
        int a[] = new int[n]; 
        for(int i = 0; i < n; i++) { a[i] = ni(); } 
        return a;
    }
    char[] ns(int n) { 
        char c[] = new char[n]; 
        int i, b = skip(); 
        for(i = 0; i < n; i++) { 
            if(isSpaceChar(b)) { break; } 
            c[i] = (char)b; b = readByte(); 
        } return i == n ? c : Arrays.copyOf(c,i);
    }
    private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
    private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }
}