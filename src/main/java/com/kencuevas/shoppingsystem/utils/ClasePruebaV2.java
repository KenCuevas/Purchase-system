package com.kencuevas.shoppingsystem.utils;

import java.nio.charset.Charset;
import java.util.Random;

public class ClasePruebaV2 {
    private String codigo;
    private String nombre;
    private String apellido;

    public ClasePruebaV2(String codigo, String nombre, String apellido) {
        int i = 15;

        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "ClasePruebaV2{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
    static String getRandomStringId(int i)
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
}
