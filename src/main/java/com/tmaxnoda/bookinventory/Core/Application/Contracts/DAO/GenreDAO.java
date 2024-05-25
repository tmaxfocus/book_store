package com.tmaxnoda.bookinventory.Core.Application.Contracts.DAO;

import com.tmaxnoda.bookinventory.Core.Domain.entities.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreDAO extends PagingAndSortingRepository<Genre, Long> {
    Genre save(Genre genre);
    Genre findByName(String name);
    List<Genre> findAll();

    @Modifying
    @Transactional
    @Query("UPDATE Genre g SET g.name = :newName WHERE g.id = :id")
    void updateGenreNameById(Long id,String newName);
    @Query("SELECT g FROM Genre g WHERE g.name = :name")
    List<Genre> findGenresByNameContainingKeyword(@Param("name") String name);
}
