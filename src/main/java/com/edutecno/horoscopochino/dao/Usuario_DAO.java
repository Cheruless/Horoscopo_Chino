package com.edutecno.horoscopochino.dao;

import com.edutecno.horoscopochino.modelo.Usuario;

import java.util.List;

public interface Usuario_DAO {
    void agregarUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
    Usuario loguearUsuario(String usuario, String pass);
    void eliminarUsuario(int idUsuario, String password);
    void modificarUsuario(int idUsuario, Usuario usuarioUpdate);
    Usuario obtenerUsuario(int idUsuario);
}
