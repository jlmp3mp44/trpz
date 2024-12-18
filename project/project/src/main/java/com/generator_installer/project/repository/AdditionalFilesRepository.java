package com.generator_installer.project.repository;

import com.generator_installer.project.entity.AdditionalFiles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Optional;

public class AdditionalFilesRepository {

  private SessionFactory sessionFactory;

  public AdditionalFilesRepository() {
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

  public Optional<AdditionalFiles> findByFileName(String fileName) {
    try (Session session = openSession()) {
      String hql = "FROM AdditionalFiles WHERE fileName = :fileName";
      Query<AdditionalFiles> query = session.createQuery(hql, AdditionalFiles.class);
      query.setParameter("fileName", fileName);

      AdditionalFiles result = query.uniqueResult();

      return Optional.ofNullable(result);
    }
  }

  public void createAdditionalFile(String fileName, String path, int size, String protectionParameters) {
    AdditionalFiles additionalFiles = new AdditionalFiles();
    additionalFiles.setFileName(fileName);
    additionalFiles.setPath(path);
    additionalFiles.setSize(size);
    additionalFiles.setProtectionParameters(protectionParameters);

    try (Session session = openSession()) {
      session.beginTransaction();
      session.save(additionalFiles);
      session.getTransaction().commit();
    }
  }
}
