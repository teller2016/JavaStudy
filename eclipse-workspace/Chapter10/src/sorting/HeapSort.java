package sorting;
public class HeapSort implements Sort{

	@Override
	public void ascending(int[] arr) {
		System.out.println("HeapSort ascending");
	}

	@Override
	public void descending(int[] arr) {
		System.out.println("HeapSort descending");		
	}
	
	@Override
	public void description() {
		System.out.println("HeapSort를 사용해");
		Sort.super.description();
	}
}