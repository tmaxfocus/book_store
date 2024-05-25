package com.tmaxnoda.bookinventory.Core.Application.Responses;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BookResponse {


    private Long id;
    private String title;

    private String isbn;


    private String publicationYear;


    private String imageUrl;

    private BigDecimal unitPrice;


    private String summary;
    private boolean active;


    private int unitsInStock;


    private String genre;


    private ArrayList<String> authors = new ArrayList<>();

    public BookResponse() {
    }

    public BookResponse(Long id,String title, String isbn, String publicationYear, String imageUrl, BigDecimal unitPrice, String summary, boolean active, int unitsInStock, String genre, ArrayList<String> authors) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.imageUrl = imageUrl;
        this.unitPrice = unitPrice;
        this.summary = summary;
        this.active = active;
        this.unitsInStock = unitsInStock;
        this.genre = genre;
        this.authors = authors;
    }
    public BookResponse(String title, String isbn, String publicationYear, String imageUrl, BigDecimal unitPrice, String summary, boolean active, int unitsInStock, String genre, ArrayList<String> authors) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.imageUrl = imageUrl;
        this.unitPrice = unitPrice;
        this.summary = summary;
        this.active = active;
        this.unitsInStock = unitsInStock;
        this.genre = genre;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
}

