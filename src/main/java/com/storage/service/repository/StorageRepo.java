package com.storage.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storage.service.entity.ImageData;

public interface StorageRepo extends JpaRepository<ImageData, Long>{
	
	Optional<ImageData> findByName(String fileName);

}
