package loopexample;

public class Q4 {

	public static void main(String[] args) {
		for(int i=1;i<=4;i++) {
			var line = "";
			int blank = (7-(2*i-1))/2;
			for(int a=0;a<blank;a++)
				line += " ";
			for(int b=0;b<2*i-1;b++)
				line += "*";
			for(int a=0;a<blank;a++)
				line += " ";
			System.out.println(line);

		}
	}

}
