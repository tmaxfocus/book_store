package com.tmaxnoda.bookinventory.Core.Application;

import com.tmaxnoda.bookinventory.Core.Domain.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void should_Return_List_Of_Genres(){
        ArrayList<Genre> genres = new ArrayList<>();
        String genreName = "Novel";
        String genreName2 = "Science Fiction";
        String genreName3 = "Comedy";
        String genreName4 = "Science";
        Genre newGenre = new Genre(genreName);
        genres.add(newGenre);
        Genre newGenre2 = new Genre(genreName2);
        genres.add(newGenre2);
        Genre newGenre3 = new Genre(genreName3);
        genres.add(newGenre3);
        Genre newGenre4 = new Genre(genreName4);
        genres.add(newGenre4);
        genreRepository.saveAll(genres);

        // find all
        List<Genre> getAllGenres = genreRepository.findAll();

        assertThat(getAllGenres.size()).isGreaterThan(0);

    }

    @Test
    void should_Save_Genre_Into_Db(){

        String genreName = "Novel";
        Genre newGenre = new Genre(genreName);

        Genre expected = genreRepository.save(newGenre);

        assertThat(expected.getName()).isEqualTo(genreName);
        assertThat(expected.getId()).isGreaterThan(0);
    }

    @Test
    void should_Find_Genre_By_Name(){

        String genreName = "Novel";
        Genre newGenre = new Genre(genreName);
        genreRepository.save(newGenre);

        Optional<Genre> expected = genreRepository.findGenreByName(genreName);

        assertThat(expected.isPresent()).isTrue();


    }

    @Test
    void should_Update_Genre(){

        String genreName = "Novel";
        String updateGenre = "Comedy Novel";
        int _upToDateResult = 0;
        Genre newGenre = new Genre(genreName);
        genreRepository.save(newGenre);


       Optional<Genre> expected = genreRepository.findById(newGenre.getId());

       if(expected.isPresent()){
           Genre _result = expected.get();

           _result.setName(updateGenre);

           Optional<Genre> newFind = genreRepository.findGenreByName(updateGenre);
           assertThat(newFind.isPresent()).isTrue();
           assertThat(newFind.get().getName()).isEqualTo(updateGenre);
       }




    }
}