package com.tmaxnoda.bookinventory.rest;

import com.tmaxnoda.bookinventory.Core.Application.Requests.AuthorRequest;
import com.tmaxnoda.bookinventory.Core.Application.Responses.AuthorResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenericResponse;
import com.tmaxnoda.bookinventory.Infrastructure.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AuthorRestController {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public GenericResponse<List<AuthorResponse>> Get(){
        return this.authorService.GetAuthors();
    }

    @PostMapping("/author")
    public GenericResponse<AuthorResponse> Post(@RequestBody AuthorRequest newAuthor){
        return this.authorService.AddNewAuthor(newAuthor.getName());
    }

    @PutMapping("/author/update")
    public GenericResponse<AuthorResponse> Update(@RequestBody AuthorRequest newAuthor){
        return this.authorService.UpdateAuthor(newAuthor);
    }

    @GetMapping("/author/{genreId}")
    public GenericResponse<AuthorResponse>  Get(@PathVariable Long genreId){
        return this.authorService.GetAuthorById(genreId);
    }

    @DeleteMapping("/author/{genreId}")
    public GenericResponse<?> Delete(@PathVariable Long genreId){
        return this.authorService.RemoveAuthorById(genreId);
    }
}
