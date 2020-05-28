/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.ItensConsignado;
import Util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class ItensConsignadoBD {
    
    //public boolean deleteItensConsignado(ItensConsignado c)
    {
        //return Banco.getCon().manipular("delete from itensCompra where codCompra = " + c.getCodCompra().getCodCompra() + "and codProduto = "+c.getCodProduto().getCod());
    }
    public List<ItensConsignado> get(String filtro)
    {
        String sql = "select * from itensconsignado";
        if (!filtro.isEmpty())
        {
            sql += " where " + filtro;
        }
        List<ItensConsignado> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while (rs.next())
            {
                aux.add(new ItensConsignado(
                            new ProdutoBD().get(rs.getInt("codProduto")),
                            new ClienteBD().get(rs.getInt("codCliente")),
                            new TamanhoBD().getTamanho(rs.getString("tamanho"), rs.getInt("codProduto")),
                            rs.getDouble("valorProduto")));
            }
        } catch (SQLException ex)
        {

        }

        return aux;
    }
    
}
