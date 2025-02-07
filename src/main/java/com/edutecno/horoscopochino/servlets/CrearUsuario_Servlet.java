package com.edutecno.horoscopochino.servlets;

import com.edutecno.horoscopochino.dao.Horoscopo_DAO;
import com.edutecno.horoscopochino.dao.Horoscopo_DAO_Impl;
import com.edutecno.horoscopochino.dao.Usuario_DAO;
import com.edutecno.horoscopochino.dao.Usuario_DAO_Impl;
import com.edutecno.horoscopochino.modelo.Horoscopo;
import com.edutecno.horoscopochino.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/crearUsuario")
public class CrearUsuario_Servlet extends HttpServlet {
    private final Usuario_DAO daoUsuario = new Usuario_DAO_Impl();
    private final Horoscopo_DAO daoHoroscopo = new Horoscopo_DAO_Impl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String usuario = req.getParameter("usuario");

        //CONVERTIMOS LA FECHA PARA ALMACENARSE EN SQL
        String fecha = req.getParameter("fecha-nacimiento");
        java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fecha);

        String pass = req.getParameter("password");
        String passConfirmacion = req.getParameter("password-confirmacion");

        //Solo en caso de que se confirme que la contraseña es igual procesamos los datos.
        if (pass.equals(passConfirmacion)) {
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setEmail(email);
            u.setUsername(usuario);
            u.setFechaNacimiento(fechaNacimiento);
            u.setPassword(pass);

            Horoscopo horoscopo = daoHoroscopo.obtenerHoroscopoPorFecha(fechaNacimiento);
            if (horoscopo != null) {
                u.setAnimal(horoscopo.getAnimal());
            } else {
                req.setAttribute("error", "No se pudo encontrar un horóscopo para esta fecha.");
                req.getRequestDispatcher("/creacionUsuario.jsp").forward(req, resp);
            }

            try {
                daoUsuario.agregarUsuario(u);
                resp.sendRedirect(req.getContextPath() + "/inicioSesion.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Error al agregar el usuario.");
                req.getRequestDispatcher("/creacionUsuario.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Las contraseñas deben ser iguales.");
            req.getRequestDispatcher("/creacionUsuario.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/creacionUsuario.jsp").forward(req, resp);
    }
}
