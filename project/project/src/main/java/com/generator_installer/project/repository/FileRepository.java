package com.generator_installer.project.repository;

import com.generator_installer.project.entity.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class FileRepository {

  private SessionFactory sessionFactory;

  public FileRepository() {
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

  // Збереження або оновлення файлу
  public void saveFile(File file) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.saveOrUpdate(file); // Зберігаємо або оновлюємо сутність
      session.getTransaction().commit();
    }
  }

  public File findById(int id) {
    try (Session session = openSession()) {
      return session.get(File.class, id); // Повертає об'єкт File або null
    }
  }

  public void deleteFile(int id) {
    try (Session session = openSession()) {
      session.beginTransaction();
      File file = session.get(File.class, id);
      if (file != null) {
        session.delete(file); // Видаляємо об'єкт, якщо він існує
      }
      session.getTransaction().commit();
    }
  }

  public List<File> findAll() {
    try (Session session = openSession()) {
      String hql = "FROM File"; // HQL-запит для отримання всіх файлів
      return session.createQuery(hql, File.class).list(); // Повертає список файлів
    }
  }
}
