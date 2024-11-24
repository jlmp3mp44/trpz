package com.generator_installer.project.repository;

import com.generator_installer.project.entity.InstallerGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallerGeneratorRepository extends JpaRepository<InstallerGenerator, Integer> {

}
