    import java.io.*;
    import java.util.*;
    import java.math.*;
    @SuppressWarnings("unchecked")
    class Graph{
        
        public int n ;
        private static ArrayList<Integer> []adjList = new ArrayList[10001];
        private static int [][]dp = new int[10001][15];
        private static int []level =new int[10001];

        public Graph(){
            // this.n= n ;

            // adjList = new ArrayList[n] ;        
            for(int i = 0 ; i < 10001 ; i++) adjList[i] = new ArrayList<>() ;               
            // dp = new int[n][14] ;
            // level = new int[n] ;
        }
        public void preProcess(){
            // Random rand = new Random();
            // int root =  rand.nextInt(n);
            dfs(0,-1) ;            
            for(int j = 1 ; j< 15 ;j++ ){
                for(int i=0;i<n ;i++){
                    dp[i][j] = -1 ;
                    if(dp[i][j-1]!=-1){
                        dp[i][j] = dp[dp[i][j-1]][j-1] ;
                    }
                }
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<5;j++){
                    // printf("%d ",dp[i][j]);
                    System.out.print(dp[i][j]+" ");
                }
                // printf("\n");
                System.out.println();
            }
        }
        public int lca(int u,int v){
            if( level[u] > level[v] ){
                u ^= v ;  v ^= u ;  u ^= v ;
            }
            int d = level[v] - level[u] ;
            if(d>0){
                int ln = log2(d);
                for(int i=ln ;i>=0 ;i--)
                    if((d & (1<<i)) > 0) v = dp[v][i] ;                                 
            }            
            if(u == v) return u ;
            int ln = log2(level[u]);
            for(int i = ln;i>= 0;i--){
                if(dp[u][i] != dp[v][i]){
                    u = dp[u][i] ;
                    v = dp[v][i] ;
                }
            }
            return dp[u][0] ;
        }

        private void dfs(int vertex , int par ){
            dp[vertex][0] = par ;
            for(int child : adjList[vertex]){
                if(child == par ){
                    continue ;
                }
                level[child] = level[vertex]+1;
                dfs(child,vertex) ;
            }
        }

        public void addEdge(int u,int v){
            adjList[u].add(v);
            adjList[v].add(u);
        }
        public int distance(int u ,int v){
            int lca = lca(u,v) ;
            int dis =  level[u]+level[v]-2*level[lca]+1 ;
            // System.out.println(lca +" "+level[u]+" "+level[v]+" "+level[lca]);
            return dis ;
        }        
        public int kthNode(int u ,int v ,int k){
            int lca = lca(u,v) ;
            int dis = level[u]+level[v]-2*level[lca]+1 ;
            // System.out.println(dis);
            if(k>dis)
                return -1 ;
            int dis_u = level[u]-level[lca]+1 ;
            // System.out.println(dis_u);
            if(k==0)
                return u ;
            if(k==dis)
                return v ;
            if(k==dis_u){
                // System.out.println("k == dis_u");
                return lca ;
            }
            if(k < dis_u){
                // System.out.println("k < dis_u");
                return kthParent(u,k) ;            
            }
            else{
                // System.out.println(dis+" "+k);
                return kthParent(v,dis-k) ;
            }
        }
        public int kthParent(int u,int k){
            int ln = log2(k);
            for(int i=ln ;i>=0;i--){
                int p = (1<<i) ;
                if(p <= k){
                    k-=p ;
                    u = dp[u][i] ;
                }
            }
            return u ;
        }
        public int log2(int n){
            if(n <= 0) throw new IllegalArgumentException();
            return 31 - Integer.numberOfLeadingZeros(n);
        }
    }
    @SuppressWarnings("unchecked")
    class DhakaJi {

        InputStream is;
        PrintWriter out;
        
        long mod = (long)(1e9 + 7), inf = (long)(3e18);
        
        void solve() {
            //SZ = sieve(); //SZ = 1000001;
            Graph g = new Graph() ;
            // for(int i = 0 ; i < 10001 ; i++) adjList[i] = new ArrayList<>() ;               
            int t = ni() ;
            StringBuilder sb = new StringBuilder("");
            while(t-- >0){
                int n = ni() ;
                g.n = n ;            
                for(int i=0;i<n-1;i++){
                    int u = ni()-1 ;
                    int v = ni()-1 ;
                    int w = ni() ;
                    g.addEdge(u,v);                
                }
                g.preProcess();   
              // gets(s);
              // while(gets(s)) {
              //  if(s[1] == 'O') break;
              //  vs = parse(s);
              //  a = atoi(vs[1].c_str());
              //  b = atoi(vs[2].c_str());
              //  LCA = getLCA(a,b);
              //  assert(LCA >= 1 && LCA <= n);
              //  if(vs[0] == "DIST") {
              //   if(LCA == a || LCA == b) res = abs(d[a] - d[b]);
              //   else res = d[a] + d[b] - 2 * d[LCA];
              //  }
              //  else if(vs[0] == "KTH") {
              //   k = atoi(vs[3].c_str());
              //   res = getKth(a,b,k,LCA);
              //  }
              //  else assert(0);
              //  printf("%lld\n",res);
              // }         
                while(true){
                    String type = ns() ;
                    if(type.charAt(1)=='O')  break ;
                    if(type.charAt(2)=='S'){
                        int u = ni()-1 ;
                        int v = ni()-1 ;
                        sb.append(g.distance(u,v)+"\n");
                    }
                    else if(type.charAt(1)=='T'){
                        int u = ni()-1 ;
                        int v = ni()-1 ;
                        int k = ni() ;
                        sb.append((1+g.kthNode(u,v,k))+"\n");
                    }
                    else {
                        break ;
                    }
                }
                sb.append("\n");
            }
            out.println(sb);
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