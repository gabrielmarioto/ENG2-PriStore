/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Categoria;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class CategoriaBD
{

    public boolean insertCategoria(Categoria c)
    {
        String sql = "insert into categoria (nome) values ('#1')";
        sql = sql.replaceAll("#1", "" + c.getNome());

        return Banco.getCon().manipular(sql);
    }

    public boolean updateCategoria(Categoria c)
    {
        String sql = "update categoria set nome = '#1' where cod = " + c.getCod();
        sql = sql.replaceAll("#1", "" + c.getNome());
        return Banco.getCon().manipular(sql);
    }

    public boolean deleteCategoria(Categoria c)
    {
        return Banco.getCon().manipular("delete from categoria where cod =" + c.getCod());
    }

    public Categoria get(int cod)
    {
        Categoria c = null;
        ResultSet rs = Banco.getCon().consultar("select * from categoria where cod =" + cod);

        try
        {
            if (rs.next())
            {
                c = new Categoria(rs.getInt("cod"), rs.getString("nome"));
            }
        } catch (SQLException ex)
        {

        }

        return c;
    }

    public List<Categoria> get(String filtro)
    {
        String sql = "select * from categoria";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Categoria> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Categoria(rs.getInt("cod"), rs.getString("nome")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
}
