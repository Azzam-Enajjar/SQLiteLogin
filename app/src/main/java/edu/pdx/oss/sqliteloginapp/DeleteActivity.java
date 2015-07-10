package edu.pdx.oss.sqliteloginapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    Bundle b;
    String user_name, user_pass;
    EditText PASSWORD_TEXT;
    Button DELETE_BUTTON;
    DatabaseOperations dop;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        PASSWORD_TEXT = (EditText) findViewById(R.id.passwordText);
        DELETE_BUTTON = (Button) findViewById(R.id.deleteButton);

        b = getIntent().getExtras();
        user_name = b.getString("user_name");

        DELETE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_pass = PASSWORD_TEXT.getText().toString();
                dop = new DatabaseOperations(ctx);
                Cursor CR = dop.getUserPassword(dop, user_name);
                boolean loginStatus = false;
                CR.moveToFirst();
                do{
                    if (user_pass.equals(CR.getString(0).toString())){
                        loginStatus = true;
                    }
                }while(CR.moveToNext());
                if (loginStatus){
                // delete user here
                    dop.deleteUser(dop, user_name, user_pass);
                    Toast.makeText(getBaseContext(),"User removed successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(getBaseContext(), "Invalid user..Try later", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }


}
