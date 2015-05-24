import java.io.IOException;
import java.net.UnknownHostException;

import javax.script.ScriptException;

import monitoring.core.LcdDisplay;

public class Main
{
//  private static String urlDyonysos = "dyonysos.ovh";
//  private static String urlAl = "archoslounge.net";
//  private static Integer httpPort = Integer.valueOf(80);
  
  public static void main(String[] args)
    throws UnknownHostException, IOException, InterruptedException, ScriptException
  {
//    GpioController gpio = GpioFactory.getInstance();
//    GpioPinDigitalOutput alOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
//    GpioPinDigitalOutput alNOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
//    GpioPinDigitalOutput dyonysosOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05);
//    GpioPinDigitalOutput dyonysosNOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
//    Serveur al = new Serveur(urlAl,httpPort);
//    al.setEtatOk(alOK);
//    al.setEtatNok(alNOK);
//    Serveur dyo = new Serveur(urlDyonysos,httpPort);
//    dyo.setEtatNok(dyonysosNOK);
//    dyo.setEtatOk(dyonysosOK);
//    List<Serveur> lesServeurs = new ArrayList<Serveur>();
//    lesServeurs.add(dyo);
//    lesServeurs.add(al);
//    Moniteur moniteur = new Moniteur();
//    moniteur.setLesServeurs(lesServeurs);
//    moniteur.setDelay(Long.valueOf(25000L));
//    moniteur.setTimeout(Long.valueOf(1000L));
//    moniteur.lancerMonitoring();
	  LcdDisplay lcdDisplay = new LcdDisplay();
	  lcdDisplay.lcdString("HELLO WORLD!", LcdDisplay.LCD_LINE_1);
	  Thread.sleep(3000L);
	  lcdDisplay.lcdString("BYE WORLD!", LcdDisplay.LCD_LINE_1);
	  Thread.sleep(3000L);
	  lcdDisplay.lcdString("", LcdDisplay.LCD_LINE_1);
  }
}
