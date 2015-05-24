import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import monitoring.core.LcdDisplay;
import monitoring.core.Monitor;
import monitoring.entities.Server;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class Main {
	private static String urlDyonysos = "dyonysos.ovh";
	private static String urlAl = "archoslounge.net";
	private static Integer httpPort = Integer.valueOf(80);

	public static void main(String[] args) throws UnknownHostException,IOException, InterruptedException, ScriptException {
		
		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalOutput greenLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
		GpioPinDigitalOutput redLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07);
		LcdDisplay lcdDisplay = new LcdDisplay();
		Server al = new Server(urlAl, httpPort,"ALounge");
		Server dyo = new Server(urlDyonysos, httpPort, "dyonysos");
		List<Server> lesServeurs = new ArrayList<Server>();
		lesServeurs.add(dyo);
		lesServeurs.add(al);
		
		Monitor moniteur = new Monitor();
		moniteur.setLesServeurs(lesServeurs);
		moniteur.setDelay(Long.valueOf(25000L));
		moniteur.setTimeout(Long.valueOf(1000L));
		moniteur.setGreenLed(greenLed);
		moniteur.setRedLed(redLed);
		moniteur.setLcdDisplay(lcdDisplay);
		
		moniteur.startMonitoring();
	}
}
