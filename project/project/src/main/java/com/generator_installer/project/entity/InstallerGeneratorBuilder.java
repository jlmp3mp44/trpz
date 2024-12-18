package com.generator_installer.project.entity;

public interface InstallerGeneratorBuilder {
  InstallerGeneratorBuilder setUser(User user);
  InstallerGeneratorBuilder setFile(File file);
  InstallerGeneratorBuilder setInstallationOption(String installationOption);
  InstallerGeneratorBuilder setShortcut(String shortcut);
  InstallerGenerator build();
}
