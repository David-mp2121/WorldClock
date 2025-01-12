// Testando mudanças pelo git

package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import conexões.API;
import exception.UnknownLocationException;
import modelos.DataTime;

public class Principal {

	public Principal() throws Exception {
	}

	static Map<String, List<String>> mapaDeZonas = null;
	static API conecta = null;
	static Scanner scann = null;

	public static void main(String[] args) throws Exception {
		conecta = new API();
		scann = new Scanner(System.in);
		mapaDeZonas = new HashMap<String, List<String>>();
		mapaDeZonas = TimeZonesConverter.criaMapaDeZonas(conecta, mapaDeZonas);// cria o map com todoas as zonas de

		try {
			String DataeHora = selecionadorTimeZones(); // tempo.

			System.out.println(DataeHora);
		} catch (UnknownLocationException e) {
			System.out.println("A zona selecinoada não foi encontrada, '"+e.getRegiao()+"' Não é uma região valida");
			
		}
//		
	}

	public static String selecionadorTimeZones() throws Exception {

		String Continente = escolheContinente(mapaDeZonas);
		String Zona = escolheZona(mapaDeZonas, Continente);
		System.out.println("Continente escolhido : " + Continente);

		System.out.println("Zona escolhida :" + Zona);
		return pesquisaHora(Zona, Continente);

	}

	private static String pesquisaHora(String zona, String continente) throws UnknownLocationException {
		String zonaCompleta = continente + "/" + zona;

		DataTime dataEHora = TimeZonesConverter.criaDataTime(conecta, zonaCompleta);

		return "Local : " + dataEHora.timeZone + "  -  " + dataEHora.dataEHoraconverter();
	}

	public static String escolheContinente(Map<String, List<String>> map) {
		System.out.println("_______________________________________");
		System.out.println("------------Relogio-Mundial------------");
		System.out.println("_______________________________________");
		System.out.println();
		System.out.println();
		List<String> listaDeContinentes = new ArrayList<String>(map.keySet());
		System.out.println(customToString(listaDeContinentes));
		System.out.println("_______________________________________");
		System.out.println("-----DIGITE O CONTINENTE ESCOLHIDO-----\n");
		String continente = scann
				.nextLine(); /* Formata o Continente para primeira letra maiuscula e as outrasminuscula */
		continente = continente.substring(0, 1).toUpperCase() + continente.substring(1).toLowerCase();
		return continente;

	}

	public static String escolheZona(Map<String, List<String>> map, String continente) {
		System.out.println("_________________________________________________");
		System.out.println("-----------------Relogio-Mundial-----------------");
		System.out.println("_________________________________________________");
		System.out.println();
		System.out.println();

		System.out.println("Regioes disponiveis no continente : " + continente);
		System.out.println();
		System.out.println();
		System.out.println("_________________________________________________");

		List<String> listaDeZonas = new ArrayList<String>();
		listaDeZonas = map.get(continente);
		System.out.println(customToString(listaDeZonas));
		System.out.println();
		System.out.println("_________________________________________________");
		System.out.println("------------DIGITE A ZONA SELECIONADA------------\n");
		String zona = scann.nextLine();
		return formataZona(zona);

	}

	public static String formataZona(String zona) {
		if (zona.contains(" ") == false) {
			zona = zona.substring(0, 1).toUpperCase() + zona.substring(1).toLowerCase();
		} else {
			String[] st = zona.split(" ");
			String primeiro = st[0];
			String ultimo = st[st.length - 1];
			primeiro = primeiro.substring(0, 1).toUpperCase() + primeiro.substring(1).toLowerCase();
			ultimo = ultimo.substring(0, 1).toUpperCase() + ultimo.substring(1).toLowerCase();
			zona = primeiro + "_" + ultimo;

		}
		return zona;
	}

	public static String customToString(List<String> list) {

		StringBuilder string = new StringBuilder();
		int i = 1;
		for (String st : list) {
			string.append(st);
			int pulaLinha = 4;
			int verificador = list.size() - 1;
			if (pulaLinha % list.size() != 0)
				verificador = list.size();

			if (i % pulaLinha != 0 && i < verificador) {
				string.append("  --  ");
			} else {
				string.append("\n");
			}

			i++;
		}
		return string.toString();
	}

}
