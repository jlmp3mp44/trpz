package com.generator_installer.project.service.implementation;

import com.generator_installer.project.entity.AdditionalFiles;
import com.generator_installer.project.entity.InstallationOption;
import com.generator_installer.project.repository.InstallationOptionsRepository;
import com.generator_installer.project.service.InstallationOptionsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
