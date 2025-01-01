package com.generator_installer.project.bridge;


import java.util.List;

public class ProcessFile {
  public static void main(String[] args) {
    // Створення різних форматтерів
    FileFormatter msiFormatter = new MsiFileFormatter();
    FileFormatter exeFormatter = new ExeFileFormatter();

    // Використання FileValidatorProcessor
    FileProcessor validatorMsiProcessor = new FileValidatorProcessor(msiFormatter);
    FileProcessor validatorExeProcessor = new FileValidatorProcessor(exeFormatter);

    // Створення та валідація MSI файлу
    System.out.println("Processing MSI file:");
    validatorMsiProcessor.processFile("installer");

    // Створення та валідація EXE файлу
    System.out.println("\nProcessing EXE file:");
    validatorExeProcessor.processFile("program");

    // Додаткове: Використання FileConvertor
    System.out.println("\nProcessing without validation:");
    FileProcessor simpleMsiProcessor = new FileConvertor(msiFormatter);
    simpleMsiProcessor.processFile("simple_installer");

  }
}

