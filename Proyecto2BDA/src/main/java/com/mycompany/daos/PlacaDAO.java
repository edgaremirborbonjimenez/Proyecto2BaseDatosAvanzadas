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
public class PlacaDAO {

    EntityManager entityManager;

    public PlacaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Placa> consultarPlacasPersona(Persona persona) {

        Query query = this.entityManager.createQuery("Select p from Placa p where p.persona = :per").setParameter("per", persona);

        List<Placa> list = query.getResultList();
        return list;
    }

    public Placa generarPlacaVehiculoNuevo(Persona persona, Vehiculo vehiculo) {

        String numeroPlaca = generaNumeroDePlaca();
        Placa placa = new Placa(new Date(), 1500F, persona, numeroPlaca, Estado.ACTIVA, vehiculo);
        entityManager.getTransaction().begin();
        entityManager.persist(placa);
        entityManager.getTransaction().commit();
        return placa;
    }

    public Placa generarPlacaVehiculoUsado(Persona persona, Vehiculo vehiculo) {

        Placa placaAnterior = consultarPlacaActiva(persona);
        String numeroPlaca = generaNumeroDePlaca();
        Placa placa = new Placa(new Date(), 1000F, persona, numeroPlaca, Estado.ACTIVA, vehiculo);
        placaAnterior.setEstado(Estado.DESACTIVA);
        placaAnterior.setFechaRecepcion(placa.getFechaEmision());
        entityManager.getTransaction().begin();
        entityManager.persist(placaAnterior);
        entityManager.persist(placa);
        entityManager.getTransaction().commit();
        return placa;
    }
    
    public List<Placa> historialPlacasFiltroReporte(FiltroReporteTramites filtro){
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Placa> cq = cb.createQuery(Placa.class);
        Root<Placa> from = cq.from(Placa.class);

        List<Predicate> filtros = new LinkedList<>();

        if (filtro.getDesde() != null && filtro.getHasta() != null) {
            filtros.add(cb.greaterThanOrEqualTo(from.get("fechaEmision"), filtro.getDesde()));
            filtros.add(cb.lessThanOrEqualTo(from.get("fechaEmision"), filtro.getHasta()));
        }
        if (filtro.getPersona()!=null) {
            filtros.add(cb.equal(from.get("persona"), filtro.getPersona()));
        }

        cq = cq.select(from).where(cb.and(filtros.toArray(new Predicate[0])));

        TypedQuery<Placa> typed = this.entityManager.createQuery(cq);

        List<Placa> lista = typed.getResultList();
        return lista;
    }

    private Placa consultarPlacaActiva(Persona persona) {
        Query query = this.entityManager.createQuery("Select p from Placa p where p.persona = :per AND p.estado = :est ");

        query.setParameter("per", persona);
        query.setParameter("est", Estado.ACTIVA);
        List<Placa> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    private String generaNumeroDePlaca() {
        char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String placa = "";
        int i = 0;
        while (i < 7) {
            for (int j = 0; j < 3; j++) {
                int num = (int) Math.floor(Math.random() * arr.length);
                char caracter = arr[num];
                placa += caracter;
                i++;
            }
            if (i < 7) {
                placa += "-";
            }
            i++;
        }
        return placa;
    }

}
