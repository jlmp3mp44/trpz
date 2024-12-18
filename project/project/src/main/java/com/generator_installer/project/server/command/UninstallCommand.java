package com.generator_installer.project.server.command;


import com.generator_installer.project.server.state.InstallerState;

import java.io.PrintWriter;

public class UninstallCommand implements Command {
  private InstallerState installerState;
  private PrintWriter out;

  public UninstallCommand(InstallerState installerState, PrintWriter out) {
    this.installerState = installerState;
    this.out = out;
  }

  @Override
  public void execute() {
    try {
      // Логіка видалення
      installerState.uninstall();
      out.println("Uninstallation completed successfully.");
    } catch (Exception e) {
      out.println("Error during uninstallation: " + e.getMessage());
    }
  }
}

