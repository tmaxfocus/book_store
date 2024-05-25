package com.tmaxnoda.bookinventory.Core.Domain.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected long id;
    @Column(name="name",length =200)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "book_author",
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            joinColumns = @JoinColumn(name = "book_id")

    )
    private Set<Book> books = new HashSet<>();

    public Author(){}

    public Author(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Author(long id, String name, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name) {
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
