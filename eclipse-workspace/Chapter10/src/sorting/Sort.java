package sorting;

public interface Sort {
	void ascending(int[] arr);
	void descending(int[] arr);
	
	default void description() {
		System.out.println("���ڸ� �����ϴ� �˰��� �Դϴ�");
	}
}
