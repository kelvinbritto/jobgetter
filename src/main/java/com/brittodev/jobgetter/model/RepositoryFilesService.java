package com.brittodev.jobgetter.model;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.brittodev.jobgetter.Services.Create;

@Service
public class RepositoryFilesService {
	
	public RepositoryFiles getRepository(String url) throws IOException {
		
		return new Create().create(url);
		
	}
}
