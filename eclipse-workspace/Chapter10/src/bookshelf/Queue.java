package bookshelf;

public interface Queue {
	void enQueue(String title);	//�迭 �� �������� �߰�
	String deQueue();	//�迭 �� ó�� �׸� ��ȯ
	int getSize();	//Queue�� �ִ� ���� ��ȯ
}
