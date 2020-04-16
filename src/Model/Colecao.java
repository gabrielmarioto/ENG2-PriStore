/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Gabriel
 */
public class Colecao
{
    private int cod;
    private String nome;
    private Date dataInicio;

    public Colecao(int cod, String nome, Date dataInicio)
    {
        this.cod = cod;
        this.nome = nome;
        this.dataInicio = dataInicio;
    }
    public Colecao()
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

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }
    
    
}
