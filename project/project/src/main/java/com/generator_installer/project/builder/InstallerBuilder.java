package com.generator_installer.project.builder;

import com.generator_installer.project.entity.*;

public class InstallerBuilder {

  private File file;
  private User user;
  private String installationOption;
  private String shortcut;
  private Documentation documentation;

  public InstallerBuilder setFile(File file) {
    this.file = file;
    return this;
  }

  public InstallerBuilder setUser(User user) {
    this.user = user;
    return this;
  }

  public InstallerBuilder setInstallationOption(String installationOption) {
    this.installationOption = installationOption;
    return this;
  }

  public InstallerBuilder setShortcut(String shortcut) {
    this.shortcut = shortcut;
    return this;
  }

  public InstallerBuilder setDocumentation(Documentation documentation) {
    this.documentation = documentation;
    return this;
  }

  public InstallerGenerator build() {
    InstallerGenerator generator = new InstallerGenerator();
    generator.setFile(this.file);
    generator.setUser(this.user);
    generator.setInstallationOption(this.installationOption);
    generator.setShortcut(this.shortcut);
    generator.setDocumentation(this.documentation);
    return generator;
  }
}
