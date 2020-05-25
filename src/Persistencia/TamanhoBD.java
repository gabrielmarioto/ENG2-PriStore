/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Tamanho;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class TamanhoBD
{

    public boolean insertTamanho(Tamanho t)
    {
        String sql = "insert into tamanho (codProduto, tamanho, qtde) values (#1, '#2', #3)";
        sql = sql.replaceAll("#1", "" + t.getCodProduto().getCod());
        sql = sql.replaceAll("#2", "" + t.getTamanho());
        sql = sql.replaceAll("#3", "" + t.getQtde());

        return Banco.getCon().manipular(sql);
    }

    public boolean updateTamanho(Tamanho t)
    {
        String sql = "update tamanho set qtde = #3 where codProduto = " + t.getCodProduto().getCod() + "and tamanho like '%"+t.getTamanho()+"%'";
        sql = sql.replaceAll("#3", "" + t.getQtde());
        
        System.out.println("Update "+sql);
        return Banco.getCon().manipular(sql);
    }
    
    public boolean deleteTamanho(Tamanho t)
    {
        return Banco.getCon().manipular("delete from tamanho where codProduto =" + t.getCodProduto().getCod());
    }    

    public Tamanho getTamanho(String tamanho, int cod)
    {
        Tamanho t = null;
        System.out.println("select * from tamanho where tamanho like '%"+tamanho+"%' and codproduto = "+cod);
        ResultSet rs = Banco.getCon().consultar("select * from tamanho where tamanho like '%"+tamanho+"%' and codproduto = "+cod);

        try
        {
            if (rs.next())
            {
                t = new Tamanho(new ProdutoBD().get(rs.getInt("codProduto")), rs.getString("tamanho"), rs.getInt("qtde"));
            }
        } catch (SQLException ex)
        {

        }

        return t;
    }
    public List<Tamanho> get(String filtro)
    {
        String sql = "select * from tamanho";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Tamanho> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Tamanho(new ProdutoBD().get(rs.getInt("codProduto")), rs.getString("tamanho"), rs.getInt("qtde")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
}
