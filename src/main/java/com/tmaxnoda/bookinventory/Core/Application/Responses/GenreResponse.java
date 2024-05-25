package com.tmaxnoda.bookinventory.Core.Application.Responses;

public class GenreResponse {

    protected long id;
    private String name;

    public GenreResponse(){}
    public GenreResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreResponse(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
