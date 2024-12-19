package com.generator_installer.project.server.repository;

import com.generator_installer.project.server.entity.File;
import java.util.Optional;
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

  public Optional<File> findById(int id) {
    try (Session session = openSession()) {
      File file = session.get(File.class, id);
      return Optional.ofNullable(file);
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

  // Save a file and return it
  public File save(File file) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.saveOrUpdate(file); // Save or update the file
      session.getTransaction().commit();
      return file; // Return the saved or updated file
    }
  }

  // Get file by fileName
  public File getByFileName(String fileName) {
    try (Session session = openSession()) {
      String hql = "FROM File WHERE fileName = :fileName";
      return session.createQuery(hql, File.class)
          .setParameter("fileName", fileName)
          .uniqueResult(); // Returns a single file or null
    }
  }

  public List<File> getByFileNames(List<String> fileNames) {
    try (Session session = openSession()) {
      // Створюємо HQL запит для вибірки кількох файлів
      String hql = "FROM File WHERE fileName IN :fileNames";

      // Повертаємо список файлів, що відповідають іменам
      return session.createQuery(hql, File.class)
          .setParameter("fileNames", fileNames)
          .list(); // Повертає список файлів
    }
  }


  // Delete a file
  public void delete(File file) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.delete(file); // Delete the provided file entity
      session.getTransaction().commit();
    }
  }

  // Delete file by fileName
  public void deleteByFileName(String fileName) {
    try (Session session = openSession()) {
      session.beginTransaction();
      String hql = "FROM File WHERE fileName = :fileName";
      File file = session.createQuery(hql, File.class)
          .setParameter("fileName", fileName)
          .uniqueResult();
      if (file != null) {
        session.delete(file); // Delete the file entity
      }
      session.getTransaction().commit();
    }
  }
}
