package com.wcode.api_hateoas.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	void add(MultipartFile image);
	
	
}