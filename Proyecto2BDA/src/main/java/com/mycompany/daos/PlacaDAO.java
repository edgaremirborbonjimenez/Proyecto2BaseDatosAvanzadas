/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Estado;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Placa;
import com.mycompany.dominio.Vehiculo;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author edemb
 */
public class PlacaDAO {

    EntityManager entityManager;

    public PlacaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Placa> consultarPlacasPersona(Persona persona) {

        Query query = this.entityManager.createQuery("Select p from Placa p where p.persona = :per").setParameter("per", persona);

        List<Placa> list = query.getResultList();
        return list;
    }

    public Placa generarPlacaVehiculoNuevo(Persona persona, Vehiculo vehiculo) {

        String numeroPlaca = generaNumeroDePlaca();
        Placa placa = new Placa(new GregorianCalendar(), 1500F, persona, numeroPlaca, Estado.ACTIVA, vehiculo);
        entityManager.getTransaction().begin();
        entityManager.persist(placa);
        entityManager.getTransaction().commit();
        return placa;
    }

    public Placa generarPlacaVehiculoUsado(Persona persona, Vehiculo vehiculo) {

        Placa placaAnterior = consultarPlacaActiva(persona);
        String numeroPlaca = generaNumeroDePlaca();
        Placa placa = new Placa(new GregorianCalendar(), 1000F, persona, numeroPlaca, Estado.ACTIVA, vehiculo);
        placaAnterior.setEstado(Estado.DESACTIVA);
        entityManager.getTransaction().begin();
        entityManager.persist(placaAnterior);
        entityManager.persist(placa);
        entityManager.getTransaction().commit();
        return placa;
    }

    private Placa consultarPlacaActiva(Persona persona) {
        Query query = this.entityManager.createQuery("Select p from Placa p where p.persona = :per AND p.estado = :est ");

        query.setParameter("per", persona);
        query.setParameter("est", Estado.ACTIVA);
        List<Placa> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    private String generaNumeroDePlaca() {
        char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String placa = "";
        int i = 0;
        while (i < 7) {
            for (int j = 0; j < 3; j++) {
                int num = (int) Math.floor(Math.random() * arr.length);
                char caracter = arr[num];
                placa += caracter;
                i++;
            }
            if (i < 7) {
                placa += "-";
            }
            i++;
        }
        return placa;
    }

}
