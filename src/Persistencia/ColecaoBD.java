/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Categoria;
import Model.Colecao;
import Model.Marca;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ColecaoBD
{

    public boolean insertColecao(Colecao c)
    {
        String sql = "insert into colecao (nome, dataInicio) values ('#1', '#2')";
        sql = sql.replaceAll("#1", "" + c.getNome());
        sql = sql.replaceAll("#2", "" + c.getDataInicio());
        return Banco.getCon().manipular(sql);
    }

    public boolean updateColecao(Colecao c)
    {
        String sql = "update colecao set nome = '#1', dataInicio = '#2' where cod = " + c.getCod();
        sql = sql.replaceAll("#1", "" + c.getNome());
        sql = sql.replaceAll("#2", "" + c.getDataInicio());
        return Banco.getCon().manipular(sql);
    }

    public boolean deleteColecao(Colecao c)
    {
        return Banco.getCon().manipular("delete from colecao where cod =" + c.getCod());
    }

    public Colecao get(int cod)
    {
        Colecao c = null;
        ResultSet rs = Banco.getCon().consultar("select * from colecao where cod =" + cod);

        try
        {
            if (rs.next())
            {
                c = new Colecao(rs.getInt("cod"), rs.getString("nome"), LocalDate.parse(rs.getString("dataInicio")));
            }
        } catch (SQLException ex)
        {

        }

        return c;
    }

    public List<Colecao> get(String filtro)
    {
        String sql = "select * from colecao";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Colecao> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Colecao(rs.getInt("cod"), rs.getString("nome"), LocalDate.parse(rs.getString("dataInicio"))));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
}
