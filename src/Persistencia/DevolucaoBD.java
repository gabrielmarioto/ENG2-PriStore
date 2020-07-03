/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Devolucao;
import Model.ItensVenda;
import Model.ParcelasAPagar;
import Model.ProdPromo;
import Model.Produto;
import Model.TableDevolucao;
import Model.Tamanho;
import Util.Banco;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class DevolucaoBD {
    
    public boolean insertDevolucao(Devolucao d,String tamanho) throws SQLException
    {
        boolean ok= false;
        
        try{
            Banco.getCon().getConnect().setAutoCommit(false);
                String sql = "insert into devolucao (cod,codVenda, codProdutoant,codprodutonovo, datadevolucao) values (#1,#2, #3 , #4,'#5')";
                sql = sql.replaceAll("#1", "" + d.getCodigo());
                sql = sql.replaceAll("#2", "" + d.getCodigoVenda());
                sql = sql.replaceAll("#3", "" + d.getCodigoProdutoant());
                if(d.getCodigoProdutonovo()==0)
                    sql = sql.replaceAll("#4", "null");
                else
                    sql = sql.replaceAll("#4", "" + d.getCodigoProdutonovo());
                sql = sql.replaceAll("#5", "" + d.getDatadevolucao());
                ok=Banco.getCon().manipular(sql);
            int i=0; 
            if(ok)
            {
                ItensVenda item = new ItensVenda().selectItens("codVenda="+d.getCodigoVenda()+" and codproduto="+d.getCodigoProdutoant()).get(0);
                if(d.getCodigoProdutonovo()!=0)//vai inserir o novo produto
                {
                    List<ItensVenda> existe = new ItensVenda().selectItens("codVenda="+d.getCodigoVenda()+" and codproduto="+d.getCodigoProdutoant());
                    if(existe.isEmpty())
                    {
                        double valor = (new Produto().selectProduto(d.getCodigoProdutonovo()).getPreco());
                        ProdPromo pd = new ProdPromo().selectPorProduto(d.getCodigoProdutonovo());
                        if(pd!=null)
                            valor=pd.getDesconto();
                        ItensVenda novoItem = new ItensVenda(d.getCodigoVenda(),new Produto().selectProduto(d.getCodigoProdutonovo()),new Tamanho(tamanho),valor,1,valor);
                        ok=novoItem.insereItem();
                      }
                      else
                      {
                          existe.get(0).setQuantidade(existe.get(0).getQuantidade()+1);
                          existe.get(0).setValorTotal(existe.get(0).getQuantidade()*existe.get(0).getValorProduto());
                          ok=existe.get(0).updateItem();

                      }
                }
              if(ok)
              {
                  if(item.getQuantidade()==1)
                  {
                      ok=item.deleteItem();
                  }
                  else
                {
                  item.setQuantidade(item.getQuantidade()-1);
                  item.setValorTotal(item.getValorProduto()*item.getQuantidade());
                  ok=item.updateItem();
                 }
              }  
          }  
        }
        catch(SQLException ex)
        {
            ok=false;
        }
        if(ok)
            Banco.getCon().getConnect().commit();
        else
             Banco.getCon().getConnect().rollback();
        Banco.getCon().getConnect().setAutoCommit(true);
        return ok;
    }

     public boolean insertDevolucoes(Devolucao d,String tamanho) throws SQLException
    {
        boolean ok= false;
        
        String sql = "insert into devolucao (cod,codVenda, codProdutoant,codprodutonovo, datadevolucao) values (#1,#2, #3 , #4,'#5')";
        sql = sql.replaceAll("#1", "" + d.getCodigo());
        sql = sql.replaceAll("#2", "" + d.getCodigoVenda());
        sql = sql.replaceAll("#3", "" + d.getCodigoProdutoant());
        if(d.getCodigoProdutonovo()==0)
            sql = sql.replaceAll("#4", "null");
        else
        sql = sql.replaceAll("#4", "" + d.getCodigoProdutonovo());
        sql = sql.replaceAll("#5", "" + d.getDatadevolucao());
        ok=Banco.getCon().manipular(sql);
 
        int i=0;
        if(ok)
        {
            ItensVenda item = new ItensVenda().selectItens("codVenda="+d.getCodigoVenda()+" and codproduto="+d.getCodigoProdutoant()).get(0);
            if(d.getCodigoProdutonovo()!=0)//vai inserir o novo produto
            {
                List<ItensVenda> existe = new ItensVenda().selectItens("codVenda="+d.getCodigoVenda()+" and codproduto="+d.getCodigoProdutoant());
                if(existe.isEmpty())
                {
                    double valor = (new Produto().selectProduto(d.getCodigoProdutonovo()).getPreco());
                    ProdPromo pd = new ProdPromo().selectPorProduto(d.getCodigoProdutonovo());
                    if(pd!=null)
                        valor=pd.getDesconto();
                    ItensVenda novoItem = new ItensVenda(d.getCodigoVenda(),new Produto().selectProduto(d.getCodigoProdutonovo()),new Tamanho(tamanho),valor,1,valor);
                    ok=novoItem.insereItem();
                }
                else
                {
                    existe.get(0).setQuantidade(existe.get(0).getQuantidade()+1);
                    existe.get(0).setValorTotal(existe.get(0).getQuantidade()*existe.get(0).getValorProduto());
                    ok=existe.get(0).updateItem(); 
                    
                }
            }
            if(ok)
            {
                if(item.getQuantidade()==1)
                {
                    ok=item.deleteItem();
                }
                else
                {
                    item.setQuantidade(item.getQuantidade()-1);
                    item.setValorTotal(item.getValorProduto()*item.getQuantidade());
                    ok=item.updateItem();
                }
            }
        }
        return ok;
    }

    public boolean updateDevolucao(Devolucao d)
    {
        String sql = "update devolucao set codProdutoant=#1, codprodutonovo= #2, datadevolucao ='#3' where cod =" + d.getCodigo() +" and codvenda ="+ d.getCodigoVenda();
           
            sql = sql.replaceAll("#1", "" + d.getCodigoProdutoant());
            sql = sql.replaceAll("#2", "" + d.getCodigoProdutonovo());
            sql = sql.replaceAll("#3", "" + d.getDatadevolucao());
        return Banco.getCon().manipular(sql);
    }
    
    
    public boolean deleteDevolucao(Produto p)
    {
        return Banco.getCon().manipular("delete from produto where cod =" + p.getCod());
    }

    public Produto get(int cod)
    {
        Produto p = null;
        ResultSet rs = Banco.getCon().consultar("select * from produto where cod =" + cod);
        try
        {
            if (rs.next())
            {
                p = new Produto(rs.getInt("cod"), new CategoriaBD().get(rs.getInt("codCategoria")), rs.getString("nome"),
                        rs.getFloat("preco"), rs.getString("descricao"), new MarcaBD().get(rs.getInt("codMarca")),
                        new ColecaoBD().get(rs.getInt("codColecao")));
            }
        } catch (SQLException ex)
        {

        }

        return p;
    }

    public List<Produto> get(String filtro)
    {
        String sql = "select * from produto";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Produto> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Produto(rs.getInt("cod"), new CategoriaBD().get(rs.getInt("codCategoria")), rs.getString("nome"), rs.getFloat("preco"),
                        rs.getString("descricao"), new MarcaBD().get(rs.getInt("codMarca")), new ColecaoBD().get(rs.getInt("codColecao"))));
            }
            
        } catch (SQLException ex)
        {

        }
        return aux;
    }
}
