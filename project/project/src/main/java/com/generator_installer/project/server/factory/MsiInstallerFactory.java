package com.generator_installer.project.server.factory;

public class MsiInstallerFactory extends InstallerFactory {
  @Override
  public Installer createInstaller() {
    return new MsiInstaller();
  }
}
