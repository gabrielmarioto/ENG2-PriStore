/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ConsignadoBD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class Consignado {
    
    private int cod;
    private Funcionario codFuncionario;
    private LocalDate dtEntrega;
    private LocalDate dtRetorno;
    private Cliente codCliente;
    private String status;

    public Consignado(int cod, Funcionario codFuncionario, LocalDate dtEntrega, LocalDate dtRetorno, Cliente codCliente, String status) {
        this.cod = cod;
        this.codFuncionario = codFuncionario;
        this.dtEntrega = dtEntrega;
        this.dtRetorno = dtRetorno;
        this.codCliente = codCliente;
        this.status = status;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Consignado() {
    }

    

    public int getCodCompra() {
        return cod;
    }

    public void setCodCompra(int codCompra) {
        this.cod = codCompra;
    }

    public Funcionario getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(Funcionario codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public LocalDate getDtEntrega() {
        return dtEntrega;
    }

    public void setDtEntrega(LocalDate dtEntrega) {
        this.dtEntrega = dtEntrega;
    }

    public LocalDate getDtRetorno() {
        return dtRetorno;
    }

    public void setDtRetorno(LocalDate dtRetorno) {
        this.dtRetorno = dtRetorno;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }
    public List<Consignado> selectConsignado(String filtro)
    {
        List<Consignado> aux = new ArrayList();
        ConsignadoBD c = new ConsignadoBD();
        aux = c.get(filtro);
        return aux;
    }
    public boolean insertConsignado()
    {
        ConsignadoBD c = new ConsignadoBD();
        return c.insertConsignado(this);
    }
    public boolean insereItens( List<ItensConsignado> aux, int cod)
    {
        int ok = 0;
        ConsignadoBD c = new ConsignadoBD();
        for ( ItensConsignado i : aux )
        {
            c.insertItens(i.getCodProduto().getCod(), cod);
        }
        if(ok == 0)
            return true;
        return false;
    }
    public boolean updateConsignado()
    {
        ConsignadoBD c = new ConsignadoBD();
        return c.updateConsignado(this);
    }
    
}
