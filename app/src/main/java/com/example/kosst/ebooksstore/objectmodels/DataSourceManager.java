package com.example.kosst.ebooksstore.objectmodels;

import java.util.ArrayList;

/**
 * Created by kossT on 15.12.2016.
 */

public class DataSourceManager {

    private ArrayList<Author> listaAutori;
    private ArrayList<EBook> listaCarti;
    private ArrayList<LegaturaCarteAutor> listaLegatura;

    public DataSourceManager() {
        this.listaAutori = new ArrayList<>();
        this.listaCarti = new ArrayList<>();
        this.listaLegatura = new ArrayList<>();

    }

    public void addBook(EBook b){
        listaCarti.add(b);
    }

    public void removeBook(String s){
        for (EBook e: listaCarti){
            if (e.getIsbn().equalsIgnoreCase(s)){
                listaCarti.remove(e);
                break;
            }
        }
    }

    public void addAuthor(Author a){
        listaAutori.add(a);
    }

    public void removeAuthor(String s){
        for (Author a: listaAutori){
            if (a.getId().equalsIgnoreCase(s)){
                listaAutori.remove(a);
                break;
            }
        }
    }

    public void addIsbnAndId(EBook e, Author a){
        listaLegatura.add(new LegaturaCarteAutor(e.getIsbn(), a.getId()));
    }

    public void removeListaLegatura(String s){
        for (LegaturaCarteAutor e: listaLegatura){
            if (e.getIsbnCarte().equalsIgnoreCase(s)){
                listaLegatura.remove(e);
            }
        }
    }




}
