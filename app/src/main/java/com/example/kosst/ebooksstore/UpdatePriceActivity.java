package com.example.kosst.ebooksstore;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.kosst.ebooksstore.adapters.RecyclerViewAdapterUpdatePrice;

public class UpdatePriceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void createRecyclerView(){

        Context context;
        RecyclerView recyclerView;
        RelativeLayout relativeLayout;
        RecyclerView.Adapter recyclerViewAdapter;
        RecyclerView.LayoutManager recyclerViewLayoutManager;

        context = getApplicationContext();

        relativeLayout = (RelativeLayout) findViewById(R.id.content_update_price);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerViewLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapterUpdatePrice(this);

        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

    }


}


