package com.generator_installer.project.server.service;

import com.generator_installer.project.server.entity.File;
import com.generator_installer.project.server.entity.User;
import java.util.List;

public interface InstallerGeneratorService {
    
    // Scenario 1: Creating an Installation Package
    String createInstallationRequest(User user);
    
    // Scenario 2: Selecting Installation Files
    boolean addInstallationFiles(List<File> files);
    
    // Scenario 3: Installing Windows with Interactive Features
    void setInteractiveConfiguration(String shortcutOption, 
                                   String licenseKey, 
                                   List<String> interactiveOptions);
    String generateInteractiveWindows();
    
    // Scenario 4: Configuring Interactive Packages
    void configurePackage(String configuration);
    String getPackageConfiguration();
    
    // Scenario 5: Installing Additional Software
    void addAdditionalSoftware(List<String> additionalSoftware);
    String installAdditionalSoftware();
}
