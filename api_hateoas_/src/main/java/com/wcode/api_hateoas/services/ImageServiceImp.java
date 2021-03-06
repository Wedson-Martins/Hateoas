package com.wcode.api_hateoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wcode.api_hateoas.models.Image;
import com.wcode.api_hateoas.repositories.ImageRepository;
import com.wcode.api_hateoas.storage.UploadFile;

@Service
public class ImageServiceImp implements ImageService {

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	UploadFile uploadFile;

	@Override
	public void updateImage(MultipartFile image) {
		this.uploadFile.salvarImage(image);
//		this.addAdressImage(adress);
	}

	private void addAdressImage(String adress) {
		Image img = new Image();
		img.setAddress(adress);
		this.imageRepository.save(img);
	}

}
