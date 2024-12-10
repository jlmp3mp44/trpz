package com.generator_installer.project.service;

import com.generator_installer.project.entity.AdditionalFiles;
import java.util.List;

public interface InstallationOptionsService {

  List<AdditionalFiles> chooseAdditionalFiles();
  void setPathProgram(String path);
}
