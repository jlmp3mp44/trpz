package com.generator_installer.project.builder;

import com.generator_installer.project.entity.Documentation;
import com.generator_installer.project.entity.File;
import com.generator_installer.project.entity.InstallerGenerator;
import com.generator_installer.project.entity.User;

public class InstallerDirector {
  private InstallerBuilder builder;

  public InstallerDirector(InstallerBuilder builder) {
    this.builder = builder;
  }

  public InstallerGenerator constructBasicInstaller(File file, User user) {
    return builder
        .setFile(file)
        .setUser(user)
        .setInstallationOption("Default Option")
        .setShortcut("No Shortcut")
        .build();
  }

  public InstallerGenerator constructAdvancedInstaller(File file, User user, Documentation documentation) {
    return builder
        .setFile(file)
        .setUser(user)
        .setDocumentation(documentation)
        .setInstallationOption("Install for all users")
        .setShortcut("Create Desktop Shortcut")
        .build();
  }
}

