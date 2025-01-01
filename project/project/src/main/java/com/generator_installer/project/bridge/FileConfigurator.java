package com.generator_installer.project.bridge;
class FileConfigurator extends FileProcessor {

  public FileConfigurator(FileFormatter formatter) {
    super(formatter);
  }

  @Override
  void processFile(String fileName) {
    System.out.println("Configuring installation package: " + fileName);
    formatter.createFile(fileName);
  }
}

