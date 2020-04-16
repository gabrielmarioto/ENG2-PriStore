/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Produto;
import Util.Banco;

/**
 *
 * @author Gabriel
 */
public class ProdutoBD
{

    //#1 = INTEIRO
    //'#1' = STRING
    public boolean insertProduto(Produto p)
    {
        String sql = "insert into produto (cod, codCategoria, nome, tamanho, preco, descricao, codMarca, codColecao) values (#1, #2, '#3', '#4', #5, '#6', #7, #8)";
        sql = sql.replaceAll("#1", "" + p.getCod());
        sql = sql.replaceAll("#2", "" + p.getCodCategoria().getCod());
        sql = sql.replaceAll("#3", "" + p.getNome());
        sql = sql.replaceAll("#4", "" + p.getTamanho());
        sql = sql.replaceAll("#5", "" + p.getPreco());
        sql = sql.replaceAll("#6", "" + p.getDescricao());
        sql = sql.replaceAll("#7", "" + p.getCodMarca().getCod());
        sql = sql.replaceAll("#8", "" + p.getCodColecao().getCod());

        return Banco.getCon().manipular(sql);
    }

    public boolean updateProduto(Produto p)
    {
        String sql = "update produto set codCategoria=#1, nome= '#2', tamanho = '#3', preco =#4, descricao ='#5', codMarca = #6, codColecao = #7 where cod =" + p.getCod();
        sql = sql.replaceAll("#1", "" + p.getCodCategoria().getCod());
        sql = sql.replaceAll("#2", "" + p.getNome());
        sql = sql.replaceAll("#3", "" + p.getTamanho());
        sql = sql.replaceAll("#4", "" + p.getPreco());
        sql = sql.replaceAll("#5", "" + p.getDescricao());
        sql = sql.replaceAll("#6", "" + p.getCodMarca().getCod());
        sql = sql.replaceAll("#7", "" + p.getCodColecao().getCod());

        return Banco.getCon().manipular(sql);
    }

    public boolean deleteProduto(Produto p)
    {
        return Banco.getCon().manipular("delete from produto where cod =" + p.getCod());
    }     
}
