package monitoring.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import monitoring.entities.Serveur;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class ThreadMoniteur
  implements Runnable
{
  private Serveur serveur;
  private Socket s;
  private Long delay;
  
  public void run()
  {
    System.out.println("Démarrage monitoring d'un serveur:" + this.serveur.getUrlServeur());
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      public void run()
      {
        System.out.println("Une demande d'arrêt a été détectée. Extinction des GPIOs.");
        ThreadMoniteur.this.serveur.getEtatOk().setState(false);
        ThreadMoniteur.this.serveur.getEtatNok().setState(false);
        System.out.println("GPIOs éteints.");
      }
    });
    for (;;)
    {
      connectTo(this.s, this.serveur.getUrlServeur(), this.serveur.getPortServeur(), this.serveur.getEtatOk(), this.serveur.getEtatNok());
      try
      {
        Thread.sleep(this.delay.longValue());
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  private void connectTo(Socket s, String url, Integer port, GpioPinDigitalOutput ledVerte, GpioPinDigitalOutput ledRouge)
  {
    Date datej = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss  => ");
    try
    {
      s = new Socket(url, port.intValue());
      ledVerte.setState(true);
      ledRouge.setState(false);
      System.out.println(sdf.format(datej) + url + " tourne correctement");
    }
    catch (UnknownHostException e)
    {
      ledVerte.setState(false);
      ledRouge.setState(true);
      System.out.println(sdf.format(datej) + url + " ne tourne plus");
    }
    catch (IOException e)
    {
      ledVerte.setState(false);
      ledRouge.setState(true);
      System.out.println(sdf.format(datej) + url + " ne tourne plus");
    }
  }
  
  public Serveur getServeur()
  {
    return this.serveur;
  }
  
  public void setServeur(Serveur serveur)
  {
    this.serveur = serveur;
  }
  
  public Socket getS()
  {
    return this.s;
  }
  
  public void setS(Socket s)
  {
    this.s = s;
  }
  
  public Long getDelay()
  {
    return this.delay;
  }
  
  public void setDelay(Long delay)
  {
    this.delay = delay;
  }
}
