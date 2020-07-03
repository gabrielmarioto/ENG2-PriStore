/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ItensVendaBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class ItensVenda {
    private int codvenda;
    private Produto codProduto;
    private Tamanho tamanho;
    private double valorProduto;
    private int quantidade;
    private double valorTotal;

    public ItensVenda() {
    }

    public ItensVenda(int codvenda,Produto codProduto, Tamanho tamanho, double valorProduto, int quantidade, double valorTotal) {
        this.codvenda = codvenda;
        this.codProduto = codProduto;
        this.tamanho = tamanho;
        this.valorProduto = valorProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public int getCodvenda() {
        return codvenda;
    }

    public void setCodvenda(int codvenda) {
        this.codvenda = codvenda;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public boolean insereItem()
    {
        ItensVendaBD c = new ItensVendaBD();
        return c.insertItens(codvenda, codProduto.getCod(), getTamanho().getTamanho(), getValorProduto(), quantidade);
    }
    
    public boolean insereItens( List<ItensVenda> aux, int cod)
    {
        int ok = 0;
        ItensVendaBD c = new ItensVendaBD();
       
        for ( ItensVenda i : aux )
        {
            c.insertItens(cod, i.getCodProduto().getCod(), i.getTamanho().getTamanho(), i.getValorProduto(), i.getQuantidade());
        }
        if(ok == 0)
            return true;
        return false;
    }
    public boolean deleteItens(int cod)
    {
        ItensVendaBD c = new ItensVendaBD();
        return c.deleteItens(cod);
    }
    
    public boolean updateItem()
    {
        ItensVendaBD c = new ItensVendaBD();
        return c.updateItem(this);
    }
    
    public boolean deleteItem()
    {
        ItensVendaBD c = new ItensVendaBD();
        return c.deleteItem(this);
    }
    public List<ItensVenda> selectItens(String filtro)
    {
        List<ItensVenda> aux = new ArrayList();
        ItensVendaBD c = new ItensVendaBD();
        aux = c.get(filtro);
        return aux;
    }
    
}
