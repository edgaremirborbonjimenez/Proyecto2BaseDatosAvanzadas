/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dominio;

import com.mycompany.daos.LicenciaDAO;
import com.mycompany.daos.PersonaDAO;
import com.mycompany.daos.PlacaDAO;
import com.mycompany.daos.VehiculoDAO;
import com.mycompany.utils.ValidacionDatos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author edemb
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, JRException {
        // TODO code application logic here
        Persona p1 = new Persona("Juan García Pérez", "GAPJ920702", new GregorianCalendar(2017, Calendar.JANUARY, 22), "5512345678", Sexo.MASCULINO, Discapacitado.NO);
        Persona p2 = new Persona("María López Hernández", "LOHM900529", new GregorianCalendar(1990, Calendar.MAY, 29), "5534567890", Sexo.FEMENINO, Discapacitado.NO);
        Persona p3 = new Persona("Pedro Sánchez Ramírez", "SARP970825", new GregorianCalendar(1997, Calendar.AUGUST, 25), "5252611989", Sexo.MASCULINO, Discapacitado.NO);

        List<Reporte> lista = new ArrayList<Reporte>();
        Licencia licencia1 = new Licencia(1L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p1);
        Licencia licencia2 = new Licencia(2L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p2);
        Licencia licencia3 = new Licencia(3L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p3);
        Licencia licencia4 = new Licencia(1L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p1);
        Licencia licencia5 = new Licencia(2L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p2);
        Licencia licencia6 = new Licencia(3L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p3);
        Licencia licencia7 = new Licencia(1L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p1);
        Licencia licencia8 = new Licencia(2L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p2);
        Licencia licencia9 = new Licencia(3L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p3);
        Licencia licencia10 = new Licencia(1L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p1);
        Licencia licencia11 = new Licencia(2L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p2);
        Licencia licencia12 = new Licencia(3L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p3);
        Licencia licencia13 = new Licencia(1L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p1);
        Licencia licencia14 = new Licencia(2L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p2);
        Licencia licencia15 = new Licencia(3L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p3);
        Licencia licencia16 = new Licencia(1L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p1);
        Licencia licencia17 = new Licencia(2L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p2);
        Licencia licencia18 = new Licencia(3L, new Date(2026, 04, 02), new Date(2029, 04, 02), 1000F, Estado.ACTIVA, p3);

        Reporte reporte1 = new Reporte(licencia1.getId(), licencia1.getFechaEmision(), licencia1.getCosto(), licencia1.getPersona().getNombreCompleto());
        Reporte reporte2 = new Reporte(licencia2.getId(), licencia2.getFechaEmision(), licencia2.getCosto(), licencia2.getPersona().getNombreCompleto());
        Reporte reporte3 = new Reporte(licencia3.getId(), licencia3.getFechaEmision(), licencia3.getCosto(), licencia3.getPersona().getNombreCompleto());
        Reporte reporte4 = new Reporte(licencia4.getId(), licencia4.getFechaEmision(), licencia4.getCosto(), licencia4.getPersona().getNombreCompleto());
        Reporte reporte5 = new Reporte(licencia5.getId(), licencia5.getFechaEmision(), licencia5.getCosto(), licencia5.getPersona().getNombreCompleto());
        Reporte reporte6 = new Reporte(licencia6.getId(), licencia6.getFechaEmision(), licencia6.getCosto(), licencia6.getPersona().getNombreCompleto());
        Reporte reporte7 = new Reporte(licencia7.getId(), licencia7.getFechaEmision(), licencia7.getCosto(), licencia7.getPersona().getNombreCompleto());
        Reporte reporte8 = new Reporte(licencia8.getId(), licencia8.getFechaEmision(), licencia8.getCosto(), licencia8.getPersona().getNombreCompleto());
        Reporte reporte9 = new Reporte(licencia9.getId(), licencia9.getFechaEmision(), licencia9.getCosto(), licencia9.getPersona().getNombreCompleto());
        Reporte reporte10 = new Reporte(licencia10.getId(), licencia10.getFechaEmision(), licencia10.getCosto(), licencia10.getPersona().getNombreCompleto());
        Reporte reporte11 = new Reporte(licencia11.getId(), licencia11.getFechaEmision(), licencia11.getCosto(), licencia11.getPersona().getNombreCompleto());
        Reporte reporte12 = new Reporte(licencia12.getId(), licencia12.getFechaEmision(), licencia12.getCosto(), licencia12.getPersona().getNombreCompleto());
        Reporte reporte13 = new Reporte(licencia13.getId(), licencia13.getFechaEmision(), licencia13.getCosto(), licencia13.getPersona().getNombreCompleto());
        Reporte reporte14 = new Reporte(licencia14.getId(), licencia14.getFechaEmision(), licencia14.getCosto(), licencia14.getPersona().getNombreCompleto());
        Reporte reporte15 = new Reporte(licencia15.getId(), licencia15.getFechaEmision(), licencia15.getCosto(), licencia15.getPersona().getNombreCompleto());
        Reporte reporte16 = new Reporte(licencia16.getId(), licencia16.getFechaEmision(), licencia16.getCosto(), licencia16.getPersona().getNombreCompleto());
        Reporte reporte17 = new Reporte(licencia17.getId(), licencia17.getFechaEmision(), licencia17.getCosto(), licencia17.getPersona().getNombreCompleto());
        Reporte reporte18 = new Reporte(licencia18.getId(), licencia18.getFechaEmision(), licencia18.getCosto(), licencia18.getPersona().getNombreCompleto());
        Reporte reporte19 = new Reporte(licencia11.getId(), licencia11.getFechaEmision(), licencia11.getCosto(), licencia11.getPersona().getNombreCompleto());
        Reporte reporte20 = new Reporte(licencia12.getId(), licencia12.getFechaEmision(), licencia12.getCosto(), licencia12.getPersona().getNombreCompleto());
        Reporte reporte21 = new Reporte(licencia13.getId(), licencia13.getFechaEmision(), licencia13.getCosto(), licencia13.getPersona().getNombreCompleto());
        Reporte reporte22 = new Reporte(licencia14.getId(), licencia14.getFechaEmision(), licencia14.getCosto(), licencia14.getPersona().getNombreCompleto());
        Reporte reporte23 = new Reporte(licencia15.getId(), licencia15.getFechaEmision(), licencia15.getCosto(), licencia15.getPersona().getNombreCompleto());
        Reporte reporte24 = new Reporte(licencia16.getId(), licencia16.getFechaEmision(), licencia16.getCosto(), licencia16.getPersona().getNombreCompleto());
        Reporte reporte25 = new Reporte(licencia17.getId(), licencia17.getFechaEmision(), licencia17.getCosto(), licencia17.getPersona().getNombreCompleto());
        Reporte reporte26 = new Reporte(licencia18.getId(), licencia18.getFechaEmision(), licencia18.getCosto(), licencia18.getPersona().getNombreCompleto());

        lista.add(reporte1);
        lista.add(reporte2);
        lista.add(reporte3);
        lista.add(reporte4);
        lista.add(reporte5);
        lista.add(reporte6);
        lista.add(reporte7);
        lista.add(reporte8);
        lista.add(reporte9);
        lista.add(reporte10);
        lista.add(reporte11);
        lista.add(reporte12);
        lista.add(reporte13);
        lista.add(reporte14);
        lista.add(reporte15);
        lista.add(reporte16);
        lista.add(reporte17);
        lista.add(reporte18);
        lista.add(reporte19);
        lista.add(reporte20);
        lista.add(reporte21);
        lista.add(reporte22);
        lista.add(reporte23);
        lista.add(reporte24);
        lista.add(reporte25);
        lista.add(reporte26);

        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lista);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);

        InputStream input = new FileInputStream("..\\jasper\\ReporteLicencia.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperViewer.viewReport(jasperPrint);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "..\\jasper\\LicenciasReport.pdf");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto2BDA");
        EntityManager em = emf.createEntityManager();
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
//        em.getTransaction().begin();
//        em.persist(p1);
//        em.persist(p2);
//        em.persist(p3);
//        em.persist(p4);
//        em.persist(p5);
//        em.persist(p6);
//        em.persist(p7);
//        em.persist(p8);
//        em.persist(p9);
//        em.persist(p10);
//        em.persist(p11);
//        em.persist(p12);
//        em.persist(p13);
//        em.persist(p14);
//        em.persist(p15);
//        em.persist(p16);
//        em.persist(p17);
//        em.persist(p18);
//        em.persist(p19);
//        em.persist(p20);
//        em.getTransaction().commit();
//
//        Persona pe = em.find(Persona.class, 1L);
//        Persona p22 = em.find(Persona.class, 2L);
//        Persona p33 = em.find(Persona.class, 3L);
//
//        String serie = "qwe123";
//        String serie1 = "AAA123";
//        String serie2 = "qweeeeeeeeeeee123";
//        String serie3 = "AAAAAAAAAAAAAA123";
//        String serie4 = "AAAA%AAAAA%AAAA123";
//        String serie5 = "SKADJ%FN2RYOI348IU%";
//        String serie6 = "SKADJFN2RYOI34%IU";
//        String serie7 = "345%634A5634";
//        System.out.println(ValidacionDatos.serieEsValida(serie));
//        System.out.println(ValidacionDatos.serieEsValida(serie1));
//        System.out.println(ValidacionDatos.serieEsValida(serie2));
//        System.out.println(ValidacionDatos.serieEsValida(serie3));
//        System.out.println(ValidacionDatos.serieEsValida(serie4));
//        System.out.println(ValidacionDatos.serieEsValida(serie5));
//        System.out.println(ValidacionDatos.serieEsValida(serie6));
//        System.out.println(ValidacionDatos.serieEsValida(serie7));
//        System.out.println(ValidacionDatos.serieEsValida( "98D7F6ASDA98SD7F6"));
//        PlacaDAO placaDAO = new PlacaDAO(em);
//        System.out.println(placaDAO.generaNumeroDePlaca());
//        System.out.println(placaDAO.generaNumeroDePlaca());
//        System.out.println(placaDAO.generaNumeroDePlaca());
//        System.out.println(placaDAO.generaNumeroDePlaca());
//        System.out.println(placaDAO.generaNumeroDePlaca());
//        System.out.println(placaDAO.generaNumeroDePlaca());
//        System.out.println(placaDAO.generaNumeroDePlaca());

