/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.VendaBD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class Venda {
    
    private int cod;
    private LocalDate data;
    private Cliente cliente;
    private String tipopgto;

    public Venda() {
    }

    public Venda(int cod, LocalDate data, Cliente cliente, String tipopgto) {
        this.cod = cod;
        this.data = data;
        this.cliente = cliente;
        this.tipopgto = tipopgto;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipopgto() {
        return tipopgto;
    }

    public void setTipopgto(String tipopgto) {
        this.tipopgto = tipopgto;
    }
    
    public List<Venda> selectVendas(String filtro)
    {
        List<Venda> aux = new ArrayList();
        VendaBD v = new VendaBD();
        aux = v.get(filtro);
        return aux;
    }
    
    public Venda selectVenda(int cod)
    {
        return new VendaBD().get(cod);
    }
    public boolean insertVenda()
    {
        VendaBD v = new VendaBD();
        return v.insertVenda(this);
    }
    
    public boolean updateVenda()
    {
        VendaBD v = new VendaBD();
        return v.updateVenda(this);
    } 
    public int getMaxPK()
    {
        VendaBD c = new VendaBD();
        return c.getMaxPK();
   }
    public boolean deleteVenda(int cod)
    {
        VendaBD c = new VendaBD();
        return c.deleteVenda(cod);
    }

    @Override
    public String toString() {
        
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return ""+data.format(formatter);
    }
    
    
    
}
