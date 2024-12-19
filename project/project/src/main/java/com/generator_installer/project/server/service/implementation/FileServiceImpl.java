package com.generator_installer.project.server.service.implementation;

import com.generator_installer.project.server.entity.File;
import com.generator_installer.project.server.repository.FileRepository;
import com.generator_installer.project.server.service.FileService;
import java.util.List;

public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl() {
        this.fileRepository = new FileRepository();
    }

    @Override
    public File createFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File getFileById(Integer id) {
        return fileRepository.findById(id.intValue())  // This returns Optional<File>
            .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    @Override
    public File getFileByName(String fileName) {
        return fileRepository.getByFileName(fileName);
    }

    @Override
    public List<File> getFileByNames(List<String> fileNames) {
        return fileRepository.getByFileNames(fileNames);
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
