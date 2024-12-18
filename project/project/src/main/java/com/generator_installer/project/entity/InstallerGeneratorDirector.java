package com.generator_installer.project.entity;

public class InstallerGeneratorDirector {
  private InstallerGeneratorBuilder builder;

  public InstallerGeneratorDirector(InstallerGeneratorBuilder builder) {
    this.builder = builder;
  }

  public InstallerGenerator createDefaultInstaller(User user, File file) {
    return builder
        .setUser(user)
        .setFile(file)
        .setInstallationOption("Default Installation")
        .setShortcut("Create on Desktop")
        .build();
  }

  public InstallerGenerator createCustomInstaller(User user, File file, String shortcut, String installationOption) {
    return builder
        .setUser(user)
        .setFile(file)
        .setInstallationOption(installationOption)
        .setShortcut(shortcut)
        .build();
  }
}

