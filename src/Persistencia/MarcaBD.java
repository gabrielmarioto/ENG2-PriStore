/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Marca;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class MarcaBD
{
    public boolean insertCategoria(Marca m)
    {
        String sql = "insert into marca (cod, nome) values (#1, '#2')";
        sql = sql.replaceAll("#1", "" + m.getCod());
        sql = sql.replaceAll("#2", "" + m.getNome());

        return Banco.getCon().manipular(sql);
    }

    public boolean updateCategoria(Marca m)
    {
        String sql = "update marca set nome = '#1' where cod = " + m.getCod();

        return Banco.getCon().manipular(sql);
    }

    public boolean deleteCategoria(Marca m)
    {
        return Banco.getCon().manipular("delete from marca where cod =" + m.getCod());
    }

    public Marca get(int cod)
    {
        Marca m = null;
        ResultSet rs = Banco.getCon().consultar("select * from marca where cod =" + cod);

        try
        {
            if (rs.next())
            {
                m = new Marca(rs.getInt("cod"), rs.getString("nome"));
            }
        } catch (SQLException ex)
        {

        }

        return m;
    }

    public List<Marca> get(String filtro)
    {
        String sql = "select * from marca";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Marca> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Marca(rs.getInt("cod"), rs.getString("nome")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
}
