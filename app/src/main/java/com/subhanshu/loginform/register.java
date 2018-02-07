package com.subhanshu.loginform;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by subhanshu singh on 23/07/2017.
 */

public class register extends Activity {

    EditText confirmpassword;
    RadioButton male, female;
    String question;
    CheckBox sub;
    String gen;
    EditText name1,password1,email1,phone1,answer1,dob1;
    RadioGroup gender1;
    Spinner question1;
    SQLiteDatabase dbase;
    String tablename="User_id";
    String user_name="name";
    String user_password="password";
    String user_email="email";

   
    String user_country="country";
    String user_phone="phone";
    String user_question="question";
    String user_answer="answer";
    TextView text;
    String ques;
    AutoCompleteTextView country1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name1           =(EditText)findViewById(R.id.edtname);
        password1       = (EditText) findViewById(R.id.edtpass);
        confirmpassword = (EditText) findViewById(R.id.edtconpass);
        email1          = (EditText) findViewById(R.id.edtemail);
        phone1          = (EditText) findViewById(R.id.edtphone);
        answer1         = (EditText) findViewById(R.id.edtans);
        gender1         = (RadioGroup) findViewById(R.id.gengroup);
        male            = (RadioButton) findViewById(R.id.radiomale);
        female          = (RadioButton) findViewById(R.id.radiofemale);
        dob1            = (EditText) findViewById(R.id.dob);
        country1        = (AutoCompleteTextView) findViewById(R.id.country_list);
        sub             = (CheckBox) findViewById(R.id.checkBox);
        text            = (TextView) findViewById(R.id.text11111);
        question1       = (Spinner) findViewById(R.id.spinnerqus);



        Resources res=getResources();
        String[] country_list1 = res.getStringArray(R.array.country);
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(register.this,R.layout.support_simple_spinner_dropdown_item,country_list1);

        country1.setAdapter(adapter1);

        String[] question = getResources().getStringArray(R.array.question);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,question);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question1.setAdapter(adapter);



//        question1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });





        dbase = openOrCreateDatabase("user_db", Context.MODE_PRIVATE, null);
        dbase.execSQL("create table if not exists " + tablename + "("
                + user_name + " varchar(50),"
                + user_password + " varchar(50),"
                + user_email + " varchar(50) PRIMARY KEY,"
                + user_gender + " varchar(10),"
                + user_dob + " varchar(20),"
                + user_country + " varchar(50),"
                + user_phone + " varchar(20),"
                + user_question + " varchar(100) NOT NULL,"
                + user_answer + " varchar(100))");
    }






    public void submit(View v) {


        if (name1.getText().toString().length() == 0) {
            name1.setError("Name Required ");
        } else {
            name1.setError(null);
        }
        if (name1.getText().toString().length() < 6 && name1.getText().toString().length() > 0) {
            name1.setError("name minmum 6 char");

        } else {
            name1.setError(null);
        }
        if (password1.getText().toString().length() == 0) {
            password1.setError("enter password");
        } else {
            password1.setError(null);
        }
        if (confirmpassword.getText().toString().length()!=0 || password1.getText().toString() .equals(confirmpassword.getText().toString())) {
            password1.setError(null);
        } else {

            password1.setError("password not match");
            confirmpassword.setError("password not match");
        }


        if (email1.getText().toString().length() == 0) {
            email1.setError("email required");
        } else {
            email1.setError(null);
        }
        if (gender1.getCheckedRadioButtonId() <= 0) {
            male.setError("Select Item");
            female.setError("select item");
        } else {
            male.setError(null);
            female.setError(null);
            if (male.isChecked()) {
                gen = male.getText().toString();
            } else {
                gen = female.getText().toString();
            }

        }
        if (country1.getText().toString().length() == 0) {
            country1.setError("enter country");
        } else {
            country1.setError(null);
        }
        if (phone1.getText().toString().length() == 0 || phone1.getText().toString().length() < 10 || phone1.getText().toString().length() > 10) {
            phone1.setError("Enter valid phone");
        } else {
            phone1.setError(null);
        }

        if (dob1.getText().toString().length() == 0 || dob1.getText().toString().length() < 10 || dob1.getText().toString().length() > 10) {
            dob1.setError("enter date of birth");
        } else {
            dob1.setError(null);
        }
        if (answer1.getText().toString().equals("")) {
            answer1.setError("Enter answer");
        } else {
            answer1.setError(null);
        }
        if (!sub.isChecked()) {
            sub.setError("not checked");
        } else {
            sub.setError(null);
        }
        if(name1.getError()==null&&password1.getError()==null&&confirmpassword.getError()==null&&email1.getError()==null
                &&dob1.getError()==null&&country1.getError()==null&&answer1.getError()==null&&gender1.getCheckedRadioButtonId()>0&&
                question1.getSelectedItemPosition()>0&&sub.isChecked()){

            ContentValues values = new ContentValues();
            values.put("name", name1.getText().toString());
            values.put("password", password1.getText().toString());
            values.put("email", email1.getText().toString());
            values.put("gender", gen);
            values.put("date_of_birth", dob1.getText().toString());
            values.put("country", country1.getText().toString());
            values.put("phone", phone1.getText().toString());
            values.put("question", question1.getSelectedItem().toString());
            values.put("answer", answer1.getText().toString());


            long count = dbase.insert(tablename, null, values);
            if (count > 0) {
                Toast.makeText(getApplicationContext(), "record inserted...", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "record not inserted..", Toast.LENGTH_LONG).show();
            }
        }

        }

    }

