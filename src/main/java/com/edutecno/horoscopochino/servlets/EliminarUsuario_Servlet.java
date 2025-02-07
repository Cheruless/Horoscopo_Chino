package com.edutecno.horoscopochino.servlets;

import com.edutecno.horoscopochino.dao.Usuario_DAO;
import com.edutecno.horoscopochino.dao.Usuario_DAO_Impl;
import com.edutecno.horoscopochino.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/eliminarUsuario")
public class EliminarUsuario_Servlet extends HttpServlet {
    private Usuario_DAO daoUsuario = new Usuario_DAO_Impl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null || session.getAttribute("usuario") != null) {
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
            String password = req.getParameter("password");

            if (password != null && !password.equals("")) {
                try {
                    daoUsuario.eliminarUsuario(usuarioLogueado.getId(), password);
                    session.invalidate();
                    resp.sendRedirect("index.jsp");
                    req.setAttribute("mensaje", "Usuario eliminado correctamente");
                } catch (Exception e) {
                    e.printStackTrace();
                    req.setAttribute("error", "Error al eliminar el usuario.");
                    req.getRequestDispatcher("/eliminacionUsuario.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("error", "Contraseña incorrecta. Ingrese contraseña para eliminar su cuenta.");
                req.getRequestDispatcher("/eliminacionUsuario.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("error", "Sesion no encontrada.");
            req.getRequestDispatcher("/eliminacionUsuario.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }
        req.getRequestDispatcher("/eliminacionUsuario.jsp").forward(req, resp);
    }
}
