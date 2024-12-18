package com.generator_installer.project.server.command;


import com.generator_installer.project.server.state.InstallerState;
import java.io.PrintWriter;

public class GenerateInstallerCommand implements Command {
  private InstallerState installerState;
  private PrintWriter out;

  public GenerateInstallerCommand(InstallerState installerState, PrintWriter out) {
    this.installerState = installerState;
    this.out = out;
  }

  @Override
  public void execute() {
    String installerPath = installerState.generateInstaller();
    out.println("Installer generated: " + installerPath);
  }
}

