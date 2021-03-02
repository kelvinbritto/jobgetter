package com.brittodev.jobgetter.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFiles {

	private ArrayList<Language> languagesGroup;

	private String totalLinesProject;
	private String totalFilesSize;
	private String projectUrl;

	public RepositoryFiles(ArrayList<Language> languagesGroup, String totalLinesProject, String totalFilesSize,
			String projectUrl) {
		this.languagesGroup = languagesGroup;
		this.totalLinesProject = totalLinesProject;
		this.totalFilesSize = totalFilesSize;
		this.projectUrl = projectUrl;
	}

	public RepositoryFiles() {
		
	}
	
	
	public List<Language> getLanguagesInfo() {
		return languagesGroup;
	}

	public void setLanguagesInfo(List<Language> languagesGroup) {
		this.languagesGroup = (ArrayList<Language>) languagesGroup;
	}

	public String getTotalLinesProject() {
		return totalLinesProject;
	}

	public void setTotalLinesProject(String totalLinesProject) {
		this.totalLinesProject = totalLinesProject;
	}

	public String getTotalFilesSize() {
		return totalFilesSize;
	}

	public void setTotalFilesSize(String totalFilesSize) {
		this.totalFilesSize = totalFilesSize;
	}

	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	@Override
	public String toString() {
		return "RepositoryFiles [languagesGroup=" + languagesGroup + ", totalLinesProject=" + totalLinesProject
				+ ", totalFilesSize=" + totalFilesSize + ", projectUrl=" + projectUrl + "]";
	}

}