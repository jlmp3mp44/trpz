package com.generator_installer.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "license")
  private String license;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "user_right")
  private String userRight;

}


