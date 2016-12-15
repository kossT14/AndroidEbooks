package com.example.kosst.ebooksstore.adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kosst.ebooksstore.R;
import com.example.kosst.ebooksstore.objectmodels.EBook;

import java.util.ArrayList;

/**
 * Created by kossT on 15.12.2016.
 */

public class RecyclerViewAdapterRemoveBooks extends RecyclerView.Adapter<RecyclerViewAdapterRemoveBooks.ViewHolder>{

    ArrayList<EBook> SubjectValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public RecyclerViewAdapterRemoveBooks(Context context1, ArrayList<EBook> SubjectValues1){

        this.SubjectValues = SubjectValues1;
        this.context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public TextView authorTv;
        public TextView ratingTv;
        public TextView priceTv;
        public TextView categoryTv;
        public Button removeButton;



        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.item_titlu_carte);
            categoryTv = (TextView)v.findViewById(R.id.item_categorie_carte);
            removeButton = (Button)v.findViewById(R.id.remove_book_button);
        }
    }

    @Override
    public RecyclerViewAdapterRemoveBooks.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        holder.textView.setText(SubjectValues.get(position).getTitle());
        //holder.authorTv.setText(SubjectValues.get(position).getTitle());
        holder.ratingTv.setText(Float.toString(SubjectValues.get(position).getRating()));
        holder.priceTv.setText(Double.toString(SubjectValues.get(position).getPrice()));
        holder.categoryTv.setText(SubjectValues.get(position).getClass().getSimpleName());

    }

    @Override
    public int getItemCount(){

        return SubjectValues.size();
    }
}
