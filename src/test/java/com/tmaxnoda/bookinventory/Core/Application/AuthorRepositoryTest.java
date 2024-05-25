package com.tmaxnoda.bookinventory.Core.Application;

import com.tmaxnoda.bookinventory.Core.Domain.entities.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void Should_Save_Author_In_Db(){
        String author_name1 = "Miguel de carvantes Savedra";
        Author author = new Author(author_name1);

        //check if authors name exists
        Optional<Author> checkIfAuthorExist = authorRepository.findAuthorByName(author_name1);

        if(checkIfAuthorExist.isEmpty())
        {
            Author saved_author = authorRepository.save(author);
            assertThat(saved_author.getId()).isGreaterThan(0);
            assertThat(saved_author.getName()).isEqualTo(author_name1);
        }


    }
}