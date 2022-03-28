package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.bean.Bidding;
import model.bean.File;
import model.bean.History;


public class Main {

	static String BASE_URL = "https://www.bombinhas.sc.gov.br";
	static Map<String, Integer> DATES;
	static {
		DATES = new HashMap<String, Integer>();
		DATES.put("jan", 1);
		DATES.put("fev", 2);
		DATES.put("mar", 3);
		DATES.put("abr", 4);
		DATES.put("mai", 5);
		DATES.put("jun", 6);
		DATES.put("jul", 7);
		DATES.put("ago", 8);
		DATES.put("set", 9);
		DATES.put("out", 10);
		DATES.put("nov", 11);
		DATES.put("dez", 12);
	}
	
	public static void main(String[] args) throws IOException {
		List<Bidding> container = new ArrayList<Bidding>();
		
		String url = BASE_URL + "/licitacoes/index/rotear/actionDestino/listar/codMapaItem/11152/pagina/1";
		Document doc = Jsoup.connect(url).get();
		
		
		List<Element> biddings = doc.select(".licitacoes.lista .item-lista > li h3 > a");
		for(Element item : biddings) {
			String biddingUrl = BASE_URL + item.attr("href");
			
			// Entra na página da licitação
			Document biddingDoc = Jsoup.connect(biddingUrl).get();
			
			// Página da licitação
			Element biddingPage = biddingDoc.getElementsByClass("licitacoes detalhes").first();
			String biddingDescription = biddingPage.getElementsByClass("objeto").first().text();
			String biddingTitle = biddingPage.getElementsByTag("h2").first().text();
			String biddingStatus = biddingPage.select("div.status").first().text();
			

			List<Element> biddingDateElements = biddingPage.select(".data .dia, .data .mes, .data .ano");
			Date biddingDate = new Date(
				biddingDateElements.get(2).text() + '/' + // Ano
				DATES.get(biddingDateElements.get(1).text()) + '/' + // Mês
				biddingDateElements.get(0).text() // Dia
			);
			

			// Captura os arquivos da licitação
			List<File> files = new ArrayList<File>();
			for(Element file: biddingPage.select(".docs:not(.historico) ul li")) {
				
				Element fileLink = file.select("a[href]").first();
				String fileUrl = fileLink.attr("href");
				String fileName = fileLink.getElementsByTag("strong").first().text();

				Pattern pattern = Pattern.compile("[\\s|\\S]*\\[(.*)\\]");
				Matcher regex = pattern.matcher(fileLink.text());
				String size = regex.replaceFirst("$1");
				
				files.add( new File(
						BASE_URL + fileUrl,
						new Date(file.select(".data-docs").text()), 
						size,
						fileName
					));
			}
		
			// Captura os Históricos da licitação
			List<History> histories = new ArrayList<History>();
			for(Element history: biddingPage.select(".docs.historico ul li")) {
				
				List<Element> data = history.select("p strong");
				Date historyDate = new Date(data.get(0).text());
				String historyStatus = data.get(1).text();
				String historyReason = null;
				
				if(history.select(".motivo strong").size() > 0)
					historyReason = history.select(".motivo strong").first().text();

				histories.add(new History(
					historyDate, 
					historyStatus,
					historyReason
				));
			}
		
			container.add(new Bidding(
				biddingTitle,
				biddingUrl,
				biddingStatus,
				biddingDescription,
				files,
				histories,
				biddingDate
			));
		}
		
		for(Bidding bid : container) {
			System.out.println(bid);
		}
		
	}
	
}
