package com.generator_installer.project.service.implementation;

import com.generator_installer.project.entity.AdditionalFiles;
import com.generator_installer.project.repository.AdditionalFilesRepository;
import com.generator_installer.project.service.AdditionalFilesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalFilesServiceImpl implements AdditionalFilesService {

    private final AdditionalFilesRepository additionalFilesRepository;

    public AdditionalFilesServiceImpl(AdditionalFilesRepository additionalFilesRepository) {
        this.additionalFilesRepository = additionalFilesRepository;
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
