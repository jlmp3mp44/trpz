package com.generator_installer.project.bridge;

class FileValidatorProcessor extends FileProcessor {

  public FileValidatorProcessor(FileFormatter formatter) {
    super(formatter);
  }

  @Override
  void processFile(String fileName) {
    if (fileName == null || fileName.isEmpty()) {
      System.err.println("Invalid file name.");
      return;
    }
    System.out.println("Validating file: " + fileName);
    formatter.createFile(fileName);
  }
}

