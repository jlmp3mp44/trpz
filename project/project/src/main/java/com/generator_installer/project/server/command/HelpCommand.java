package com.generator_installer.project.server.command;


import java.io.PrintWriter;

public class HelpCommand implements Command {

  private final PrintWriter out;

  public HelpCommand(PrintWriter out) {
    this.out = out;
  }

  @Override
  public void execute() {
    out.println("Available commands:");
    out.println("1. help - Displays this help message.");
    out.println("2. configure_packages <shortcutOption>,<licenseKey>,<options> - Configures installation options.");
    out.println("3. generate_files - Generates necessary installation files.");
    out.println("4. uninstall - Uninstalls the application.");
    out.println("5. exit - Closes the connection to the server.");
  }
}

