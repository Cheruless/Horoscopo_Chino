package com.edutecno.horoscopochino.dao;

import com.edutecno.horoscopochino.modelo.Usuario;

import java.util.List;

public interface Usuario_DAO {
    public void agregarUsuario(Usuario usuario);
    public List<Usuario> obtenerUsuarios();
    public Usuario loguearUsuario(String usuario, String pass);
    public void eliminarUsuario(int idUsuario, String password);
    public void modificarUsuario(int idUsuario, Usuario usuarioUpdate);
    public Usuario obtenerUsuario(int idUsuario);
}
