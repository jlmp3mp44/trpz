package com.generator_installer.project.server.service.implementation;

import com.generator_installer.project.server.entity.Documentation;
import com.generator_installer.project.server.repository.DocumentationRepository;
import com.generator_installer.project.server.service.DocumentationService;

public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository documentationRepository;

    public DocumentationServiceImpl() {
        this.documentationRepository = new DocumentationRepository();
    }

    @Override
    public boolean checkLicense(String license) {
        Documentation documentation = documentationRepository.findByLicense(license)
            .orElseThrow(() -> new RuntimeException("License not found: " + license));

        return documentation.getInfo() != null && documentation.getInfo().contains("valid");
    }

    @Override
    public void setRight(String right) {
        Documentation documentation = new Documentation();
        documentation.setInfo(right);
        documentationRepository.save(documentation);
    }
}
