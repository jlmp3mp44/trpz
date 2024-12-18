package com.generator_installer.project.service.implementation;

import com.generator_installer.project.entity.Documentation;
import com.generator_installer.project.repository.DocumentationRepository;
import com.generator_installer.project.service.DocumentationService;
import org.springframework.stereotype.Service;

@Service
public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository documentationRepository;

    public DocumentationServiceImpl(DocumentationRepository documentationRepository) {
        this.documentationRepository = documentationRepository;
    }

    @Override
    public boolean checkLicense(String license) {
        // Шукаємо документацію за ліцензією
        Documentation documentation = documentationRepository.findByLicense(license)
            .orElseThrow(() -> new RuntimeException("License not found: " + license));

        // Перевірка: якщо поле info містить "valid", ліцензія вважається дійсною
        return documentation.getInfo() != null && documentation.getInfo().contains("valid");
    }

    @Override
    public void setRight(String right) {
        Documentation documentation = new Documentation();
        documentation.setInfo(right);
        documentationRepository.save(documentation);
    }
}
