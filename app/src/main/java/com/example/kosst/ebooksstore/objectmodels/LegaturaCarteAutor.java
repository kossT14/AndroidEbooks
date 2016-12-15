package com.example.kosst.ebooksstore.objectmodels;

/**
 * Created by kossT on 15.12.2016.
 */

public class LegaturaCarteAutor {

    private String idAutor;
    private String isbnCarte;

    public LegaturaCarteAutor(String idAutor, String isbnCarte) {
        this.idAutor = idAutor;
        this.isbnCarte = isbnCarte;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getIsbnCarte() {
        return isbnCarte;
    }

    public void setIsbnCarte(String isbnCarte) {
        this.isbnCarte = isbnCarte;
    }
}
