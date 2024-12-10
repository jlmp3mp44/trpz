package com.generator_installer.project.service;

import com.generator_installer.project.entity.File;
import java.util.List;

public interface FileService {

  File createFile(File file);

  File getFileById(Integer id);

  File getFileByName(String fileName);

  List<File> getAllFiles();

  File updateFile(Integer id, File fileDetails);

  void deleteFile(Integer id);

  void deleteFileByName(String fileName);
}
