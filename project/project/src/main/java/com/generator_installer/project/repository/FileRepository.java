package com.generator_installer.project.repository;

import com.generator_installer.project.entity.File;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

  File getByFileName(String name);
  void deleteByFileName(String name);

  List<File> getAll();
}



