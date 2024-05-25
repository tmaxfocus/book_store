package com.tmaxnoda.bookinventory.Core.Application.Responses;

public class AuthorResponse {

    protected long id;

    private String name;

    public AuthorResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorResponse(String name) {

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
