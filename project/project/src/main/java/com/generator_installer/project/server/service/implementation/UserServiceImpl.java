package com.generator_installer.project.server.service.implementation;

import com.generator_installer.project.server.entity.User;
import com.generator_installer.project.server.repository.UserRepository;
import com.generator_installer.project.server.service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }
  @Override
  public User createUser(User user) {
      return userRepository.save(user);
  }

  @Override
  public User getUserById(Integer id) {
      return userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }

  @Override
  public List<User> getAllUsers() {
      return userRepository.findAll();
  }

  @Override
  public User updateUser(Integer id, User userDetails) {
      User user = getUserById(id);
      user.setUserName(userDetails.getUserName());
      user.setEmail(userDetails.getEmail());
      user.setLicense(userDetails.getLicense());
      user.setPassword(userDetails.getPassword());
      user.setUserRight(userDetails.getUserRight());
      return userRepository.save(user);
  }

  @Override
  public void deleteUser(Integer id) {
      User user = getUserById(id);
      userRepository.delete(user);
  }
}
