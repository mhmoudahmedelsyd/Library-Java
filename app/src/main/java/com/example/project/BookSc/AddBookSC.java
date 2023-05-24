package com.example.project.BookSc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.Database.BookDatabase;
import com.example.project.R;

public class AddBookSC extends AppCompatActivity {
    EditText txt_title,txt_author,txt_page;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_sc);
        txt_title=findViewById(R.id.title_input);
        txt_author=findViewById(R.id.author_input);
        txt_page=findViewById(R.id.Page_input);
        add_button=findViewById(R.id.save_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDatabase Mydb=new BookDatabase(AddBookSC.this);
                Mydb.insertBook(txt_title.getText().toString().trim(),txt_author.getText().toString().trim(),
                        Integer.valueOf(txt_page.getText().toString().trim()));
                startActivity(new Intent(getApplicationContext(),Sc_Book.class));
                overridePendingTransition(0,0);

            }
        });
    }
}