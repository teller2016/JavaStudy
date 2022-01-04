package etc;
import java.util.ArrayList;
public class BubbleSort {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(55);
		arr.add(2);
		arr.add(13);
		int[] ar = {1,7,32,7,9,2,7,38,42};
		
		for(int i =0; i<ar.length-1; i++)
			for(int j=0;j<ar.length-1-i;j++) {
				if(ar[j]>ar[j+1]) {
					int temp = ar[j+1];
					ar[j+1] = ar[j];
					ar[j] = temp;
				}
			}
		
		for(int a : ar)
			System.out.println(a);
		
		arr.sort(null);
		System.out.println(arr);
		for(int a : arr)
			System.out.println(a);
	}

}
