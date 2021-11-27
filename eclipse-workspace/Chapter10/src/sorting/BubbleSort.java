package sorting;
public class BubbleSort implements Sort{

	@Override
	public void ascending(int[] arr) {
		System.out.println("BubbleSort ascending");
	}

	@Override
	public void descending(int[] arr) {
		System.out.println("BubbleSort descending");		
	}
	
	@Override
	public void description() {
		System.out.println("BubbleSort를 사용해");
		Sort.super.description();
	}
}