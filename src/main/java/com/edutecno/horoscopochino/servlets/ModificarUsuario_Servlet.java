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

@WebServlet("/modificarUsuario")
public class ModificarUsuario_Servlet extends HttpServlet {
    private Usuario_DAO daoUsuario = new Usuario_DAO_Impl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");


        if (session != null || session.getAttribute("usuario") != null) {
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

            if (usuarioLogueado.getPassword().equals(password)) {
                //TODO: SI EL USUARIO NO QUIERE MODIFICAR CONTRASEÑA, NO MODIFICARLA.
                String passwordModificar = req.getParameter("password-modificar");
                String passwordModificarConfirm = req.getParameter("password-modificar-confirm");

                if (passwordModificar.equals(passwordModificarConfirm)) {
                    try {
                        Usuario usuarioModificar = new Usuario();
                        //                  Si el nombre NO ES NULL y NO ESTÁ VACIO entonces ASIGNAMOS NOMBRE.
                        usuarioModificar.setNombre(nombre != null && !nombre.trim().isEmpty() ? nombre : null);
                        usuarioModificar.setEmail(email != null && !email.trim().isEmpty() ? email : null);
                        usuarioModificar.setUsername(usuario != null && !usuario.trim().isEmpty() ? usuario : null);

                        usuarioModificar.setPassword( !passwordModificar.trim().isEmpty() ? passwordModificar : null);

                        daoUsuario.modificarUsuario(usuarioLogueado.getId(), usuarioModificar);
                        session.invalidate();
                        resp.sendRedirect(req.getContextPath() + "/index.jsp");
                    } catch (Exception e) {
                        e.printStackTrace();
                        req.setAttribute("error", "Error al modificar el usuario.");
                        req.getRequestDispatcher("/modificacionUsuario.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("error", "Contraseñas no coinciden.");
                    req.getRequestDispatcher("/modificacionUsuario.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("error", "Contraseña incorrecta.");
                req.getRequestDispatcher("/modificacionUsuario.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("error", "Sesion no encontrada.");
            req.getRequestDispatcher("/modificacionUsuario.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); //si es que no existe ninguna sesion, retornará NULL
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }
        req.getRequestDispatcher("/modificacionUsuario.jsp").forward(req, resp);
    }
}
