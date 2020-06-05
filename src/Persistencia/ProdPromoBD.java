/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Marca;
import Model.ProdPromo;
import Model.Produto;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ProdPromoBD
{
    public boolean insert(ProdPromo p)
    {
        String sql = "insert into prodpromo (prod_cod,promo_cod,ativo,valordesc) values (#1,#2,#3,#4)";
        sql = sql.replaceAll("#1", "" + p.getCodigoProduto());
        sql = sql.replaceAll("#2", "" + p.getCodigoPromocao());
        sql = sql.replaceAll("#3", "true");
        sql = sql.replaceAll("#4", "" + p.getDesconto());

        return Banco.getCon().manipular(sql);
    }

    public boolean update(ProdPromo p)
    {
        String sql = "update prodpromo set ativo = #1, valordesc= #2 where prod_cod = " + p.getCodigoProduto() + " and promo_cod = "+p.getCodigoPromocao();
        sql = sql.replaceAll("#1", ""+p.isAtivo());
        sql = sql.replaceAll("#2", "" + p.getDesconto());
        return Banco.getCon().manipular(sql);
    }

    public boolean delete(ProdPromo p)
    {
        return Banco.getCon().manipular("delete from marca where prod_cod = " + p.getCodigoProduto() + " and promo_cod = "+p.getCodigoPromocao());
    }

    
    public ProdPromo get(int cod)
    {
        ProdPromo p = null;
        String sql = "select * from prodpromo where ";
        return p;
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
