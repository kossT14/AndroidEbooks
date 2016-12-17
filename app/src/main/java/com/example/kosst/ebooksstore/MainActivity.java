package com.example.kosst.ebooksstore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kosst.ebooksstore.database.DB;
import com.example.kosst.ebooksstore.database.DbProvider;
import com.example.kosst.ebooksstore.objectmodels.ArtAlbum;
import com.example.kosst.ebooksstore.objectmodels.DataSourceManager;
import com.example.kosst.ebooksstore.objectmodels.Novel;
import com.example.kosst.ebooksstore.objectmodels.TechnicalBook;

import static com.example.kosst.ebooksstore.objectmodels.DataSourceManager.TAG_LOG_BOOKS;

public class MainActivity extends AppCompatActivity {

    public static DataSourceManager ds = new DataSourceManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        addBooksAndAuthorsToDB();
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

        Button testDB = (Button) findViewById(R.id.test_DB);
        testDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = DbProvider.getInstance().query( true, DB.Books.TABLE_NAME, null, null,
                        null, null, null, null, null );
                c.moveToFirst();
                String s = "";
                while (c.moveToNext()){
                    s+=(c.getString(c.getColumnIndex(DB.Books.TITLE)));
                    s+=(c.getString(c.getColumnIndex(DB.Books.PRICE)));
                }
                Log.w(TAG_LOG_BOOKS, s + " B R ");
                Toast.makeText(MyApplication.getAppContext(), s, Toast.LENGTH_LONG).show();


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
        Cursor c = DbProvider.getInstance().query( true, DB.Books.TABLE_NAME, null, null,
                null, null, null, null, null );
        c.moveToFirst();
        String bookTitle = "";
        String bookCategory = "";
        double price = 0d;
        float rating = 0f;
        String isbn = "";
        while (c.moveToNext()){
            bookTitle = c.getString(c.getColumnIndex(DB.Books.TITLE));
            price = Double.parseDouble(c.getString(c.getColumnIndex(DB.Books.PRICE)));
            rating = Float.parseFloat(c.getString(c.getColumnIndex(DB.Books.RATING)));
            isbn = c.getString(c.getColumnIndex(DB.Books.ID));
            bookCategory = c.getString(c.getColumnIndex(DB.Books.CATEGORY));
            if (bookCategory.equalsIgnoreCase("Technical")){
                MainActivity.ds.addBook(new TechnicalBook(isbn,bookTitle, 20, price, rating));
            }else if (bookCategory.equalsIgnoreCase("Novel")){
                MainActivity.ds.addBook(new Novel(isbn,bookTitle, 30, price, rating));
            }else {
                MainActivity.ds.addBook(new ArtAlbum(isbn,bookTitle, 40, price, rating));
            }
        }


    }

    public void addBooksAndAuthorsToDB(){

        if (DbProvider.getInstance() == null){
            Log.w(TAG_LOG_BOOKS, "Patras e praf la pokere!! LASA-TEee!");

        }

        for (int i = 0; i <  50; i++){
            ContentValues record = new ContentValues();
            String idCarte = Integer.toString(i);
            record.put( DB.Books.ID, idCarte );
            record.put( DB.Books.TITLE, "Titlu carte " + i);
            record.put( DB.Books.PRICE, Integer.toString(5 *i));
            record.put(DB.Books.RATING, Integer.toString(i%5));
            if (i % 3 == 0) {
                record.put(DB.Books.CATEGORY, "Technical ");
            }else if (i % 3 == 1){
                record.put(DB.Books.CATEGORY, "Novel ");
            }else {
                record.put(DB.Books.CATEGORY, "Art album ");
            }

            DbProvider.getInstance().insert( DB.Books.TABLE_NAME, record );

            ContentValues recordAuthor = new ContentValues();
            String idAuthor = Integer.toString(i);
            recordAuthor.put( DB.Authors.ID, idAuthor );
            recordAuthor.put( DB.Authors.SURNAME, "Surname " + i);
            recordAuthor.put( DB.Authors.NAME, "Name " + i);

            DbProvider.getInstance().insert( DB.Authors.TABLE_NAME, recordAuthor );

            ContentValues recordAuthorBookLink = new ContentValues();
            recordAuthorBookLink.put( DB.BookAuthorLink.ID, idCarte );
            recordAuthorBookLink.put( DB.BookAuthorLink.ISBN, idAuthor);
            DbProvider.getInstance().insert( DB.BookAuthorLink.TABLE_NAME, recordAuthorBookLink );

        }

    }




}
