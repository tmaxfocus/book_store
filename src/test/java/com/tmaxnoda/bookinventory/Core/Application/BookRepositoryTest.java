package com.tmaxnoda.bookinventory.Core.Application;

import com.tmaxnoda.bookinventory.Core.Domain.entities.Author;
import com.tmaxnoda.bookinventory.Core.Domain.entities.Book;
import com.tmaxnoda.bookinventory.Core.Domain.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void should_Save_Book_Into_Db(){
        //create genre
        String genreName = "Novel";
        Genre newGenre = new Genre(genreName);
        Genre saved_genre= genreRepository.save(newGenre);
        //create author
        Set<Author> authors = new HashSet<>();
        String author_name1 = "Miguel de carvantes Savedra";
        Author author = new Author(author_name1);
        Author saved_author = authorRepository.save(author);
        authors.add(saved_author);



        Book book = new Book("Don Quixote","02c6e9dd5a7b4",
                "2018",
                "https://coverart.oclc.org/ImageWebSvc/oclc/+-+3584215756_140.jpg?allowDefault=false&client=WorldcatOrgUI",
                new BigDecimal("50.0"),"The final and greatest utterance of the human mind.",
                true,10,new Date(),saved_genre,authors);

        Book bookExpected = bookRepository.save(book);

        Book foundBook =bookRepository.findById(1L).get();

        System.out.println(bookExpected.toString());

        assertThat(foundBook.getId()).isGreaterThan(0);
    }
}