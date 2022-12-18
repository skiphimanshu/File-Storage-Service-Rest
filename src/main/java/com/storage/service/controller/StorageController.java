package com.storage.service.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.storage.service.services.StorageService;

@RestController
@RequestMapping("/storage")
public class StorageController {
	
	@Autowired
	private StorageService service;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImg(@RequestParam("image") MultipartFile file) throws IOException {
		
		String uploadImage = service.uploadImage(file);
		
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}
	
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> downloadImg(@PathVariable("fileName") String file) {
		
		 byte[] downloadImage = service.downloadImage(file);
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(downloadImage);
	}
	
	
	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		
		String uploadFile = service.uploadFile(file);
		
		return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
	}
	
	@GetMapping("/download-file/{fileName}")
	public ResponseEntity<?> downloadFile(@PathVariable("fileName") String file) throws IOException {
		
		byte[] downloadFIle = service.downloadFIle(file);
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(downloadFIle);
	}

}
