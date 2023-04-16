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
 * Clase que maneja la Persistencia de las Placas
 *
 * @author edemb
 */
public class PlacaDAO implements IPlacaDAO {

    EntityManager entityManager;

    /**
     * Constructor
     *
     * @param entityManager EntityManager a utilizar
     */
    public PlacaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Consutla las Placas de las Personas y con la configuracion de paginado
     *
     * @param persona Persona a la que se le buscaran sus Placas
     * @param configuracionDePaginado Configuracion de paginado
     * @return Una lista de Placas
     */
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

    /**
     * Genera la placa a un vehiculo nuevo, osea que no esta registrado en la
     * base de datos
     *
     * @param persona Persona a la que se le pertenecera la Placa
     * @param vehiculo Vehiculo al que se le generara la Placa
     * @return Placa generada
     */
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

    /**
     * Genera la placa a un vehiculo usado, osea que esta registrado en la base
     * de datos y desactiva la Placa anterios
     *
     * @param persona Persona a la que se le pertenecera la Placa
     * @param vehiculo Vehiculo al que se le generara la Placa
     * @return Placa generada
     */
    public Placa generarPlacaVehiculoUsado(Persona persona, Vehiculo vehiculo) {
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

    /**
     * Consulta la Placa que este activa buscandola con la serie del auto
     *
     * @param serie Serie con la que se buscara la Placa
     * @return Placa activa, null en caso de no encontrarla
     */
    public Placa consultarPlacaActiva(String serie) {
        VehiculoDAO vehiculoDAO = new VehiculoDAO(entityManager);
        Vehiculo vehiculo = vehiculoDAO.consultaVehiculoPorSerie(serie);
        Query query = this.entityManager.createQuery("Select p from Placa p where p.vehiculo = :veh AND p.estado = :est ");

        query.setParameter("veh", vehiculo);
        query.setParameter("est", Estado.ACTIVA);
        Placa placaActiva = (Placa) query.getSingleResult();
        try {
            placaActiva.getNumero();
            return placaActiva;
        } catch (Exception e) {
            //No se encontro placa activa
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * Genera Numero de Placas de Vehiculo
     *
     * @return un texto con el numero de placa
     */
    public String generaNumeroDePlaca() {
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

    /**
     * Consulta las Placas con Filtro para el Reporte
     *
     * @param filtro Filtro a utilizar para consutar las Placas
     * @return lista de Placas, null si no se pudo consultar
     */
    public List<Placa> consultaReportePlaca(FiltroReporteTramites filtro) {
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
