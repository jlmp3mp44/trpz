package com.generator_installer.project.server.service.implementation;

import com.generator_installer.project.server.entity.AdditionalFiles;
import com.generator_installer.project.server.repository.AdditionalFilesRepository;
import com.generator_installer.project.server.service.AdditionalFilesService;

import java.util.List;

public class AdditionalFilesServiceImpl implements AdditionalFilesService {

    private final AdditionalFilesRepository additionalFilesRepository;

    public AdditionalFilesServiceImpl() {
        this.additionalFilesRepository = new AdditionalFilesRepository();
    }

    @Override
    public AdditionalFiles findByName(String name) {
        return additionalFilesRepository.findByFileName(name)
            .orElseThrow(() -> new RuntimeException("File not found: " + name));
    }

    @Override
    public List<AdditionalFiles> getListOfAdditionalFiles() {
        return additionalFilesRepository.findAll();
    }
}
