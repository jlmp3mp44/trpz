package com.generator_installer.project.server;

import com.generator_installer.project.server.command.CheckLicenseCommand;
import com.generator_installer.project.server.command.Command;
import com.generator_installer.project.server.command.ConfigurePackagesCommand;
import com.generator_installer.project.server.command.HelpCommand;
import com.generator_installer.project.server.command.SelectFilesCommand;  // Імпорт нової команди
import com.generator_installer.project.server.command.SetPathCommand;
import com.generator_installer.project.server.entity.InstallerGenerator;
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
import com.generator_installer.project.server.entity.File; // Імпорт сутності File
import com.generator_installer.project.server.state.InitialState;
import com.generator_installer.project.server.state.InstallerState;  // Імпорт стану установника
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List; // Імпорт списку файлів

public class Server {

  private static InstallerManager installerManager;

  public static void main(String[] args) throws IOException {
    // Створення серверного сокету та очікування на підключення
    ServerSocket ss = new ServerSocket(4999);
    System.out.println("Server is waiting for connection...");

    Socket s = ss.accept();
    System.out.println("Client connected.");

    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    PrintWriter out = new PrintWriter(s.getOutputStream(), true);

    // Створення інстансів сервісів
    installerManager = new InstallerManager(out);

    AdditionalFilesService additionalFilesService = new AdditionalFilesServiceImpl();
    DocumentationService documentationService = new DocumentationServiceImpl();
    FileService fileService = new FileServiceImpl();
    InstallationOptionsService installationOptionsService = new InstallationOptionsServiceImpl();
    InstallerGeneratorService installerGeneratorService = new InstallerGeneratorServiceImpl();
    UserService userService = new UserServiceImpl();

    InstallerState installerState = new InitialState(new InstallerGenerator());  // Ініціалізація стану установника
    List<File> files = fileService.getAllFiles();  // Отримуємо всі файли через сервіс

    // Обробка повідомлень від клієнта
    String clientMessage;
    while ((clientMessage = in.readLine()) != null) {
      System.out.println("Received from client: " + clientMessage);

      String[] parts = clientMessage.split(" ", 2);
      String command = parts[0].toLowerCase();
      String myArgs = parts.length > 1 ? parts[1] : "";

      // Розділяємо аргументи за комами
      String[] arguments = myArgs.split(",");

      String shortcutOption;
      String licenseKey;
      List<String> options;

      // Обробка різних команд
      switch (command) {
        case "help":
          Command helpCommand = new HelpCommand(out);
          helpCommand.execute();
          break;
        case "select_files":
          // Створення та виконання команди для вибору файлів
          Command selectFilesCommand = new SelectFilesCommand(installerState, files, out);
          selectFilesCommand.execute();
          break;
        case "create_installation":
          // Припустимо, що в arguments[0] це shortcutOption, в arguments[1] - licenseKey, а решта - options
          shortcutOption = arguments[0];
          licenseKey = arguments[1];
          options = List.of(arguments).subList(2, arguments.length); // Отримуємо список з опцій після 2-го елемента

          // Створюємо команду для налаштування пакетів
          Command createInstallationCommand = new ConfigurePackagesCommand(installerState, shortcutOption, licenseKey, options, out);
          createInstallationCommand.execute();
          break;

        case "configure_packages":
          // Отримуємо параметри з масиву arguments
          shortcutOption = arguments[0]; // Припускаємо, що перший аргумент - це shortcutOption
          licenseKey = arguments[1]; // Припускаємо, що другий аргумент - це licenseKey
          options = List.of(arguments).subList(2, arguments.length); // Інші аргументи - це options

          // Створюємо команду для налаштування пакетів
          Command configurePackagesCommand = new ConfigurePackagesCommand(installerState, shortcutOption, licenseKey, options, out);
          configurePackagesCommand.execute();
          break;

        case "add_files":
          // Перетворення аргументів в список файлів
          List<File> selectedFiles = fileService.getFileByNames(List.of(arguments)); // Припускаємо, що аргументи - це назви файлів, які треба вибрати

          // Отримуємо поточний стан установника (якщо він вже ініціалізований)
          installerState = installerManager.getInstallerState();

          // Створюємо команду для вибору файлів
          Command addFilesCommand = new SelectFilesCommand(installerState, selectedFiles, out);
          addFilesCommand.execute();
          break;

        case "check_license":
          // Виклик команди для перевірки ліцензії
          Command checkLicenseCommand = new CheckLicenseCommand(documentationService, arguments[0], out);
          checkLicenseCommand.execute();
          break;
        case "set_path_program":
          // Виклик команди для налаштування шляху до програми
          Command setPathCommand = new SetPathCommand(installationOptionsService, arguments[0], out);
          setPathCommand.execute();
          break;
        case "exit":
          out.println("Goodbye!");
          s.close();
          ss.close();
          System.out.println("Connection closed.");
          return;
        default:
          out.println("Unknown command: " + clientMessage);
          break;
      }
    }
  }
}
