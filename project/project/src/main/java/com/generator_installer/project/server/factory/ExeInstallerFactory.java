package com.generator_installer.project.server.factory;

public class ExeInstallerFactory extends InstallerFactory {
  @Override
  public Installer createInstaller() {
    return new ExeInstaller();
  }
}
