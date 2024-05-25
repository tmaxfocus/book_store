package com.tmaxnoda.bookinventory.Core.Application.Requests;


import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BookRequest {

    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title must contain only letters")
    @NotNull(message="title is mandatory")
    @NotBlank(message = "invalid title")
    private String title;
    @Pattern(regexp ="^[0-9-]*$",message = "ISBN must contain numbers and hyphen alone")
    @NotNull(message="ISBN is mandatory")
    @NotBlank(message = "invalid ISBN")
    private String isbn;

    @Pattern(regexp ="^[0-9]+$",message = "Year of publication must contain numbers alone")
    @NotNull(message="publicationYear is mandatory")
    @NotBlank(message = "invalid publicationYear")
    private String publicationYear;

    @URL(message = "Invalid URL format for image")
    private String imageUrl;
    @DecimalMin(value = "0.00", inclusive = true, message = "Unit price must be greater than or equal to 0.00")
    private BigDecimal unitPrice;

    @NotNull(message="summary is mandatory")
    @NotBlank(message = "summary cannot be blank")
    private String summary;
    private boolean active;

    @Min(value = 0, message = "Units in stock must be greater than or equal to 0")
    @Max(value = 1000, message = "Units in stock cannot exceed 1000")
    private int unitsInStock;

    @NotNull(message="genre is mandatory")
    @NotBlank(message = "genre cannot be blank")
    private String genre;

    @NotNull(message="author is mandatory")
    private ArrayList<String> authors= new ArrayList<>();

    public BookRequest(){}

    public BookRequest(String title, String isbn, String publicationYear, String imageUrl, BigDecimal unitPrice, String summary, boolean active, int unitsInStock, String genre, ArrayList<String> authors) {
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
