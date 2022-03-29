package connection;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConnectionFactory {

	private static ConnectionFactory connection;

	private final String URL = "http://webscrapper-client.local/";
	
	private String uri;
	private Map<String, String> params;
	
	private ConnectionFactory () {}
	
	public static ConnectionFactory init() {
		if (connection != null)
			return connection;
		
		return new ConnectionFactory();
	}
	
	public ConnectionFactory setConnection(String uri, Map<String, String> params) {
		this.uri = uri;
		this.params = params;
		return this;
	}
	
	public ConnectionFactory setConnection(String uri) {
		this.uri = uri;
		return this;
	}
	
	public void post(String body) throws Exception{
		
		var request = HttpRequest.newBuilder()
				.uri(URI.create(this.getUrl()))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(body))
				.build();
		var client = HttpClient.newHttpClient();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
	}
	
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getUrl() {
		return URL + this.uri;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
