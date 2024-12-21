package com.generator_installer.project.server.factory;

class MsiInstallerCreator extends InstallerCreator {
  @Override
  InstallerPackage factoryMethod() {
    return new MsiInstaller();
  }
}
