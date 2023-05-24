package com.example.project.BookSc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.Database.BookDatabase;
import com.example.project.R;

public class UpdateBookSC extends AppCompatActivity {
    EditText title_txt,author_txt,pages_txt;
    Button update_button,delete_button;

    String id,title,author,pages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_book_sc);
        title_txt=findViewById(R.id.title_input_update);
        author_txt=findViewById(R.id.author_input_update);
        pages_txt=findViewById(R.id.Page_input_update);
        update_button=findViewById(R.id.update_button);
        delete_button=findViewById(R.id.delete_button);
        getAndSetIntentData();
        ActionBar ab=getSupportActionBar();
        if(ab!=null){
            ab.setTitle(title);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDatabase myDB=new BookDatabase(UpdateBookSC.this);
                title=title_txt.getText().toString().trim();
                author=author_txt.getText().toString().trim();
                pages=pages_txt.getText().toString().trim();
                myDB.updateData(id,title,author,pages);
                startActivity(new Intent(getApplicationContext(),Sc_Book.class));
                finish();

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();

            }
        });


    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("title")&&
                getIntent().hasExtra("author")&&getIntent().hasExtra("pages")){
            //getting data from intent

            id=getIntent().getStringExtra("id");
            title=getIntent().getStringExtra("title");
            author=getIntent().getStringExtra("author");
            pages=getIntent().getStringExtra("pages");
            //setting data
            title_txt.setText(title);
            author_txt.setText(author);
            pages_txt.setText(pages);

        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+title+"?");
        builder.setMessage("Are you sure you want to delete "+title+"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BookDatabase myDB=new BookDatabase(UpdateBookSC.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}