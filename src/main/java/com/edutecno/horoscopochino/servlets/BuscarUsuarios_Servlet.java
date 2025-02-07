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
import java.util.List;

@WebServlet("/buscarUsuarios")
public class BuscarUsuarios_Servlet extends HttpServlet {
    private final Usuario_DAO daoUsuario = new Usuario_DAO_Impl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("usuario") == null){
            resp.sendRedirect("index.jsp");
            return;
        }
        try {
            List<Usuario> listaUsuarios = daoUsuario.obtenerUsuarios();
            req.setAttribute("listaUsuarios", listaUsuarios);
        }catch (Exception e){
            req.setAttribute("error","Error al listar usuarios");
            e.printStackTrace();
        }
        req.getRequestDispatcher("/listarUsuarios.jsp").forward(req, resp);
    }
}
