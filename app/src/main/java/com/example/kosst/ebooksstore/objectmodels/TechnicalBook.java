/*
 * Implementation of class TechnicalBook, 
 * subclass of EBook
 */
package com.example.kosst.ebooksstore.objectmodels;

import java.util.List;


/**
 *
 * @author kossT
 */
public class TechnicalBook extends EBook {
    
    // Inherited constructor
    public TechnicalBook(String isbn, String title, int noOfPages,  double price, float rating) {
        super(isbn, title, noOfPages,  price, rating);
    }

    
    
}
