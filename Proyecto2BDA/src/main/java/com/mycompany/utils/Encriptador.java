/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utils;

/**
 * Clase que contiene los metodos para encriptar y desencriptar
 *
 * @author edemb
 */
public class Encriptador {

    /**
     * Encripta el texto
     *
     * @param texto Texto a encriptar
     * @return texto Encriptado
     */
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

    /**
     * Desencripta el texto
     *
     * @param mesnajeEncriptado Texto a desencriptar
     * @return Texto desencriptado
     */
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
