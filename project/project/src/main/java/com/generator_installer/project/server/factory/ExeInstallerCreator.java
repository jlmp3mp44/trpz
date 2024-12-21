package com.generator_installer.project.server.factory;

class ExeInstallerCreator extends InstallerCreator {
  @Override
  InstallerPackage factoryMethod() {
    return new ExeInstaller();
  }
}


