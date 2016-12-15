/*
 * Implementation of class Novel, 
 * subclass of EBook
 */
package com.example.kosst.ebooksstore.objectmodels;

import java.util.List;

/**
 *
 * @author kossT
 */
public class Novel extends EBook {
    
    // Inherited constructor
    public Novel(String isbn, String title, int noOfPages, double price, float rating) {
        super(isbn, title, noOfPages, price, rating);
    }

    public Novel() {
    }
}
