package generics;

public class GenericPrinter<T extends Material> {
	private T material;

	public T getMaterial() {
		return material;
	}

	public void setMaterial(T material) {	//T 자료형 변수 material을 반환하는 제네릭 메서드
		this.material = material;
	}
	
	public String toString() {
		return material.toString();
	}
	
	public void printing() {
		material.doPrinting(); 		//상위 클래스 Material의 메서드 호출
	}
}
