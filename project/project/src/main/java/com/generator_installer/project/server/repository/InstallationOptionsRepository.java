package com.generator_installer.project.server.repository;

import com.generator_installer.project.server.entity.InstallationOption;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class InstallationOptionsRepository {

  private SessionFactory sessionFactory;

  public InstallationOptionsRepository() {
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

  public void saveOrUpdate(InstallationOption installationOption) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.saveOrUpdate(installationOption);
      session.getTransaction().commit();
    }
  }

  public InstallationOption findById(int id) {
    try (Session session = openSession()) {
      return session.get(InstallationOption.class, id);
    }
  }

  public void deleteById(int id) {
    try (Session session = openSession()) {
      session.beginTransaction();
      InstallationOption installationOption = session.get(InstallationOption.class, id);
      if (installationOption != null) {
        session.delete(installationOption);
      }
      session.getTransaction().commit();
    }
  }

  public List<InstallationOption> findAll() {
    try (Session session = openSession()) {
      String hql = "FROM InstallationOption";
      return session.createQuery(hql, InstallationOption.class).list();
    }
  }

  // Save new InstallationOption if it doesn't exist
  public void save(InstallationOption option) {
    try (Session session = openSession()) {
      session.beginTransaction();
      // Save the InstallationOption only if it's new (not already persisted)
      session.save(option);
      session.getTransaction().commit();
    }
  }
}
