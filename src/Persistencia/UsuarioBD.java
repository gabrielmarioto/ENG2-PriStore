/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Categoria;
import Model.Usuario;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class UsuarioBD
{

    public boolean insertUsuario(Usuario u)
    {
        String sql = "insert into usuario (fun_cod,usu_login,usu_senha,usu_nivel) values (#0,'#1','#2',#3)";
        sql = sql.replaceAll("#0", "" + u.getCodigo());
        sql = sql.replaceAll("#1", "" + u.getLogin());
        sql = sql.replaceAll("#2", "" + u.getSenha());
        sql = sql.replaceAll("#3", "" + u.getNivel());

        return Banco.getCon().manipular(sql);
    }

    public boolean updateUsuario(Usuario u)
    {
        String sql = "update usuario set usu_login = '#1', usu_senha='#2' , usu_nivel=#3 where cod = " + u.getCodigo();
        sql = sql.replaceAll("#1", "" + u.getLogin());
        sql = sql.replaceAll("#2", "" + u.getSenha());
        sql = sql.replaceAll("#3", "" + u.getNivel());
        return Banco.getCon().manipular(sql);
    }

    public boolean deleteUsuario(Usuario u)
    {
        return Banco.getCon().manipular("delete from usuario where fun_cod =" + u.getCodigo());
    }

    public Usuario get(int cod)
    {
        Usuario u = null;
        ResultSet rs = Banco.getCon().consultar("select * from usuario where fun_cod =" + cod);

        try
        {
            if (rs.next())
            {
                u = new Usuario(rs.getInt("fun_cod"), rs.getString("usu_login"),rs.getString("usu_senha"),rs.getInt("usu_nivel"));
            }
        } catch (SQLException ex)
        {

        }

        return u;
    }

    public List<Usuario> get(String filtro)
    {
        String sql = "select * from usuario";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Usuario> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Usuario(rs.getInt("fun_cod"), rs.getString("usu_login"),rs.getString("usu_senha"),rs.getInt("usu_nivel")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
}
