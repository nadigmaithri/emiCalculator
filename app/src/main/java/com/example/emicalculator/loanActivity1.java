package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loanActivity1 extends AppCompatActivity  {
    Button calc,reset,link;
    EditText p,r,t,intrest,result,totamount;
    TextView loant;
    int mode;
    String website;
    IaidlService mservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        link=(Button)findViewById(R.id.clickhere);
        loant=(TextView)findViewById(R.id.loantype);
        Intent it= getIntent();
        mode = it.getIntExtra("mode",0);
        website= it.getStringExtra("link");
//        System.out.println(website);
//        System.out.println(mode);
        switch (mode){
                case 1: loant.setText("HOME LOAN");break;
                case 2: loant.setText("PERSONAL LOAN");break;
                case 3: loant.setText("EDUCATION LOAN");break;
                case 4: loant.setText("CAR LOAN");break;
                default:loant.setText("EMI CALCULATOR");
            }
        link.setOnClickListener(v -> {
                Uri uri = Uri.parse(website);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
        p=(EditText)findViewById(R.id.principal);
        r=(EditText)findViewById(R.id.rate);
        t=(EditText)findViewById(R.id.years);
        intrest=(EditText)findViewById(R.id.intrest);
        totamount=(EditText)findViewById(R.id.totamt);
        result=(EditText)findViewById(R.id.emi);
        reset=(Button)findViewById(R.id.reset);
        Intent intentService = new Intent(this,emiService.class);
        bindService(intentService,mconnection, Context.BIND_AUTO_CREATE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setText("");
                r.setText("");
                intrest.setText("");
                result.setText("");
                t.setText("");
                totamount.setText("");
            }
        });
        calc=(Button)findViewById(R.id.calculate);
        calc.setOnClickListener((v -> {
            String st1= p.getText().toString();
            String st2 = r.getText().toString();
            String st3 = t.getText().toString();
            if(TextUtils.isEmpty(st1)){
                p.setError("Enter principal Amount");
                p.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(st2)){
                r.setError("Enter Intrest rates");
                r.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(st3)){
                t.setError("Enter time");
                t.requestFocus();
                return;
            }
            float princi  =Float.parseFloat(st1);
            float intr = Float.parseFloat(st2);
            float year = Float.parseFloat(st3);
            float principal,rate,months,dvdnt,FD,D,emi=0,TA=0,TI=0;
            try {
                 principal = mservice.calPric(princi);
                 rate = mservice.calInt(intr);
                 months = mservice.calMonth(year);
                 dvdnt = mservice.calDvdnt(rate, months);
                 FD = mservice.calfinalDvdnt(principal, rate, dvdnt);
                 D = mservice.calDivider(dvdnt);
                emi = mservice.calEmi(FD, D);
                 TA = mservice.calTA(emi, months);
                 TI = mservice.calToatlIntrest(TA, principal);
            }catch (RemoteException e){
                e.printStackTrace();
            }
             result.setText("₹ "+String.valueOf(emi));
             intrest.setText("₹ "+String.valueOf(TI));
            totamount.setText("₹ "+String.valueOf(TA));
        }));

    }
    ServiceConnection mconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mservice = IaidlService.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

//    public float calToatlIntrest(float TA, float principal) {
//        return (float)(TA-principal);
//    }
//
//    public float calTA(float emi, float months) {
//        return (float)(emi*months);
//    }
//
//    public float calEmi(float FD, float D) {
//        return (float)(FD/D);
//    }
//
//    public float calDivider(float dvdnt) {
//        return (float) (dvdnt-1);
//    }
//
//    public float calfinalDvdnt(float principal, float rate, float dvdnt) {
//        return (float)(principal*rate*dvdnt);
//    }
//
//    public float calDvdnt(float rate, float months) {
//        return (float) (Math.pow(1+rate,months));
//    }
//
//    public float calMonth(float year) {
//        return (float) (year*12);
//    }
//
//    public float calInt(float intr) {
//        return (float) (intr/12/100);
//    }
//
//    public float calPric(float princi) {
//        return (float) princi;
//    }
}