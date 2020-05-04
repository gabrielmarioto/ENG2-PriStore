/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.FuncionarioBD;
import Util.Banco;
import java.sql.SQLException;
 import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class Funcionario {
    private int codigo;
    private String nome;
    private String cpf;
    private char sexo;
    private double salario;
    private String telefone;
    private String email;
    private String endereco;
    private String bairro;
    private String cidade;
    private String cep;

    public Funcionario() {
    }

    public Funcionario(int codigo, String nome, String cpf, char sexo, double salario, String telefone, String email, String endereco,String bairro, String cidade,String cep) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.salario = salario;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
    
     public boolean insert()
    {
        codigo= Banco.getCon().getMaxPK("Funcionario", "fun_cod")+1;
        if(codigo==0)
            codigo=1;
        FuncionarioBD func = new FuncionarioBD();  
        return func.insertFuncionario(this);
    }
    public boolean update()
    {
        FuncionarioBD func = new FuncionarioBD();      
        return func.updateFuncionario(this);
    }
    public boolean delete() throws SQLException
    {
        FuncionarioBD func = new FuncionarioBD();      
        return func.deleteFuncionario(this);
    }
    public Funcionario selectFuncionario(int cod)
    {
        Funcionario f;
        FuncionarioBD func = new FuncionarioBD();      
        f = func.get(cod);
        return f;
    }
    public List<Funcionario> selectFuncionario(String filtro)
    {
        List<Funcionario> aux = new ArrayList();
        FuncionarioBD func = new FuncionarioBD();      
        aux = func.get(filtro);
        
        return aux;
    }
    @Override
    public String toString() {
        return nome;
    }
}
