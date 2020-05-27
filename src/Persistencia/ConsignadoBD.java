/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;
import Model.Consignado;
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
public class ConsignadoBD {
    
    public List<Consignado> get(String filtro)
    {
        List<Consignado> aux = new ArrayList();
        String sql = "select * from consignacao";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new Consignado(rs.getInt("cod"),
                        new FuncionarioBD().get(rs.getInt("codFunc")),
                        LocalDate.parse(rs.getString("dataEntrega")),
                        LocalDate.parse(rs.getString("dataRetorno")),
                        new ClienteBD().get(rs.getInt("codCliente")),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex)
        {
        }
        return aux;
    }
}
