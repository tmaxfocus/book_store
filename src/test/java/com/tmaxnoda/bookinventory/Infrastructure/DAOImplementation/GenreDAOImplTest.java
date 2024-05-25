package com.tmaxnoda.bookinventory.Infrastructure.DAOImplementation;

import com.tmaxnoda.bookinventory.Core.Application.Contracts.DAO.GenreDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class GenreDAOImplTest {


    @Autowired
    private GenreDAO genreRepository;



    @Test
    void shouldPersistInDb(){

        /**
        String genreName = "Horror";
        String horrorTwo = "Horror Two";
        Genre newgenre = new Genre(genreName);

        Genre save = genreRepository.save(newgenre);
         genreRepository.updateGenreNameById(save.getId(), horrorTwo);
        List<Genre> like = genreRepository.findGenresByNameContainingKeyword(horrorTwo);
        //get saves genre
        Genre expected = genreRepository.findByName(horrorTwo);


        assertThat(expected.getName()).isEqualTo(genreName);
         **/
    }
}