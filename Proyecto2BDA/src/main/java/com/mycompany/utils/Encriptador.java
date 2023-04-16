/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utils;

/**
 *
 * @author edemb
 */
public class Encriptador {

    public static String encriptar(String texto) {
        char[] arr = texto.toCharArray();
        String encriptado = "";
        for (char i : arr) {
            int num = (int) i;
            num += 5;
            encriptado += (char) num;
        }
        return encriptado;
    }

    public static String desencriptado(String mesnajeEncriptado) {
        char[] arr = mesnajeEncriptado.toCharArray();
        String encriptado = "";
        for (char i : arr) {
            int num = (int) i;
            num -= 5;
            encriptado += (char) num;
        }
        return encriptado;
    }
}
