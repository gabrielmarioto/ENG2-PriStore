/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.FornecedorBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Fornecedor
{

    private int cod;
    private String nome, cnpj, inscrocaoEstadual, endereco, email, telefone, cidade, bairro;
    private int numRua;
    private String cep;

    public Fornecedor(int cod, String nome, String cnpj, String inscrocaoEstadual, String endereco, String email, String telefone, String cidade, int numRua, String bairro, String cep)
    {
        this.cod = cod;
        this.nome = nome;
        this.cnpj = cnpj;
        this.inscrocaoEstadual = inscrocaoEstadual;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.cidade = cidade;
        this.numRua = numRua;
        this.bairro = bairro;
        this.cep = cep;
    }

    public Fornecedor()
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

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getInscrocaoEstadual()
    {
        return inscrocaoEstadual;
    }

    public void setInscrocaoEstadual(String inscrocaoEstadual)
    {
        this.inscrocaoEstadual = inscrocaoEstadual;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public int getNumRua()
    {
        return numRua;
    }

    public void setNumRua(int numRua)
    {
        this.numRua = numRua;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public boolean insertFornecedor()
    {
        FornecedorBD fornecedor = new FornecedorBD();
        return fornecedor.insertFornecedor(this);
    }

    public boolean updateFornecedor()
    {
        FornecedorBD fornecedor = new FornecedorBD();
        return fornecedor.updateFornecedor(this);
    }

    public boolean deleteFornecedor()
    {
        FornecedorBD fornecedor = new FornecedorBD();
        return fornecedor.deleteFornecedor(this);
    }

    public Fornecedor selectFornecedor(int codigo)
    {
        Fornecedor f;
        FornecedorBD fornecedor = new FornecedorBD();
        f = fornecedor.get(codigo);

        return f;
    }
    public List<Fornecedor> selectFornecedor(String filtro)
    {
        List<Fornecedor> aux = new ArrayList();
        FornecedorBD fornecedor = new FornecedorBD();
        aux = fornecedor.get(filtro);

        return aux;
    }

    @Override
    public String toString() {
        return nome;
    }
}
