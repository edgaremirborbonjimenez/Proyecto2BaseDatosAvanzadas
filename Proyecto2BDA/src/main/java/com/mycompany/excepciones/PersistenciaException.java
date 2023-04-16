/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.excepciones;

/**
 *
 * @author Usuario
 */
public class PersistenciaException extends Exception{

    /**
     * Constructor por defecto
     */
    public PersistenciaException() {
    }

    /**
     * Constructor que crea una nueva instancia de la excepción 
     * PersistenciaException con un mensaje personalizado.
     * @param message Mensaje
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una nueva instancia de la excepción 
     * PersistenciaException, con un mensaje personalizado y una causa específica.
     * @param message Mensaje 
     * @param cause Causa
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor crea una nueva instancia de la excepción 
     * PersistenciaException con una causa específica.
     * @param cause Causa
     */
    public PersistenciaException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor que crea una nueva instancia de la excepción 
     * PersistenciaException con un mensaje personalizado, una causa específica, 
     * y configurar si se permiten o no la supresión de excepciones y si la 
     * traza de pila se puede escribir en un archivo.
     * @param message Mensaje
     * @param cause Causa
     * @param enableSuppression Indica si se permiten o no la supresión de excepciones
     * @param writableStackTrace Indica si la traza de pila se puede escribir en un archivo
     */
    public PersistenciaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
    
}

