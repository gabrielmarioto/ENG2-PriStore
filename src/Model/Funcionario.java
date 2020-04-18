/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.FuncionarioBD;
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

    public Funcionario() {
    }

    public Funcionario(int codigo, String nome, String cpf, char sexo, double salario, String telefone, String email, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.salario = salario;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
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
    
     public boolean insert()
    {
        FuncionarioBD func = new FuncionarioBD();        
        return func.insertFuncionario(this);
    }
    public boolean update()
    {
        FuncionarioBD func = new FuncionarioBD();      
        return func.updateFuncionario(this);
    }
    public boolean delete()
    {
        FuncionarioBD func = new FuncionarioBD();      
        return func.deleteFuncionario(this);
    }
    public Funcionario selectProduto(int cod)
    {
        Funcionario f;
        FuncionarioBD func = new FuncionarioBD();      
        f = func.get(cod);
        return f;
    }
    public List<Funcionario> selectProduto(String filtro)
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
