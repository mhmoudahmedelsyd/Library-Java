package com.example.project.Auth;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.model.Account;
import com.example.project.Database.AccountDataBase;
import com.example.project.R;

public class Register extends AppCompatActivity {
    private TextView errorTxtView;
    private EditText etUserName,etPassword,etConfirmPassword,etId;
    Account account;
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";

    public static final String ID = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        errorTxtView=(TextView)findViewById(R.id.errorTxtView) ;
        etUserName=(EditText)findViewById(R.id.etUserName);
        etId=(EditText)findViewById(R.id.etId);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etConfirmPassword=(EditText)findViewById(R.id.etConfirmPassword) ;
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void logInTxtView(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
    }
    public void signUpButton(View v){

        if(etUserName.getText().toString().isEmpty()){
            errorTxtView.setText("user name is required");
        }else if(etId.getText().toString().isEmpty()||etId.getText().toString().length()!=14){
            if(etId.getText().toString().isEmpty())errorTxtView.setText("ID is required");
            else errorTxtView.setText("ID is not correct");
        }else if(etPassword.getText().toString().isEmpty()){
            errorTxtView.setText("password is required");
        }else if(etConfirmPassword.getText().toString().isEmpty()){
            errorTxtView.setText("confirm password is required");
        }else  if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())
                ||etConfirmPassword.getText().toString().length()<8){
            if(etConfirmPassword.getText().toString().length()<8)
                errorTxtView.setText("password should more than or equals 8 character");
            else errorTxtView.setText("password should equals confirm password");
        }else{
            account=new Account(etUserName.getText().toString(),etPassword.getText().toString(),etId.getText().toString());
            SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(ID, etId.getText().toString());
            editor.apply();
            saveInDatabase(account);
        }

    }


    public void saveInDatabase(Account account){
        AccountDataBase dp=new AccountDataBase(this);
        if(dp.selectUniqueID(account))
            errorTxtView.setText("ID is Exists before");
        else if(!dp.insertAccount(account))
            errorTxtView.setText("USERNAME is Exists before");
        else{
            etUserName.setText("");
            etPassword.setText("");
            etConfirmPassword.setText("");
            etId.setText("");
            errorTxtView.setText("");
            Toast.makeText(this, "Done You Can Login Now", Toast.LENGTH_LONG).show();


            startActivity(new Intent(getApplicationContext(),login.class));
        }

    }
}