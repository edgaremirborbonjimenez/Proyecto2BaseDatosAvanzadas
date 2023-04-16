/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author edemb
 */
public class ConexionDAO {

    private EntityManager entityManager;

    public ConexionDAO() {
    }

    /**
     * Metodo para crear la conexion con la base de datos
     * @param persistenceUnitName String del nombre de la base de datos 
     */
    public void crearConexion(String persistenceUnitName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Metodo para regresar el Objeto EntityManager
     * @return Regresa el Objeto EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }    
    
}
