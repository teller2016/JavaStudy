package loopexample;

public class Q5 {

	public static void main(String[] args) {
		int lines = 7;
		int star = 1;
		
		for(int i=1; i<=lines; i++) {
			var line = "";
			int blank = (lines-star)/2;
			
			for(int a=0;a<blank;a++)
				line += " ";
			for(int b=0;b<star;b++)
				line += "*";
			for(int a=0;a<blank;a++)
				line += " ";
			
			if(i<=lines/2) {
				star += 2;
			}
			else {
				star -= 2;
			}
			
			System.out.println(line);
		}
	}

}
