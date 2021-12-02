package stream;
import java.util.Arrays;
public class IntArrayTest {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		
		int sumVal = Arrays.stream(arr).sum();	// 배열에 저장된 값을 모두 더함
		int count = (int)Arrays.stream(arr).count();//배열의 요소 개수 반환
					//반환값이 long형이랑 int형으로 변환
		System.out.println(sumVal);
		System.out.println(count);
	}

}
