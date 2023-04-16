/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Pago;
import com.mycompany.dominio.Tramite;
import com.mycompany.interfaces.IPagoDAO;
import javax.persistence.EntityManager;

/**
 * Clase que maneja la persistencia de los Pagos
 *
 * @author edemb
 */
public class PagoDAO implements IPagoDAO {

    private EntityManager entityManager;

    /**
     * Constructor
     *
     * @param entityManager EntityManager a utilizar
     */
    public PagoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Genera un Pago
     *
     * @param tramite Tramite al que se le genera el Pago
     * @return Pago generado
     */
    public Pago generarPago(Tramite tramite) {
        if (tramite == null) {
            return null;
        }
        Pago pago = new Pago(tramite.getCosto(), tramite);
        entityManager.getTransaction().begin();
        entityManager.persist(pago);
        entityManager.getTransaction().commit();
        return pago;
    }

}
