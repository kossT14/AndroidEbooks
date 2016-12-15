/*
 * Implementation of class ArtAlbum, 
 * subclass of EBook
 */
package com.example.kosst.ebooksstore.objectmodels;

import java.util.List;

/**
 *
 * @author kossT
 */
public class ArtAlbum extends EBook {
    
    // Inherited constructor
    public ArtAlbum(String isbn, String title, int noOfPages,double price, float rating) {
        super(isbn, title, noOfPages, price, rating);
    }

    public ArtAlbum() {
    }
}
