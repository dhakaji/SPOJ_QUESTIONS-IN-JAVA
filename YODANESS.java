import java.io.*;
import java.util.*;
import java.util.*;
class Algorithms{
	static int count=0;
	static String []temp;
	public static int inversionCount(String []arr,HashMap<String,Integer> priority){
		temp=new String[arr.length];
		count = 0;
		mergeSort(arr,priority,0,arr.length-1);
		return count ;
	}
	public static void mergeSort(String []arr,HashMap<String,Integer> priority,int start,int end){
		if(start<end){
			int mid = start + (end-start)/2;
			mergeSort(arr,priority,start,mid);
			mergeSort(arr,priority,mid+1,end);
			merge(arr,priority,start,mid,end);
		}
	}
	private static void merge(String []arr,HashMap<String,Integer> priority,int start ,int mid ,int end){
		for(int i=start;i<=end;i++){
			temp[i]=arr[i];
		}
		int i = start ;
		int j = mid+1 ;
		int k = start ;
		while(i<=mid && j<=end){
			if(priority.get(temp[i])<=priority.get(temp[j])){
				arr[k]=temp[i];
				i++;
			}
			else {
				arr[k]=temp[j];
				j++;
				count+=mid-i+1;
			}
			k++;
		}
		while(i<=mid){
			arr[k]=temp[i];
			i++;
			k++;
		}
		while(j<=end){
			arr[k]=temp[j];
			j++;
			k++;
		}
	}

}
class DhakaJi{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int  t = sc.nextInt();
		StringBuilder sb=new StringBuilder("");
		while(t-- >0){
			int n = sc.nextInt();
			sc.nextLine();
			String st[]=sc.nextLine().split(" ");
			String []or=sc.nextLine().split(" ");
			HashMap<String,Integer> hm=new HashMap<>();
			for(int i=0;i<n;i++){
				hm.put(or[i],i+1);
			}
			int count = Algorithms.inversionCount(st,hm) ;
			// for(int i=0;i<n;i++){
			// 	int x = hm.get(st[i]);
			// 	for(int j=i+1;j<n;j++){
			// 		int y = hm.get(st[j]);
			// 		if(x>y)
			// 			count++;
			// 	}
			// }
			sb.append(count+"\n");
		}
		System.out.println(sb);
	}
}