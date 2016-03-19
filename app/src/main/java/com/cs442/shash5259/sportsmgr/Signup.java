package com.cs442.shash5259.sportsmgr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Signup extends AppCompatActivity implements View.OnClickListener{
    TextView link,input1;
    EditText et_dob,et_name,et_email,et_pass,et_phone;
    Calendar myCalendar;
    Button b_signup;
    RadioButton male,female;
    RadioGroup rg;
    DatePickerDialog.OnDateSetListener date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        link = (TextView)findViewById(R.id.link_login);
        et_dob = (EditText)findViewById(R.id.input_dob);
        et_name = (EditText)findViewById(R.id.input_name);
        et_phone = (EditText)findViewById(R.id.input_phone);
        et_pass = (EditText)findViewById(R.id.input_password);
        et_email = (EditText)findViewById(R.id.input_email);
        input1 = (TextView)findViewById(R.id.input);
        b_signup = (Button)findViewById(R.id.btn_signup);
        rg = (RadioGroup)findViewById(R.id.gender_group);



        String first = "<font color='#00000'>Sports</font>";
        String next = "<font color='#000000'> Manager</font>";
        input1.setText(Html.fromHtml(first + next));

        link.setOnClickListener(this);
        et_dob.setOnClickListener(this);
        b_signup.setOnClickListener(this);



        //Calendar Popup on Edit Text Click
          myCalendar = Calendar.getInstance();

         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.link_login:
                Intent i = new Intent(Signup.this,Login1.class);
                startActivity(i);
                break;
            case R.id.input_dob:
                new DatePickerDialog(Signup.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_signup:
                String ans = getSelection();// gets null if no checkbox selected
                //validating if any of the fields are empty
                if(TextUtils.isEmpty(et_dob.getText()) || TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_phone.getText()) || TextUtils.isEmpty(et_email.getText()) || TextUtils.isEmpty(et_pass.getText()) || ans==null)
                {
                    Toast.makeText(Signup.this,"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i1 = new Intent(Signup.this, Login1.class);
                    startActivity(i1);//goes to login page if all are filled
                }
                break;

        }
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_dob.setText(sdf.format(myCalendar.getTime()));
    }

    private String getSelection(){

        male = (RadioButton)findViewById(R.id.check_male);
        female = (RadioButton)findViewById(R.id.check_female);
        if (male.isChecked())
        {
            return male.getText().toString();
        }
        if (female.isChecked())
        {
            return female.getText().toString();
        }


        return null;

    }
}
