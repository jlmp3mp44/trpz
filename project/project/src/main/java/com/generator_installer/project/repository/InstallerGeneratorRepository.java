package com.generator_installer.project.repository;

import com.generator_installer.project.entity.File;
import com.generator_installer.project.entity.InstallerGenerator;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallerGeneratorRepository extends JpaRepository<InstallerGenerator, Integer> {

}
