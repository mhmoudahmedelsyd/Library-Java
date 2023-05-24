package com.example.project.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Auth.login;
import com.example.project.Location.MapsActivity;
import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ID = "ID";
    TextView textView_username,textView_id;
    final int requestCode = 101;
    Button aboutUs_Button,FAQS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarprofile_layout);
        requestUserPermissions();
        profileInfo();
        bottomNavigationView = findViewById(R.id.BottomNavigation_profile);
        aboutUs_Button = findViewById(R.id.about_us);
        FAQS = findViewById(R.id.FAQS);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.logout:
                        Logout();
                        return true;
                }
                return false;
            }
        });
aboutUs_Button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), about_us.class));
        overridePendingTransition(0,0);
    }
});
FAQS.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), faqs.class));
        overridePendingTransition(0,0);
    }
});



    }


    void requestUserPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.ACCESS_COARSE_LOCATION"}, this.requestCode);
        }
    }



    public void ShowLocation(View v) {
        Intent intent = new Intent(Profile.this, MapsActivity.class);
        startActivity(intent);
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
        Toast.makeText(this, "Done Logout", Toast.LENGTH_SHORT).show();
    }

    public void profileInfo(){
        textView_username=findViewById(R.id.profile_username);
        textView_id=findViewById(R.id.profile_id);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String username= sharedPreferences.getString(USERNAME,"");
        String id= sharedPreferences.getString(ID,"");
        textView_username.setText(username);
        textView_id.setText(id);

    }


}