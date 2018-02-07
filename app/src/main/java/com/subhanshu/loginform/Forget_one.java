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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by subhanshu singh on 30/07/2017.
 */

public class Forget_one extends Activity {

    EditText ans;
    TextView question;
    Button submit;
    SQLiteDatabase dbase;
    String email;
    String tablename="User_id";
    Cursor c,d;
    String ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farget2);
        ans=(EditText)findViewById(R.id.ans);
        question=(TextView)findViewById(R.id.que);
        submit=(Button)findViewById(R.id.submit);
        Intent in=getIntent();
        email = in.getStringExtra("EMAIL");
        question.setText("");
       // ques=in.getStringExtra("QUES");
       // question.setText(ques.toString());
        dbase = openOrCreateDatabase("user_db", Context.MODE_PRIVATE,null);
        c=dbase.query(tablename,null,null,null,null,null,null,null);


        while(c.moveToNext()) {
            if (email.equals(c.getString(2)) ){
                question.setText("Question ->"+c.getString(7));
            }

        }
        d=dbase.query(tablename, null,"email='"+email+"'", null,null,null,null);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(d.moveToNext()){
                if(ans.getText().toString().equals(d.getString(8))) {
                    Intent in = new Intent(Forget_one.this, Password_Change.class);
                    in.putExtra("Email",email);
                    startActivity(in);
                    Toast.makeText(Forget_one.this,"answer match", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Forget_one.this,"answer not match", Toast.LENGTH_LONG).show();
                }

                }

            }
        });
    }
}
