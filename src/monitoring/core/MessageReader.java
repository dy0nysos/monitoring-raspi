package monitoring.core;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class MessageReader implements Runnable {
	
	private Map<ThreadMonitor, Message> messagesMap;
	private BlockingQueue<Message> messages;

	public MessageReader(Map<ThreadMonitor,Message> messagesMap,BlockingQueue<Message> messages){
		this.messages=messages;
		this.messagesMap=messagesMap;
	}
	
	public void run() {
		while(true){
			Message message = messages.poll();
			if(message != null){
				messagesMap.put(message.getOriginThread(), message);
				System.out.println("New message");
			}
			try {
				Thread.sleep(2500L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
