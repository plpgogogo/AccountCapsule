package com.acapsule.accountcapsule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.acapsule.accountcapsule.parentfrags.MainContentFragment;

public class MainActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            MainContentFragment mainContentFragment = new MainContentFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, mainContentFragment).commit();
        }
    }
}
