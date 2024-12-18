package com.generator_installer.project.repository;

import com.generator_installer.project.entity.AdditionalFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdditionalFilesRepository extends JpaRepository<AdditionalFiles, Integer> {

  Optional<AdditionalFiles> findByFileName(String fileName);
}
