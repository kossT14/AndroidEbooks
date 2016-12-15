package com.example.kosst.ebooksstore.adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kosst.ebooksstore.MainActivity;
import com.example.kosst.ebooksstore.R;

/**
 * Created by kossT on 15.12.2016.
 */

public class RecyclerViewAdapterRemoveBooks extends RecyclerView.Adapter<RecyclerViewAdapterRemoveBooks.ViewHolder>{

    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public RecyclerViewAdapterRemoveBooks(Context context1){
        this.context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

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

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_remove,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){

        holder.textView.setText(MainActivity.ds.getListaCarti().get(position).getTitle());
        //holder.authorTv.setText(SubjectValues.get(position).getTitle());
        holder.categoryTv.setText(MainActivity.ds.getListaCarti().get(position).getClass().getSimpleName());
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ds.removeBookByIndex(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount(){

        return MainActivity.ds.getListaCarti().size();
    }
}
