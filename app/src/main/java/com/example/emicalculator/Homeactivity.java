package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Homeactivity extends AppCompatActivity implements View.OnClickListener {
  ImageButton homel,edul,personall,carl;
  int mode;

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
        if(v.equals(homel)){
            mode=1;
            Intent it = new Intent(this,loanActivity1.class);
            it.putExtra("mode",mode);
            it.putExtra("link","https://www.bankbazaar.com/home-loan-interest-rate.html");
            startActivity(it);

        }
        else if(v.equals(personall)){
            mode=2;
            Intent it = new Intent(this,loanActivity1.class);
            it.putExtra("mode",mode);
            it.putExtra("link","https://www.bankbazaar.com/personal-loan-interest-rate.html");
            startActivity(it);

        }
        else if(v.equals(edul)){
            mode=3;
            Intent it = new Intent(this,loanActivity1.class);
            it.putExtra("mode",mode);
            it.putExtra("link","https://www.bankbazaar.com/education-loan-interest-rate.html");
            startActivity(it);

        }
        else if(v.equals(carl)){
            mode=4;
            Intent it = new Intent(this,loanActivity1.class);
            it.putExtra("mode",mode);
            it.putExtra("link","https://www.bankbazaar.com/car-loan-interest-rate.html");
            startActivity(it);

        }


    }
}