/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author edemb
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Proyecto2BDA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Persona persona = new Persona("Emir", "Borbon", "Jimenez", "2345678", new GregorianCalendar(2003, 10, 2), "6421068907", Sexo.MASCULINO);
        //Tramite licencia = new Licencia(1000F, persona, new GregorianCalendar(28, 3, 2026));
        //Pago pago = new Efectivo(1000F, licencia);
        //licencia.setPagos(Arrays.asList(pago));

        Vehiculo vehiculo = new Automovil("ABCDEFG", "Toyota", "Rojo", "Ssdas", "sdasa");
        Tramite placa = new Placa(500F, persona, "-------", new GregorianCalendar(28, 3, 2023), null, vehiculo);
        Pago pagoPlaca = new Tarjeta(600F, placa, "0000000000000000");

        entityManager.persist(persona);
        entityManager.getTransaction().commit();
        
        
//        entityManager.getTransaction().begin();
//
//        entityManager.persist(licencia);
//        entityManager.persist(pago);
//        entityManager.getTransaction().commit();
        
        
        entityManager.getTransaction().begin();

        entityManager.persist(vehiculo);
        entityManager.getTransaction().commit();
        
        
        entityManager.getTransaction().begin();

        entityManager.persist(placa);
        entityManager.getTransaction().commit();
        
        
        entityManager.getTransaction().begin();

        entityManager.persist(pagoPlaca);

        entityManager.getTransaction().commit();
        
        
        entityManager.close();
    }

}
