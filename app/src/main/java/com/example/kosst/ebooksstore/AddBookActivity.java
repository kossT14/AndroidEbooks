package com.example.kosst.ebooksstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kosst.ebooksstore.objectmodels.ArtAlbum;
import com.example.kosst.ebooksstore.objectmodels.Author;
import com.example.kosst.ebooksstore.objectmodels.EBook;
import com.example.kosst.ebooksstore.objectmodels.LegaturaCarteAutor;
import com.example.kosst.ebooksstore.objectmodels.Novel;
import com.example.kosst.ebooksstore.objectmodels.TechnicalBook;

import static android.R.attr.category;
import static android.R.attr.title;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Button addBook = (Button) findViewById(R.id.save_book);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddBookActivity.this);
                builder1.setMessage("Do you really want to add this book?");
                builder1.setCancelable(true);


                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                metodaAdaugare();

                                dialog.cancel();
                            }
                        });


                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();


            }

        });

    }


    public void metodaAdaugare()
    {

        final EditText category = (EditText) findViewById(R.id.categorie);
        category.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        final EditText title = (EditText) findViewById(R.id.Titlu);
        title.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        final EditText noPages = (EditText) findViewById(R.id.Numar_pagini);
        noPages.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        final EditText price = (EditText) findViewById(R.id.Pret);
        price.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        final EditText rating = (EditText) findViewById(R.id.Rating);
        rating.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        final EditText authorSurname = (EditText) findViewById(R.id.nume_autor);
        authorSurname.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        final EditText authorName = (EditText) findViewById(R.id.prenume_autor);
        authorName.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        //-------

                if (MainActivity.ds.isValidCategory(category.getText().toString())){
                    if (!title.getText().toString().isEmpty()){
                        if (!noPages.getText().toString().isEmpty()){
                            if (!price.getText().toString().isEmpty()){
                                if (!rating.getText().toString().isEmpty()){
                                    if (!authorSurname.getText().toString().isEmpty()){
                                        if (!authorName.getText().toString().isEmpty()){
                                            String categoryString = category.getText().toString();
                                            Author autor = new Author(MainActivity.ds.generatedAuthorId(),
                                                    authorSurname.getText().toString(),
                                                    authorName.getText().toString());
                                            MainActivity.ds.addAuthor(autor);
                                            switch (categoryString.toLowerCase()) {
                                                case "technical book": {
                                                    TechnicalBook eb = new TechnicalBook(MainActivity.ds.generatedIsbn(),
                                                            title.getText().toString(), Integer.parseInt(noPages.getText().toString()),
                                                            Double.parseDouble(price.getText().toString()),
                                                            Float.parseFloat(rating.getText().toString()));
                                                    MainActivity.ds.addBook(eb);
                                                    MainActivity.ds.addIsbnAndId(eb, autor);
                                                    break;
                                                }
                                                case "novel": {
                                                    Novel eb = new Novel(MainActivity.ds.generatedIsbn(),
                                                            title.getText().toString(), Integer.parseInt(noPages.getText().toString()),
                                                            Double.parseDouble(price.getText().toString()),
                                                            Float.parseFloat(rating.getText().toString()));
                                                    MainActivity.ds.addBook(eb);
                                                    MainActivity.ds.addIsbnAndId(eb, autor);
                                                    break;
                                                }
                                                case "art album": {
                                                    ArtAlbum eb = new ArtAlbum(MainActivity.ds.generatedIsbn(),
                                                            title.getText().toString(), Integer.parseInt(noPages.getText().toString()),
                                                            Double.parseDouble(price.getText().toString()),
                                                            Float.parseFloat(rating.getText().toString()));
                                                    MainActivity.ds.addBook(eb);
                                                    MainActivity.ds.addIsbnAndId(eb, autor);
                                                    break;
                                                }
                                                default: {
                                                    break;
                                                }

                                            }

                                            category.setText("");
                                            title.setText("");
                                            noPages.setText("");
                                            price.setText("");
                                            rating.setText("");
                                            authorSurname.setText("");
                                            authorName.setText("");

                                        }else {
                                            authorName.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                        }
                                    }else {
                                        authorSurname.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                    }
                                }else {
                                    rating.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                }
                            }else {
                                price.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            }
                        }else {
                            noPages.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }
                    }else {
                        title.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }
                }else {
                    category.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }


        //------
    }
}




