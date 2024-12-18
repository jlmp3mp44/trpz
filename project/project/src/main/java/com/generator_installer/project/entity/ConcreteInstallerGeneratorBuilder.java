package com.generator_installer.project.entity;

public class ConcreteInstallerGeneratorBuilder implements InstallerGeneratorBuilder {
  private InstallerGenerator installerGenerator;

  public ConcreteInstallerGeneratorBuilder() {
    this.installerGenerator = new InstallerGenerator();
  }

  @Override
  public InstallerGeneratorBuilder setUser(User user) {
    installerGenerator.setUser(user);
    return this;
  }

  @Override
  public InstallerGeneratorBuilder setFile(File file) {
    installerGenerator.setFile(file);
    return this;
  }

  @Override
  public InstallerGeneratorBuilder setInstallationOption(String installationOption) {
    installerGenerator.setInstallationOption(installationOption);
    return this;
  }

  @Override
  public InstallerGeneratorBuilder setShortcut(String shortcut) {
    installerGenerator.setShortcut(shortcut);
    return this;
  }

  @Override
  public InstallerGenerator build() {
    return installerGenerator;
  }
}

