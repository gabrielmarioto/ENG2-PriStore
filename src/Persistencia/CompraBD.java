/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Compra;
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
public class CompraBD
{

    public boolean insertCompra(Compra c)
    {
        String sql = "insert into compra (valorTotal, codForn, dataCompra, desconto) values (#1, #2, '#3', #4)";
        sql = sql.replaceAll("#1", "" + c.getValorTotal());
        sql = sql.replaceAll("#2", "" + c.getCodForn().getCod());
        sql = sql.replaceAll("#3", "" + c.getDataCompra());
        sql = sql.replaceAll("#4", "" + c.getDesconto());        
        
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }

    public boolean updateCompra(Compra c)
    {
        String sql = "update compra set codForn = #1, valorTotal = #2, desconto = #3, dataCompra = '#4' where codCompra = " + c.getCodCompra();
        sql = sql.replaceAll("#1", "" + c.getCodForn().getCod());
        sql = sql.replaceAll("#2", "" + c.getValorTotal());
        sql = sql.replaceAll("#3", "" + c.getDesconto());
        sql = sql.replaceAll("#4", "" + c.getDataCompra());
        return Banco.getCon().manipular(sql);
    }

    public boolean deleteCompra(Compra c)
    {
        return Banco.getCon().manipular("delete from compra where codCompra =" + c.getCodCompra());
    }

    public Compra get(int cod)
    {
        Compra c = null;
        ResultSet rs = Banco.getCon().consultar("select * from compra where codCompra =" + cod);

        try
        {
            if (rs.next())
            {
                c = new Compra(rs.getInt("codCompra"), new FornecedorBD().get(rs.getInt("codForn")), rs.getDouble("valorTotal"), rs.getDouble("desconto"), LocalDate.parse(rs.getString("dataCompra")));
            }
        } catch (SQLException ex)
        {

        }

        return c;
    }
    
    public List<Compra> get(String filtro)
    {
        String sql = "select * from compra";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Compra> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Compra(rs.getInt("codCompra"), new FornecedorBD().get(rs.getInt("codForn")), rs.getDouble("valorTotal"), rs.getDouble("desconto"), LocalDate.parse(rs.getString("dataCompra"))));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
    
    public int getMaxPK()
    {
       return Banco.getCon().getMaxPK("compra", "codcompra");
    }
}
