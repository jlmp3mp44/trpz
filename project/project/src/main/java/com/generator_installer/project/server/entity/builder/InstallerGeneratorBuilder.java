package com.generator_installer.project.server.entity.builder;

import com.generator_installer.project.server.entity.File;
import com.generator_installer.project.server.entity.InstallerGenerator;
import com.generator_installer.project.server.entity.User;

public interface InstallerGeneratorBuilder {
  InstallerGeneratorBuilder setUser(User user);
  InstallerGeneratorBuilder setFile(File file);
  InstallerGeneratorBuilder setInstallationOption(String installationOption);
  InstallerGeneratorBuilder setShortcut(String shortcut);
  InstallerGenerator build();
}
