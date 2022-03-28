package connection;

import java.util.Map;

public class ConnectionFactory {

	private static ConnectionFactory connection;

	private final String URL = "https://webscrapper.will/connect/";
	private final String TOKEN = "";
	
	private String uri;
	private Map<String, String> params;
	
	
	private ConnectionFactory () {}
	
	public ConnectionFactory init() {
		if (connection != null)
			return connection;
		
		return new ConnectionFactory();
	}
	
	public void setConnection(String uri, Map<String, String> params) {
		this.uri = uri;
		this.params = params;
	}

	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
