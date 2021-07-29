// IaidlService.aidl
package com.example.emicalculator;

// Declare any non-default types here with import statements

interface IaidlService {
   float calToatlIntrest(float TA, float principal);
   float calTA(float emi, float months);
   float calEmi(float FD, float D);
   float calDivider(float dvdnt);
   float calfinalDvdnt(float principal, float rate, float dvdnt);
   float calDvdnt(float rate, float months);
   float calMonth(float year);
   float calInt(float intr);
   float calPric(float princi);
}