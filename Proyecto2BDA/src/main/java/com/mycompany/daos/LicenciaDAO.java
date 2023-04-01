/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Discapacitado;
import com.mycompany.dominio.FiltroHistorial;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Vigencia;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    public List<Licencia> consultarLicenciasPersona(Persona persona) {

        Query query = this.entityManager.createQuery("Select l from Licencia l where p.persona = :per").setParameter("per", persona);

        List<Licencia> list = query.getResultList();
        return list;
    }

    public Licencia generarLicencia(Vigencia vigencia, Persona persona) {
        Float costo = 0F;
        GregorianCalendar fechaVigencia = new GregorianCalendar();
        if (vigencia == Vigencia.Ano_1) {
            costo = 600F;
            fechaVigencia.add(1, 1);
        } else if (vigencia == Vigencia.Ano_2) {
            costo = 900F;
            fechaVigencia.add(1, 2);

        } else if (vigencia == Vigencia.Ano_3) {
            costo = 1100F;
            fechaVigencia.add(1, 3);

        }

        if (persona.getDiscapasitado() == Discapacitado.SI) {
            costo = costo - 400F;
        }

        Licencia licencia = new Licencia(new GregorianCalendar(), fechaVigencia, costo, persona);

        entityManager.getTransaction().begin();

        entityManager.persist(licencia);

        entityManager.getTransaction().commit();
        return licencia;
    }

}
