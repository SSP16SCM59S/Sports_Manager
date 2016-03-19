package com.cs442.shash5259.sportsmgr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login1 extends AppCompatActivity
{

    private CallbackManager mcallbackmanager;


    private FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken maccess = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            if(profile != null)
            {
                Toast.makeText(Login1.this,profile.getName(),Toast.LENGTH_LONG).show();
            }


        }

        @Override
        public void onCancel() {
            Toast.makeText(Login1.this,"Cancelled",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(Login1.this,"Did not work",Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mcallbackmanager = CallbackManager.Factory.create();

        final EditText etuser = (EditText) findViewById(R.id.etusername);
        final EditText etpass = (EditText) findViewById(R.id.etpassword);
        Button blogin = (Button) findViewById(R.id.Blogin);
        final TextView tv = (TextView) findViewById(R.id.login_text_user);
        final TextView tv1 = (TextView)findViewById(R.id.login_text_pass);
        LoginButton login = (LoginButton)findViewById(R.id.login_button);
        TextView tv2 = (TextView)findViewById(R.id.textView);
        TextView tv3 = (TextView)findViewById(R.id.textView2);

        login.setReadPermissions("user_friends");
        login.registerCallback(mcallbackmanager,mcallback);
        //Typeface font = Typeface.createFromAsset(getAssets(), "Helvetica Narrow Bold.ttf");
        //tv3.setTypeface(font,font.BOLD);
        String first = "Sports";
        String next = "<font color='#000000'> Manager</font>";
        tv3.setText(Html.fromHtml(first + next));
        //tv3.setText("Sports Manager");

        etuser.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                tv.setText("Username");
                etuser.setText(" ");

                return false;
            }
        });



        etpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            tv1.setText("Password");
                etpass.setText("");
                return false;
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login1.this,MainActivity.class);
                startActivity(i);

            }
        });

        tv2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login1.this,Signup.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackmanager.onActivityResult(requestCode,resultCode,data);
    }


}
