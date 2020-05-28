/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ItensConsignadoBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class ItensConsignado {
    
    private Produto codProduto;
    private Cliente codCliente;
    private Tamanho tamanho;
    private double valorProduto;

    public ItensConsignado() {
    }

    public ItensConsignado(Produto codProduto, Cliente codCliente, Tamanho tamanho, double valorProduto) {
        this.codProduto = codProduto;
        this.codCliente = codCliente;
        this.tamanho = tamanho;
        this.valorProduto = valorProduto;
    }

    public Produto getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto) {
        this.codProduto = codProduto;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }
    public List<ItensConsignado> selectItens(String filtro)
    {
        List<ItensConsignado> aux = new ArrayList();
        ItensConsignadoBD c = new ItensConsignadoBD();
        aux = c.get(filtro);
        return aux;
    }
}
