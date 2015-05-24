package monitoring.core;

public class Message {
	
	private ThreadMonitor originThread;
	private String message;
	private Boolean isUp;
	public ThreadMonitor getOriginThread() {
		return originThread;
	}
	public void setOriginThread(ThreadMonitor originThread) {
		this.originThread = originThread;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getIsUp() {
		return isUp;
	}
	public void setIsUp(Boolean isUp) {
		this.isUp = isUp;
	}

}
