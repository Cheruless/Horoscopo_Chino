package com.edutecno.horoscopochino.servlets;

import com.edutecno.horoscopochino.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/consultarHoroscopo")
public class ConsultarHoroscopo_Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("usuario") != null){
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            req.setAttribute("animalUsuario", usuario.getAnimal());
        }
        else{
            req.setAttribute("animalUsuario", "Desconocido");
            req.setAttribute("error", "Sesion no encontrada.");
        }

        req.getRequestDispatcher("/consultaHoroscopoChino.jsp").forward(req, resp);
    }
}
