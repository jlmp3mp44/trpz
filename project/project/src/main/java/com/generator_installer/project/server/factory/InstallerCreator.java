package com.generator_installer.project.server.factory;

abstract class InstallerCreator {
  abstract InstallerPackage factoryMethod();

  public void createPackage() {
    InstallerPackage installer = factoryMethod();
    installer.configureFiles();
    installer.configureWindows();
    installer.buildPackage();
  }
}

