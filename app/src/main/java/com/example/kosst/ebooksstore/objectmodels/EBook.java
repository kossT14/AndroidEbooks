/*
 * Implementation of abstract class EBook
 */
package com.example.kosst.ebooksstore.objectmodels;

import java.util.List;


/**
 *
 * @author kossT
 */
public abstract class EBook {
    // List of attributes
    private String isbn;
    private String title;
    private int noOfPages;
    private double price;
    private float rating;
    private int noOfRatings;
    
    // Default constructor
    public EBook(){
        super();
    }

    // Convenience constructor
    public EBook(String isbn, String title, int noOfPages, double price, float rating) {
        this.isbn = isbn;
        this.title = title;
        this.noOfPages = noOfPages;
        this.price = price;
        this.rating = rating;
    }
    
    // ISBN getter and setter
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // eBook title getter and setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // eBook no of pages getter and setter
    public int getNoOfPages() {
        return noOfPages;
    }
    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    // eBook list of authors  - getter and setter    
//    public List<Author> getAuthor() {
//        return author;
//    }
//    public void setAuthor(List<Author> author) {
//        this.author = author;
//    }

    // eBook price getter and setter
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // eBook rating getter
    public float getRating() {
        return rating;
    }
    // eBook rating setter updates current rating 
    // when a new rating is being added
    public void setRating(float rating) {
        this.rating = ((this.rating * this.noOfRatings) + rating) 
                    / (this.noOfRatings + 1);
    }

    // eBook no of ratings getter
    public int getNoOfRatings() {
        return noOfRatings;
    }
    // eBook no of ratings setter
    // whenever a new rating is added, it keeps track of total no of ratings
    public void setNoOfRatings(int noOfRatings) {
        this.noOfRatings += noOfRatings;
    }
    
   
}
