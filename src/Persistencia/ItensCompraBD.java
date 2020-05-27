/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.ItensCompra;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ItensCompraBD
{

    public boolean insertItensCompra(ItensCompra c)
    {
        String sql = "insert into itensCompra  (codCompra, codProduto, tamanho, valorProduto, quantidade) values (#1, #2, '#5', #3, #4)";
        sql = sql.replaceAll("#1", "" + c.getCodCompra().getCodCompra());
        sql = sql.replaceAll("#2", "" + c.getCodProduto().getCod());        
        sql = sql.replaceAll("#5", "" + c.getTamanho());
        sql = sql.replaceAll("#3", "" + c.getValorProduto());
        sql = sql.replaceAll("#4", "" + c.getQntd());
        
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }

    public boolean updateItensCompra(ItensCompra c)
    {
        String sql = "update itensCompra set quantidade = #4 where codCompra = " + c.getCodCompra().getCodCompra() + " and codProduto = " + c.getCodProduto().getCod();
        sql = sql.replaceAll("#4", "" + c.getQntd());
        
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }

    public boolean deleteItensCompra(ItensCompra c)
    {
        System.out.println("delete from itensCompra where codCompra = " + c.getCodCompra().getCodCompra() + "and codProduto = "+c.getCodProduto().getCod());
        return Banco.getCon().manipular("delete from itensCompra where codCompra = " + c.getCodCompra().getCodCompra() + "and codProduto = "+c.getCodProduto().getCod());
    }

    public ItensCompra get(int codCompra)
    {
        ItensCompra c = null;
        ResultSet rs = Banco.getCon().consultar("select * from itensCompra where codCompra =" + codCompra);

        try
        {
            if (rs.next())
            {
                c = new ItensCompra(new ProdutoBD().get(rs.getInt("codProduto")), new CompraBD().get(rs.getInt("codCompra")),new TamanhoBD().getTamanho(rs.getString("tamanho"), rs.getInt("codProduto")), rs.getDouble("valorProduto"), rs.getInt("quantidade"));
            }
        } catch (SQLException ex)
        {

        }

        return c;
    }

    public List<ItensCompra> get(String filtro)
    {
        String sql = "select * from itensCompra";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        System.out.println(sql);
        List<ItensCompra> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new ItensCompra(new ProdutoBD().get(rs.getInt("codProduto")), new CompraBD().get(rs.getInt("codCompra")),new TamanhoBD().getTamanho(rs.getString("tamanho"), rs.getInt("codProduto")), rs.getDouble("valorProduto"), rs.getInt("quantidade")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
}
