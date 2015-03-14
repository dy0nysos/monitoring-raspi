package monitoring.entities;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Serveur
{
  private String urlServeur;
  private Integer portServeur;
  private GpioPinDigitalOutput etatOk;
  private GpioPinDigitalOutput etatNok;
  
  public Serveur() {}
  
  public Serveur(String url, Integer port)
  {
    this.urlServeur = url;
    this.portServeur = port;
  }
  
  public String getUrlServeur()
  {
    return this.urlServeur;
  }
  
  public void setUrlServeur(String urlServeur)
  {
    this.urlServeur = urlServeur;
  }
  
  public Integer getPortServeur()
  {
    return this.portServeur;
  }
  
  public void setPortServeur(Integer portServeur)
  {
    this.portServeur = portServeur;
  }
  
  public GpioPinDigitalOutput getEtatOk()
  {
    return this.etatOk;
  }
  
  public void setEtatOk(GpioPinDigitalOutput etatOk)
  {
    this.etatOk = etatOk;
  }
  
  public GpioPinDigitalOutput getEtatNok()
  {
    return this.etatNok;
  }
  
  public void setEtatNok(GpioPinDigitalOutput etatNok)
  {
    this.etatNok = etatNok;
  }
}
