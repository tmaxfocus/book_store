package com.tmaxnoda.bookinventory.Core.Application;

import com.tmaxnoda.bookinventory.Core.Domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findBookByTitle(String genreName);
}
