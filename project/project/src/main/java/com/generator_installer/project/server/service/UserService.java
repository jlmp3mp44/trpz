package com.generator_installer.project.server.service;

import com.generator_installer.project.server.entity.User;
import java.util.List;

public interface UserService {

  User createUser(User user);

  User getUserById(Integer id);

  List<User> getAllUsers();

  User updateUser(Integer id, User userDetails);

  void deleteUser(Integer id);
}
