package com.tmaxnoda.bookinventory.Infrastructure.Service;

import com.tmaxnoda.bookinventory.Core.Application.AuthorRepository;
import com.tmaxnoda.bookinventory.Core.Application.BookRepository;
import com.tmaxnoda.bookinventory.Core.Application.Exceptions.GenericException;
import com.tmaxnoda.bookinventory.Core.Application.GenreRepository;
import com.tmaxnoda.bookinventory.Core.Application.Requests.BookRequest;
import com.tmaxnoda.bookinventory.Core.Application.Responses.BookResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenericResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.ResponseMessage;
import com.tmaxnoda.bookinventory.Core.Domain.entities.Author;
import com.tmaxnoda.bookinventory.Core.Domain.entities.Book;
import com.tmaxnoda.bookinventory.Core.Domain.entities.Genre;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    public GenericResponse<BookResponse>  GetAuthorById(Long id){
        Optional<Book> getBook= bookRepository.findById(id);
        if(getBook.isEmpty()){
            throw new GenericException("Invalid genre request - " + id);
        }

        //get authors name
        Set<Author> getAuthors = getBook.get().getAuthors();
        ArrayList<String> AuthorsName= new ArrayList<>();
        for(Author authorName : getAuthors){
            AuthorsName.add(authorName.getName());
        }
        BookResponse response =  new BookResponse(getBook.get().getTitle(),
                getBook.get().getIsbn(),
                getBook.get().getPublicationYear(),
                getBook.get().getImageUrl(),
                getBook.get().getUnitPrice(),
                getBook.get().getDescription(),
                getBook.get().isActive(),
                getBook.get().getUnitsInStock(),
                getBook.get().getGenre().getName(),
                AuthorsName);
        return new GenericResponse<BookResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                response);

    }

    public GenericResponse<ArrayList<BookResponse>> GetBooks(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Book> books = this.bookRepository.findAll(sort);
        ArrayList<BookResponse> bookResponseList = new ArrayList<>();

        for(Book book : books){
            ArrayList<String> AuthorsName= new ArrayList<>();
            Set<Author> getAuthors = book.getAuthors();
            for(Author authorName : getAuthors){
                AuthorsName.add(authorName.getName());
            }
            bookResponseList.add(new BookResponse(book.getId(),book.getTitle(),
                    book.getIsbn(),
                    book.getPublicationYear(),
                    book.getImageUrl(),
                    book.getUnitPrice(),
                    book.getDescription(),
                    book.isActive(),
                    book.getUnitsInStock(),
                    book.getGenre().getName(),
                    AuthorsName));

        }
        GenericResponse<ArrayList<BookResponse>> genericResponseResult = new GenericResponse<ArrayList<BookResponse>>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                bookResponseList);

        return  genericResponseResult;
    }


    public GenericResponse<String> AddNewBook(BookRequest bookRequest){
        //check if Genre already exist
        Optional<Book> getBook =  this.bookRepository.findBookByTitle(bookRequest.getTitle());

        if(getBook.isPresent()){
            throw new GenericException("Book already exist");
        }

        // check if Genre Exist

        //check if Genre already exist
        Optional<Genre> getGenre =  genreRepository.findGenreByName(bookRequest.getGenre());

        if(getGenre.isEmpty()){
            throw new GenericException("Genre does not exist, please contact the administrator");
        }

        Set<Author> authors = new HashSet<>();

        for (String book : bookRequest.getAuthors()){
            Optional<Author> getAuthor =  authorRepository.findAuthorByName(book);
            if(getAuthor.isEmpty()){
                throw new GenericException("Author Does not exist");
            }
            authors.add(getAuthor.get());
        }



        // create new Book
        Book book = new Book(bookRequest.getTitle(),
                bookRequest.getIsbn(),
                bookRequest.getPublicationYear(),
                bookRequest.getImageUrl(),
                bookRequest.getUnitPrice(),
                bookRequest.getSummary(),
                bookRequest.isActive(),
                bookRequest.getUnitsInStock(),new Date(),getGenre.get(),authors);

        Book newBook = this.bookRepository.save(book);
        return new GenericResponse<String>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                newBook.getTitle());

    }


    @Transactional
    @Modifying
    public GenericResponse<String> UpdateBook(BookRequest bookRequest){
        //check if Genre already exist
        Optional<Book> getBook =  this.bookRepository.findById(bookRequest.getId());

        if(getBook.isEmpty()){
            throw new GenericException("Book cannot be found.");
        }

        // check if Genre Exist

        //check if Genre already exist
        Optional<Genre> getGenre =  genreRepository.findGenreByName(bookRequest.getGenre());

        if(getGenre.isEmpty()){
            throw new GenericException("Genre does not exist, please contact the administrator");
        }

        Set<Author> authors = new HashSet<>();

        for (String book : bookRequest.getAuthors()){
            Optional<Author> getAuthor =  authorRepository.findAuthorByName(book);
            if(getAuthor.isEmpty()){
                throw new GenericException("Author name does not exist");
            }
            authors.add(getAuthor.get());
        }



        // update Book
        getBook.get().setActive(bookRequest.isActive());
        getBook.get().setTitle(bookRequest.getTitle());
        getBook.get().setIsbn(bookRequest.getIsbn());
        getBook.get().setPublicationYear(bookRequest.getPublicationYear());
        getBook.get().setImageUrl(bookRequest.getImageUrl());
        getBook.get().setUnitPrice(bookRequest.getUnitPrice());
        getBook.get().setDescription(bookRequest.getSummary());
        getBook.get().setUnitsInStock(bookRequest.getUnitsInStock());
        getBook.get().setDateModified(new Date());
        getBook.get().setGenre(new Genre(getGenre.get().getId(),bookRequest.getGenre()));
        getBook.get().setAuthors(authors);

        //Book newBook = getBook.get().save(book);
        return new GenericResponse<String>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                getBook.get().getTitle());

    }
}
