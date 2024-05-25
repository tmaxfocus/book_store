package com.tmaxnoda.bookinventory.Infrastructure.Service;

import com.tmaxnoda.bookinventory.Core.Application.AuthorRepository;
import com.tmaxnoda.bookinventory.Core.Application.Exceptions.GenericException;
import com.tmaxnoda.bookinventory.Core.Application.Requests.AuthorRequest;
import com.tmaxnoda.bookinventory.Core.Application.Responses.AuthorResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenericResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.ResponseMessage;
import com.tmaxnoda.bookinventory.Core.Domain.entities.Author;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService (AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public GenericResponse<List<AuthorResponse>> GetAuthors(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Author> authors = this.authorRepository.findAll(sort);
        List<AuthorResponse> authorsResponse = new ArrayList<>();
        for(Author author : authors){
            authorsResponse.add(new AuthorResponse(author.getId(),author.getName()));
        }
        return new GenericResponse<List<AuthorResponse>>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                authorsResponse);
    }

    public GenericResponse<AuthorResponse> AddNewAuthor(String authorName){
        //check if Genre already exist
        Optional<Author> getAuthor =  authorRepository.findAuthorByName(authorName);

        if(getAuthor.isPresent()){
            throw new GenericException("Author  already exist");
        }
        Author newAuthor = authorRepository.save(new Author(authorName));
        return new GenericResponse<AuthorResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                new AuthorResponse(newAuthor.getId(),newAuthor.getName()));

    }

    @Transactional
    @Modifying
    public GenericResponse<AuthorResponse> UpdateAuthor(AuthorRequest author){

        Author updateAuthor = new Author(author.getId(),author.getName());
        //check if Genre already exist
        Optional<Author> getAuthor =  authorRepository.findById(updateAuthor.getId());

        if(getAuthor.isEmpty()){
            throw new GenericException("Author name does not exist");
        }
        if(getAuthor.get().getName() == author.getName()){
            throw new GenericException("Author's Name already exist in the DB ");
        }


        getAuthor.get().setName(updateAuthor.getName());

        return new GenericResponse<AuthorResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                null);
    }

    public GenericResponse<AuthorResponse>  GetAuthorById(Long id){
        Optional<Author> getGenre= authorRepository.findById(id);
        if(getGenre.isEmpty()){
            throw new GenericException("Invalid genre request - " + id);
        }
        return new GenericResponse<AuthorResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                 new AuthorResponse(getGenre.get().getId(),getGenre.get().getName()));

    }

    public GenericResponse<AuthorResponse> RemoveAuthorById(Long id){
        Optional<Author> getGenre= authorRepository.findById(id);
        if(getGenre.isEmpty()){
            throw new GenericException("Invalid genre request");
        }
        authorRepository.deleteById(id);

        return new GenericResponse<AuthorResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                null);
    }


}
