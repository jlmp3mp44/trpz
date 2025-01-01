package com.generator_installer.project.bridge;

import java.io.File;
import java.io.IOException;

class ExeFileFormatter implements FileFormatter {

  @Override
  public void createFile(String fileName) {
    System.out.println("Starting EXE file creation for: " + fileName + ".exe");

    String pythonScript = fileName + ".py";
    File scriptFile = new File(pythonScript);
    if (!scriptFile.exists()) {
      System.err.println("Error: Python script not found - " + pythonScript);
      return;
    }

    String command = "pyinstaller --onefile " + pythonScript;

    try {
      Process process = Runtime.getRuntime().exec(command);

      int exitCode = process.waitFor();

      if (exitCode == 0) {
        System.out.println("EXE file created successfully: " + fileName + ".exe");
      } else {
        System.err.println("Failed to create EXE file. Exit code: " + exitCode);
      }

    } catch (IOException | InterruptedException e) {
      System.err.println("Error during EXE file creation: " + e.getMessage());
    }
  }
}


