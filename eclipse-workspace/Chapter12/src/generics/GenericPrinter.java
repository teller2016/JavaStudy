package generics;

public class GenericPrinter<T extends Material> {
	private T material;

	public T getMaterial() {
		return material;
	}

	public void setMaterial(T material) {	//T �ڷ��� ���� material�� ��ȯ�ϴ� ���׸� �޼���
		this.material = material;
	}
	
	public String toString() {
		return material.toString();
	}
	
	public void printing() {
		material.doPrinting(); 		//���� Ŭ���� Material�� �޼��� ȣ��
	}
}
