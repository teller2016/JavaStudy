package remind;

public class BeginnerLevel extends PlayerLevel {

	@Override
	public void run() {
		System.out.println("초보 달리기");
	}

	@Override
	public void jump() {
		System.out.println("초보 점프");
	}

	@Override
	public void turn() {
		System.out.println("초보 돌기");
	}

}
