package com.generator_installer.project.service.implementation;

import com.generator_installer.project.entity.File;
import com.generator_installer.project.repository.FileRepository;
import com.generator_installer.project.service.FileService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File createFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File getFileById(Integer id) {
        return fileRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    @Override
    public File getFileByName(String fileName) {
        return fileRepository.getByFileName(fileName);
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public File updateFile(Integer id, File fileDetails) {
        File file = getFileById(id);
        file.setFileName(fileDetails.getFileName());
        file.setSize(fileDetails.getSize());
        file.setPath(fileDetails.getPath());
        return fileRepository.save(file);
    }

    @Override
    public void deleteFile(Integer id) {
        File file = getFileById(id);
        fileRepository.delete(file);
    }

    @Override
    public void deleteFileByName(String fileName) {
        fileRepository.deleteByFileName(fileName);
    }
}
