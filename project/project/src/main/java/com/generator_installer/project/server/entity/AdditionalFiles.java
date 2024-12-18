package com.generator_installer.project.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "additional_files")
public class AdditionalFiles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "fileName")
  private String fileName;

  @Column(name = "path")
  private String path;

  @Column(name = "size")
  private int size;

  @Column(name = "protection_parametrs")
  private String protectionParameters;

}

