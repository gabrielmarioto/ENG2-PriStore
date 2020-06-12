/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Consignado;
import Model.Venda;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class VendaBD {
    public List<Venda> get(String filtro)
    {
        List<Venda> aux = new ArrayList();
        String sql = "select * from venda";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Venda(
                            rs.getInt("codVenda"),
                            LocalDate.parse(rs.getString("data")),
                            new ClienteBD().get(rs.getInt("codCliente")),
                            rs.getString("tipo")
                ));
            }
        } catch (SQLException ex)
        {
            System.out.println("erro");
        }
        return aux;
    }
    public Venda get(int cod)
    {
        Venda c = null;
        ResultSet rs = Banco.getCon().consultar("select * from venda where codVenda =" + cod);

        try
        {
            if (rs.next())
            {
                c = new Venda(
                        rs.getInt("cod"),
                        LocalDate.parse(rs.getString("data")),
                        new ClienteBD().get(rs.getInt("cliente")),
                        rs.getString("tipopgto")
                );
            }
        } catch (SQLException ex)
        {

        }

        return c;
    }
    public boolean insertVenda(Venda v)
    {
        String sql = "insert into venda (data, codCliente, tipo) values ('#2',#3, '#4')";
        sql = sql.replaceAll("#2", "" + v.getData());
        sql = sql.replaceAll("#3", "" + v.getCliente().getCod());
        sql = sql.replaceAll("#4", "" + v.getTipopgto());
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }
    
    public boolean updateVenda(Venda v)
    {
        String sql = "update venda set codVenda = #1, data = '#2', codCliente = #3, tipo = '#4' where codVenda = " + v.getCod();
        //System.out.println(c.getCodCliente().getCod()+"");
        sql = sql.replaceAll("#1", "" + v.getCod());
        sql = sql.replaceAll("#2", "" + v.getData());
        sql = sql.replaceAll("#3", "" + v.getCliente().getCod());
        sql = sql.replaceAll("#4", "" + v.getTipopgto());
        
        return Banco.getCon().manipular(sql);
    }
    public int getMaxPK()
    {
       return Banco.getCon().getMaxPK("venda", "codVenda");
    }
     public boolean deleteVenda(int c)
    {
        return Banco.getCon().manipular("delete from venda where codVenda = " + c);
    }
    
}
