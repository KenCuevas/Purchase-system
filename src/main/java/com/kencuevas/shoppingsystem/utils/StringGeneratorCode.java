package com.kencuevas.shoppingsystem.utils;
import java.nio.charset.*;
import java.util.*;

public class StringGeneratorCode {
   public String getRandomStringId(int i)
    {
        // First bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String myString;
        StringBuffer stringBuffer;
        String theAlphaNumerics;

        new Random().nextBytes(bytearray);

        myString = new String(bytearray, Charset.forName("UTF-8"));
        stringBuffer = new StringBuffer();

        // Remove all spacial char...
        theAlphaNumerics = myString.replaceAll("[^A-Z0-9]", "");

        // Random Selection
        for(int m = 0; m < theAlphaNumerics.length();m++){
            if(Character.isLetter(theAlphaNumerics.charAt(m))
                    && (i>0) || Character.isDigit(theAlphaNumerics.charAt(m))
                    && (i > 0)){
                stringBuffer.append(theAlphaNumerics.charAt(m));

                i--;
            }
        }
        // The resulting string
        return stringBuffer.toString();
    }
//    public static void main(String[] args) {
//        // The random string length
//        int i = 15;
//
//        // Output
//        System.out.println(getRandomStringId(i));
//    }
}
