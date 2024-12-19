package com.generator_installer.project.server.service;

import com.generator_installer.project.server.entity.File;
import java.util.List;

public interface FileService {

  File createFile(File file);

  File getFileById(Integer id);

  File getFileByName(String fileName);

  List<File> getFileByNames(List<String> fileNames);

  List<File> getAllFiles();

  File updateFile(Integer id, File fileDetails);

  void deleteFile(Integer id);

  void deleteFileByName(String fileName);
}
