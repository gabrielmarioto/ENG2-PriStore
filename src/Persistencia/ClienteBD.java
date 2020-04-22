/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Cliente;
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
public class ClienteBD {
    
    public List<Cliente> get(String filtro)
    {
        String sql = "select * from cliente";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<Cliente> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome")
                ,rs.getString("cli_cpf"), rs.getString("cli_end"), rs.getString("cli_email")
                ,rs.getString("cli_telefone"), rs.getString("cli_sexo").charAt(0), rs.getDouble("cli_saldo")
                , LocalDate.parse(rs.getString("cli_dtNasc"))));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
    public boolean insertCliente(Cliente c)
    {
        String sql = "insert into cliente (cli_nome, cli_cpf, cli_end, cli_email, cli_telefone, cli_sexo, cli_saldo, cli_dtNasc)\n" +
"values ('#1','#2','#3','#4', '#5','#6',#7,'#8')";
        sql = sql.replaceAll("#1", "" + c.getNome());
        sql = sql.replaceAll("#2", "" + c.getCpf());
        sql = sql.replaceAll("#3", "" + c.getEnd());
        sql = sql.replaceAll("#4", "" + c.getEmail());
        sql = sql.replaceAll("#5", "" + c.getTelefone());
        sql = sql.replaceAll("#6", "" + c.getSexo());
        sql = sql.replaceAll("#7", "" + c.getSaldo());
        sql = sql.replaceAll("#8", "" + c.getDtNascimento());
       
        return Banco.getCon().manipular(sql);
    }

    public boolean updateCliente(Cliente c)
    {
        String sql = "update cliente set cli_nome = '#1', cli_cpf = '#2', cli_end = '#3' "
                + ", cli_email = '#4', cli_telefone = '#5', cli_sexo = '#6' , cli_saldo = #7"
                + ", cli_dtNasc = '#8' where cli_cod = " + c.getCod();
        sql = sql.replaceAll("#1", "" + c.getNome());
        sql = sql.replaceAll("#2", "" + c.getCpf());
        sql = sql.replaceAll("#3", "" + c.getEnd());
        sql = sql.replaceAll("#4", "" + c.getEmail());
        sql = sql.replaceAll("#5", "" + c.getTelefone());
        sql = sql.replaceAll("#6", "" + c.getSexo());
        sql = sql.replaceAll("#7", "" + c.getSaldo());
        sql = sql.replaceAll("#8", "" + c.getDtNascimento());
         System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }

    public boolean deleteCliente(Cliente c)
    {
        return Banco.getCon().manipular("delete from cliente where cli_cod =" + c.getCod());
    }

    public Cliente get(int cod)
    {
        Cliente c = null;
        ResultSet rs = Banco.getCon().consultar("select * from cliente where cli_cod =" + cod);

        try
        {
            if (rs.next())
            {
                c = new Cliente(rs.getInt("cli_cod"), rs.getString("cli_nome")
                ,rs.getString("cli_cpf"), rs.getString("cli_end"), rs.getString("cli_email")
                ,rs.getString("cli_telefone"), rs.getString("cli_sexo").charAt(0), rs.getDouble("cli_saldo")
                , LocalDate.parse(rs.getString("cli_dtNasc")));
            }
        } catch (SQLException ex)
        {

        }

        return c;
    }
}
