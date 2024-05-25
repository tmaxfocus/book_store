package com.tmaxnoda.bookinventory.Infrastructure.Service;


import com.tmaxnoda.bookinventory.Core.Application.Exceptions.GenericException;
import com.tmaxnoda.bookinventory.Core.Application.GenreRepository;
import com.tmaxnoda.bookinventory.Core.Application.Requests.GenreRequest;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenericResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.GenreResponse;
import com.tmaxnoda.bookinventory.Core.Application.Responses.ResponseMessage;
import com.tmaxnoda.bookinventory.Core.Domain.entities.Genre;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {


    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public GenericResponse<List<GenreResponse>> GetGenres(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
       List<Genre> genres = this.genreRepository.findAll(sort);
       List<GenreResponse> genreResponseList = new ArrayList<>();
       for(Genre genre : genres){
           genreResponseList.add(new GenreResponse(genre.getId(),genre.getName()));
       }
        return new GenericResponse<List<GenreResponse>>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                genreResponseList);
    }
    public GenericResponse<GenreResponse> AddNewGenre(String genreName){
        //check if Genre already exist
       Optional<Genre> getGenre =  genreRepository.findGenreByName(genreName);

       if(getGenre.isPresent()){
           throw new GenericException("Genre  already exist");
       }
        Genre newGenre = genreRepository.save(new Genre(genreName));
        return new GenericResponse<GenreResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                new GenreResponse(newGenre.getId(),newGenre.getName()));

    }

    @Transactional
    public GenericResponse<GenreResponse> UpdateGenre(GenreRequest genre){

        Genre updateGenre = new Genre(genre.getId(),genre.getName());
        //check if Genre already exist
        Optional<Genre> getGenre =  genreRepository.findById(updateGenre.getId());

        if(getGenre.isEmpty()){
            throw new GenericException("Genre name already exist");
        }
        if(getGenre.get().getName() == genre.getName()){
            throw new GenericException("Nane already exist ");
        }


        getGenre.get().setName(updateGenre.getName());

        return new GenericResponse<GenreResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                null);
    }

    public GenericResponse<GenreResponse>  GetGenreById(Long id){
        Optional<Genre> getGenre= genreRepository.findById(id);
        if(getGenre.isEmpty()){
            throw new GenericException("Invalid genre request - " + id);
        }
        return new GenericResponse<GenreResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                new GenreResponse(getGenre.get().getId(),getGenre.get().getName()));

    }


    public GenericResponse<GenreResponse> RemoveGenreById(Long id){
        Optional<Genre> getGenre= genreRepository.findById(id);
        if(getGenre.isEmpty()){
            throw new IllegalStateException("Invalid genre request");
        }
        genreRepository.deleteById(id);

        return new GenericResponse<GenreResponse>(HttpStatus.OK.value(),
                ResponseMessage.Success,System.currentTimeMillis(),
                null);
    }
}
