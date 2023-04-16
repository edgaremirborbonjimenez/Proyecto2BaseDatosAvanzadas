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

    /**
     * Constructor por defecto
     */
    public ConexionDAO() {
    }

    /**
     * Metodo para crear la conexion con la base de datos
     *
     * @param persistenceUnitName String del nombre de la base de datos
     * @return El entityManager generado
     */
    public EntityManager crearConexion(String persistenceUnitName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

}
