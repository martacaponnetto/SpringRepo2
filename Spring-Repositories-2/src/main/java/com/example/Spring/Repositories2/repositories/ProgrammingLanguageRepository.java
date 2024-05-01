package com.example.Spring.Repositories2.repositories;

import com.example.Spring.Repositories2.entities.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "repo-prog-languages")
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {
}