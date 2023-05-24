package com.example.project.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.project.R;

public class about_us extends AppCompatActivity {

    LinearLayout visitWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        SubscribeOnClick();
    }

    public void SubscribeUs(String s){
        try{
            Uri uri=Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }catch (Exception e){
            Toast.makeText(this, "No WebSite Linked", Toast.LENGTH_SHORT).show();
        }
    }
    public void SubscribeOnClick(){
        visitWebsite=findViewById(R.id.visitWebsite);
        visitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubscribeUs("https://www.facebook.com/mhmoud.sydd");
            }
        });
    }
}