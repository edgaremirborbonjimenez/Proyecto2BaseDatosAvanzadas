/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Tramite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author edemb
 */
public class TramiteDAO {
   private EntityManager entityManager;

    public TramiteDAO() {
    }

    public TramiteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
//   
//   private List<Tramite> consultarTramitesDePersona(Persona persona){
//       CriteriaBuilder critariaBuilder = this.entityManager.getCriteriaBuilder();
//       CriteriaQuery criteriaQuery = critariaBuilder.createQuery();
//       Root<Tramite> from = criteriaQuery.from(Tramite.class);
//       CriteriaQuery select = criteriaQuery.select(from);
//       select.where(critariaBuilder.)
//       
//       
//   }
}
