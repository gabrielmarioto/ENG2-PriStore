/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ParcelasAPagarBD;
import Persistencia.VendaBD;
import Util.Banco;
import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRUNO
 */
public class ParcelasAPagar {
    
    private Venda venda;
    private double valor;
    private String status;
    private LocalDate vencimento;

    public ParcelasAPagar() {
    }

    public ParcelasAPagar(Venda venda, double valor, String status, LocalDate vencimento) {
        this.venda = venda;
        this.valor = valor;
        this.status = status;
        this.vencimento = vencimento;
    }
    public ParcelasAPagar(double valor, LocalDate vencimento) {
        this.valor = valor;
        this.vencimento = vencimento;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }
    public boolean insereParcelas(List<ParcelasAPagar> aux, int cod)
    {

        int ok = 0;
        ParcelasAPagarBD c = new ParcelasAPagarBD();
        aux.forEach((i) -> {
            c.insertParcelas(cod, i.getValor(), i.getStatus(), i.getVencimento());
        });
        return true;
    }
    
    public boolean updateParcelas(List<ParcelasAPagar> aux) throws SQLException
    {
        ParcelasAPagarBD c = new ParcelasAPagarBD();
        boolean flag=true;
        try {
            Banco.getCon().getConnect().setAutoCommit(false);
            int i=0; 
            while(i<aux.size() && flag)
            {
            flag=c.updateParcelas(aux.get(i));
            i++;
            }
        } catch (SQLException ex) {
            flag=false;
        }
       if(flag)
            Banco.getCon().getConnect().commit();
        else
             Banco.getCon().getConnect().rollback();
        Banco.getCon().getConnect().setAutoCommit(true);
        return flag;
            
        
    }
    public boolean deleteParcelas(int cod)
    {
        ParcelasAPagarBD c = new ParcelasAPagarBD();
        return c.deleteParcela(cod);
    }
    public List<ParcelasAPagar> selectParcelas(String filtro)
    {
        List<ParcelasAPagar> aux = new ArrayList();
        ParcelasAPagarBD c = new ParcelasAPagarBD();
        aux = c.get(filtro);
        return aux;
    }
    
    
    
}
