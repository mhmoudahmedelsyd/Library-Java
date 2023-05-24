package com.example.project.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.project.Auth.login;
import com.example.project.R;
import com.example.project.BookSc.Sc_Book;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    BottomNavigationView bottomNavigationView;
    TextView SubscribeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        ImageSlider imageSlider=findViewById(R.id.imageSlider);
        ArrayList<SlideModel>slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


        bottomNavigationView=findViewById(R.id.BottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:

                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.logout:
                        Logout();
                        return true;
                }
                return false;
            }
        });
        SubscribeOnClick();

    }

     public void Logout(){
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME,"");
        editor.putString(PASSWORD,"" );
        editor.apply();
        Toast.makeText(this, "UserIs:"+sharedPreferences.getString(USERNAME,"")+" PassWord "+sharedPreferences.getString(PASSWORD,""), Toast.LENGTH_SHORT).show();

        Intent i=new Intent(getApplicationContext(), login.class);
        startActivity(i);
        finish();
//        Toast.makeText(this, "Done Logout", Toast.LENGTH_SHORT).show();
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
        SubscribeText=findViewById(R.id.Subscribetxt);
        SubscribeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubscribeUs("https://www.facebook.com/mhmoud.sydd");
            }
        });
    }


    public void moveListView(View view){
        Intent intent = new Intent(this, Sc_Book.class  );
        startActivity(intent);
    }
}