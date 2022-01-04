package etc;

public class Test{
	private int pri = 5;
	static int sta = 1;
	
	public int getPri() {
		return this.pri;
	}
	
	public static void main(String[] args) {
		String A = new String("a");
		String B = new String("a");
		System.out.println(A==B);
		String C = "a";
		String D = "a";
		System.out.println(C==D);
		
		String s = new String("a");
		modify(s);
		System.out.println(s);
	}
	public static void modify(String s) {
		s = "abc";
	}
	public static int add(int a, int b) {
		return a+b;
	}
}
