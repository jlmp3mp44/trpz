package com.generator_installer.project.bridge;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class MsiFileFormatter implements FileFormatter {

  @Override
  public void createFile(String fileName) {
    System.out.println("Starting MSI file creation for: " + fileName + ".msi");

    File msiFile = new File(fileName + ".msi");
    try (FileOutputStream fos = new FileOutputStream(msiFile)) {

      fos.write(generateFakeMsiHeader());
      fos.write(generateFakeMsiContent(fileName));

      System.out.println("MSI file created successfully: " + msiFile.getAbsolutePath());

    } catch (IOException e) {
      System.err.println("Error during MSI file creation: " + e.getMessage());
    }
  }

  private byte[] generateFakeMsiHeader() {
    return "MSIHEADER".getBytes();
  }

  private byte[] generateFakeMsiContent(String fileName) {
    String content = "Content for " + fileName + ".msi";
    return content.getBytes();
  }
}
