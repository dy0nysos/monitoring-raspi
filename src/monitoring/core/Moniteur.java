package monitoring.core;

import java.util.List;

import monitoring.entities.Serveur;

public class Moniteur
{
  private List<Serveur> lesServeurs;
  private Long timeout;
  private Long delay;
  
  public void lancerMonitoring()
  {
    if ((this.lesServeurs != null) && (!this.lesServeurs.isEmpty())) {
      for (Serveur serveur : this.lesServeurs)
      {
        ThreadMoniteur tm = new ThreadMoniteur();
        tm.setServeur(serveur);
        tm.setDelay(this.delay);
        Thread t = new Thread(tm);
        t.start();
      }
    }
  }
  
  public List<Serveur> getLesServeurs()
  {
    return this.lesServeurs;
  }
  
  public void setLesServeurs(List<Serveur> lesServeurs)
  {
    this.lesServeurs = lesServeurs;
  }
  
  public Long getTimeout()
  {
    return this.timeout;
  }
  
  public void setTimeout(Long timeout)
  {
    this.timeout = timeout;
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
