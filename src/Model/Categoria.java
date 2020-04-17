/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.CategoriaBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Categoria
{

    private int cod;
    private String nome;

    public Categoria(int cod, String nome)
    {
        this.cod = cod;
        this.nome = nome;
    }

    public Categoria()
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

    public boolean insertCategoria()
    {
        CategoriaBD categoria = new CategoriaBD();
        return categoria.insertCategoria(this);
    }

    public boolean updateCategoria()
    {
        CategoriaBD categoria = new CategoriaBD();
        return categoria.updateCategoria(this);
    }

    public boolean deleteCategoria()
    {
        CategoriaBD categoria = new CategoriaBD();
        return categoria.deleteCategoria(this);
    }

    public Categoria selectCategoria(int codigo)
    {
        Categoria c;
        CategoriaBD categoria = new CategoriaBD();
        c = categoria.get(cod);

        return c;
    }

    public List<Categoria> selectCategoria(String filtro)
    {
        List<Categoria> aux = new ArrayList();
        CategoriaBD categoria = new CategoriaBD();
        aux = categoria.get(filtro);

        return aux;
    }
    
    @Override
    public String toString() {
        return nome;
    }

}
