package scheduler;

public interface Scheduler {
	public void getNextCall();		//다음 전화 가져오는 기능
	public void sendCallToAgent();	// 상담원에게 전화 배분하는 기능
}
