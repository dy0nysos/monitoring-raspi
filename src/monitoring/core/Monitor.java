package monitoring.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

import monitoring.entities.Server;

public class Monitor {
	private List<Server> servers;
	private Long timeout;
	private Long delay;
	private BlockingQueue<Message> messages = new LinkedTransferQueue<Message>();
	private Map<ThreadMonitor, Message> messageMap = new HashMap<>();
	private GpioPinDigitalOutput greenLed;
	private GpioPinDigitalOutput redLed;
	private LcdDisplay lcdDisplay;

	public void startMonitoring() throws InterruptedException {
		if (this.lcdDisplay == null) {
			System.out.println("LCD Display not set");
			System.exit(-1);
		}
		if (this.greenLed == null || this.redLed == null) {
			System.out.println("At least a led is not set");
			System.exit(-1);
		}
		if (this.servers == null || this.servers.isEmpty()) {
			System.out.println("Servers are not set");
			System.exit(-1);
		}
		for (Server server : this.servers) {
			System.out.println("Launches monitoring for " + server.getServerUrl());
			ThreadMonitor tm = new ThreadMonitor(this.messages);
			tm.setServeur(server);
			tm.setDelay(this.delay);
			Thread t = new Thread(tm);
			t.start();
			Thread.sleep(1000L);
		}
		MessageReader messageReader = new MessageReader(messageMap, messages);
		Thread t = new Thread(messageReader);
		t.start();

		MessageIntepretor messageInterpretor = new MessageIntepretor(lcdDisplay, greenLed, redLed, messageMap);
		Thread t2 = new Thread(messageInterpretor);
		t2.start();
	}

	public List<Server> getLesServeurs() {
		return this.servers;
	}

	public void setLesServeurs(List<Server> lesServeurs) {
		this.servers = lesServeurs;
	}

	public Long getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	public Long getDelay() {
		return this.delay;
	}

	public void setDelay(Long delay) {
		this.delay = delay;
	}

	public GpioPinDigitalOutput getRedLed() {
		return redLed;
	}

	public void setRedLed(GpioPinDigitalOutput redLed) {
		this.redLed = redLed;
	}

	public GpioPinDigitalOutput getGreenLed() {
		return greenLed;
	}

	public void setGreenLed(GpioPinDigitalOutput greenLed) {
		this.greenLed = greenLed;
	}

	public LcdDisplay getLcdDisplay() {
		return lcdDisplay;
	}

	public void setLcdDisplay(LcdDisplay lcdDisplay) {
		this.lcdDisplay = lcdDisplay;
	}
}
