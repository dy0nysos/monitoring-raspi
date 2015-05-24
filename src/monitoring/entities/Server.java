package monitoring.entities;


public class Server
{
  private String serverUrl;
  private Integer serverPort;
  private String serverName;
  
  public Server() {}
  
  public Server(String url, Integer port, String name)
  {
    this.serverUrl = url;
    this.serverPort = port;
    this.serverName = name;
  }
  
  public String getServerUrl()
  {
    return this.serverUrl;
  }
  
  public void setServerUrl(String serverUrl)
  {
    this.serverUrl = serverUrl;
  }
  
  public Integer getServerPort()
  {
    return this.serverPort;
  }
  
  public void setServerPort(Integer serverPort)
  {
    this.serverPort = serverPort;
  }

public String getServerName() {
	return serverName;
}

public void setServerName(String serverName) {
	this.serverName = serverName;
}

}
