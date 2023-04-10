/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Discapacitado;
import com.mycompany.dominio.Estado;
import com.mycompany.dominio.FiltroHistorial;
import com.mycompany.dominio.FiltroReporteTramites;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Placa;
import com.mycompany.dominio.Vigencia;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
        Date fechaV = new Date();
        Licencia licenciaAnterior = this.consultarLicenciaActiva(persona);
        if (vigencia == Vigencia.Ano_1) {
            costo = 600F;
            fechaV.setYear(fechaV.getYear() + 1);
        } else if (vigencia == Vigencia.Ano_2) {
            costo = 900F;
            fechaV.setYear(fechaV.getYear() + 2);
        } else if (vigencia == Vigencia.Ano_3) {
            costo = 1100F;
            fechaV.setYear(fechaV.getYear() + 3);
        }

        if (persona.getDiscapasitado() == Discapacitado.SI) {
            costo = costo - 400F;
        }

        Licencia licencia = new Licencia(new Date(), fechaV, costo, Estado.ACTIVA, persona);
        if(licenciaAnterior!=null){
        licenciaAnterior.setEstado(Estado.DESACTIVA);
        }
        entityManager.getTransaction().begin();
        if(licenciaAnterior!=null){
        entityManager.persist(licenciaAnterior);
        }
        entityManager.persist(licencia);

        entityManager.getTransaction().commit();
        return licencia;
    }

    public List<Licencia> historialLicenciaFiltroReporte(FiltroReporteTramites filtro) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Licencia> cq = cb.createQuery(Licencia.class);
        Root<Licencia> from = cq.from(Licencia.class);

        List<Predicate> filtros = new LinkedList<>();

        if (filtro.getDesde() != null && filtro.getHasta() != null) {
            filtros.add(cb.greaterThanOrEqualTo(from.get("fechaEmision"), filtro.getDesde()));
            filtros.add(cb.lessThanOrEqualTo(from.get("fechaEmision"), filtro.getHasta()));
        }
        if (filtro.getPersona() != null) {
            filtros.add(cb.equal(from.get("persona"), filtro.getPersona()));
        }

        cq = cq.select(from).where(cb.and(filtros.toArray(new Predicate[0])));

        TypedQuery<Licencia> typed = this.entityManager.createQuery(cq);

        List<Licencia> lista = typed.getResultList();
        return lista;
    }

    private Licencia consultarLicenciaActiva(Persona persona) {
        Query query = this.entityManager.createQuery("Select p from Licencia p where p.persona = :per AND p.estado = :est ");

        query.setParameter("per", persona);
        query.setParameter("est", Estado.ACTIVA);
        List<Licencia> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
