package com.generator_installer.project.server.state;

import com.generator_installer.project.entity.File;
import com.generator_installer.project.entity.User;

import java.util.List;

public interface InstallerState {
    String initializeInstaller(User user);
    boolean addFiles(List<File> files);
    void configure(String shortcutOption, String licenseKey, List<String> options);
    String generateInstaller();
    String getStateName();
    void generateFiles();
    void uninstall();
}
