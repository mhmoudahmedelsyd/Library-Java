package com.example.project.BookSc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.project.Database.BookDatabase;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Sc_Book extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    BookDatabase myDB;
    ArrayList<String> book_id,book_title,book_author,book_pages;
    CustomAdapterSc customAdapterSc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc_book);
        recyclerView =findViewById(R.id.recycleView);
        floatingActionButton=findViewById(R.id.add_buttom);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AddBookSC.class);
                startActivity(i);
            }
        });

        myDB=new BookDatabase(Sc_Book.this);
        book_title=new ArrayList<>();
        book_id=new ArrayList<>();
        book_author=new ArrayList<>();
        book_pages=new ArrayList<>();
        displayData();
        customAdapterSc=new CustomAdapterSc(Sc_Book.this,this,book_title,book_author,book_pages,book_id);
        recyclerView.setAdapter(customAdapterSc);
        recyclerView.setLayoutManager(new LinearLayoutManager(Sc_Book.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            recreate();
        }
    }

    void displayData(){
        Cursor cursor=myDB.SelectAllBooks();
        if(cursor.getCount()==0){
            Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));

            }
        }

    }
}