import java.util.*;
class Rmq{

	private long [][]sparse;
	private int row ,col ;
	public Rmq(long []arr){		
		row = arr.length ;
		col = log2(row)+1 ;
		sparse = new long[row][col] ;
		preProcess(arr);
	}

	private void preProcess(long []arr){
		for(int i=0;i < row ;i++){
			sparse[i][0]=arr[i];
		}
		for(int j=1; j < col ;j++){
			for(int i=0;(i+(1<<j)-1) < row ;i++){
				sparse[i][j] = operation(sparse[i][j-1],sparse[i+(1<<(j-1))][j-1]) ;
			}
		}
	}

	public long query(long []arr ,int left , int right ){
		int range = right - left + 1; 
		int k = log2(range) ;
		return operation(sparse[left][k],sparse[left+range-(1<<k)][k]) ;
	}	

	private int log2(int data){
        return (int )(Math.log(data)/Math.log(2)) ;
    }
    private long operation(long x,long y){
    	return Math.min(x,y) ;
    }
}
class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder sb =  new StringBuilder("");
		int k = 1;
		while(t-- >0){
			int n = sc.nextInt();
			int q = sc.nextInt() ;
			long []arr =  new long[n] ;
			for(int i=0;i<n;i++)
				arr[i] = sc.nextLong() ;
			Rmq rmq = new Rmq(arr) ;		
			sb.append("Scenario #"+k+":\n");
			// System.out.println(Arrays.toString(arr));
			// rmq.printf();
			for(int i=0;i<q;i++){
				int a = sc.nextInt()-1 ;
				int b = sc.nextInt()-1 ;
				sb.append(rmq.query(arr,a,b)+"\n");
				// System.out.println(rmq.query(arr,a,b));
			}	
			k++;
		}
		System.out.println(sb);
	}
}