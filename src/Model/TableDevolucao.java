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
public class TableDevolucao {
    private int codigo;
    private String nome;
    private double preco;
    private int qtde;
    private String tamanho;
    private int codVenda;

    public TableDevolucao() {
    }

    public TableDevolucao(int codigo, String nome, double preco, int qtde,String tamanho,int codVenda) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.qtde = qtde;
        this.tamanho = tamanho;
        this.codVenda=codVenda;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    
    public void pegaProduto(Produto p)
    {
        this.codigo=p.getCod();
        this.nome=p.getNome();
        this.preco=p.getPreco(); 
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }
    public boolean insere()
    {
        return true;
    }
    
    public boolean update()
    {
        return true;
    }
}
