package com.edutecno.horoscopochino.dao;

import com.edutecno.horoscopochino.modelo.Horoscopo;
import com.edutecno.horoscopochino.procesaconexion.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Horoscopo_DAO_Impl implements Horoscopo_DAO {
    @Override
    public List<Horoscopo> obtenerHoroscopo() {
        List<Horoscopo> listaHoroscopo = new ArrayList<>();
        String sql = "SELECT * FROM horoscopo";
        try(
                Connection con = ConexionDB.generaPoolConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                Horoscopo h = new Horoscopo();
                h.setAnimal(rs.getString("animal"));
                h.setFecha_inicio(rs.getDate("fecha_inicio"));
                h.setFecha_fin(rs.getDate("fecha_fin"));
                listaHoroscopo.add(h);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listaHoroscopo;
    }

    @Override
    public Horoscopo obtenerHoroscopoPorFecha(Date fecha) {
        List<Horoscopo> listaHoroscopo = obtenerHoroscopo();
        return busquedaBinariaPorFecha(listaHoroscopo, fecha, 0, listaHoroscopo.size() - 1);
    }

    private Horoscopo busquedaBinariaPorFecha(List<Horoscopo> lista, Date fecha, int inicio, int fin){
        //Si no se encuentra la fecha retornamos NULL.
        if(inicio > fin)
            return null;

        /*
         * Partimos desde la mitad del largo de la lista.
         * Luego con recursividad de va a la mitad de la mitad y así sucesivamente.
         */
        int medio = (inicio + fin) / 2;
        Horoscopo h = lista.get(medio);

        /*
         * Preguntamos si está en el rango establecido de la lista o es exacto.
         * */
        if( (fecha.after(h.getFecha_inicio()) && fecha.before(h.getFecha_fin()))  ||
                fecha.equals(h.getFecha_inicio()) || fecha.equals(h.getFecha_fin()))
            return h;

        /*
         * Hacemos recursividad.
         * Si la fecha de nacimiento es anterior a la fecha_inicio de la lista
         *   buscamos en la mitad a la izquierda
         * Si la fecha de nacimiento es despues a la fecha_inicio de la lista
         *   buscamos en la mitad a la derecha
         * */
        if(fecha.before(h.getFecha_inicio()))
            return busquedaBinariaPorFecha(lista, fecha, inicio, medio-1);
        return busquedaBinariaPorFecha(lista, fecha, medio+1, fin);
    }
}
