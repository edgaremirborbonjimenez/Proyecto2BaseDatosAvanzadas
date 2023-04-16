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
import com.mycompany.interfaces.ILicenciaDAO;
import com.mycompany.utils.ConfiguracionDePaginado;
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
 * Clase que contiene todos los metodos de la persistencia de las Licencias
 *
 * @author edemb
 */
public class LicenciaDAO implements ILicenciaDAO {

    private EntityManager entityManager;

    /**
     * Constructor
     *
     * @param entityManager EntityManager a utilizar
     */
    public LicenciaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Consulta las Licencias de una Persona y contiene la configuracion de
     * Paginado
     *
     * @param persona Persona con la que buscara las Licencias
     * @param configuracionDePaginado Configuracion del Paginado para la tabla
     * @return Una lista de Licencias
     */
    public List<Licencia> consultarLicenciasPersona(Persona persona, ConfiguracionDePaginado configuracionDePaginado) {
        int offset = configuracionDePaginado.getElementoASaltar();
        int limit = configuracionDePaginado.getElementosPorPagina();
        Query query = this.entityManager.createQuery("Select l from Licencia l where l.persona = :per").setParameter("per", persona);

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Licencia> list = query.getResultList();
        return list;
    }

    /**
     * Genera licencias
     *
     * @param vigencia Vigencia que tendra la Licencia
     * @param persona Persona a la que se le generara
     * @return Regresa la Licencia generada
     */
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
        if (licenciaAnterior != null) {
            licenciaAnterior.setEstado(Estado.DESACTIVA);
        }
        entityManager.getTransaction().begin();
        if (licenciaAnterior != null) {
            entityManager.persist(licenciaAnterior);
        }
        entityManager.persist(licencia);

        entityManager.getTransaction().commit();
        return licencia;
    }

    /**
     * Consulta la Licencia que este activa de la persona
     *
     * @param persona Persona a la que le buscaran la Licencia Activa
     * @return La Licencia Activa, null en caso de no encontrarse
     */
    public Licencia consultarLicenciaActiva(Persona persona) {
        if (persona == null) {
            return null;
        }
        System.out.println(this.entityManager);
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

    /**
     * Consulta las Licencias dependiendo del filtro del reporte
     *
     * @param filtro Filtro que utilizara para buscar las Licencias
     * @return Una lista de Licencias, null si no se pudo consultar
     */
    public List<Licencia> consultaReporteLicencia(FiltroReporteTramites filtro) {
        String spql = "Select l from Licencia l";
        Query query;
        List<Licencia> lista;
        if (filtro.getNombre() != null) {
            spql += " INNER JOIN l.persona per where per.nombreCompleto LIKE :nombre";
            if (filtro.getDesde() != null) {
                spql += " AND l.fechaEmision BETWEEN :desde AND :hasta";

                query = this.entityManager.createQuery(spql, Licencia.class);
                query.setParameter("nombre", "%" + filtro.getNombre() + "%");
                query.setParameter("desde", filtro.getDesde());
                query.setParameter("hasta", filtro.getHasta());

                lista = query.getResultList();
                return lista;
            } else {
                query = this.entityManager.createQuery(spql, Licencia.class);
                query.setParameter("nombre", "%" + filtro.getNombre() + "%");

                lista = query.getResultList();
                return lista;
            }
        } else {
            if (filtro.getDesde() != null) {
                spql += " WHERE l.fechaEmision BETWEEN :desde AND :hasta";

                query = this.entityManager.createQuery(spql, Licencia.class);
                query.setParameter("desde", filtro.getDesde());
                query.setParameter("hasta", filtro.getHasta());

                lista = query.getResultList();
                return lista;
            }
        }
        //Si el Filtro esta vacio
        return null;
    }
}
