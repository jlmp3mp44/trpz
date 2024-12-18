package com.generator_installer.project.server.service.implementation;

import com.generator_installer.project.server.entity.AdditionalFiles;
import com.generator_installer.project.server.entity.InstallationOption;
import com.generator_installer.project.server.repository.InstallationOptionsRepository;
import com.generator_installer.project.server.service.InstallationOptionsService;

import java.util.List;
import java.util.stream.Collectors;

public class InstallationOptionsServiceImpl implements InstallationOptionsService {

    private final InstallationOptionsRepository installationOptionsRepository;

    public InstallationOptionsServiceImpl(InstallationOptionsRepository installationOptionsRepository) {
        this.installationOptionsRepository = installationOptionsRepository;
    }

    @Override
    public List<AdditionalFiles> chooseAdditionalFiles() {
        return installationOptionsRepository.findAll().stream()
            .map(InstallationOption::getAdditionalFile)
            .collect(Collectors.toList());
    }

    @Override
    public void setPathProgram(String path) {
        InstallationOption option = installationOptionsRepository.findAll().stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No installation options found"));

        option.setPathProgram(path);
        installationOptionsRepository.save(option);
    }
}
