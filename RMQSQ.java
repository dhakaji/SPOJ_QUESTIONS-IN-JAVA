import java.util.*;
class Rmq{

	int [][]sparse;
	int row ,col ;
	public Rmq(int []arr){
		int n = arr.length ;
		int size = log2(n)+1 ;
		row = n ;
		col = size ;
		sparse = new int[n][size] ;
		preProcess(arr);
	}

	private void preProcess(int []arr){
		for(int i=0;i < row ;i++){
			sparse[i][0]=i;
		}
		for(int j=1; j < col ;j++){
			for(int i=0;(i+(1<<j)-1) < row ;i++){
				if(arr[sparse[i][j-1]] < arr[sparse[i+(1<<(j-1) )][j-1]]){
					sparse[i][j] = sparse[i][j-1] ;
				}
				else 
					sparse[i][j] = sparse[i+(1<<(j-1) )][j-1] ;
			}
		}
	}

	public int query(int []arr ,int left , int right ){
		int l = right - left + 1; 
		int k = log2(l) ;
		return Math.min(arr[sparse[left][k]],arr[sparse[left+l-(1<<k )][k]]) ;
	}	

	private int log2(int data){
        return (int )(Math.log(data)/Math.log(2)) ;
    }
}
class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder("");
		int []arr= new int[n] ;
		for(int i=0;i<n;i++){
			arr[i] = sc.nextInt();			
		}
		int q = sc.nextInt() ;
		Rmq rmq = new Rmq(arr);
		for(int i=0;i<q;i++){
			int left = sc.nextInt();
			int right = sc.nextInt();
			sb.append(rmq.query(arr,left,right)+"\n");
		}
		System.out.println(sb);
	}
}