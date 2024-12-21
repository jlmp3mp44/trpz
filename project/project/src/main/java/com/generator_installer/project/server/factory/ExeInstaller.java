package com.generator_installer.project.server.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ExeInstaller implements InstallerPackage {
    private String sourceDir;
    private String targetDir;

    public ExeInstaller(String sourceDir, String targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

    public void configureFiles() {
        System.out.println("Configuring files for .exe installer.");
        try {
            Files.copy(Path.of(sourceDir, "myapp.exe"), Path.of(targetDir, "myapp.exe"));
            System.out.println("Files copied from " + sourceDir + " to " + targetDir);
        } catch (IOException e) {
            System.out.println("Error configuring files: " + e.getMessage());
        }
    }

    public void configureWindows() {
        System.out.println("Setting up interactive windows for .exe installer.");
        String licenseKey = "ABC123"; // This could be taken from user input
        System.out.println("License key entered: " + licenseKey);
    }

    public void buildPackage() {
        System.out.println("Building .exe installer package.");
        System.out.println(".exe installer package built successfully.");
    }
}



