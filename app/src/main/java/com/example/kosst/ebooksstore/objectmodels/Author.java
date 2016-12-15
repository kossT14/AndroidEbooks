/*
 * Implementation of class Author.
 */
package com.example.kosst.ebooksstore.objectmodels;

/**
 *
 * @author kossT
 */
public class Author {
    
    // List of attributes
    private String id, name, surname;

    public Author(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    // Getter and setter for id attribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for name attribute
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for surname attribute
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
}
