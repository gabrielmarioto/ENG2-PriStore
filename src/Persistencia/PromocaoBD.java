/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Marca;
import Model.Produto;
import Model.Promocao;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class PromocaoBD
{

    //#1 = INTEIRO
    //'#1' = STRING
    public boolean insertPromocao(Promocao p)
    {
        String sql = "insert into (nome,, datainicio, datafinal, tipo,valorpromocao) values ('#2', #4 , #5, '#6', #7)";
        sql = sql.replaceAll("#2", "" + p.getNome());
        sql = sql.replaceAll("#3", "" + p.getInicio());
        sql = sql.replaceAll("#5", "" + p.getFim());
        sql = sql.replaceAll("#6", "" + p.getTipo());
        sql = sql.replaceAll("#7", "" + p.getValor());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean updatePromocao(Promocao p)
    {
        String sql = "update produto set  nome= '#2', datainicio =#4, datafinal =#5, tipo = '#6', valorpromocao = #7 where cod =" + p.getCodigo();
         sql = sql.replaceAll("#2", "" + p.getNome());
        sql = sql.replaceAll("#3", "" + p.getInicio());
        sql = sql.replaceAll("#5", "" + p.getFim());
        sql = sql.replaceAll("#6", "" + p.getTipo());
        sql = sql.replaceAll("#7", "" + p.getValor());

        return Banco.getCon().manipular(sql);
    }

    public boolean deletePromocao(Promocao p)
    {
        return Banco.getCon().manipular("delete from promocao where cod =" + p.getCodigo());
    }

    public Promocao get(int cod)
    {
        Promocao p = null;
        ResultSet rs = Banco.getCon().consultar("select * from promocao where cod =" + cod);
        try
        {
            if (rs.next())
            {
                p = new Promocao(rs.getInt("cod"), rs.getString("nome"), rs.getDate("inicio"), rs.getDate("final"),rs.getString("tipo"),rs.getDouble("valorpromocao"));
            }
        } catch (SQLException ex)
        {

        }

        return p;
    }

    public List<Promocao> get(String filtro)
    {
        String sql = "select * from promocao";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Promocao> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Promocao(rs.getInt("cod"), rs.getString("nome"), rs.getDate("inicio"), rs.getDate("final"),rs.getString("tipo"),rs.getDouble("valorpromocao")));
            }
        } catch (SQLException ex)
        {

        }
        return aux;
    }
}
