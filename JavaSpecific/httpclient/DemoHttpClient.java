package httpclient;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class DemoHttpClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		getResponse("http://openjdk.java.net/");
		getResponseJava11("http://openjdk.java.net/");
	}

	private static void getResponseJava11(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println)
				.join();
	}

	public static void getResponse(String url) throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
		URI uri = URI.create(url);
		HttpRequest request = HttpRequest.newBuilder(uri).build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(response.body());
	}

	public static HttpClient getClientWithMetaData() {
		return HttpClient.newBuilder().version(Version.HTTP_2).followRedirects(Redirect.ALWAYS)
				.proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
				.authenticator(Authenticator.getDefault()).build();
	}

}
