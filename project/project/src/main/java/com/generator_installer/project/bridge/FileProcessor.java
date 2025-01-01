package com.generator_installer.project.bridge;

abstract class FileProcessor {
  protected FileFormatter formatter;

  protected FileProcessor(FileFormatter formatter) {
    this.formatter = formatter;
  }

  abstract void processFile(String fileName);
}

