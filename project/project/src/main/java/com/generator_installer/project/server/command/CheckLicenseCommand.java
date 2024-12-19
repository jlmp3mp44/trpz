package com.generator_installer.project.server.command;


import com.generator_installer.project.server.service.DocumentationService;
import java.io.PrintWriter;

public class CheckLicenseCommand implements Command {
  private DocumentationService documentationService;
  private String licenseKey;
  private PrintWriter out;

  public CheckLicenseCommand(DocumentationService documentationService, String licenseKey, PrintWriter out) {
    this.documentationService = documentationService;
    this.licenseKey = licenseKey;
    this.out = out;
  }

  @Override
  public void execute() {
    // Перевірка ліцензії через сервіс
    boolean isLicenseValid = documentationService.checkLicense(licenseKey);

    // Виведення результату перевірки
    if (isLicenseValid) {
      out.println("License is valid.");
    } else {
      out.println("Invalid license key.");
    }
  }
}

