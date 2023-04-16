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

    @Override
    /**
     * Metodo que implementa de IVehiculoDAO para registrar un Vehiculo
     * @param vehiculo Vehiculo a registrar
     * @return Regresa un Vehiculo
     */
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

    @Override
    /**
     * Metodo que implementa de IVehiculoDAO para consutlar un vehiculo por su serie
     * @param serie Serie a buscar
     * @return Regresa un vehiculo 
     */
    public Vehiculo consultaVehiculoPorSerie(String serie) {
        try{
        Query query = entityManager.createQuery("Select v from Vehiculo v where v.serie= :ser").setParameter("ser", serie);
        Vehiculo vehiculo = (Vehiculo) query.getSingleResult();
        vehiculo.getSerie();
        return vehiculo;
        }catch(Exception e){
            //No se encontro el vehiculo
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    /**
     * Metodo que implementa de IVehiculoDAO para verificar si una serie existe 
     * en la base de datos
     * @param serie Serie a verificar si existe
     * @return Regresa true si la serie ya existe, false si no existe
     */
    public Boolean existeLaSerie(String serie) {
        Vehiculo vehiculo = this.consultaVehiculoPorSerie(serie);
        if (vehiculo == null) {
            return false;
        } else {
            return true;
        }
    }

}
