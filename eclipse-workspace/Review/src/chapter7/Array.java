package chapter7;

import java.util.ArrayList;

public class Array {

	public static void main(String[] args) {
		int[] array = new int[10];
		int[] array2 = {1,2,3,4};
		
		for(int i=0; i<array2.length; i++)
			System.out.println(array2[i]);
		
		for(int i : array2)
			System.out.println(i);
		////////////////////////////////////
		ArrayList<String> li = new ArrayList<String>();
		li.add("ABC");
		li.add("BCD");
		
		for(String str : li)
			System.out.println(str);
	}

}
