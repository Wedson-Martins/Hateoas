package com.wcode.api_hateoas.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFile {

	@Value("${file.dir.source}")
	private String source;

	@Value("${file.dir.source-image}")
	private String dirImage;

	public void salvarImage(MultipartFile image) {
		System.out.println("AAA: " + image.getOriginalFilename());
		this.salvar(this.dirImage, image);
	}

	private void salvar(String dir, MultipartFile file) {
		Path dirPath = Paths.get(this.source, dir);
		Path filePath = dirPath.resolve(file.getOriginalFilename());

		try {
			Files.createDirectories(dirPath);
			file.transferTo(filePath.toFile());
		} catch (IOException e) {
			throw new RuntimeException("Problems trying to save file.", e);
		}
	}
}
