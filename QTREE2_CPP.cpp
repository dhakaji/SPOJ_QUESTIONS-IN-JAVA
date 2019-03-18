#include <bits/stdc++.h> 
#include <cstdio>
#include <climits>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;
void preProcess();
int lca(int ,int );
void dfs(int ,int );
int distance(int ,int );
int kthNode(int ,int ,int );
int kthParent(int ,int );
int log2(int );
const int INF = INT_MAX;
const int MAX = 1<<14;
const int LOG = 14;
vector<int> adjList[MAX+1];
int dp[MAX+1][LOG+1];
int level[MAX+1];
int n;
void preProcess(){
    dfs(0,-1) ;
    // for(int i=0;i<n;i++){
    //     for(int j=0;j<5;j++){
    //         printf("%d ",dp[i][j]);
    //     }
    //     printf("\n");
    // }
    for(int j=1;j<15;j++){
        for(int i=0;i<n;i++){
            dp[i][j] = -1 ;            
            if(dp[i][j-1]!=-1){
                dp[i][j] = dp[dp[i][j-1]][j-1] ;
            }
        }
    }
    // for(int i=0;i<n;i++){
    //     for(int j=0;j<5;j++){
    //         printf("%d ",dp[i][j]);
    //     }
    //     printf("\n");
    // }
}
int lca(int u,int v){
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

void dfs(int vertex , int par ){
    dp[vertex][0] = par ;    
    int size = adjList[vertex].size();
    for(int i=0;i<size;i++){
        int child = adjList[vertex][i];
        if(child==par)
            continue ;
        level[child] = level[vertex]+1;
        dfs(child,vertex) ;    
    }
}
int distance(int u ,int v){
    int l = lca(u,v) ;
    int dis =  level[u]+level[v]-2*level[l]+1 ;
    // System.out.println(lca +" "+level[u]+" "+level[v]+" "+level[lca]);
    return dis ;
}
int kthNode(int u ,int v ,int k){
    int l = lca(u,v) ;    
    int dis_u = level[u]-level[l]+1 ;    
    if(k <= dis_u){
        return kthParent(u,k) ;            
    }
    else{
        return kthParent(v,dis-k) ;
    }
}
int kthParent(int u,int k){
    int ln = log2(k);
    // printf("%d",ln);
    for(int i=ln ;i>=0;i--){
        int p = (1<<i) ;
        // printf("%d",p);
        if(p <= k){
            k-=p ;
            u = dp[u][i] ;
        }
    }
    return u ;
}
int log2(int n){    
    // return 31 - Integer.numberOfLeadingZeros(n);
    return log(n)/log(2) ;
    
}

int main(){
    int t ;
    char query[8];
    scanf("%d",&t);
    while(t--){
        // int n ;
        scanf("%d",&n);
        for(int i=0;i<n;i++){
            adjList[i].clear();
            level[i]=0;
        }
        for(int i=0;i<n-1;i++){
            int u,v,w ;
            scanf("%d",&u);
            scanf("%d",&v);
            scanf("%d",&w);
            adjList[u-1].push_back(v-1);
            adjList[v-1].push_back(u-1);
        }
        preProcess();
        int st,en,k,l ;
        while(scanf("%s", query)==1) {
            if(query[1]=='O') break;
            scanf("%d%d", &st, &en);
            l = lca(st, en);
            if(query[1]=='I') printf("%d\n", level[u]+level[v]-2*level[l]+1 ;);
            else if(query[1]=='T') {
                scanf("%d", &k);
                // if(lvl[st]-lvl[u]+1 >= k) v = find(st, lvl[st]-k+1);
                // else v = find(en, 2*lvl[u]+k-lvl[st]-1);
                printf("%d\n", kthNode(st-1,en-1,k)+1);
            }
        }
            // printf("%d\n",res);
        
        printf("\n");
    }

    return 0 ;
}