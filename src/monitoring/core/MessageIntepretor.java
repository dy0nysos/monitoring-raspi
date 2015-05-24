package monitoring.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class MessageIntepretor implements Runnable {

	private LcdDisplay lcdDiplay;
	private GpioPinDigitalOutput greenLed;
	private GpioPinDigitalOutput redLed;
	private Map<ThreadMonitor, Message> messageMap;
	private List<ThreadMonitor> blockingMonitors;
	
	public MessageIntepretor(LcdDisplay lcdDisplay,GpioPinDigitalOutput greenLed, GpioPinDigitalOutput redLed, Map<ThreadMonitor,Message> messageMap){
		this.lcdDiplay = lcdDisplay;
		this.greenLed = greenLed;
		this.redLed = redLed;
		this.messageMap = messageMap;
		this.blockingMonitors = new ArrayList<ThreadMonitor>();
	}
	
	public void run() {
		while(true){
			for (ThreadMonitor threadMonitor : messageMap.keySet()) {
				Message message = this.messageMap.get(threadMonitor);
				try {
					System.out.println(message.getOriginThread().getServeur().getServerName()+" "+message.getMessage()+" "+message.getIsUp());
					lcdDiplay.lcdString(message.getMessage());
					if(!message.getIsUp()){
						this.blockingMonitors.add(threadMonitor);
					}else{
						this.blockingMonitors.remove(threadMonitor);
					}
					if(blockingMonitors.size()>0){
						System.out.println("locked");
						this.greenLed.setState(false);
						this.redLed.setState(true);
					}else{
						System.out.println("unlocked");
						this.greenLed.setState(true);
						this.redLed.setState(false);
					}
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public LcdDisplay getLcdDiplay() {
		return lcdDiplay;
	}

	public void setLcdDiplay(LcdDisplay lcdDiplay) {
		this.lcdDiplay = lcdDiplay;
	}

	public GpioPinDigitalOutput getGreenLed() {
		return greenLed;
	}

	public void setGreenLed(GpioPinDigitalOutput greenLed) {
		this.greenLed = greenLed;
	}

	public GpioPinDigitalOutput getRedLed() {
		return redLed;
	}

	public void setRedLed(GpioPinDigitalOutput redLed) {
		this.redLed = redLed;
	}

	public Map<ThreadMonitor, Message> getMessageMap() {
		return messageMap;
	}

	public void setMessageMap(Map<ThreadMonitor, Message> messageMap) {
		this.messageMap = messageMap;
	}

	public List<ThreadMonitor> getBlockingMonitors() {
		return blockingMonitors;
	}

	public void setBlockingMonitors(List<ThreadMonitor> blockingMonitors) {
		this.blockingMonitors = blockingMonitors;
	}

}
