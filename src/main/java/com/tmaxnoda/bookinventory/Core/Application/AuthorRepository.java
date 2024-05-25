package com.tmaxnoda.bookinventory.Core.Application;

import com.tmaxnoda.bookinventory.Core.Domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findAuthorByName(String authorName);
}
