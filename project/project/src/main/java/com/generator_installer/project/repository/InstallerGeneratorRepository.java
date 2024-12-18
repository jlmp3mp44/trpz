package com.generator_installer.project.repository;

import com.generator_installer.project.entity.InstallerGenerator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class InstallerGeneratorRepository {

  private SessionFactory sessionFactory;

  public InstallerGeneratorRepository() {
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

  public void saveInstallerGenerator(InstallerGenerator installerGenerator) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.saveOrUpdate(installerGenerator);
      session.getTransaction().commit();
    }
  }

  public InstallerGenerator findById(int id) {
    try (Session session = openSession()) {
      return session.get(InstallerGenerator.class, id);
    }
  }

  public void deleteInstallerGenerator(int id) {
    try (Session session = openSession()) {
      session.beginTransaction();
      InstallerGenerator installerGenerator = session.get(InstallerGenerator.class, id);
      if (installerGenerator != null) {
        session.delete(installerGenerator);
      }
      session.getTransaction().commit();
    }
  }

  public List<InstallerGenerator> findAll() {
    try (Session session = openSession()) {
      String hql = "FROM InstallerGenerator"; // HQL-запит для отримання всіх InstallerGenerator
      return session.createQuery(hql, InstallerGenerator.class).list();
    }
  }
}
