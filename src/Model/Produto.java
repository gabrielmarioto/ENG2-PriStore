/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ProdutoBD;

/**
 *
 * @author Gabriel
 */
public class Produto
{
    private int cod;
    private Categoria codCategoria;
    private String nome;
    private char tamanho;
    private float preco;
    private String descricao;
    private Marca codMarca;
    private Colecao codColecao;

    public Produto(int cod, Categoria codCategoria, String nome, char tamanho, float preco, String descricao, Marca codMarca, Colecao codColecao)
    {
        this.cod = cod;
        this.codCategoria = codCategoria;
        this.nome = nome;
        this.tamanho = tamanho;
        this.preco = preco;
        this.descricao = descricao;
        this.codMarca = codMarca;
        this.codColecao = codColecao;
    }
    public Produto()
    {
        
    }
    public int getCod()
    {
        return cod;
    }

    public void setCod(int cod)
    {
        this.cod = cod;
    }

    public Categoria getCodCategoria()
    {
        return codCategoria;
    }

    public void setCodCategoria(Categoria codCategoria)
    {
        this.codCategoria = codCategoria;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public char getTamanho()
    {
        return tamanho;
    }

    public void setTamanho(char tamanho)
    {
        this.tamanho = tamanho;
    }

    public float getPreco()
    {
        return preco;
    }

    public void setPreco(float preco)
    {
        this.preco = preco;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Marca getCodMarca()
    {
        return codMarca;
    }

    public void setCodMarca(Marca codMarca)
    {
        this.codMarca = codMarca;
    }

    public Colecao getCodColecao()
    {
        return codColecao;
    }

    public void setCodColecao(Colecao codColecao)
    {
        this.codColecao = codColecao;
    }

    public void insert()
    {
        ProdutoBD prod = new ProdutoBD();        
        prod.insertProduto(this);
    }
    public void update()
    {
        ProdutoBD prod = new ProdutoBD();      
        prod.updateProduto(this);
    }
    public void delete()
    {
        ProdutoBD prod = new ProdutoBD();      
        prod.deleteProduto(this);
    }
    
}
