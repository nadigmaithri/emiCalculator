package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Homeactivity extends AppCompatActivity implements View.OnClickListener {
  ImageButton homel,edul,personall,carl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        homel= (ImageButton) findViewById(R.id.bhome);
        edul=(ImageButton) findViewById(R.id.bedu);
        personall=(ImageButton)findViewById(R.id.bpersonal);
        carl=(ImageButton)findViewById(R.id.bcar);
        homel.setOnClickListener(this);
        edul.setOnClickListener(this);
        personall.setOnClickListener(this);
        carl.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.equals(homel)|v.equals(edul)|v.equals(personall)|v.equals(carl)){
            Intent it = new Intent(this,loanActivity1.class);
            startActivity(it);

        }

    }
}