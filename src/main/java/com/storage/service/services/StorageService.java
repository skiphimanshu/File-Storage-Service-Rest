package com.storage.service.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.storage.service.entity.FileData;
import com.storage.service.entity.ImageData;
import com.storage.service.repository.FileDataRepository;
import com.storage.service.repository.StorageRepo;
import com.storage.service.utils.ImageUtils;

@Service
public class StorageService {

	@Autowired
	private StorageRepo storageRepo;
	@Autowired
	private FileDataRepository fileDataRepository;
	
	private final String Foder_Path = "C:\\Users\\Himanshu\\Desktop\\filefolder";
	
	public String uploadImage(MultipartFile multipartFile) throws IOException {
		
		ImageData imageCom = storageRepo.save(ImageData.builder()
				.name(multipartFile.getOriginalFilename())
				.type(multipartFile.getContentType())
				.imageData(ImageUtils.compressImage(multipartFile.getBytes())).build());
		
		if(imageCom != null) {
			
			return "File Upload SuccssFully "+multipartFile.getOriginalFilename();
		}
		return null;
	}
	
	
	public byte[] downloadImage(String fileName) {
		
		Optional<ImageData> dbImgdata = storageRepo.findByName(fileName);
		
		// de compress image
		
		byte[] decompressImage = ImageUtils.decompressImage(dbImgdata.get().getImageData());
		
		return decompressImage;
		
	}
	
	public String uploadFile(MultipartFile file) throws  IOException {
		
		String filepath =  Foder_Path+"\\"+file.getOriginalFilename();
		
		FileData saveFile = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filepath).build());
		
		file.transferTo(new File(filepath));
		
		if(saveFile != null) {
			
			return  "File Save Sucessfully "+file.getOriginalFilename();
		}
		return null;
		
	}
	
	
	public byte[] downloadFIle(String file) throws IOException {
		
		Optional<FileData> fileData = fileDataRepository.findByName(file);
		
		String filePath = fileData.get().getFilePath();
		
		
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		
		return images;

	}
}
