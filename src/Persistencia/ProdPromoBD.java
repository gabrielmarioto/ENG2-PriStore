/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Marca;
import Model.ProdPromo;
import Model.Produto;
import Model.ProdutoPm;
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

    
    public ProdPromo getPorProduto(int cod)
    {
       ProdPromo p = null;
       ResultSet rs = Banco.getCon().consultar("select * from prodpromo where ativo=true and prod_cod="+cod);
        try
        {
            if (rs.next())
            {
                p = new ProdPromo(rs.getInt("prod_cod"), rs.getInt("promo_cod"),rs.getDouble("valordesc"),rs.getBoolean("ativo"));
            }
        } catch (SQLException ex)
        {

        }

        return p;
    }
    public List<ProdPromo> get(String filtro)
    {
        String sql = "select * from prodPromo ";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<ProdPromo> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new ProdPromo(rs.getInt("prod_cod"), rs.getInt("promo_cod"),rs.getDouble("valordesc"),rs.getBoolean("ativo")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
    
    public List<ProdutoPm> getProdutos(String filtro){
        String sql= "select * from produto inner join prodpromo on prod_cod=cod ";
        if(!filtro.isEmpty())
            sql+= "where "+ filtro;
        List<ProdutoPm> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new ProdutoPm(rs.getInt("cod"), new CategoriaBD().get(rs.getInt("codCategoria")), rs.getString("nome"), rs.getFloat("preco"),
                        rs.getString("descricao"), new MarcaBD().get(rs.getInt("codMarca")), new ColecaoBD().get(rs.getInt("codColecao"))));
            }
        } catch (SQLException ex)
        {

        }
        return aux;
  
    }

    public void updateAtivo(int cod,boolean ativo)
    {
        String sql = "update prodpromo set ativo = #1 where promo_cod = "+cod;
        sql = sql.replaceAll("#1", ""+ativo);
       
        Banco.getCon().manipular(sql);
    }
}
