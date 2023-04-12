/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Estado;
import com.mycompany.dominio.FiltroReporteTramites;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Placa;
import com.mycompany.dominio.Vehiculo;
import com.mycompany.interfaces.IPlacaDAO;
import com.mycompany.utils.ConfiguracionDePaginado;
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
public class PlacaDAO implements IPlacaDAO{

    EntityManager entityManager;

    public PlacaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Placa> consultarPlacasPersona(Persona persona, ConfiguracionDePaginado configuracionDePaginado) {

        try {
            int offset = configuracionDePaginado.getElementoASaltar();
            int limit = configuracionDePaginado.getElementosPorPagina();

            Query query = this.entityManager.createQuery("Select p from Placa p where p.persona = :per").setParameter("per", persona);

            query.setFirstResult(offset);
            query.setMaxResults(limit);

            List<Placa> list = query.getResultList();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Placa generarPlacaVehiculoNuevo(Persona persona, Vehiculo vehiculo) {
        try {
            String numeroPlaca = generaNumeroDePlaca();
            Placa placa = new Placa(new Date(), 1500F, persona, numeroPlaca, Estado.ACTIVA, vehiculo);
            entityManager.getTransaction().begin();
            entityManager.persist(placa);
            entityManager.getTransaction().commit();
            return placa;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Placa generarPlacaVehiculoUsado(Persona persona,Vehiculo vehiculo) {
        try {           
            Placa placaAnterior = consultarPlacaActiva(vehiculo.getSerie());
            String numeroPlaca = generaNumeroDePlaca();
            Placa placa = new Placa(new Date(), 1000F, persona, numeroPlaca, Estado.ACTIVA, vehiculo);
            placaAnterior.setEstado(Estado.DESACTIVA);
            placaAnterior.setFechaRecepcion(placa.getFechaEmision());
            entityManager.getTransaction().begin();
            entityManager.persist(placaAnterior);
            entityManager.persist(placa);
            entityManager.getTransaction().commit();
            return placa;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    public List<Placa> historialPlacasFiltroReporte(FiltroReporteTramites filtro) {
//        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<Placa> cq = cb.createQuery(Placa.class);
//        Root<Placa> from = cq.from(Placa.class);
//
//        List<Predicate> filtros = new LinkedList<>();
//
//        if (filtro.getDesde() != null && filtro.getHasta() != null) {
//            filtros.add(cb.greaterThanOrEqualTo(from.get("fechaEmision"), filtro.getDesde()));
//            filtros.add(cb.lessThanOrEqualTo(from.get("fechaEmision"), filtro.getHasta()));
//        }
//        if (filtro.getPersona() != null) {
//            filtros.add(cb.equal(from.get("persona"), filtro.getPersona()));
//        }
//
//        cq = cq.select(from).where(cb.and(filtros.toArray(new Predicate[0])));
//
//        TypedQuery<Placa> typed = this.entityManager.createQuery(cq);
//
//        List<Placa> lista = typed.getResultList();
//        return lista;
//    }

    private Placa consultarPlacaActiva(String serie) {
        VehiculoDAO vehiculoDAO = new VehiculoDAO(entityManager);
        Vehiculo vehiculo = vehiculoDAO.consultaVehiculoPorSerie(serie);
        Query query = this.entityManager.createQuery("Select p from Placa p where p.vehiculo = :veh AND p.estado = :est ");

        query.setParameter("veh", vehiculo);
        query.setParameter("est", Estado.ACTIVA);
        Placa placaActiva = (Placa) query.getSingleResult();
        try{
            placaActiva.getNumero();
            return placaActiva;
        }catch(Exception e){
            //No se encontro placa activa
            System.out.println(e.getMessage());
            return null;
        }

    }

    private String generaNumeroDePlaca() {
        char[] arr = {'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        char[] arrDig = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        String placa = "";
        int i = 0;
        while (i < 7) {
            if (i < 4) {
                for (int j = 0; j < 3; j++) {
                    int num = (int) Math.floor(Math.random() * arr.length);
                    char caracter = arr[num];
                    placa += caracter;
                    i++;
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    int num = (int) Math.floor(Math.random() * arrDig.length);
                    char caracter = arrDig[num];
                    placa += caracter;
                    i++;
                }

            }
            if (i < 7) {
                placa += "-";
            }
            i++;
        }
        return placa;
    }
    
        public List<Placa> consultaReporteLicencia(FiltroReporteTramites filtro) {
        String spql = "Select l from Placa l";
        Query query;
        List<Placa> lista;
        if (filtro.getNombre() != null) {
            spql += " INNER JOIN l.persona per where per.nombreCompleto LIKE :nombre";
            if (filtro.getDesde() != null) {
                spql += " AND l.fechaEmision BETWEEN :desde AND :hasta";

                query = this.entityManager.createQuery(spql, Placa.class);
                query.setParameter("nombre", "%" + filtro.getNombre() + "%");
                query.setParameter("desde", filtro.getDesde());
                query.setParameter("hasta", filtro.getHasta());

                lista = query.getResultList();
                return lista;
            } else {
                query = this.entityManager.createQuery(spql, Placa.class);
                query.setParameter("nombre", "%" + filtro.getNombre() + "%");

                lista = query.getResultList();
                return lista;
            }
        } else {
            if (filtro.getDesde() != null) {
                spql += " WHERE l.fechaEmision BETWEEN :desde AND :hasta";

                query = this.entityManager.createQuery(spql, Placa.class);
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
