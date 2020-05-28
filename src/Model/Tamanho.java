/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.TamanhoBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Tamanho
{
    private Produto codProduto;
    private String tamanho;
    private int qtde;

    public Tamanho(Produto codProduto, String tamanho, int qtde)
    {
        this.codProduto = codProduto;
        this.tamanho = tamanho;
        this.qtde = qtde;
    }
    public Tamanho(String tamanho)
    {
        this.tamanho = tamanho;
    }

    public Tamanho()
    {
    }

    public Produto getCodProduto()
    {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto)
    {
        this.codProduto = codProduto;
    }

    public String getTamanho()
    {
        return tamanho;
    }

    public void setTamanho(String tamanho)
    {
        this.tamanho = tamanho;
    }

    public int getQtde()
    {
        return qtde;
    }

    public void setQtde(int qtde)
    {
        this.qtde = qtde;
    }
    
    public boolean insertTamanho()
    {
        TamanhoBD t = new TamanhoBD();
        return t.insertTamanho(this);
    }
    
    public boolean updateTamanho()
    {
        TamanhoBD t = new TamanhoBD();
        return t.updateTamanho(this);
    }
    
    public boolean deleteTamanho()
    {
        TamanhoBD t = new TamanhoBD();
        return t.deleteTamanho(this);
    }
    
    public Tamanho select(String tamanho, int cod)
    {
        Tamanho td;
        TamanhoBD t = new TamanhoBD();
        td = t.getTamanho(tamanho, cod);
        return td;
    }
    
    public List<Tamanho> selectTamanho(String filtro)
    {
        List<Tamanho> aux = new ArrayList();
        TamanhoBD t = new TamanhoBD();
        aux = t.get(filtro);
        return aux;
    }

    @Override
    public String toString()
    {
        return tamanho;
    }
    
    
}
