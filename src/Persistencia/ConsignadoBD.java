/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;
import Model.Consignado;
import Model.ItensConsignado;
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
    public boolean insertConsignado(Consignado c)
    {
        String sql = "insert into consignacao (codfunc, dataentrega, dataretorno, codcliente, status) values (#1, '#2','#3', #4, '#5')";
        sql = sql.replaceAll("#1", "" + c.getCodFuncionario().getCodigo());
        sql = sql.replaceAll("#2", "" + c.getDtEntrega());
        sql = sql.replaceAll("#3", "" + c.getDtRetorno());
        sql = sql.replaceAll("#4", "" + c.getCodCliente().getCod());        
        sql = sql.replaceAll("#5", c.getStatus());   
        
        return Banco.getCon().manipular(sql);
    }
    public boolean insertItens(int coditem, int cod)
    {
        String sql = "insert into itensconsignado (codconsignado, codproduto)"
                + "values (#1, '#2')";
        sql = sql.replaceAll("#1", "" + cod);
        sql = sql.replaceAll("#2", "" + coditem);
        
        return Banco.getCon().manipular(sql);
    }
    public boolean updateConsignado(Consignado c)
    {
        String sql = "update consignacao set codfunc = #1, dataentrega = '#2', dataretorno = '#3', codcliente = #4, status = '#5' where cod = " + c.getCod();
        //System.out.println(c.getCodCliente().getCod()+"");
        sql = sql.replaceAll("#1", "" + c.getCodFuncionario().getCodigo());
        sql = sql.replaceAll("#2", "" + c.getDtEntrega());
        sql = sql.replaceAll("#3", "" + c.getDtRetorno());
        sql = sql.replaceAll("#4", c.getCodCliente().getCod()+"");        
        sql = sql.replaceAll("#5", c.getStatus());   
        return Banco.getCon().manipular(sql);
    }
}
