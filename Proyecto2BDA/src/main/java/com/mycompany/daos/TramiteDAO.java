/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Tramite;
import com.mycompany.interfaces.ITramiteDAO;
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
public class TramiteDAO implements ITramiteDAO{
   private EntityManager entityManager;

    public TramiteDAO() {
    }

    public TramiteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<Tramite> consultarTramitesPersona(Persona persona) {

        Query query = this.entityManager.createQuery("Select l from Tramite l where p.persona = :per").setParameter("per", persona);

        List<Tramite> list = query.getResultList();
        return list;
    }
}
