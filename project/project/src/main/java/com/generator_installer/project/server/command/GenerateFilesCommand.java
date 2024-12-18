package com.generator_installer.project.server.command;


import com.generator_installer.project.server.state.InstallerState;

import java.io.PrintWriter;

public class GenerateFilesCommand implements Command {
  private InstallerState installerState;
  private PrintWriter out;

  public GenerateFilesCommand(InstallerState installerState, PrintWriter out) {
    this.installerState = installerState;
    this.out = out;
  }

  @Override
  public void execute() {
    try {
      // Генерація файлів
      installerState.generateFiles();
      out.println("Files generated successfully.");
    } catch (Exception e) {
      out.println("Error during file generation: " + e.getMessage());
    }
  }
}

