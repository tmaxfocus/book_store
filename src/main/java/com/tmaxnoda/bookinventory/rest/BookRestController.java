package com.tmaxnoda.bookinventory.rest;

import com.tmaxnoda.bookinventory.Core.Application.Requests.BookRequest;
import com.tmaxnoda.bookinventory.Core.Application.Responses.BookResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenericResponse;
import com.tmaxnoda.bookinventory.Infrastructure.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public GenericResponse<String>Post(@RequestBody @Valid  BookRequest newBook){
        return this.bookService.AddNewBook(newBook);
    }

    @GetMapping("/book")
    public GenericResponse<ArrayList<BookResponse>> Get(){
        return this.bookService.GetBooks();
    }

    @GetMapping("/book/{genreId}")
    public GenericResponse<BookResponse>Get(@PathVariable Long genreId){
        return this.bookService.GetAuthorById(genreId);
    }

    @PutMapping("/book")
    public GenericResponse<String>Update(@RequestBody @Valid  BookRequest updateBook){
        return this.bookService.UpdateBook(updateBook);
    }
}
