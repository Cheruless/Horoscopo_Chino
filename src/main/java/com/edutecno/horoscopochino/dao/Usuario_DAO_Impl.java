package com.edutecno.horoscopochino.dao;

import com.edutecno.horoscopochino.modelo.Usuario;
import com.edutecno.horoscopochino.procesaconexion.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario_DAO_Impl implements Usuario_DAO {
    @Override
    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, username, email, fecha_nacimiento, password, animal) VALUES (?,?," +
                "?,?,?,?)";
        try (
                Connection con = ConexionDB.generaPoolConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, usuario.getFechaNacimiento());
            ps.setString(5, usuario.getPassword());
            ps.setString(6, usuario.getAnimal());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "select * from usuarios";
        try (
                Connection con = ConexionDB.generaPoolConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Usuario u = getDataUsuario(rs);
                usuarios.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public Usuario loguearUsuario(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE BINARY username=? AND BINARY password=?";
        Usuario usuario = null;
        try (
                Connection con = ConexionDB.generaPoolConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                usuario = getDataUsuario(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public void eliminarUsuario(int idUsuario, String password) {
        String sql = "DELETE FROM usuarios WHERE id=? AND BINARY password=?";
        try (
                Connection con = ConexionDB.generaPoolConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idUsuario);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificarUsuario(int idUsuario, Usuario usuarioUpdate) {
        StringBuilder sql = new StringBuilder("UPDATE usuarios SET ");
        List<String> campos = new ArrayList<>();
        List<Object> valores = new ArrayList<>();

        if (usuarioUpdate.getNombre() != null) {
            campos.add("nombre = ?");
            valores.add(usuarioUpdate.getNombre());
        }
        if (usuarioUpdate.getUsername() != null) {
            campos.add("username = ?");
            valores.add(usuarioUpdate.getUsername());
        }
        if (usuarioUpdate.getEmail() != null) {
            campos.add("email = ?");
            valores.add(usuarioUpdate.getEmail());
        }
        if (usuarioUpdate.getPassword() != null) {
            campos.add("password = ?");
            valores.add(usuarioUpdate.getPassword());
        }

        if (campos.isEmpty()) {
            return;
        }

        sql.append(String.join(", ", campos));
        sql.append(" WHERE id = ?");

        try (
                Connection con = ConexionDB.generaPoolConexion();
                PreparedStatement ps = con.prepareStatement(sql.toString())
        ) {
            for (int i = 0; i < valores.size(); i++) {
                ps.setObject(i + 1, valores.get(i));
            }
            ps.setInt(valores.size() + 1, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id=?";
        try (
                Connection con = ConexionDB.generaPoolConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                usuario = getDataUsuario(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    private Usuario getDataUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setUsername(rs.getString("username"));
        usuario.setEmail(rs.getString("email"));
        usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        usuario.setPassword(rs.getString("password"));
        usuario.setAnimal(rs.getString("animal"));
        return usuario;
    }
}
