package com.example.kosst.ebooksstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.kosst.ebooksstore.objectmodels.Author;
import com.example.kosst.ebooksstore.objectmodels.DataSourceManager;
import com.example.kosst.ebooksstore.objectmodels.LegaturaCarteAutor;
import com.example.kosst.ebooksstore.objectmodels.Novel;

public class MainActivity extends AppCompatActivity {

    public static DataSourceManager ds = new DataSourceManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addBooksandAuthors();


        Button addBook = (Button) findViewById(R.id.buton_add_book);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(i);


            }
        });

        Button removeBook = (Button) findViewById(R.id.buton_remove_book);
        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RemoveBooksActivity.class);
                startActivity(i);


            }
        });

        Button listBook = (Button) findViewById(R.id.buton_list_books);
        listBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListBooksActivity.class);
                startActivity(i);


            }
        });

        Button updatePrice = (Button) findViewById(R.id.update_price);
        updatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UpdatePriceActivity.class);
                startActivity(i);


            }
        });

        Button updateRating = (Button) findViewById(R.id.update_rating);
        updateRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UpdateRatingActivity.class);
                startActivity(i);


            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addBooksandAuthors(){
        for (int i = 0; i < 10; i++){
            Novel n = new Novel( Integer.toString(i), "Titlu carte " + i, 2 * i, 5*i + 7, i % 5);
            MainActivity.ds.addBook(n);
            Author a = new Author(Integer.toString(i+2), "Nume " + i, "Prenume " + i);
            MainActivity.ds.addAuthor(a);
            MainActivity.ds.addIsbnAndId(n, a);

        }

    }

}
