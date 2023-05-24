package com.example.project.BookSc;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.ArrayList;

public class CustomAdapterSc extends RecyclerView.Adapter<CustomAdapterSc.MyViewHolder> {
    ArrayList book_id,book_title,book_author,book_pages;
    Context context;
    Activity activity;
    int position;
    CustomAdapterSc(Activity activity,Context context,ArrayList book_title,ArrayList book_author,ArrayList book_pages,ArrayList book_id){
        this.context=context;
        this.activity=activity;
        this.book_title=book_title;
        this.book_author=book_author;
        this.book_pages=book_pages  ;
        this.book_id=book_id  ;
    }
    @NonNull
    @Override
    public CustomAdapterSc.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.items_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterSc.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.item_book_title.setText(String.valueOf(book_title.get(position)));
    holder.item_book_author.setText(String.valueOf(book_author.get(position)));
    holder.item_book_pages.setText(String.valueOf(book_pages.get(position)));
    holder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(context,UpdateBookSC.class);
            i.putExtra("id",String.valueOf(book_id.get(position)));
            i.putExtra("title",String.valueOf(book_title.get(position)));
            i.putExtra("author",String.valueOf(book_author.get(position)));
            i.putExtra("pages",String.valueOf(book_pages.get(position)));
            activity.startActivityForResult(i,1);
        }
    });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_book_title,item_book_author,item_book_pages;
        ImageView imageViewEdit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_book_title=itemView.findViewById(R.id.item_book_title);
            item_book_author=itemView.findViewById(R.id.item_book_author);
            item_book_pages=itemView.findViewById(R.id.item_book_pages);
            imageViewEdit=itemView.findViewById(R.id.item_edit);
        }
    }
}
