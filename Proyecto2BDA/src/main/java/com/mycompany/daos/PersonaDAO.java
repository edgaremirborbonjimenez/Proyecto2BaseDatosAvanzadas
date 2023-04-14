/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Discapacitado;
import com.mycompany.dominio.FiltroHistorial;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Sexo;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.mycompany.interfaces.IPersonaDAO;
import com.mycompany.utils.ConfiguracionDePaginado;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author edemb
 */
public class PersonaDAO implements IPersonaDAO {

    private EntityManager entityManager;

    public PersonaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void registrarPersonas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto2BDA");
        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Persona p1 = new Persona("Ana Fernanda Beltran Gastelum", "GAPJ920702", new GregorianCalendar(2004, Calendar.JUNE, 24), "6421073923", Sexo.FEMENINO, Discapacitado.NO);
        Persona p2 = new Persona("María López Hernández", "LOHM900529", new GregorianCalendar(1990, Calendar.MAY, 29), "5534567890", Sexo.FEMENINO, Discapacitado.NO);
        Persona p3 = new Persona("Pedro Sánchez Ramírez", "SARP970825", new GregorianCalendar(1997, Calendar.AUGUST, 25), "5252611989", Sexo.MASCULINO, Discapacitado.NO);
        Persona p4 = new Persona("Laura Martínez Gómez", "MAGL840704", new GregorianCalendar(1984, Calendar.JULY, 4), "5512345678", Sexo.FEMENINO, Discapacitado.SI);
        Persona p5 = new Persona("Miguel Rodríguez Torres", "ROTM910627", new GregorianCalendar(1991, Calendar.JUNE, 27), "5534567890", Sexo.FEMENINO, Discapacitado.NO);
        Persona p6 = new Persona("Ana González Vázquez", "GOVA810118", new GregorianCalendar(1981, Calendar.JANUARY, 18), "5523456789", Sexo.FEMENINO, Discapacitado.SI);
        Persona p7 = new Persona("José Díaz Castro", "DICJ750712", new GregorianCalendar(1975, Calendar.JULY, 12), "5512345678", Sexo.MASCULINO, Discapacitado.NO);
        Persona p8 = new Persona("Paula Reyes Flores", "REFP800522", new GregorianCalendar(1980, Calendar.MAY, 22), "5534567890", Sexo.MASCULINO, Discapacitado.SI);
        Persona p9 = new Persona("Ricardo Hernández López", "HELJ860423", new GregorianCalendar(1986, Calendar.APRIL, 23), "5523456789", Sexo.FEMENINO, Discapacitado.NO);
        Persona p10 = new Persona("Carmen Perez García", "PEGJ950303", new GregorianCalendar(1995, Calendar.MARCH, 3), "5512345678", Sexo.FEMENINO, Discapacitado.SI);
        Persona p11 = new Persona("Fernando Romero Sánchez", "ROSF920611", new GregorianCalendar(1992, Calendar.JUNE, 11), "5534567890", Sexo.MASCULINO, Discapacitado.NO);
        Persona p12 = new Persona("Juan Perez García", "PEGJ940201", new GregorianCalendar(1994, Calendar.FEBRUARY, 4), "5551234567", Sexo.MASCULINO, Discapacitado.NO);
        Persona p13 = new Persona("Ana Torres Hernández ", "TOHA960612", new GregorianCalendar(1996, Calendar.JUNE, 12), "5552345678", Sexo.FEMENINO, Discapacitado.SI);
        Persona p14 = new Persona("Luis García González", "GAGL891223", new GregorianCalendar(1989, Calendar.DECEMBER, 23), "5553456789", Sexo.MASCULINO, Discapacitado.SI);
        Persona p15 = new Persona("Carmen Sánchez Martínez", "SAMC950709", new GregorianCalendar(1995, Calendar.JULY, 9), "5554567890", Sexo.FEMENINO, Discapacitado.NO);
        Persona p16 = new Persona("Ricardo Martínez Torres", "MATR930523", new GregorianCalendar(1993, Calendar.MAY, 23), "5555678901", Sexo.MASCULINO, Discapacitado.NO);
        Persona p17 = new Persona("Patricia Flores Cruz", "FOCR901209", new GregorianCalendar(1990, Calendar.DECEMBER, 9), " 5556789012", Sexo.FEMENINO, Discapacitado.SI);
        Persona p18 = new Persona("Fernando Ruiz Hernández", "RUIF860422", new GregorianCalendar(1986, Calendar.APRIL, 22), "5557890123", Sexo.MASCULINO, Discapacitado.SI);
        Persona p19 = new Persona("María Martínez López", "MALL980108", new GregorianCalendar(1998, Calendar.JANUARY, 8), "5558901234", Sexo.FEMENINO, Discapacitado.NO);
        Persona p20 = new Persona("Jorge Hernández Sánchez", "HESJ920706", new GregorianCalendar(1992, Calendar.JULY, 6), "5559012345", Sexo.MASCULINO, Discapacitado.SI);

        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(p3);
        entityManager.persist(p4);
        entityManager.persist(p5);
        entityManager.persist(p6);
        entityManager.persist(p7);
        entityManager.persist(p8);
        entityManager.persist(p9);
        entityManager.persist(p10);
        entityManager.persist(p11);
        entityManager.persist(p12);
        entityManager.persist(p13);
        entityManager.persist(p14);
        entityManager.persist(p15);
        entityManager.persist(p16);
        entityManager.persist(p17);
        entityManager.persist(p18);
        entityManager.persist(p19);
        entityManager.persist(p20);

