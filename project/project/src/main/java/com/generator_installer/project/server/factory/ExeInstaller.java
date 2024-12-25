package com.generator_installer.project.server.factory;

import java.io.*;
import java.util.zip.*;

public class ExeInstaller implements Installer {

  private String sourceDirectory;
  private String outputFilePath;

  public ExeInstaller(String sourceDirectory, String outputFilePath) {
    this.sourceDirectory = sourceDirectory;
    this.outputFilePath = outputFilePath;
  }

  @Override
  public void createInstaller() {
    System.out.println("Generating EXE installer...");
    try {
      // Створення ZIP-архіву з вихідних файлів
      String zipFilePath = outputFilePath + ".zip";
      zipDirectory(new File(sourceDirectory), zipFilePath);

      // Використання Launch4j для створення EXE файлу
      ProcessBuilder processBuilder = new ProcessBuilder(
          "launch4j", "config.xml"
      );
      processBuilder.redirectErrorStream(true);
      Process process = processBuilder.start();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
      }
      int exitCode = process.waitFor();
      if (exitCode == 0) {
        System.out.println("EXE installer created successfully at: " + outputFilePath);
      } else {
        System.err.println("Failed to create EXE installer. Exit code: " + exitCode);
      }
    } catch (IOException | InterruptedException e) {
      System.err.println("Error generating EXE installer: " + e.getMessage());
    }
  }

  private void zipDirectory(File sourceDir, String zipFilePath) throws IOException {
    try (FileOutputStream fos = new FileOutputStream(zipFilePath);
        ZipOutputStream zipOut = new ZipOutputStream(fos)) {
      zipFiles(sourceDir, sourceDir.getName(), zipOut);
    }
  }

  private void zipFiles(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
    if (fileToZip.isHidden()) {
      return;
    }
    if (fileToZip.isDirectory()) {
      File[] children = fileToZip.listFiles();
      for (File childFile : children) {
        zipFiles(childFile, fileName + "/" + childFile.getName(), zipOut);
      }
      return;
    }
    try (FileInputStream fis = new FileInputStream(fileToZip)) {
      ZipEntry zipEntry = new ZipEntry(fileName);
      zipOut.putNextEntry(zipEntry);
      byte[] bytes = new byte[1024];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }
    }
  }
}


