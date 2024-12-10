package com.generator_installer.project.service;

import com.generator_installer.project.entity.AdditionalFiles;
import java.util.List;

public interface AdditionalFilesService {

  AdditionalFiles findByName(String name);
  List<AdditionalFiles> getListOfAdditionalFiles();

}