//        try{
//        Query query = em.createQuery("Select p from Persona p where p.id= :i").setParameter("i", 20L);
//        Persona persona = (Persona) query.getSingleResult();
//        persona.getNombreCompleto();
//            System.out.println("Se encontro a la persona");
//        }catch(Exception e){
//            System.out.println("No se encontro a la persona");
//        }
//        Vehiculo vehiculo = new Automovil("1234567", "Honda", "Rojo", "Recta", "4xd");
//        VehiculoDAO vDao = new VehiculoDAO(em);
//        Vehiculo v = vDao.registraVehiculo(vehiculo);
//        System.out.println(v);
//
//        GregorianCalendar vigencia = new GregorianCalendar(2026, 04, 02);
//        vigencia.add(1, 3);
//        Licencia l1 = new Licencia(new GregorianCalendar(2026, 04, 02), new GregorianCalendar(2029, 04, 02), 1000F, pe);
//        Licencia l2 = new Licencia(new GregorianCalendar(2026, 04, 02), new GregorianCalendar(2029, 04, 02), 1000F, p22);
//        Licencia l3 = new Licencia(new GregorianCalendar(2026, 04, 02), new GregorianCalendar(2029, 04, 02), 1000F, p33);
//
//        em.getTransaction().begin();
//        em.persist(l1);
//        em.persist(l2);
//        em.persist(l3);
//        em.getTransaction().commit();
//        Persona p = em.find(Persona.class, 2L);
//////        Vehiculo carro = em.find(Automovil.class, 1L);
//////
//////        PlacaDAO placaDao = new PlacaDAO(em);
//////        Placa placa = placaDao.generarPlacaVehiculoUsado(p, carro);
////
//        FiltroReporteTramites filtro = new FiltroReporteTramites();
//        filtro.setDesde(new GregorianCalendar(2026, 05, 02));
//        filtro.setHasta(new GregorianCalendar(2026, 05, 02));
//        filtro.setLicencia(true);
//        filtro.setPlaca(true);
//        filtro.setPersona(p);
//
//////        CriteriaBuilder cb = em.getCriteriaBuilder();
//////        CriteriaQuery<Tramite> cq = cb.createQuery(Tramite.class);
//////        Root<Tramite> from = cq.from(Tramite.class);
//////
//////        List<Predicate> filtros = new LinkedList<>();
//////
//////        if (filtro.getDesde() != null && filtro.getHasta() != null) {
//////            filtros.add(cb.greaterThanOrEqualTo(from.get("fechaEmision"), filtro.getDesde()));
//////            filtros.add(cb.lessThanOrEqualTo(from.get("fechaEmision"), filtro.getHasta()));
//////        }
//////        if (filtro.getPersona()!=null) {
//////            filtros.add(cb.equal(from.get("persona"), filtro.getPersona()));
//////        }
//////
//////        cq = cq.select(from).where(cb.and(filtros.toArray(new Predicate[0])));
//////
//////        TypedQuery<Tramite> typed = em.createQuery(cq);
//////
//////        List<Tramite> lista = typed.getResultList();
//////
//////        for (Tramite o : lista) {
//////            System.out.println(o.getId());
//////        }
        //TypedQuery<Tuple> query = em.createQuery("Select p.nombreCompleto AS Nombre from Persona p ", Tuple.class);
