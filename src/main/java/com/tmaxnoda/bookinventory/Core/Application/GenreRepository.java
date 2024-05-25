package com.tmaxnoda.bookinventory.Core.Application;

import com.tmaxnoda.bookinventory.Core.Domain.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    Optional<Genre> findGenreByName(String genreName);

}
