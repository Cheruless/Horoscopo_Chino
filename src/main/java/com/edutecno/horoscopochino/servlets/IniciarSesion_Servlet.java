package com.edutecno.horoscopochino.servlets;

import com.edutecno.horoscopochino.dao.Usuario_DAO;
import com.edutecno.horoscopochino.dao.Usuario_DAO_Impl;
import com.edutecno.horoscopochino.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


//FUNCIONAL
@WebServlet("/iniciarSesion")
public class IniciarSesion_Servlet extends HttpServlet {
    private final Usuario_DAO daoUsuario = new Usuario_DAO_Impl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Usuario usuario = daoUsuario.loguearUsuario(username, password);
        if (usuario != null) {
            req.getSession().setAttribute("usuario", usuario);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("error", "Usuario o contraseña incorrectos");
            req.getRequestDispatcher("/inicioSesion.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); //si es que no existe ninguna sesion, retornará NULL
        if (session != null && session.getAttribute("usuario") != null)
            resp.sendRedirect("index.jsp");
        req.getRequestDispatcher("/inicioSesion.jsp").forward(req, resp);
    }
}
