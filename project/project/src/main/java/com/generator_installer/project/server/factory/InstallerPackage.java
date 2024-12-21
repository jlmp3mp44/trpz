package com.generator_installer.project.server.factory;

interface InstallerPackage {
  void configureFiles();
  void configureWindows();
  void buildPackage();
}

