package com.wcode.api_hateoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wcode.api_hateoas.services.ImageService;

//@RestController
@RequestMapping("image")
public class ImageController {

	@Autowired
	ImageService imageService;

	@PostMapping
	public void updateImage(@RequestParam("image") MultipartFile image) {
		 this.imageService.updateImage(image);
	}

}
