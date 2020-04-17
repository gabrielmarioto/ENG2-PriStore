/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ColecaoBD;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Colecao
{

    private int cod;
    private String nome;
    private LocalDate dataInicio;

    public Colecao(int cod, String nome, LocalDate dataInicio)
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

    public LocalDate getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public boolean insertColecao()
    {
        ColecaoBD colecao = new ColecaoBD();
        return colecao.insertColecao(this);
    }

    public boolean updateColecao()
    {
        ColecaoBD colecao = new ColecaoBD();
        return colecao.updateColecao(this);
    }

    public boolean deleteColecao()
    {
        ColecaoBD colecao = new ColecaoBD();
        return colecao.deleteColecao(this);
    }

    public Colecao selectColecao(int codigo)
    {
        Colecao c;
        ColecaoBD colecao = new ColecaoBD();
        c = colecao.get(codigo);

        return c;
    }

    public List<Colecao> selectColecao(String filtro)
    {
        List<Colecao> aux = new ArrayList();
        ColecaoBD colecao = new ColecaoBD();
        aux = colecao.get(filtro);

        return aux;
    }

    @Override
    public String toString() {
        return nome;
    }
}
