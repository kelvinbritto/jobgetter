package com.brittodev.jobgetter.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


//This class has all communications with GitHub`s platafform
public class Services {

	//Search and find Urls with languages filter
	public List<String> getUrlsLinguagens(String urlRepository) {

		Document doc = null;
		List<String> urls = new ArrayList<String>();

		try {
			doc = Jsoup.connect(urlRepository).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements newsHeadlines = doc
				.select("a.d-inline-flex.flex-items-center.flex-nowrap.Link--secondary.no-underline.text-small.mr-3");

		for (Element headline : newsHeadlines) {
			urls.add("https://github.com/" + headline.attr("href"));
		}

		return urls;

	}

	//Method to find files URL on the page
	public List<String> findFilesPage(String url, Integer numeroDePaginas, Integer n) throws IOException {
		
		List<String> array = new ArrayList<String>();

		try {
			
			
			while (n <= numeroDePaginas) {
				Document doc = Jsoup.connect(url + "&p=" + n).get();

				Elements newsHeadlines = doc.select("div.f4.text-normal");

				for (Element headline : newsHeadlines) {

					array.add("https://github.com" + headline.select("a").attr("href"));
					System.out.println("https://github.com" + headline.select("a").attr("href"));
				}
				
				System.out.println("Pagina" + n);

				n++;
			}
			
		} catch (Exception e) {
			
			try {
			    //Time to wait GibHub's error (429 Too many requests)
				TimeUnit.SECONDS.sleep(30);
				System.out.println("Waiting 30 Seconds");
			} catch (InterruptedException ie) {
			    Thread.currentThread().interrupt();
			}
			
			findFilesPage(url, numeroDePaginas, n);			
		}
		return array;
	}
	

	//Get how many pages has on the language filter
	public Integer getNumberOfPages(String url) throws IOException {

		Document doc = Jsoup.connect(url).get();

		try {
			Elements em = doc.select("em.current");

			return Integer.parseInt(em.attr("data-total-pages"));

		} catch (Exception e) {
			return 1;
		}
	}

	//return Object[] with lines and fileSize
	public Object[] getFileSizeAndLinesOfCode(String urlArquivo) throws IOException {

		Document doc = Jsoup.connect(urlArquivo).get();
		

		Elements newsHeadlines = doc
				.select("div.text-mono.f6.flex-auto.pr-3.flex-order-2.flex-md-order-1.mt-2.mt-md-0");

		String linhasEKb = null;

		Integer numeroDeLinhas = 0;
		String tamanho = null;

		Object[] array = { numeroDeLinhas, tamanho };

		for (Element headline : newsHeadlines) {
			linhasEKb = headline.ownText();
		}

		String[] nova1 = linhasEKb.split(" ");
		String[] nova2 = linhasEKb.split("sloc");

		numeroDeLinhas = Integer.parseInt(nova1[0]);
		tamanho = nova2[1].substring(2);

		array[0] = numeroDeLinhas;
		array[1] = tamanho;

		return array;

	}

	
	//Sum  file List<String> in Kb
	public Float sumFileSize(List<String> lista) {

		List<Double> kb = new ArrayList<Double>();
		List<Integer> bytes = new ArrayList<Integer>();

		for (String string : lista) {
			if (string.contains("KB")) {
				kb.add(Double.parseDouble(string.split(" ")[0]));
			} else {
				bytes.add(Integer.parseInt(string.split(" ")[0]));
			}

		}

		Double sumKb = kb.stream().collect(Collectors.summingDouble(Double::doubleValue));

		Integer sumBytes = bytes.stream().collect(Collectors.summingInt(Integer::intValue));

		Float bytes2Kb = sumBytes.floatValue() / 1000;

		return sumKb.floatValue() + bytes2Kb;

	}

	//Sum lines
	public Integer sumLines(List<Integer> lista) {

		Integer sum = lista.stream().collect(Collectors.summingInt(Integer::intValue));

		return sum;
	}

	

}
