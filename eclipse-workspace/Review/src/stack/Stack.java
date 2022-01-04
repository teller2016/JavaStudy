package stack;

class Node{
	int val;
	Node next;
	
	public Node(int val, Node next) {
		this.val = val;
		this.next = next;
	}
}

public class Stack {
	
	Node cur = null;
	
	public void push(int val) {
		this.cur = new Node(val, this.cur);
	}
	
	public int pop() {
		if(this.cur == null) {
			System.out.println("null¿Ã¥Ÿ");
			return 0;
		}
		int curVal = this.cur.val;
		this.cur = this.cur.next;
		return curVal;
	}
	
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.pop();
		stack.push(12);
		stack.push(11);
		stack.push(11);
		stack.push(11);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
