package com.edutecno.horoscopochino.dao;

import com.edutecno.horoscopochino.modelo.Horoscopo;

import java.sql.Date;
import java.util.List;

public interface Horoscopo_DAO {
    List<Horoscopo> obtenerHoroscopo();
    Horoscopo obtenerHoroscopoPorFecha(Date fecha);
}
