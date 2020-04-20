/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Fornecedor;
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
public class FornecedorBD
{

    public boolean insertFornecedor(Fornecedor f)
    {
        String sql = "insert into fornecedor (nome, cnpj, inscricaoEstadual, endereco, email, tel, cidade, numRua, bairro, cep) values ('#2', '#3', '#4', '#5', '#6', '#7', '#8', #9, '#A', '#B')";
        sql = sql.replaceAll("#2", "" + f.getNome());
        sql = sql.replaceAll("#3", "" + f.getCnpj());
        sql = sql.replaceAll("#4", "" + f.getInscrocaoEstadual());
        sql = sql.replaceAll("#5", "" + f.getEndereco());
        sql = sql.replaceAll("#6", "" + f.getEmail());
        sql = sql.replaceAll("#7", "" + f.getTelefone());
        sql = sql.replaceAll("#8", "" + f.getCidade());
        sql = sql.replaceAll("#9", "" + f.getNumRua());
        sql = sql.replaceAll("#A", "" + f.getBairro());
        sql = sql.replaceAll("#B", "" + f.getCep());
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }

    public boolean updateFornecedor(Fornecedor f)
    {
        String sql = "update fornecedor set nome = '#1', cnpj = '#2', inscricaoEstadual = '#3', endereco = '#4', email = '#5', tel = '#6', cidade = '#7', numRua = #8, bairro = '#9', cep = '#A' where cod = " + f.getCod();

        sql = sql.replaceAll("#1", "" + f.getNome());
        sql = sql.replaceAll("#2", "" + f.getCnpj());
        sql = sql.replaceAll("#3", "" + f.getInscrocaoEstadual());
        sql = sql.replaceAll("#4", "" + f.getEndereco());
        sql = sql.replaceAll("#5", "" + f.getEmail());
        sql = sql.replaceAll("#6", "" + f.getTelefone());
        sql = sql.replaceAll("#7", "" + f.getCidade());
        sql = sql.replaceAll("#8", "" + f.getNumRua());
        sql = sql.replaceAll("#9", "" + f.getBairro());
        sql = sql.replaceAll("#A", "" + f.getCep());

        return Banco.getCon().manipular(sql);
    }

    public boolean deleteFornecedor(Fornecedor f)
    {
        return Banco.getCon().manipular("delete from fornecedor where cod =" + f.getCod());
    }

    public Fornecedor get(int cod)
    {
        Fornecedor f = null;
        ResultSet rs = Banco.getCon().consultar("select * from fornecedor where cod =" + cod);
        try
        {
            if (rs.next())
            {
                f = new Fornecedor(rs.getInt("cod"), rs.getString("nome"), rs.getString("cnpj"), rs.getString("inscricaoEstadual"), rs.getString("endereco"), rs.getString("email"), rs.getString("telefone"), rs.getString("cidade"), rs.getInt("numRua"), rs.getString("bairro"), rs.getString("cep"));
            }
        } catch (SQLException ex)
        {

        }
        return f;
    }

    public List<Fornecedor> get(String filtro)
    {
        String sql = "select * from fornecedor";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Fornecedor> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Fornecedor(rs.getInt("cod"), rs.getString("nome"), rs.getString("cnpj"), rs.getString("inscricaoEstadual"), rs.getString("endereco"), rs.getString("email"), rs.getString("tel"), rs.getString("cidade"), rs.getInt("numRua"), rs.getString("bairro"), rs.getString("cep")));
            }
        } catch (SQLException ex)
        {

        }
        for (int i = 0; i < aux.size(); i++)
        {
            System.out.println(aux.get(i));
        }
        return aux;
    }
}
