/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.ItensConsignado;
import Model.ItensVenda;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class ItensVendaBD {
    public List<ItensVenda> get(String filtro)
    {
        String sql = "select * from itensVenda";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<ItensVenda> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new ItensVenda(new ProdutoBD().get(rs.getInt("codProduto")),
                        new TamanhoBD().getTamanho(rs.getString("tamanho"), rs.getInt("codProduto")),
                        rs.getDouble("valorProduto"),
                        rs.getInt("quantidade"),
                        rs.getInt("quantidade") * rs.getDouble("valorProduto")
                ));
            }
        } catch (SQLException ex)
        {}
        
        return aux;
    }
    public boolean insertItens(int cod, int coditem, String tamanho, Double valor, int quantidade) 
    {
        String sql = "insert into itensVenda (codVenda, codProduto, tamanho, valorProduto, quantidade)"
                + "values (#1, #2, '#3', #4, #5)";
        sql = sql.replaceAll("#1", "" + cod);
        sql = sql.replaceAll("#2", "" + coditem);
        sql = sql.replaceAll("#3", tamanho);
        sql = sql.replaceAll("#4", "" + valor);
        sql = sql.replaceAll("#5", "" + quantidade);
        return Banco.getCon().manipular(sql);
    }
    public boolean deleteItens(int v)
    {
        return Banco.getCon().manipular("delete from itensVenda where codVenda = " + v);
    }
}
