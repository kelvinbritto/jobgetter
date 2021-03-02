package com.brittodev.jobgetter.model;


public class Language {

	private String languageName;
	private String sizeFiles;
	private String linesSize;

	public String getName() {
		return languageName;
	}

	public void setName(String name) {
		this.languageName = name;
	}

	public String getSizeFiles() {
		return sizeFiles;
	}

	public void setSizeFiles(String sizeFiles) {
		this.sizeFiles = sizeFiles;
	}

	public String getLineSize() {
		return linesSize;
	}

	public void setLinesSize(String linesSize) {
		this.linesSize = linesSize;
	}

	@Override
	public String toString() {
		return "Language [languageName=" + languageName + ", sizeFiles=" + sizeFiles + ", linesSize=" + linesSize + "]";
	}

}
