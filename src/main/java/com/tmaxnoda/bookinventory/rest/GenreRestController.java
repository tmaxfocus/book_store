package com.tmaxnoda.bookinventory.rest;

import com.tmaxnoda.bookinventory.Core.Application.Requests.GenreRequest;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenericResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenreResponse;
import com.tmaxnoda.bookinventory.Infrastructure.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class GenreRestController {

    private final GenreService genreService;

    @Autowired
    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genre")
    public GenericResponse<List<GenreResponse>> Get(){
        return this.genreService.GetGenres();
    }

    @PostMapping("/genre")
    public GenericResponse<GenreResponse> Post(@RequestBody GenreRequest newGenre){
        return genreService.AddNewGenre(newGenre.getName());
    }

    @PutMapping("/genre/update")
    public GenericResponse<GenreResponse> Update(@RequestBody GenreRequest newGenre){
        return this.genreService.UpdateGenre(newGenre);
    }

    @GetMapping("/genre/{genreId}")
    public GenericResponse<GenreResponse>  Get(@PathVariable Long genreId){
        return this.genreService.GetGenreById(genreId);
    }

    @DeleteMapping("/genre/{genreId}")
    public GenericResponse<GenreResponse> Delete(@PathVariable Long genreId){
        return this.genreService.RemoveGenreById(genreId);
    }
}
