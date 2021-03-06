package com.example.kosst.ebooksstore.objectmodels;

import android.util.Log;

import com.example.kosst.ebooksstore.MainActivity;

import java.util.ArrayList;

/**
 * Created by kossT on 15.12.2016.
 */

public class DataSourceManager {

    private ArrayList<Author> listaAutori;
    private ArrayList<EBook> listaCarti;
    private ArrayList<LegaturaCarteAutor> listaLegatura;

    public static final String TAG_LOG_BOOKS = "lista";



    public DataSourceManager() {
        this.listaAutori = new ArrayList<>();
        this.listaCarti = new ArrayList<>();
        this.listaLegatura = new ArrayList<>();

    }

    public ArrayList<Author> getListaAutori() {
        return listaAutori;
    }

    public ArrayList<EBook> getListaCarti() {
        return listaCarti;
    }

    public ArrayList<LegaturaCarteAutor> getListaLegatura() {
        return listaLegatura;
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

    public void removeBookByIndex(int index){
        listaCarti.remove(index);
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

    public boolean isValidCategory(String s){
        boolean isValid = false;
        if ((s.equalsIgnoreCase("technical book"))
                || (s.equalsIgnoreCase("novel"))
                || (s.equalsIgnoreCase("art album")) ) {
            isValid = true;
        }
        return isValid;
    }

    public String generatedIsbn (){
        if (listaCarti.isEmpty()) {
            return "0";
        }else{
            return Integer.toString(listaCarti.size()) ;
        }
    }

    public String generatedAuthorId (){
        if (listaAutori.isEmpty()) {
            return "0";
        }else{
            return Integer.toString(listaAutori.size()) ;
        }
    }

    public void displayBookList() {
        for (EBook libraryCatalog1 : listaCarti) {
            Log.w(TAG_LOG_BOOKS, libraryCatalog1.getIsbn() + " "
                    + libraryCatalog1.getTitle());

        }
    }

    public void displayAuthorList() {
        for (Author a1 : listaAutori) {
            Log.w(TAG_LOG_BOOKS, a1.getId() + " "
                    + a1.getSurname() + " "
                    + a1.getName());

        }
    }

    public void updatePrice(int index, double price) {
        for (int i = 0; i < MainActivity.ds.listaCarti.size(); i++ ){
            if (i == index) {
                listaCarti.get(i).setPrice(price);
            }
        }
    }



}
