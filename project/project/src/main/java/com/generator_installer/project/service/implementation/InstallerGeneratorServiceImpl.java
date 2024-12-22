package com.generator_installer.project.service.implementation;

import com.generator_installer.project.entity.File;
import com.generator_installer.project.entity.InstallerGenerator;
import com.generator_installer.project.entity.User;
import com.generator_installer.project.repository.InstallerGeneratorRepository;
import com.generator_installer.project.service.FileService;
import com.generator_installer.project.service.InstallerGeneratorService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InstallerGeneratorServiceImpl implements InstallerGeneratorService {

    private final InstallerGeneratorRepository installerGeneratorRepository;
    private final FileService fileService;
    private InstallerGenerator currentInstaller;

    public InstallerGeneratorServiceImpl(InstallerGeneratorRepository installerGeneratorRepository,
                                       FileService fileService) {
        this.installerGeneratorRepository = installerGeneratorRepository;
        this.fileService = fileService;
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
