package edu.pdx.oss.sqliteloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLogin(View view){
        status = 1;
        Bundle b = new Bundle();
        b.putInt("status", status);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void openRegister(View view){
        Bundle b = new Bundle();
        b.putInt("status", status);
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void openUpdate(View view){
        status = 2;
        Bundle b = new Bundle();
        b.putInt("status", status);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void openDelete(View view){
        status = 3;
        Bundle b = new Bundle();
        b.putInt("status", status);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void openAsthmaUndone(View view){
        Intent intent = new Intent(this, AsthmaUndone.class);
        startActivity(intent);
    }
}
