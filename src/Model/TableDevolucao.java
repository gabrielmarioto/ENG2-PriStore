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
    private Date data;
    private int tamanho;
    private int codVenda;

    public TableDevolucao() {
    }

    public TableDevolucao(int codigo, String nome, double preco, Date data,int tamanho) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.data = data;
        this.tamanho = tamanho;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    public void pegaProduto(Produto p)
    {
        this.codigo=p.getCod();
        this.nome=p.getNome();
        this.preco=p.getPreco(); 
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
