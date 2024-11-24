package com.generator_installer.project;

import com.generator_installer.project.entity.AdditionalFiles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectApplicationTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testAdditionalFilesEntity() {
		AdditionalFiles file = new AdditionalFiles();
		file.setFileName("example.txt");
		file.setPath("/path/to/file");
		file.setSize(12345);
		file.setProtectionParameters("none");

		entityManager.persist(file);
		entityManager.flush();
	}
}


