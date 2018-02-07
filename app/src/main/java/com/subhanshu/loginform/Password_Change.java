package com.subhanshu.loginform;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by subhanshu singh on 30/07/2017.
 */

public class Password_Change extends Activity {
    EditText pass;
    Button submit;
    String tablename="User_id";
    String email;
    SQLiteDatabase dbase;
    EditText conf;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

        pass=(EditText)findViewById(R.id.pass);
        conf=(EditText)findViewById(R.id.conpass);
        submit=(Button)findViewById(R.id.sub);
        Intent in=getIntent();
        email = in.getStringExtra("Email");
        dbase = openOrCreateDatabase("user_db", Context.MODE_PRIVATE,null);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass.getText().toString().length() == 0 || conf.getText().toString().length() == 0) {
                    pass.setError("enter password");
                    conf.setError("enter valid password");
                } else {
                    if (pass.getText().toString().equals(conf.getText().toString())) {
                        ContentValues values = new ContentValues();
                        values.put("password", pass.getText().toString());
                        count = dbase.update(tablename, values, "email=?", new String[]{email.toString()});
                        if (count > 0) {
                            Toast.makeText(Password_Change.this, "sucessfully updated", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(Password_Change.this, MainActivity.class);
                            startActivity(in);
                        }
                            else{
                                Toast.makeText(Password_Change.this,"password update fail",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

            }

        });
    }
}
