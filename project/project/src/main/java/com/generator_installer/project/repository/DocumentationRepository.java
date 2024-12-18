package com.generator_installer.project.repository;

import com.generator_installer.project.entity.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentationRepository extends JpaRepository<Documentation, Integer> {

  Optional<Documentation> findByLicense(String license);
}
