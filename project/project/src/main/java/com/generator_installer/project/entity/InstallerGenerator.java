package com.generator_installer.project.entity;

import com.generator_installer.project.state.InitialState;
import com.generator_installer.project.state.InstallerState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

/**
 * InstallerGenerator entity
 */
@Entity
@Data
@Table(name = "installers_generator")
public class InstallerGenerator {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "file_id", referencedColumnName = "ID")
  private File file;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "ID")
  private User user;

  @Column(name = "Installation_option")
  private String installationOption;

  @Column(name = "shortcut")
  private String shortcut;

  @Transient // This field won't be persisted in the database
  private InstallerState state;

  public InstallerGenerator() {
    this.state = new InitialState(this);
  }

  public void setState(InstallerState state) {
    this.state = state;
  }

  public String getCurrentState() {
    return state.getStateName();
  }

  // Delegate methods to state
  public String initializeInstaller(User user) {
    return state.initializeInstaller(user);
  }

  public boolean addFiles(List<File> files) {
    return state.addFiles(files);
  }

  public void configure(String shortcutOption, String licenseKey, List<String> options) {
    state.configure(shortcutOption, licenseKey, options);
  }

  public String generateInstaller() {
    return state.generateInstaller();
  }
}
