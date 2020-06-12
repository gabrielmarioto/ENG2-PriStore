/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;
import Model.ItensConsignado;
import Model.ParcelasAPagar;
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
public class ParcelasAPagarBD {
    
    public List<ParcelasAPagar> get(String filtro)
    {
        String sql = "select * from parcelasAPagar";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<ParcelasAPagar> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(
                new ParcelasAPagar(
                        new VendaBD().get(rs.getInt("codVenda")),
                        rs.getDouble("valor"),
                        rs.getString("status"),
                        LocalDate.parse(rs.getString("vencimento"))
                ));
            }
        } catch (SQLException ex)
        {}
        
        return aux;
    }
    public boolean deleteParcela(int p)
    {
        return Banco.getCon().manipular("delete from parcelasAPagar where codVenda = " + p);
    }
    public boolean insertParcelas(int cod, double valor, String status, LocalDate data) 
    {
        String sql = "insert into parcelasapagar (codVenda, valor, status, vencimento) values (#1, #2, '#3', '#4')";
        sql = sql.replaceAll("#1", "" + cod);
        sql = sql.replaceAll("#2", "" + valor);
        sql = sql.replaceAll("#3", status);
        sql = sql.replaceAll("#4", "" + data);
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }
    
}
