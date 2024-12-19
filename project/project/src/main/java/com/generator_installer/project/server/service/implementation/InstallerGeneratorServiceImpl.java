package com.generator_installer.project.server.service.implementation;

import com.generator_installer.project.server.entity.File;
import com.generator_installer.project.server.entity.InstallerGenerator;
import com.generator_installer.project.server.entity.User;
import com.generator_installer.project.server.repository.FileRepository;
import com.generator_installer.project.server.repository.InstallerGeneratorRepository;
import com.generator_installer.project.server.service.FileService;
import com.generator_installer.project.server.service.InstallerGeneratorService;
import java.util.List;

public class InstallerGeneratorServiceImpl implements InstallerGeneratorService {

    private final InstallerGeneratorRepository installerGeneratorRepository;
    private final FileService fileService;
    private InstallerGenerator currentInstaller;

    public InstallerGeneratorServiceImpl() {
        this.installerGeneratorRepository = new InstallerGeneratorRepository();
        this.fileService = new FileServiceImpl();
    }

    // Scenario 1: Creating an Installation Package
    @Override
    public String createInstallationRequest(User user) {
        currentInstaller = new InstallerGenerator();
        String result = currentInstaller.initializeInstaller(user);
        currentInstaller = installerGeneratorRepository.save(currentInstaller);
        return result;
    }

    // Scenario 2: Selecting Installation Files
    @Override
    public boolean addInstallationFiles(List<File> files) {
        try {
            for (File file : files) {
                fileService.createFile(file);
            }
            boolean result = currentInstaller.addFiles(files);
            currentInstaller = installerGeneratorRepository.save(currentInstaller);
            return result;
        } catch (Exception e) {
            return false;
        }
    }

    // Scenario 3: Installing Windows with Interactive Features
    @Override
    public void setInteractiveConfiguration(String shortcutOption,
                                          String licenseKey, 
                                          List<String> interactiveOptions) {
        currentInstaller.configure(shortcutOption, licenseKey, interactiveOptions);
        currentInstaller = installerGeneratorRepository.save(currentInstaller);
    }

    @Override
    public String generateInteractiveWindows() {
        return currentInstaller.generateInstaller();
    }

    // Scenario 4: Configuring Interactive Packages
    @Override
    public void configurePackage(String configuration) {
        currentInstaller.configure(configuration, null, List.of(configuration));
        currentInstaller = installerGeneratorRepository.save(currentInstaller);
    }

    @Override
    public String getPackageConfiguration() {
        return currentInstaller.getInstallationOption();
    }

    // Scenario 5: Installing Additional Software
    @Override
    public void addAdditionalSoftware(List<String> additionalSoftware) {
        String currentOptions = currentInstaller.getInstallationOption();
        List<String> newOptions = new java.util.ArrayList<>();
        if (currentOptions != null && !currentOptions.isEmpty()) {
            newOptions.add(currentOptions);
        }
        newOptions.addAll(additionalSoftware);
        currentInstaller.configure(currentInstaller.getShortcut(), null, newOptions);
        currentInstaller = installerGeneratorRepository.save(currentInstaller);
    }

    @Override
    public String installAdditionalSoftware() {
        return currentInstaller.generateInstaller();
    }
}