//        TypedQuery<Tuple> query = em.createQuery("SELECT p.nombreCompleto AS nombre FROM Persona p", Tuple.class);
//
//        List<Tuple> lista = query.getResultList();
//        for (Tuple tupla : lista) {
//            String nombreCompleto = tupla.get("nombre", String.class);
//            System.out.println(nombreCompleto);
//        }
////        TypedQuery<Tuple> query = em.createQuery("SELECT p FROM Persona p", Tuple.class);
////        List<Tuple> lista = query.getResultList();
////        for (Tuple nombre : lista) {
////            System.out.println(nombre);
////        }
//       
//        System.out.println(placa.getNumero());
//        
//        Query query = em.createQuery("Select p from Placa p where p.persona = :per AND p.estado = :est ");
//
//        query.setParameter("per", p);
//        query.setParameter("est", Estado.ACTIVA);
//        List<Placa> list = query.getResultList();
//
//        for (Placa o : list) {
//            System.out.println(o.getId());
//        }
//        placaDao.generarPlacaVehiculoNuevo(p, carro);
//        for (int o = 0; o < 10; o++) {
//            char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
//                'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
//                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
//                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
//                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//            String placa = "";
//            int i = 0;
//            while (i < 7) {
//                for (int j = 0; j < 3; j++) {
//                    int num = (int) Math.floor(Math.random() * arr.length);
//                    char caracter = arr[num];
//                    placa += caracter;
//                    i++;
//                }
//                if (i < 7) {
//                    placa += "-";
//                }
//                i++;
//            }
//            System.out.println(placa);
//        }
//    Vehiculo carro = new Automovil("Masda", "Homda", "Royp", "Recta", "400");
//    em.getTransaction().begin();
//    em.persist(carro);
//    em.getTransaction().commit();
//
//               
//               LicenciaDAO licenciaDAO = new LicenciaDAO(em);
//               licenciaDAO.generarLicencia(Vigencia.Ano_1,p);
//
//        Query query = em.createQuery("Select l from Tramite l where l.persona = :per").setParameter("per", p);
//
//        List<Tramite> list = query.getResultList();
//        
//        for(Tramite o : list){
//            System.out.println(o.getId());
//        }
//        Persona p = em.find(Persona.class, 15L);
//        Licencia l = new Licencia(new GregorianCalendar(2023, 03, 31), new GregorianCalendar(2026, 03, 31), 600F, "234234234", p);
//        Vehiculo v = new Automovil("swe", "wewerqw", "aeqwe", "weqw", "aweq");
//        v= em.find(Vehiculo.class, 2L);
//        
//        Placa placs = new Placa(new GregorianCalendar(2023, 03, 31), 8000F, p, "tsdtsdg", Estado.ACTIVA, v);
//        em.getTransaction().begin();
//        
//        //em.persist(v);
//        em.persist(placs);
//
//        em.getTransaction().commit();
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Licencia> cq = cb.createQuery(Licencia.class);
//        Root<Licencia> from = cq.from(Licencia.class);
//        CriteriaQuery select = cq.select(from);
//
//        TypedQuery<Licencia> type = em.createQuery(select);
//        List<Licencia> list = type.getResultList();
//        Query query = em.createQuery("Select l from Licencia l where p.persona = :per").setParameter("per", p);
//
//        List<Licencia> list = query.getResultList();
//
//        for (Licencia o : list) {
//          SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//            System.out.println(o.getId()+","+formateador.format(o.getFechaEmision().getTime())+","+formateador.format(o.getFechaVigencia().getTime())+","+o.getCosto());
//        }
//
//        em.close();
//                  SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//
//        GregorianCalendar fecha = new GregorianCalendar();
//                System.out.println(formateador.format(fecha.getTime()));
//
//        fecha.add(0, 3);
//        
//        System.out.println(formateador.format(fecha.getTime()));
//
//        GregorianCalendar fecha2 = new GregorianCalendar();
//        fecha2.add(1, 3);
//        
//        System.out.println(formateador.format(fecha2.getTime()));
//                GregorianCalendar fecha3 = new GregorianCalendar();
//        fecha3.add(2, 3);
//        
//        System.out.println(formateador.format(fecha3.getTime()));
//    
//                       GregorianCalendar fecha4 = new GregorianCalendar();
//        fecha4.add(3, 3);
//        
//        System.out.println(formateador.format(fecha4.getTime()));
        String nombre = "laura";
        Date desde = new Date(2023, 3, 10);
        Date hasta = new Date(2023, 3, 10);
        desde.setYear(desde.getYear() - 1900);
        hasta.setYear(hasta.getYear() - 1900);
        LocalDate d = LocalDate.of(2023, 6, 10);
        Date fecha = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LicenciaDAO licenciaDAO = new LicenciaDAO(em);
        FiltroReporteTramites filtro = new FiltroReporteTramites();
        filtro.setDesde(fecha);
        filtro.setHasta(fecha);
        filtro.setNombre("L");

