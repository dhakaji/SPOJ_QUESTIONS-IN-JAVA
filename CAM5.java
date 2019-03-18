import java.io.*;
import java.util.*;
import java.math.*;
@SuppressWarnings("unchecked")
class DhakaJi {

    InputStream is;
    PrintWriter out;
    
    long mod = (long)(1e9 + 7), inf = (long)(3e18);
    // private int dfs(ArrayList<Integer> []adjList,int n){
    //     boolean []visited=new boolean[n+1];
    //     int count=0;
    //     for(int i=1;i<=n;i++){
    //         if(!visited[i]){
    //             count++;
    //             dfs(adjList,visited,i);
    //         }
    //     }
    //     return count ;
    // }
    // private void dfs(ArrayList<Integer> []adjList,boolean []visited,int vertex){
    //     visited[vertex]=true ;
    //     for( int child : adjList[vertex]){
    //         if(!visited[child]){
    //             dfs(adjList,visited,child);
    //         }
    //     }
    // }
    private int bfs(ArrayList<Integer> []adjList,int n){
        boolean visited[]=new boolean[n];
        int count=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                count++;
                bfs(adjList,visited,i);
            }
        }
        return count ;
    }
    private void bfs(ArrayList<Integer> adjList[],boolean []visited,int vertex){
        // visited[vertex]=true ;
        Queue<Integer> queue=new LinkedList<Integer>();
        // for(int child : adjList[vertex]){
        //     if(!visited[child])
        //     queue.add(child);
        // }
        queue.add(vertex);
        while(!queue.isEmpty()){
            int x=queue.poll();
            // if(visited[x])
            //     continue ;
            visited[x]=true ;
            for(int child : adjList[x]){
                if(!visited[child]){
                    queue.add(child);
                }
            }
        }



    }
    void solve() {
        //SZ = sieve(); //SZ = 1000001;
        int t=ni();
        while(t-- >0){
            int n = ni();
            int e = ni();
            ArrayList<Integer> adjList[]=new ArrayList[n];
            for(int i=0;i<n;i++)
                adjList[i]=new ArrayList<>();
            for(int i=0;i<e;i++){
                int u = ni();
                int v = ni();
                adjList[u].add(v) ;
                adjList[v].add(u) ;                
            }
            int connectedComponents=bfs(adjList,n);
            out.println(connectedComponents);
            // DisjointSet<Integer> ds=new DisjointSet<>();
            // for(int i=1;i<=n;i++)
            //     ds.makeSet(i);
            // for(int i=0;i<e;i++){
            //     ds.union(ni(),ni());
            // }
            // out.println(ds.numberOfSets());
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
        long s = System.currentTimeMillis();
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
class DisjointSet<T>{
    private HashMap<T,Node> map=new HashMap<>(); 
    private HashSet<T> allSets=new HashSet<>();
    private class Node{
        int rank;
        T data;
        Node parent;
        Node(T data){
            this.data=data;
            parent=this;
            rank=0;
        }
    }
    public void makeSet(T data){
        if(allSets.contains(data))
            return ;
        Node node=new Node(data);
        map.put(data,node);
        allSets.add(data);
    }
    public boolean isSet(T data){
        return map.containsKey(data);
    }
    public boolean union(T data1,T data2){
        if(!map.containsKey(data1) || !map.containsKey(data2))
            return false;
        Node root1=findSet(map.get(data1));
        Node root2=findSet(map.get(data2));
        if(root2.data.equals(root1.data))
            return false;
        if(root1.rank>=root2.rank){             // Union by Rank :- The node which has higher rank will                                             
            if(root1.rank==root2.rank)          //                  become parent of node with lower rank
                root1.rank+=1;
            root2.parent=root1;         
            allSets.remove(root2.data);
        }
        else{
            root1.parent=root2;
            allSets.remove(root1.data);
        }
        return true;
    }
    public int numberOfSets(){
        return allSets.size();
    }
    public boolean isRepresentative(T data){
        return allSets.contains(data);
    }
    public Iterator<T> getAllSets(){
        return allSets.iterator();
    }
    public T findSet(T data){
        return findSet(map.get(data)).data;
    }   
    private Node findSet(Node node){
        if(node==node.parent)
            return node;
        node.parent=findSet(node.parent);   // Path compression in each findSet operation 
        return node.parent ;
    }
    public String toString(){
        Iterator<T> it=getAllSets();        
        StringBuilder sb=new StringBuilder("[");
        if(it.hasNext())
            sb.append(it.next()+"");
        while(it.hasNext()){
            sb.append(","+it.next());
        }
        sb.append("]");
        return sb.toString();
    }
    /*public String toString(){
        Iterator<T> it=getAllSets();        
        if(it.hasNext())
            System.out.print("["+it.next());
        while(it.hasNext()){
            System.out.print(","+it.next());
        }
        System.out.println("]");
    }*/
}