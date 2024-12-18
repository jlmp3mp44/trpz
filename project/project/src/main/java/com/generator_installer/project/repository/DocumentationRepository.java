package com.generator_installer.project.repository;

import com.generator_installer.project.entity.Documentation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Optional;

public class DocumentationRepository {

  private SessionFactory sessionFactory;

  public DocumentationRepository() {
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

  public Optional<Documentation> findByLicense(String license) {
    try (Session session = openSession()) {
      String hql = "FROM Documentation WHERE license = :license";
      Query<Documentation> query = session.createQuery(hql, Documentation.class);
      query.setParameter("license", license);

      Documentation result = query.uniqueResult();

      return Optional.ofNullable(result);
    }
  }

  public void saveDocumentation(Documentation documentation) {
    try (Session session = openSession()) {
      session.beginTransaction();
      session.saveOrUpdate(documentation);
      session.getTransaction().commit();
    }
  }

  public Documentation findById(int id) {
    try (Session session = openSession()) {
      return session.get(Documentation.class, id);
    }
  }

  public void deleteDocumentation(int id) {
    try (Session session = openSession()) {
      session.beginTransaction();
      Documentation documentation = session.get(Documentation.class, id);
      if (documentation != null) {
        session.delete(documentation);
      }
      session.getTransaction().commit();
    }
  }
}
