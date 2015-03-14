import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import monitoring.core.Moniteur;
import monitoring.entities.Serveur;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class Main
{
  private static String urlDyonysos = "dyonysos.ovh";
  private static String urlAl = "archoslounge.net";
  private static Integer httpPort = Integer.valueOf(80);
  
  public static void main(String[] args)
    throws UnknownHostException, IOException, InterruptedException
  {
    GpioController gpio = GpioFactory.getInstance();
    GpioPinDigitalOutput alOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
    GpioPinDigitalOutput alNOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
    GpioPinDigitalOutput dyonysosOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05);
    GpioPinDigitalOutput dyonysosNOK = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
    Serveur al = new Serveur("archoslounge.net", Integer.valueOf(80));
    al.setEtatOk(alOK);
    al.setEtatNok(alNOK);
    Serveur dyo = new Serveur("dyonysos.ovh", Integer.valueOf(80));
    dyo.setEtatNok(dyonysosNOK);
    dyo.setEtatOk(dyonysosOK);
    List<Serveur> lesServeurs = new ArrayList();
    lesServeurs.add(dyo);
    lesServeurs.add(al);
    Moniteur moniteur = new Moniteur();
    moniteur.setLesServeurs(lesServeurs);
    moniteur.setDelay(Long.valueOf(25000L));
    moniteur.setTimeout(Long.valueOf(1000L));
    moniteur.lancerMonitoring();
  }
}
