package com.cs442.shash5259.sportsmgr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import com.facebook.FacebookSdk;
public class Login extends AppCompatActivity
{

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Login.this, Login1.class);
                Login.this.startActivity(mainIntent);
                Login.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


}
