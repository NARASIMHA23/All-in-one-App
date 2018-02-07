package com.subhanshu.loginform;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

    Button next, login;
    EditText emailid, password;
    TextView forget;
    SQLiteDatabase dbase;
    String tablename="User_id";
    String user_name="name";
    String user_password="password";
    String user_email="email";
    String user_gender="gender";
    String user_dob="date_of_birth";
    String user_country="country";
    String user_phone="phone";
    String user_question="question";
    String user_answer="answer";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        next = (Button) findViewById(R.id.click);
        login = (Button) findViewById(R.id.login);
        emailid = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        forget=(TextView)findViewById(R.id.forget);

        dbase = openOrCreateDatabase("user_db", Context.MODE_PRIVATE,null);
        //dbase.execSQL("select * from "+tablename+" where "+user_email+"="+emailid+";");
       // String q="SELECT "+user_email+","+user_password+" FROM "+tablename+" WHERE " +
        //        user_email+"="+emailid.getText().toString()+" AND "+user_password+"="+password+";";
      //  dbase.rawQuery(q,null);
      // dbase.rawQuery("select email,password from User_id where email=? password=?",new String[]{emailid.getText().toString(),password.getText().toString()});




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, register.class);
                startActivity(in);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (emailid.length() == 0) {
//                    emailid.setError("Enter email id");
//                }
//                if (password.getText().toString().length() == 0) {
//                    password.setError("enter password");
//                }
//                if(emailid.length()==0 && password.getText().toString().length()==0) {
//                }
//                else if(emailid.getText().toString().equals(user_email)&& password.getText().toString().equals(user_password)){
//                    Intent in =new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(in);
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "email and password doesnot match", Toast.LENGTH_SHORT).show();
//
//                }
                Cursor c=dbase.query(tablename,null,null,null,null,null,null,null);
                while(c.moveToNext()) {
                    if (emailid.getText().toString().length() == 0 || password.getText().toString().length() == 0) {
                                emailid.setError("Email required");
                                password.setError("Password rrquired");
                    }
                    else {
                        if (emailid.getText().toString().equals(c.getString(2)) && password.getText().toString().equals(c.getString(1))) {
                            Toast.makeText(MainActivity.this, "succssfull login", Toast.LENGTH_SHORT).show();
                            emailid.setText("");
                            password.setText("");
                            Intent in = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(in);
                        }else{
                            Toast.makeText(MainActivity.this, "login fail", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                }
            });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(MainActivity.this,Forget.class);
                startActivity(a);
            }
        });
    }

}