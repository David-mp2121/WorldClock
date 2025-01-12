package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import conexões.API;
import exception.UnknownLocationException;
import modelos.DataTime;

public class TimeZonesConverter {
	Gson gson = new Gson();
	public static Map<String, List<String>> criaMapaDeZonas(API api, Map<String, List<String>> map) throws Exception {
		String json = api.BuscaTimeZoneJson();
		JsonArray jsonArray = null;
		try {
			if (map != null)
				map.clear();
			// Transforma o json em um array, ja separando os elementos(continente/timezone)
			// do json em objetos dentro do array

			// System.out.println("String do json que vai virar array : " + json);
			jsonArray = JsonParser.parseString(json).getAsJsonArray();

			jsonArray.forEach(jsonElement -> {
				// Pega um elemento do Array e transforma ele em String
				String timeZone = jsonElement.getAsString();

				// Metodo split fatia uma String onde tiver o caractere selecionado e trasnforma
				// os pedaçõs em uma list de strings
				String[] separado = timeZone.split("/");
				// verifica se tem duas String : uma do continente e uma da zona
				if (separado.length == 2) {
					String continent = separado[0];

					String zone = separado[1];
					// Adiciona no map verificando se a chave ja esta no map, se nao estiver cria um
					// array para adicionar as zonas corresponder ao continete

					map.computeIfAbsent(continent, cont -> new ArrayList<String>()).add(zone);
				}
			});
		} catch (Exception e) {
			System.out.println("Não foi possivel mapear o json da api por motivos de : " + e.getMessage());
			System.out.println("Json da conexão: " + json);
			System.out.println("json Array : " + jsonArray);
		}
		return map;

	}

	public static DataTime criaDataTime(API api, String Regiao) throws UnknownLocationException {
		Gson gson = new Gson();

		String json = api.horarioJson(Regiao);
		DataTime data =  gson.fromJson(json, DataTime.class);
		if(data.erro !=null)
			throw new UnknownLocationException("Regiao");
		
		
		
		
		
		return data;

	}

}
