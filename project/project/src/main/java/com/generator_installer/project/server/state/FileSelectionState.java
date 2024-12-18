package com.generator_installer.project.server.state;

import com.generator_installer.project.entity.File;
import com.generator_installer.project.entity.InstallerGenerator;
import com.generator_installer.project.entity.User;
import java.util.List;

public class FileSelectionState implements InstallerState {
    private final InstallerGenerator installer;

    public FileSelectionState(InstallerGenerator installer) {
        this.installer = installer;
    }

    @Override
    public String initializeInstaller(User user) {
        return "Installer already initialized";
    }

    @Override
    public boolean addFiles(List<File> files) {
        try {
            for (File file : files) {
                installer.setFile(file); // Note: This might need to be modified to handle multiple files
            }
            installer.setState(new ConfigurationState(installer));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void configure(String shortcutOption, String licenseKey, List<String> options) {
    }

    @Override
    public String generateInstaller() {
        return "Cannot generate installer without configuration";
    }

    @Override
    public String getStateName() {
        return "File Selection State";
    }
}
