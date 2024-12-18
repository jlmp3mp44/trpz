package com.generator_installer.project.server.repository;

import com.generator_installer.project.server.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Optional;

public class UserRepository {

  private SessionFactory sessionFactory;

  public UserRepository() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure()
        .build();
    try {
      sessionFactory = new MetadataSources(registry)
          .buildMetadata()
          .buildSessionFactory();
    } catch (Exception e) {
      StandardServiceRegistryBuilder.destroy(registry);
      throw new RuntimeException("Не вдалося створити SessionFactory", e);
    }
  }

  public void close() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }

  private Session openSession() {
    return sessionFactory.openSession();
  }

  public void saveUser(User user) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.saveOrUpdate(user);
      session.getTransaction().commit();
    }
  }

  public Optional<User> findById(int id) {
    try (Session session = openSession()) {
      return Optional.ofNullable(session.get(User.class, id)); // Wrap result in Optional
    }
  }

  public void deleteUser(int id) {
    try (Session session = openSession()) {
      session.beginTransaction();
      User user = session.get(User.class, id);
      if (user != null) {
        session.delete(user);
      }
      session.getTransaction().commit();
    }
  }

  public List<User> findAll() {
    try (Session session = openSession()) {
      String hql = "FROM User";
      return session.createQuery(hql, User.class).list();
    }
  }

  public User save(User user) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.save(user);
      session.getTransaction().commit();
      return user;
    }
  }

  public void delete(User user) {
    try (Session session = openSession()) {
      session.beginTransaction();
      if (user != null) {
        session.delete(user);
      }
      session.getTransaction().commit();
    }
  }
}
