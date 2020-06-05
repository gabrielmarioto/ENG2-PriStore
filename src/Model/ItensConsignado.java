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
    private Tamanho tamanho;
    private double valorProduto;
    private int quantidade;

    public ItensConsignado() {
    }

    public ItensConsignado( Produto codProduto, Tamanho tamanho, double valorProduto, int quantidade) {
        this.codProduto = codProduto;
        this.tamanho = tamanho;
        this.valorProduto = valorProduto;
        this.quantidade = quantidade;
    }

    public Produto getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto) {
        this.codProduto = codProduto;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public boolean deleteItens(int cod)
    {
        ItensConsignadoBD c = new ItensConsignadoBD();
        return c.deleteItens(cod);
    }
    public List<ItensConsignado> selectItens(String filtro)
    {
        List<ItensConsignado> aux = new ArrayList();
        ItensConsignadoBD c = new ItensConsignadoBD();
        aux = c.get(filtro);
        return aux;
    }
}
