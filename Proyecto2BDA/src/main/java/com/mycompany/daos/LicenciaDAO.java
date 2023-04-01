/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.FiltroHistorial;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author edemb
 */
public class LicenciaDAO {

    private EntityManager entityManager;

    public LicenciaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private List<Licencia> consultarLicenciasPersona(Persona persona) {

        Query query = this.entityManager.createQuery("Select l from Licencia l where p.persona = :per").setParameter("per", persona);

        List<Licencia> list = query.getResultList();
        return list;
    }

}
