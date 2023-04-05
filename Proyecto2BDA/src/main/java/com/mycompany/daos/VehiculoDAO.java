/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Vehiculo;
import com.mycompany.interfaces.IVehiculoDAO;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author edemb
 */
public class VehiculoDAO implements IVehiculoDAO {

    EntityManager entityManager;

    public VehiculoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Vehiculo registraVehiculo(Vehiculo vehiculo) {
        if (existeLaSerie(vehiculo.getSerie())) {
            System.out.println("Vehiculo ya existente");
            return null;
        } else {
            this.entityManager.getTransaction().begin();
            entityManager.persist(vehiculo);
            this.entityManager.getTransaction().commit();
        }
        return consultaVehiculoPorSerie(vehiculo.getSerie());
    }

    public Vehiculo consultaVehiculoPorSerie(String serie) {
        try{
        Query query = entityManager.createQuery("Select v from Vehiculo v where v.serie= :ser").setParameter("ser", serie);
        Vehiculo vehiculo = (Vehiculo) query.getSingleResult();
        vehiculo.getSerie();
        return vehiculo;
        }catch(Exception e){
            //No se encontro el vehiculo
            return null;
        }
    }

    public Boolean existeLaSerie(String serie) {
        Vehiculo vehiculo = this.consultaVehiculoPorSerie(serie);
        if (vehiculo == null) {
            return false;
        } else {
            return true;
        }
    }

}
