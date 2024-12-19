package com.generator_installer.project.server.command;


import com.generator_installer.project.server.service.InstallationOptionsService;
import java.io.PrintWriter;

public class SetPathCommand implements Command {
  private InstallationOptionsService installationOptionsService;
  private String path;
  private PrintWriter out;

  public SetPathCommand(InstallationOptionsService installationOptionsService, String path, PrintWriter out) {
    this.installationOptionsService = installationOptionsService;
    this.path = path;
    this.out = out;
  }

  @Override
  public void execute() {
    if (path != null && !path.isEmpty()) {
      // Встановлюємо шлях через сервіс
      installationOptionsService.setPathProgram(path);
      out.println("Path set successfully to: " + path);
    } else {
      out.println("Invalid path. Please provide a valid path.");
    }
  }
}