        entityManager.getTransaction().commit();
    }

    public List<Persona> consultaTotal(ConfiguracionDePaginado configPaginado){
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto2BDA");
            entityManager = emf.createEntityManager();
            
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);

            Root<Persona> persona = criteria.from(Persona.class);

            criteria.select(persona);
            
            int offset = configPaginado.getElementoASaltar();
            int limit = configPaginado.getElementosPorPagina();
            
            TypedQuery<Persona> query = entityManager.createQuery(criteria);
            
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            
            List <Persona> listaPersonas = (List <Persona>) query.getResultList();
            return listaPersonas;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Persona> buscarPersonas(FiltroHistorial parametros, ConfiguracionDePaginado configPaginado) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto2BDA");
            entityManager = emf.createEntityManager();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);

            Root<Persona> persona = criteria.from(Persona.class);

            List<Predicate> filtros = new LinkedList<>();

            int offset = configPaginado.getElementoASaltar();
            int limit = configPaginado.getElementosPorPagina();

            if (parametros.getRFC() != null) {
                filtros.add(builder.like(persona.get("rfc"), "%" + parametros.getRFC() + "%"));
            }
            if (parametros.getNombreCompleto() != null) {
                filtros.add(builder.like(persona.get("nombreCompleto"), "%" + parametros.getNombreCompleto() + "%"));
            }
            if (parametros.getAnioNacimiento() != null) {
                filtros.add(builder.equal(builder.function("YEAR", Integer.class, persona.get("fechaNacimiento")), parametros.getAnioNacimiento()));
            }

            criteria.select(persona)
                    .where(
                            builder.and(filtros.toArray(new Predicate[0]))
                    );

            TypedQuery<Persona> query = entityManager.createQuery(criteria);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            List<Persona> personas = query.getResultList();
            return personas;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Persona buscarPersonaRFC(String rfc) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto2BDA");
            entityManager = emf.createEntityManager();
            
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);

            Root<Persona> persona = criteria.from(Persona.class);

            criteria.select(persona)
                    .where(
                            builder.like(persona.get("rfc"), "%" + rfc + "%")
                    );

            TypedQuery<Persona> query = entityManager.createQuery(criteria);

            Persona personas = query.getSingleResult();
            return personas;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
