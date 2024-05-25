package com.tmaxnoda.bookinventory.Core.Domain.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="genre")
public class Genre{

    //@Column(name="name", length =40, nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected long id;

    @Column(name="name",length =200,unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private Set<Book> books;

    public Genre(){}
    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id) {
        this.id = id;


    }

    public Genre(long id, String name) {
        this.id = id;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
