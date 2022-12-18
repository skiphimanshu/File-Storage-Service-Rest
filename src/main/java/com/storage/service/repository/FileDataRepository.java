package com.storage.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storage.service.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long>{
	
	Optional<FileData> findByName(String fileName);

}
