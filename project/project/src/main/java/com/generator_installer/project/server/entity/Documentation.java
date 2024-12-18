package com.generator_installer.project.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "documentation")
public class Documentation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "License")
  private String license;

  @Column(name = "Info")
  private String info;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "ID")
  private User user;

}