//        List<Licencia> lista = licenciaDAO.consultaReporteLicencia(filtro);
//        for (Licencia i : lista) {
//            System.out.println(i.getId());
//            System.out.println(i.getPersona().getNombreCompleto());
//        }
        SimpleDateFormat forateador = new SimpleDateFormat("yyyy-MM-dd");

        Calendar desde1 = new GregorianCalendar(2023, 3, 10);
        Calendar hasta1 = new GregorianCalendar(2023, 3, 10);

//        String d = forateador.format(desde1.getTime());
//        String h = forateador.format(hasta1.getTime());
//        String d = forateador.format(desde);
//        String h = forateador.format(hasta);
//        String dF = "'" + d + "'";
//        String hF = "'" + h + "'";
//
//        System.out.println(dF);
//        System.out.println(hF);
//        Query query = em.createQuery("Select p from Placa p INNER JOIN p.persona per where per.nombreCompleto LIKE :nombre", Placa.class).setParameter("nombre", "%" + nombre + "%");
//     Query query = em.createQuery("Select p from Placa p INNER JOIN p.persona per where per.nombreCompleto LIKE :nombre AND p.fechaEmision BETWEEN :desde AND :hasta");
//     Query query = em.createQuery("Select p from Placa p where p.fechaEmision BETWEEN '2023-4-10' AND '2023-4-10'",Placa.class);
//        Query query = em.createQuery("Select p from Placa p where p.fechaEmision BETWEEN :desde AND :hasta", Placa.class);
////    query.setParameter("nombre","%"+nombre+"%");
//        query.setParameter("desde", fecha);
//        query.setParameter("hasta", fecha);
//        List<Placa> lista = query.getResultList();
//        for (Placa i : lista) {
//            System.out.println(i.getNumero());
//            System.out.println(i.getPersona().getNombreCompleto());
//        }
//        
    }

}
