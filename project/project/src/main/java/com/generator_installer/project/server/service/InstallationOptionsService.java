package com.generator_installer.project.server.service;

import com.generator_installer.project.server.entity.AdditionalFiles;
import java.util.List;

public interface InstallationOptionsService {

  List<AdditionalFiles> chooseAdditionalFiles();
  void setPathProgram(String path);
}
