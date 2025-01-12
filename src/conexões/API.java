package conexões;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class API {

	public API() throws IOException, InterruptedException {

	}

	HttpClient client = HttpClient.newHttpClient();

	public HttpResponse<String> requisicao(String url) throws IOException, InterruptedException, URISyntaxException {

		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).version(HttpClient.Version.HTTP_1_1).build();
		;

		return client.send(request, BodyHandlers.ofString());

	}

	public String BuscaTimeZoneJson() throws IOException, InterruptedException {

		String endereco = "http://worldtimeapi.org/api/timezone";
		try {
			HttpResponse<String> response = requisicao(endereco);
			return response.body();

		} catch (Exception e) {
			System.out.println("Não foi possivel se conectar com a api, tente novamente mais tarde");
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
			System.out.println("Resultado nao encontrado");
			return null;
		}

	}

	public String horarioJson(String timeZone) {
		String url = "https://worldtimeapi.org/api/timezone/"+timeZone;
		
		try {
			HttpResponse<String> response = requisicao(url);
			System.out.println(response.body());
			return response.body();
		} catch (Exception e) {
			System.out.println("Não foi possivel se conectar com a api, tente novamente mais tarde");
			System.out.println(e.toString());
			System.out.println(e.getMessage());
			System.out.println("Resultado nao encontrado");
			return null;
		}
	}
}
