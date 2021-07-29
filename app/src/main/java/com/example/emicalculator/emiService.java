package com.example.emicalculator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class emiService extends Service {
    public emiService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
    IaidlService.Stub mBinder = new IaidlService.Stub() {
        @Override
        public float calToatlIntrest(float TA, float principal) throws RemoteException {
            return (float)(TA-principal);
        }

        @Override
        public float calTA(float emi, float months) throws RemoteException {
            return (float)(emi*months);
        }

        @Override
        public float calEmi(float FD, float D) throws RemoteException {
            return (float)(FD/D);
        }

        @Override
        public float calDivider(float dvdnt) throws RemoteException {
            return (float) (dvdnt-1);
        }

        @Override
        public float calfinalDvdnt(float principal, float rate, float dvdnt) throws RemoteException {
            return (float)(principal*rate*dvdnt);
        }

        @Override
        public float calDvdnt(float rate, float months) throws RemoteException {
            return (float) (Math.pow(1+rate,months));
        }

        @Override
        public float calMonth(float year) throws RemoteException {
            return (float) (year*12);
        }

        @Override
        public float calInt(float intr) throws RemoteException {
            return (float) (intr/12/100);
        }

        @Override
        public float calPric(float princi) throws RemoteException {
            return (float) princi;
        }
    };
}