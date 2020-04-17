/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.MarcaBD;
import java.util.ArrayList;
import java.util.List;

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

    public boolean insertMarca()
    {
        MarcaBD marca = new MarcaBD();
        return marca.insertMarca(this);
    }

    public boolean updateMarca()
    {
        MarcaBD marca = new MarcaBD();
        return marca.updateMarca(this);
    }

    public boolean deleteMarca()
    {
        MarcaBD marca = new MarcaBD();
        return marca.deleteMarca(this);
    }

    public Marca selectMarca(int codigo)
    {
        Marca m;
        MarcaBD marca = new MarcaBD();
        m = marca.get(cod);

        return m;
    }

    public List<Marca> selectMarca(String filtro)
    {
        List<Marca> aux = new ArrayList();
        MarcaBD marca = new MarcaBD();
        aux = marca.get(filtro);

        return aux;
    }

    @Override
    public String toString() {
        return nome;
    }
}
