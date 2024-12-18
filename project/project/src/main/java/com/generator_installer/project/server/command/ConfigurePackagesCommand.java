package com.generator_installer.project.server.command;


import com.generator_installer.project.server.state.InstallerState;
import java.io.PrintWriter;
import java.util.List;

public class ConfigurePackagesCommand implements Command {
  private InstallerState installerState;
  private String shortcutOption;
  private String licenseKey;
  private List<String> options;
  private PrintWriter out;

  public ConfigurePackagesCommand(InstallerState installerState, String shortcutOption, String licenseKey, List<String> options, PrintWriter out) {
    this.installerState = installerState;
    this.shortcutOption = shortcutOption;
    this.licenseKey = licenseKey;
    this.options = options;
    this.out = out;
  }

  @Override
  public void execute() {
    installerState.configure(shortcutOption, licenseKey, options);
    out.println("Packages configured successfully.");
  }
}

