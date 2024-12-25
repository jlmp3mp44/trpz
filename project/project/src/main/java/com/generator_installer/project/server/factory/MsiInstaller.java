package com.generator_installer.project.server.factory;

import java.io.*;

public class MsiInstaller implements Installer {

  private String sourceDirectory;
  private String outputFilePath;

  public MsiInstaller(String sourceDirectory, String outputFilePath) {
    this.sourceDirectory = sourceDirectory;
    this.outputFilePath = outputFilePath;
  }

  @Override
  public void createInstaller() {
    System.out.println("Generating MSI installer...");
    try {
      // Використання WiX Toolset для створення MSI файлу
      ProcessBuilder processBuilder = new ProcessBuilder(
          "candle", "-out", "installer.wixobj", "installer.wxs"
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
        ProcessBuilder linker = new ProcessBuilder(
            "light", "-out", outputFilePath, "installer.wixobj"
        );
        linker.redirectErrorStream(true);
        Process linkProcess = linker.start();
        try (BufferedReader linkReader = new BufferedReader(new InputStreamReader(linkProcess.getInputStream()))) {
          String linkLine;
          while ((linkLine = linkReader.readLine()) != null) {
            System.out.println(linkLine);
          }
        }
        int linkExitCode = linkProcess.waitFor();
        if (linkExitCode == 0) {
          System.out.println("MSI installer created successfully at: " + outputFilePath);
        } else {
          System.err.println("Failed to create MSI installer. Linker exit code: " + linkExitCode);
        }
      } else {
        System.err.println("Failed to compile MSI installer. Candle exit code: " + exitCode);
      }
    } catch (IOException | InterruptedException e) {
      System.err.println("Error generating MSI installer: " + e.getMessage());
    }
  }
}
