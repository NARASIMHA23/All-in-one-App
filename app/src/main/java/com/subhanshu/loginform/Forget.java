package com.subhanshu.loginform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by subhanshu singh on 30/07/2017.
 */

public class Forget extends Activity {
    EditText email;
    Button next;
    SQLiteDatabase dbase;
    String tablename="User_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget);

        email=(EditText)findViewById(R.id.email);
        next=(Button)findViewById(R.id.next);
        dbase = openOrCreateDatabase("user_db", Context.MODE_PRIVATE,null);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor c=dbase.query(tablename,null,null,null,null,null,null,null);
                while(c.moveToNext()) {
                    if (email.getText().toString().length() == 0 ) {
                        email.setError("Email required");
                    }
                    else {
                        if (email.getText().toString().equals(c.getString(2)) ) {
                            Intent in = new Intent(Forget.this, Forget_one.class);
                            in.putExtra("EMAIL",email.getText().toString());
                            in.putExtra("QUES",c.getString(7));
                            startActivity(in);
                            email.setText("");
                        }
                        else{
                            Toast.makeText(Forget.this, "email not matched", Toast.LENGTH_SHORT).show();
                        }

                        }
                }
            }
        });

    }
}
