package com.generator_installer.project.state;

import com.generator_installer.project.entity.File;
import com.generator_installer.project.entity.InstallerGenerator;
import com.generator_installer.project.entity.User;
import java.util.List;

public class ReadyState implements InstallerState {
    private final InstallerGenerator installer;

    public ReadyState(InstallerGenerator installer) {
        this.installer = installer;
    }

    @Override
    public String initializeInstaller(User user) {
        return "Installer already initialized";
    }

    @Override
    public boolean addFiles(List<File> files) {
        return false; // Not allowed in this state
    }

    @Override
    public void configure(String shortcutOption, String licenseKey, List<String> options) {
        // Can reconfigure if needed
        installer.setShortcut(shortcutOption);
        installer.setInstallationOption(String.join(",", options));
    }

    @Override
    public String generateInstaller() {
        // Here would be the actual logic to generate the installer
        return "Generating installer with configuration: " + installer.getInstallationOption();
    }

    @Override
    public String getStateName() {
        return "Ready State";
    }
}
