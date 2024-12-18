package com.generator_installer.project.server.service;

import com.generator_installer.project.server.entity.AdditionalFiles;
import java.util.List;

public interface AdditionalFilesService {

  AdditionalFiles findByName(String name);
  List<AdditionalFiles> getListOfAdditionalFiles();

}
