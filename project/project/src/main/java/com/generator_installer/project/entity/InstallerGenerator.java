package com.generator_installer.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import jakarta.persistence.*;

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

}


