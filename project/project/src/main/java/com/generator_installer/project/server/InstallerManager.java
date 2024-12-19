package com.generator_installer.project.server;


import com.generator_installer.project.server.entity.AdditionalFiles;
import com.generator_installer.project.server.entity.File;
import com.generator_installer.project.server.entity.InstallerGenerator;
import com.generator_installer.project.server.entity.User;
import com.generator_installer.project.server.service.AdditionalFilesService;
import com.generator_installer.project.server.service.DocumentationService;
import com.generator_installer.project.server.service.FileService;
import com.generator_installer.project.server.service.InstallationOptionsService;
import com.generator_installer.project.server.service.InstallerGeneratorService;
import com.generator_installer.project.server.service.UserService;
import com.generator_installer.project.server.service.implementation.AdditionalFilesServiceImpl;
import com.generator_installer.project.server.service.implementation.DocumentationServiceImpl;
import com.generator_installer.project.server.service.implementation.FileServiceImpl;
import com.generator_installer.project.server.service.implementation.InstallationOptionsServiceImpl;
import com.generator_installer.project.server.service.implementation.InstallerGeneratorServiceImpl;
import com.generator_installer.project.server.service.implementation.UserServiceImpl;
import com.generator_installer.project.server.state.InitialState;
import com.generator_installer.project.server.state.InstallerState;
import java.io.PrintWriter;
import java.util.List;


public class InstallerManager {

  private AdditionalFilesService additionalFilesService;
  private DocumentationService documentationService;
  private FileService fileService;
  private InstallationOptionsService installationOptionsService;
  private InstallerGeneratorService installerGeneratorService;
  private UserService userService;
  private PrintWriter out;
  private InstallerState installerState;


  public InstallerManager(PrintWriter out) {
    this.additionalFilesService = new AdditionalFilesServiceImpl();
    this.documentationService = new DocumentationServiceImpl();
    this.fileService = new FileServiceImpl();
    this.installationOptionsService = new InstallationOptionsServiceImpl();
    this.installerGeneratorService = new InstallerGeneratorServiceImpl();
    this.userService = new UserServiceImpl();
    this.out = out;
    this.installerState = new InitialState(new InstallerGenerator());
  }

  public InstallerState getInstallerState() {
    return installerState;
  }


  public void getAdditionalFilesByName(String name) {
    AdditionalFiles additionalFiles = additionalFilesService.findByName(name);
    if (additionalFiles != null) {
      out.println("Found additional file: " + additionalFiles.getFileName());
    } else {
      out.println("Additional file not found.");
    }
  }

  public void listAllAdditionalFiles() {
    List<AdditionalFiles> additionalFilesList = additionalFilesService.getListOfAdditionalFiles();
    if (additionalFilesList != null && !additionalFilesList.isEmpty()) {
      for (AdditionalFiles file : additionalFilesList) {
        out.println("Additional file: " + file.getFileName());
      }
    } else {
      out.println("No additional files available.");
    }
  }

  public void checkLicenseValidity(String license) {
    boolean isValid = documentationService.checkLicense(license);
    if (isValid) {
      out.println("License is valid.");
    } else {
      out.println("Invalid license.");
    }
  }

  public void setUserRights(String right) {
    documentationService.setRight(right);
    out.println("Rights set to: " + right);
  }

  public void createFile(File file) {
    File createdFile = fileService.createFile(file);
    out.println("File created: " + createdFile.getFileName());
  }

  public void updateFile(Integer id, File fileDetails) {
    File updatedFile = fileService.updateFile(id, fileDetails);
    out.println("File updated: " + updatedFile.getFileName());
  }

  public void deleteFileById(Integer id) {
    fileService.deleteFile(id);
    out.println("File with ID " + id + " deleted.");
  }

  public void deleteFileByName(String fileName) {
    fileService.deleteFileByName(fileName);
    out.println("File with name " + fileName + " deleted.");
  }

  public void chooseAdditionalFiles() {
    List<AdditionalFiles> additionalFilesList = installationOptionsService.chooseAdditionalFiles();
    if (additionalFilesList != null && !additionalFilesList.isEmpty()) {
      out.println("Selected additional files:");
      for (AdditionalFiles file : additionalFilesList) {
        out.println(file.getFileName());
      }
    } else {
      out.println("No additional files selected.");
    }
  }

  public void setInstallationPath(String path) {
    installationOptionsService.setPathProgram(path);
    out.println("Installation path set to: " + path);
  }

  public void createInstallationRequest(User user) {
    String request = installerGeneratorService.createInstallationRequest(user);
    out.println("Installation request created: " + request);
  }

  public void addInstallationFiles(List<File> files) {
    boolean success = installerGeneratorService.addInstallationFiles(files);
    if (success) {
      out.println("Installation files added successfully.");
    } else {
      out.println("Failed to add installation files.");
    }
  }

  public void configureInteractiveInstallation(String shortcutOption, String licenseKey, List<String> interactiveOptions) {
    installerGeneratorService.setInteractiveConfiguration(shortcutOption, licenseKey, interactiveOptions);
    out.println("Interactive configuration set.");
  }

  public void generateInteractiveWindows() {
    String packagePath = installerGeneratorService.generateInteractiveWindows();
    out.println("Interactive Windows package generated: " + packagePath);
  }

  public void configurePackage(String configuration) {
    installerGeneratorService.configurePackage(configuration);
    out.println("Package configured with: " + configuration);
  }

  public void getPackageConfiguration() {
    String config = installerGeneratorService.getPackageConfiguration();
    out.println("Current package configuration: " + config);
  }

  public void installAdditionalSoftware(List<String> additionalSoftware) {
    String installationPath = installerGeneratorService.installAdditionalSoftware();
    out.println("Additional software installed at: " + installationPath);
  }

  public void createUser(User user) {
    User createdUser = userService.createUser(user);
    out.println("User created: " + createdUser.getUserName());
  }

  public void updateUser(Integer id, User userDetails) {
    User updatedUser = userService.updateUser(id, userDetails);
    out.println("User updated: " + updatedUser.getUserName());
  }

  public void deleteUser(Integer id) {
    userService.deleteUser(id);
    out.println("User with ID " + id + " deleted.");
  }

  public void listAllUsers() {
    List<User> userList = userService.getAllUsers();
    if (userList != null && !userList.isEmpty()) {
      for (User user : userList) {
        out.println("User: " + user.getUserName());
      }
    } else {
      out.println("No users available.");
    }
  }
}

