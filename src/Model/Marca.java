/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Gabriel
 */
public class Marca
{
    private int cod;
    private String nome;

    public Marca(int cod, String nome)
    {
        this.cod = cod;
        this.nome = nome;
    }
    public Marca()
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
    
    
}
