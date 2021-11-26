package remind;

public class SuperLevel extends PlayerLevel {
	@Override
	public void run() {
		System.out.println("고급 달리기");
	}

	@Override
	public void jump() {
		System.out.println("고급 점프");
	}

	@Override
	public void turn() {
		System.out.println("고급 돌기");
	}
}
