package com.generator_installer.project.server.state;

import com.generator_installer.project.server.entity.File;
import com.generator_installer.project.server.entity.InstallerGenerator;
import com.generator_installer.project.server.entity.User;
import java.util.List;

public class InitialState implements InstallerState {
    private final InstallerGenerator installer;

    public InitialState(InstallerGenerator installer) {
        this.installer = installer;
    }

    @Override
    public String initializeInstaller(User user) {
        installer.setUser(user);
        installer.setState(new FileSelectionState(installer));
        return "Installer initialized, ready for file selection";
    }

    @Override
    public boolean addFiles(List<File> files) {
        return false; // Not allowed in this state
    }

    @Override
    public void configure(String shortcutOption, String licenseKey, List<String> options) {
    }

    @Override
    public String generateInstaller() {
        return "Cannot generate installer in initial state";
    }

    @Override
    public String getStateName() {
        return "Initial State";
    }

    @Override
    public String generateFiles() {
        return null;
    }

    @Override
    public String uninstall() {
        return null;
    }
}
