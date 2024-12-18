package com.generator_installer.project.repository;

import com.generator_installer.project.entity.InstallationOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallationOptionsRepository extends JpaRepository<InstallationOption, Integer> {
}
