import java.util.*;
//import java.util.*;
class Spoj{	
	static class Edge implements Comparable<Edge>{
		int src,dest,weight;
		Edge(int s,int d,int w){
			src=s;
			dest=d;
			weight=w;
		}
		public int compareTo(Edge e){
			return this.weight-e.weight ;
		}
	}	
	public static long KuruskalMST(Edge []edge,int v){
		Edge []result=new Edge[v];
		// for(int i=0;i<v;i++)
		// 	result[i]=new Edge();
		Arrays.sort(edge);
		DisjointSet<Integer> ds=new DisjointSet<>();
		for(int i=1;i<=v;i++)
			ds.makeSet(i);
		int i=0,index=0;
		long mstweight=0 ;
		while(index<v-1 && i<edge.length){
			Edge e=edge[i++];			
			int parent1=ds.findSet(e.src);
			int parent2=ds.findSet(e.dest);
			if(parent1!=parent2){
				ds.union(e.src,e.dest);
				result[index++]=e;
				mstweight+=e.weight ;
			}
		}
		return mstweight ;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		Edge edge[]=new Edge[m];				
		for(int i=0;i<m;i++){
			int u=sc.nextInt();
			int v=sc.nextInt();
			int w=sc.nextInt();
			//adjList[u].add(new Node(v,w));
			edge[i]=new Edge(u,v,w);
		}
		long minWeight=KuruskalMST(edge,n);
		System.out.println(minWeight);
	}
	static class DisjointSet<T>{
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
			if(root1.rank>=root2.rank){				// Union by Rank :- The node which has higher rank will												
				if(root1.rank==root2.rank)			//				    become parent of node with lower rank
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
			node.parent=findSet(node.parent);  	// Path compression in each findSet operation 
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
}
