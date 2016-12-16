package com.example.kosst.ebooksstore.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kosst.ebooksstore.MainActivity;
import com.example.kosst.ebooksstore.R;

/**
 * Created by kossT on 15.12.2016.
 */

public class RecyclerViewAdapterUpdatePrice extends RecyclerView.Adapter<RecyclerViewAdapterUpdatePrice.ViewHolder>{

    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public interface OnRecyclerItemClickListener {

        void onRecyclerItemClick(String data);
    }


    public RecyclerViewAdapterUpdatePrice(Context context1){
        this.context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView bookTitleTV;

        public TextView priceTV;
        public Button updatePriceButton;



        public ViewHolder(View v){
            super(v);
            bookTitleTV = (TextView)v.findViewById(R.id.item_titlu_carte);
            priceTV = (TextView)v.findViewById(R.id.item_pret_carte);
            updatePriceButton = (Button)v.findViewById(R.id.update_price_button);
        }
    }

    @Override
    public RecyclerViewAdapterUpdatePrice.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_update_price,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){

        holder.bookTitleTV.setText(MainActivity.ds.getListaCarti().get(position).getTitle());
        //holder.authorTv.setText(SubjectValues.get(position).getTitle());
        holder.priceTV.setText(Double.toString(MainActivity.ds.getListaCarti().get(position).getPrice()));
        holder.updatePriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Enter updated price:");
                builder1.setCancelable(true);

                final EditText input = new EditText(RecyclerViewAdapterUpdatePrice.this.context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder1.setView(input);


                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                MainActivity.ds.updatePrice(position, Double.parseDouble(input.getText().toString()) );
                                notifyDataSetChanged();
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

    @Override
    public int getItemCount(){

        return MainActivity.ds.getListaCarti().size();
    }
}
