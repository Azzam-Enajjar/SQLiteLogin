package edu.pdx.oss.sqliteloginapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// update user

public class UpdateActivity extends AppCompatActivity {
    Bundle b;
    String user_name, user_pass, new_user_name;
    EditText USER_NAME_TEXT;
    Button UPDATE_BUTTON;
    DatabaseOperations dop;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        USER_NAME_TEXT = (EditText) findViewById(R.id.usernameText);
        UPDATE_BUTTON = (Button) findViewById(R.id.updateButton);

        b = getIntent().getExtras();
        user_name = b.getString("user_name");
        user_pass = b.getString("user_password");

        UPDATE_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_user_name = USER_NAME_TEXT.getText().toString();
                dop = new DatabaseOperations(ctx);
                dop.updateUser(dop, user_name, user_pass, new_user_name);
                Toast.makeText(getBaseContext(),"Update success", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }


}
