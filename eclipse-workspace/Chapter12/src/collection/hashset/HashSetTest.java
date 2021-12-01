package collection.hashset;
import java.util.HashSet;
public class HashSetTest {

	public static void main(String[] args) {
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add(new String("정몰리"));
		hashSet.add(new String("정보리"));
		hashSet.add(new String("정말리"));
		hashSet.add(new String("정몰리"));
		
		System.out.println(hashSet);
	}
}
