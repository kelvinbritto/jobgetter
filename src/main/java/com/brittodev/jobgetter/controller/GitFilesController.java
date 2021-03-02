package com.brittodev.jobgetter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.brittodev.jobgetter.model.Err;
import com.brittodev.jobgetter.model.RepositoryFiles;
import com.brittodev.jobgetter.model.RepositoryFilesService;

@RestController
@RequestMapping("/")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class GitFilesController {

	@Autowired
	RepositoryFilesService repository;

	@GetMapping
	@Cacheable(value = "repository")
	public ResponseEntity<RepositoryFiles> getProjectGit(String url) {
		
		if(url == "") {
			System.out.println("BAD.REQUEST -> " + url);
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println("Received -> " + url);
		
		RepositoryFiles repositoryFiles = new RepositoryFiles();

		try {
			repositoryFiles = repository.getRepository(url);
		} catch (Exception e) {
			System.out.println("BAD.REQUEST -> " + url);
			return ResponseEntity.badRequest().build();
		}
		System.out.println("Send -> " + url);
		return ResponseEntity.ok(repositoryFiles);
	}

}
