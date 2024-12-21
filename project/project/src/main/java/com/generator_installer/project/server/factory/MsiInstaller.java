package com.generator_installer.project.server.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class MsiInstaller implements InstallerPackage {
    private String sourceDir;
    private String targetDir;

    public MsiInstaller(String sourceDir, String targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

    public void configureFiles() {
        System.out.println("Configuring files for .msi installer.");
        try {
            Files.copy(Path.of(sourceDir, "myapp.msi"), Path.of(targetDir, "myapp.msi"));
            System.out.println("Files copied from " + sourceDir + " to " + targetDir);
        } catch (IOException e) {
            System.out.println("Error configuring files: " + e.getMessage());
        }
    }

    public void configureWindows() {
        System.out.println("Setting up interactive windows for .msi installer.");
        String licenseKey = "XYZ789";
        System.out.println("License key entered: " + licenseKey);
    }

    public void buildPackage() {
        System.out.println("Building .msi installer package.");
        System.out.println(".msi installer package built successfully.");
    }
}