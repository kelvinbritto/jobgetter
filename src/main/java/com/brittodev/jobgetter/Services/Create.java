package com.brittodev.jobgetter.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.brittodev.jobgetter.model.Language;
import com.brittodev.jobgetter.model.RepositoryFiles;

public class Create {

	Services serv = new Services();

	public final RepositoryFiles create(String urlProjeto) throws IOException {

		List<Integer> tamanhoLinhas = new ArrayList<Integer>();
		List<String> tamanhoArquivos = new ArrayList<String>();
		List<Language> linguagens = new ArrayList<Language>();
		List<String> urls = serv.getUrlsLinguagens(urlProjeto);

		Float totalFiles = 0f;
		Integer totalLinhas = 0;

		RepositoryFiles repository = new RepositoryFiles();

		for (String urlLinguagem : urls) {

			tamanhoLinhas = new ArrayList<Integer>();
			tamanhoArquivos = new ArrayList<String>();

			List<String> filesUrl = (serv.findFilesPage(urlLinguagem, serv.getNumberOfPages(urlLinguagem), 1));
			Object[] sizeAndLines = null;

			for (String file : filesUrl) {
				try {
					sizeAndLines = serv.getFileSizeAndLinesOfCode(file);
				} catch (Exception e) {
					try {
						// Time to wait GibHub's error (429 Too many requests)
						System.out.println("Waiting 30 Seconds");
						TimeUnit.SECONDS.sleep(30);
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}
					sizeAndLines = serv.getFileSizeAndLinesOfCode(file);
				}

				if (tamanhoArquivos.size() > 0) {
					if (sizeAndLines[0] == tamanhoArquivos.get(tamanhoArquivos.size() - 1)) {
						System.out.println("igual");
					} else {
						tamanhoLinhas.add((Integer) sizeAndLines[0]);
						tamanhoArquivos.add((String) sizeAndLines[1]);
					}
				}else {
					tamanhoLinhas.add((Integer) sizeAndLines[0]);
					tamanhoArquivos.add((String) sizeAndLines[1]);
				}

			}

			Language linguagem = new Language();
			// Get Language Name Extension
			String[] file = filesUrl.get(0).split("/");
			linguagem.setName(file[file.length - 1].split("\\.")[1].toUpperCase());

			Float a = serv.sumFileSize(tamanhoArquivos);
			totalFiles = a + totalFiles;

			Integer b = serv.sumLines(tamanhoLinhas);

			totalLinhas = b + totalLinhas;

			linguagem.setSizeFiles(a.toString() + " Kb");
			linguagem.setLinesSize(b.toString() + " Lines");

			linguagens.add(linguagem);

		}

		repository.setLanguagesInfo(linguagens);
		repository.setTotalFilesSize(totalFiles + " Kb");
		repository.setTotalLinesProject(totalLinhas + " Lines");
		repository.setProjectUrl(urlProjeto);

		return repository;
	}
}
