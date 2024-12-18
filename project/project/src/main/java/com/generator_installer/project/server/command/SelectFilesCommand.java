package com.generator_installer.project.server.command;



import com.generator_installer.project.entity.File;
import com.generator_installer.project.server.state.InstallerState;
import java.util.List;
import java.io.PrintWriter;

public class SelectFilesCommand implements Command {
  private InstallerState installerState;
  private List<File> files;
  private PrintWriter out;

  public SelectFilesCommand(InstallerState installerState, List<File> files, PrintWriter out) {
    this.installerState = installerState;
    this.files = files;
    this.out = out;
  }

  @Override
  public void execute() {
    if (files != null && !files.isEmpty()) {
      installerState.addFiles(files);
      out.println("Files selected successfully.");
    } else {
      out.println("No files selected. Please select some files.");
    }
  }
}

