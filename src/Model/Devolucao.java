/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Arthur
 */
public class Devolucao {
    private int codigo;
    private int codigoProdutoant;
    private int codigoProdutonovo;
    private int codigoVenda;
    private Date datadevolucao;

    public Devolucao() {
    }

    public Devolucao(int codigo, int codigoProdutoant,int codigoProdutonovo ,int codigoVenda, Date datadevolucao) {
        this.codigo = codigo;
        this.codigoProdutoant = codigoProdutoant;
        this.codigoVenda = codigoVenda;
        this.datadevolucao = datadevolucao;
        this.codigoProdutonovo = codigoProdutonovo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoProduto() {
        return codigoProdutoant;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProdutoant = codigoProduto;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public Date getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

    public int getCodigoProdutonovo() {
        return codigoProdutonovo;
    }

    public void setCodigoProdutonovo(int codigoProdutonovo) {
        this.codigoProdutonovo = codigoProdutonovo;
    }
    
    
    
}
