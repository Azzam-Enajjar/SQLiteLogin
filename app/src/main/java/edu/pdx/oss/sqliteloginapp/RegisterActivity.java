package edu.pdx.oss.sqliteloginapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    EditText USER_NAME_TEXT, USER_PASS_TEXT, CON_PASS_TEXT;
    String user_name, user_pass, con_pass;
    Button REGISTER_BUTTON;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        USER_NAME_TEXT = (EditText) findViewById(R.id.usernameText);
        USER_PASS_TEXT = (EditText) findViewById(R.id.passwordText);
        CON_PASS_TEXT = (EditText) findViewById(R.id.confirmText);
        REGISTER_BUTTON = (Button) findViewById(R.id.registerButton);

        REGISTER_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = USER_NAME_TEXT.getText().toString();
                user_pass = USER_PASS_TEXT.getText().toString();
                con_pass = CON_PASS_TEXT.getText().toString();

                if(!(user_pass.equals(con_pass))){
                    Toast.makeText(getBaseContext(), "Passwords are not match", Toast.LENGTH_LONG ).show();
                    USER_NAME_TEXT.setText("");
                    USER_PASS_TEXT.setText("");
                    CON_PASS_TEXT.setText("");
                }
                else{
                    DatabaseOperations DB = new DatabaseOperations(ctx);
                    DB.putInformation(DB, user_name, user_pass);
                    Toast.makeText(getBaseContext(), "Registeration success", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });



    }


}
