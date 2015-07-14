package edu.pdx.oss.sqliteloginapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText USER_NAME_TEXT, USER_PASS_TEXT;
    String user_name, user_pass;
    Button LOGIN_BUTTON;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        LOGIN_BUTTON = (Button) findViewById(R.id.loginButton);
        USER_NAME_TEXT = (EditText) findViewById(R.id.usernameText);
        USER_PASS_TEXT = (EditText) findViewById(R.id.passwordText);

        LOGIN_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = getIntent().getExtras();
                int status = b.getInt("status");
                if (status == 1){
                    Toast.makeText(getBaseContext(), "Please wait...", Toast.LENGTH_LONG).show();
                    user_name = USER_NAME_TEXT.getText().toString();
                    user_pass = USER_PASS_TEXT.getText().toString();
                    DatabaseOperations dop = new DatabaseOperations(ctx);
                    Cursor CR = dop.getInformation(dop);
                    CR.moveToFirst();
                    boolean loginStatus = false;
                    String name = "";
                    do{
                       if(user_name.equals(CR.getString(0)) && (user_pass.equals(CR.getString(1))) ){
                           loginStatus = true;
                           name = CR.getString(0);
                       }
                    }while(CR.moveToNext());
                    if (loginStatus) {
                        Toast.makeText(getBaseContext(), "Login Success----\n Welcome " + name, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Login Failed----" + name, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else if(status == 2){
                    Toast.makeText(getBaseContext(), "Please wait...", Toast.LENGTH_LONG).show();
                    user_name = USER_NAME_TEXT.getText().toString();
                    user_pass = USER_PASS_TEXT.getText().toString();
                    DatabaseOperations dop = new DatabaseOperations(ctx);
                    Cursor CR = dop.getInformation(dop);
                    CR.moveToFirst();
                    boolean loginStatus = false;
                    String name = "";
                    String pass = "";
                    do{
                        if(user_name.equals(CR.getString(0)) && (user_pass.equals(CR.getString(1))) ){
                            loginStatus = true;
                            name = CR.getString(0);
                            pass = CR.getString(1);
                        }
                    }while(CR.moveToNext());
                    if (loginStatus) {
                        Toast.makeText(getBaseContext(), "Login Success----\n Welcome " + name, Toast.LENGTH_LONG).show();
                        Bundle B = new Bundle();
                        B.putString("user_name", name);
                        B.putString("user_password", pass);
                        Intent intent = new Intent(LoginActivity.this, UpdateActivity.class);
                        intent.putExtras(B);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Login Failed----" + name, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else if(status == 3) {
                    Toast.makeText(getBaseContext(), "Please wait...", Toast.LENGTH_LONG).show();
                    user_name = USER_NAME_TEXT.getText().toString();
                    user_pass = USER_PASS_TEXT.getText().toString();
                    DatabaseOperations dop = new DatabaseOperations(ctx);
                    Cursor CR = dop.getInformation(dop);
                    CR.moveToFirst();
                    boolean loginStatus = false;
                    String name = "";
                    do{
                        if(user_name.equals(CR.getString(0)) && (user_pass.equals(CR.getString(1))) ){
                            loginStatus = true;
                            name = CR.getString(0);
                        }
                    }while(CR.moveToNext());
                    if (loginStatus) {
                        Toast.makeText(getBaseContext(), "Login Success----\n Welcome " + name, Toast.LENGTH_LONG).show();
                        Bundle B = new Bundle();
                        B.putString("user_name", name);
                        Intent intent = new Intent(LoginActivity.this, DeleteActivity.class);
                        intent.putExtras(B);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Login Failed----" + name, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });
    }
}
