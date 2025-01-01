package com.generator_installer.project.bridge;
class FileConvertor extends FileProcessor {

  public FileConvertor(FileFormatter formatter) {
    super(formatter);
  }

  @Override
  void processFile(String fileName) {
    System.out.println("Configuring installation package: " + fileName);
    formatter.createFile(fileName);
  }
}

