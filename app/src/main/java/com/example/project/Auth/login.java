package com.example.project.Auth;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.model.Account;
import com.example.project.Database.AccountDataBase;
import com.example.project.R;
import com.example.project.Screens.home;

public class login extends AppCompatActivity {

    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    private TextView tvErrorMessage;
    private EditText etUserName,etPassword;
    private CheckBox checkBoxSaveLogin;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        tvErrorMessage=(TextView)findViewById(R.id.tvErrorMessage);
        etUserName=(EditText)findViewById(R.id.etUserName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        checkBoxSaveLogin=(CheckBox)findViewById(R.id.checkBoxSaveLogin);
        saveLogin();

    }
    public void loginButton(View view) {
        if(etUserName.getText().toString().isEmpty()){
            tvErrorMessage.setText("user name is required");
        }else if(etPassword.getText().toString().isEmpty()||etPassword.getText().toString().length()<8){
            if(etPassword.getText().toString().isEmpty())tvErrorMessage.setText("Password is required");
            else tvErrorMessage.setText("Password is not correct");
        }else{
            account=new Account(etUserName.getText().toString(),etPassword.getText().toString());
            checkLogin(account);
        }
    }

    public boolean checkLogin(Account account){
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

        AccountDataBase dp=new AccountDataBase(this);
        if(dp.selectUniqueAccount(account)){
            if (checkBoxSaveLogin.isChecked()) {
                saveInSharedPreferences();
            }
            Intent i=new Intent(getApplicationContext(), home.class);
            startActivity(i);
            finish();
            Toast.makeText(this, "UserIs:"+sharedPreferences.getString(USERNAME,"")+" PassWord "+sharedPreferences.getString(PASSWORD,""), Toast.LENGTH_SHORT).show();

//            Toast.makeText(this, "welcome  "+loadFromSharedPreferences(), Toast.LENGTH_LONG).show();
            setEditTextEmpty(etUserName);
            setEditTextEmpty(etPassword);
            setTextViewEmpty(tvErrorMessage);
        }else {
            setEditTextEmpty(etUserName);
            setEditTextEmpty(etPassword);
            tvErrorMessage.setText("username or password is wrong");
        }

        return false;
    }
    public void saveInSharedPreferences(){
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, etUserName.getText().toString());
        editor.putString(PASSWORD, etPassword.getText().toString());
        editor.putBoolean("SaveLogin", true);
        editor.apply();
    }

    public String loadFromSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME,"");
    }
    public void signUpTxtView(View v) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }
    public void setEditTextEmpty(EditText editText){
        editText.setText("");
    }
    public void setTextViewEmpty(TextView textView){
        textView.setText("");
    }
    public void saveLogin(){
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getString(USERNAME,"")=="" &&sharedPreferences.getString(PASSWORD,"")==""){


        }else{
            Intent i=new Intent(getApplicationContext(),home.class);
            startActivity(i);
            finish();
        }
    }

}