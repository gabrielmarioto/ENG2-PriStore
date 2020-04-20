/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Fornecedor;
import Model.Funcionario;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 * 
 * 
 */
public class FuncionarioBD {
    public boolean insertFuncionario(Funcionario f)
    {
        String sql = "insert into funcionario (fun_cod, fun_nome, fun_cpf, fun_sexo, fun_salario, fun_telefone, fun_email, fun_endereco,fun_bairro,fun_cidade,fun_cep)"
                                               + " values (#1, '#2', '#3', '#4', #5, '#6', '#7', '#8','#9','#A','#B')";
        sql = sql.replaceAll("#1", "" + f.getCodigo());
        sql = sql.replaceAll("#2", "" + f.getNome());
        sql = sql.replaceAll("#3", "" + f.getCpf());
        sql = sql.replaceAll("#4", "" + f.getSexo());
        sql = sql.replaceAll("#5", "" + f.getSalario());
        sql = sql.replaceAll("#6", "" + f.getTelefone());
        sql = sql.replaceAll("#7", "" + f.getEmail());
        sql = sql.replaceAll("#8", "" + f.getEndereco());
        sql = sql.replaceAll("#9", "" + f.getBairro());
        sql = sql.replaceAll("#A", "" + f.getCidade());
        sql = sql.replaceAll("#B", "" + f.getCep());
        return Banco.getCon().manipular(sql);
    }

    public boolean updateFuncionario(Funcionario f)
    {
        String sql = "update funcionario set fun_nome = '#2', fun_cpf = '#3', fun_sexo = '#4', fun_salario = #5, fun_telefone = '#6', fun_email = '#7',"
                + " fun_endereco = '#8', fun_bairro = '#9' ,fun_cidade = '#A', fun_cep ='#B'  where fun_cod = " + f.getCodigo();


        sql = sql.replaceAll("#2", "" + f.getNome());
        sql = sql.replaceAll("#3", "" + f.getCpf());
        sql = sql.replaceAll("#4", "" + f.getSexo());
        sql = sql.replaceAll("#5", "" + f.getSalario());
        sql = sql.replaceAll("#6", "" + f.getTelefone());
        sql = sql.replaceAll("#7", "" + f.getEmail());
        sql = sql.replaceAll("#8", "" + f.getEndereco());
        sql = sql.replaceAll("#9", "" + f.getBairro());
        sql = sql.replaceAll("#A", "" + f.getCidade());
        sql = sql.replaceAll("#B", "" + f.getCep());

        return Banco.getCon().manipular(sql);
    }

    public boolean deleteFuncionario(Funcionario f)
    {
        return Banco.getCon().manipular("delete from funcionario where fun_cod =" + f.getCodigo());
    }

    public Funcionario get(int cod)
    {
        Funcionario f = null;
        ResultSet rs = Banco.getCon().consultar("select * from Funcionario where fun_cod =" + cod);
        try
        {
            if (rs.next())
            {
                f = new Funcionario(rs.getInt("fun_cod"), rs.getString("fun_nome"), rs.getString("fun_cpf"),
                        rs.getString("fun_sexo").charAt(0),rs.getDouble("fun_salario"),rs.getString("fun_telefone"),
                        rs.getString("fun_email"), rs.getString("fun_endereco"),rs.getString("fun_bairro"),
                        rs.getString("fun_cidade"),rs.getString("fun_cep"));
            }
        } catch (SQLException ex)
        {

        }
        return f;
    }

    public List<Funcionario> get(String filtro)
    {
        String sql = "select * from funcionario";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Funcionario> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add( new Funcionario(rs.getInt("fun_cod"), rs.getString("fun_nome"), rs.getString("fun_cpf"),
                        rs.getString("fun_sexo").charAt(0),rs.getDouble("fun_salario"),rs.getString("fun_telefone"),
                        rs.getString("fun_email"), rs.getString("fun_endereco"),rs.getString("fun_bairro"),
                        rs.getString("fun_cidade"),rs.getString("fun_cep")));
            }
        } catch (SQLException ex)
        {

        }
        return aux;
    }
}
