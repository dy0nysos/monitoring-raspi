package monitoring.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import monitoring.entities.Server;

public class ThreadMonitor implements Runnable{
  private Server server;
  private Socket s;
  private Long delay;
  private BlockingQueue<Message> queue;
  
  public ThreadMonitor(BlockingQueue<Message> queue){
	  this.queue = queue;
  }
  
  public void run(){
    System.out.println("Starting to monitor :" + this.server.getServerUrl());
    while (true){
      connectTo(this.s, this.server.getServerUrl(), this.server.getServerPort());
      try{
        Thread.sleep(this.delay.longValue());
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }
  
  private void connectTo(Socket s, String url, Integer port){
    Date datej = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("MMddyy  HH:mm:ss");
    try{
      s = new Socket(url, port.intValue());
      Message message = new Message();
      message.setMessage(sdf.format(datej) + this.server.getServerName() + " up");
      message.setOriginThread(this);
      message.setIsUp(true);
      this.queue.add(message);
    }catch (UnknownHostException e){
      Message message = new Message();
      message.setMessage(sdf.format(datej) + this.server.getServerName() + " down");
      message.setOriginThread(this);
      message.setIsUp(false);
      this.queue.add(message);
    }catch (IOException e){
      Message message = new Message();
      message.setMessage(sdf.format(datej) + this.server.getServerName() + " down");
      message.setOriginThread(this);
      message.setIsUp(false);
      this.queue.add(message);
    }
  }
  
  public Server getServeur(){
    return this.server;
  }
  
  public void setServeur(Server serveur){
    this.server = serveur;
  }
  
  public Socket getS(){
    return this.s;
  }
  
  public void setS(Socket s){
    this.s = s;
  }
  
  public Long getDelay(){
	  return this.delay;
  }
  
  public void setDelay(Long delay)
  {
    this.delay = delay;
  }
}
